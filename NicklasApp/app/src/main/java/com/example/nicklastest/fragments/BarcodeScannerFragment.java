package com.example.nicklastest.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicklastest.MainActivity;
import com.example.nicklastest.R;
import com.example.nicklastest.models.OpenFoodData.DirectOpenProductResponse;
import com.example.nicklastest.models.OpenFoodData.Nutriments;
import com.example.nicklastest.models.OpenFoodData.Product;
import com.example.nicklastest.models.Product.ProductRequest;
import com.example.nicklastest.models.Product.StaticProductResponse;
import com.example.nicklastest.services.OpenFoodService;
import com.example.nicklastest.services.ProductService;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.Manifest.permission.CAMERA;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BarcodeScannerFragment extends Fragment {

    private SurfaceView surfaceView;
    private EditText editText;
    private TextView textView;

    private final String BASE_URL = "http://10.0.2.2:5000/api/Product/";
    private final String FOOD_URL = "https://world.openfoodfacts.org/api/v0/product/";

    private static final int CAMERA_REQUEST_CODE = 100;

    private String[] neededPermissions = new String[]{CAMERA};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_barcode_scanner, container, false);
        surfaceView = view.findViewById(R.id.surface_view);
        textView = view.findViewById(R.id.text_barcode);


        // Check camera permission
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[] { Manifest.permission.CAMERA }, CAMERA_REQUEST_CODE);
        } else {
            startCameraPreview(surfaceView, textView);
        }
        getProduct("5701101210033");

        return view;
    }

    private void startCameraPreview(SurfaceView surfaceView, TextView textView) {
        // Set up barcode detector and processor
        BarcodeDetector detector = new BarcodeDetector.Builder(getContext())
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();
        detector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() { }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                // Process the barcode results
                SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() > 0) {
                    String barcodeResult = barcodes.valueAt(0).displayValue;
                    textView.post(() -> textView.setText(barcodeResult));
                    getProduct(barcodeResult);
                }
            }
        });

        // Initialize the camera source and start the preview
        CameraSource cameraSource = new CameraSource.Builder(getContext(), detector)
                .setRequestedPreviewSize(640, 480)
                .setAutoFocusEnabled(true)
                .build();
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    try {
                        cameraSource.start(holder);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });
    }

    private void getProduct(String barCode) {
        Retrofit retrofit = createRetrofit();

        ProductService productService = retrofit.create(ProductService.class);

        Log.d("GetById", "Product is being fetched");

        productService.GetById(barCode)
                .subscribeOn(Schedulers.io()) // run on IO thread
                .observeOn(AndroidSchedulers.mainThread()) // observe on Android main thread
                .flatMap(product -> {
                    Log.d("getProduct", "Product was found! : " + product.getProductName());
                    return Single.just(product);
                })
                .onErrorResumeNext(error -> {
                    if(error instanceof HttpException && ((HttpException) error).code() == 404) {
                        Log.e("getProduct", "Product was not found!");
                        return handleProductNotFoundError(productService, barCode);
                    } else {
                        error.printStackTrace();
                        return Single.error(error);
                    }
                })
                .flatMap(product -> {
                    Log.d("getProduct", "Product has been created!");
                    Log.d("getProduct", "Returning created object!");
                    return Single.just(product);
                })
                .subscribe();
    }

    private Single<StaticProductResponse> handleProductNotFoundError(ProductService productService, String barCode) {
        return productService.GetOpenFood(FOOD_URL +  barCode)
                .subscribeOn(Schedulers.io()) // run on IO thread
                .observeOn(AndroidSchedulers.mainThread()) // observe on Android main thread
                .doOnError(error -> {
                    Log.e("handleProductNotFound", "Error fetching product: " + error.getMessage());
                })
                .flatMap(response -> {
                    if(response.getStatus() == 0) {
                        Log.e("handleProductNotFoundError", "No product was found using barcode: " + barCode);
                        return Single.error(new Exception());
                    } else {
                        Log.d("handleProductNotFoundError", "Product was found using OpenFoodFacts");
                        return createProduct(productService, response);
                    }
                })
                .onErrorResumeNext(error -> {
                    Log.e("handleProductNotFound", "Error fetching product: " + error.getMessage());
                    error.printStackTrace();
                    return Single.error(error);
                });
    }

    private Single<StaticProductResponse> createProduct(ProductService productService, DirectOpenProductResponse product) {
        Product productInstance = product.getProduct();
        Nutriments nutrimentsInstance = productInstance.getNutriments();

        Log.d("product", String.valueOf(nutrimentsInstance.getCarbohydrates100g()));
        Log.d("product", String.valueOf(nutrimentsInstance.getEnergyKcal100g()));
        Log.d("product", String.valueOf(nutrimentsInstance.getEnergy100g()));
        Log.d("product", String.valueOf(nutrimentsInstance.getFat100g()));
        Log.d("product", String.valueOf(nutrimentsInstance.getFiber100g()));
        Log.d("product", String.valueOf(nutrimentsInstance.getProteins100g()));
        Log.d("product", String.valueOf(nutrimentsInstance.getSaturatedFat100g()));
        Log.d("product", String.valueOf(nutrimentsInstance.getSugars100g()));
        Log.d("product", String.valueOf(nutrimentsInstance.getSalt100g()));

        ProductRequest request = new ProductRequest(
                product.getCode(),
                productInstance.getGenericName(),
                productInstance.getBrandOwner(),
                nutrimentsInstance.getEnergyKcal100g(),
                nutrimentsInstance.getFat100g(),
                nutrimentsInstance.getSaturatedFat100g(),
                nutrimentsInstance.getCarbohydrates100g(),
                nutrimentsInstance.getSugars100g(),
                nutrimentsInstance.getFiber100g(),
                nutrimentsInstance.getProteins100g(),
                nutrimentsInstance.getSalt100g()
        );
        Log.d("createProduct", "Product is being created");
        return productService.Create(BASE_URL, request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(error -> {
                    Log.e("createProduct", "Error creating new product: " + error.getMessage());
                    error.printStackTrace();
                    return Single.error(error);
                });
    }




    private Retrofit createRetrofit() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Nutriments.class, new NutrimentsDeserializer())
                .create();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, start the camera preview
                Toast.makeText(getContext(), "Camera permission granted", Toast.LENGTH_SHORT).show();
                startCameraPreview(surfaceView, textView);
            } else {
                // Permission denied, show an error message
                Toast.makeText(getContext(), "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static class NutrimentsDeserializer implements JsonDeserializer<Nutriments> {
        @Override
        public Nutriments deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            Nutriments nutriments = new Nutriments();

            nutriments.setCarbohydrates100g(jsonObject.get("carbohydrates_100g").getAsDouble());
            nutriments.setEnergy100g(jsonObject.get("energy_100g").getAsDouble());
            nutriments.setFat100g(jsonObject.get("fat_100g").getAsDouble());
            nutriments.setProteins100g(jsonObject.get("proteins_100g").getAsDouble());
            nutriments.setSaturatedFat100g(jsonObject.get("saturated-fat_100g").getAsDouble());
            nutriments.setSalt100g(jsonObject.get("salt_100g").getAsDouble());
            if(!jsonObject.has("energy-kcal_100g")) {
                if(jsonObject.has("energy-kj_100g")) {
                    nutriments.setEnergyKcal100g(jsonObject.get("energy-kj_100g").getAsDouble() * 0.239);
                } else {
                    nutriments.setEnergyKcal100g(0.0);

                }
            } else {
                nutriments.setEnergyKcal100g(jsonObject.get("energy-kcal_100g").getAsDouble());
            }

            if(!jsonObject.has("sugars_100g")) {
                nutriments.setSugars100g(0.0);
            } else {
                nutriments.setSugars100g(jsonObject.get("sugars_100g").getAsDouble());
            }

            if(!jsonObject.has("fiber_100g")) {
                nutriments.setFiber100g(0.0);
            } else {
                nutriments.setFiber100g(jsonObject.get("fiber_100g").getAsDouble());
            }
            return nutriments;
        }
    }
}