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
import com.example.nicklastest.models.Product.StaticProductResponse;
import com.example.nicklastest.services.OpenFoodService;
import com.example.nicklastest.services.ProductService;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import static android.Manifest.permission.CAMERA;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
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

    private void doesProductExist(String barCode) {

        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/api/Product/")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("https://world.openfoodfacts.org/api/v0/product/")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductService productService = retrofit1.create(ProductService.class);
        OpenFoodService openFoodService = retrofit2.create(OpenFoodService.class);



        Disposable disposable = productService.GetById(barCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<StaticProductResponse>() {
                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull StaticProductResponse staticProductResponse) {
                        // TODO: Apply the fetched data in a new UI
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        if (e instanceof HttpException && ((HttpException) e).code() == 404) {
                            Disposable disposable = openFoodService.GetByBarcode(barCode)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeWith(new DisposableObserver<DirectOpenProductResponse>() {
                                        @Override
                                        public void onNext(@io.reactivex.rxjava3.annotations.NonNull DirectOpenProductResponse productResponse) {
                                            // TODO: If it does exist create a new instance of the fetched data in own database
                                            Disposable disposable = productService.Create(null)
                                                    .subscribeOn(Schedulers.io())
                                                    .observeOn(AndroidSchedulers.mainThread())
                                                    .subscribeWith(new DisposableObserver<StaticProductResponse>() {
                                                        @Override
                                                        public void onNext(@io.reactivex.rxjava3.annotations.NonNull StaticProductResponse staticProductResponse) {

                                                        }

                                                        @Override
                                                        public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                                                        }

                                                        @Override
                                                        public void onComplete() {
                                                            this.dispose();
                                                        }
                                                    });
                                        }

                                        @Override
                                        public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                            // TODO: If it doesn't exist do something
                                        }

                                        @Override
                                        public void onComplete() {

                                        }
                                    });
                        } else {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
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
}