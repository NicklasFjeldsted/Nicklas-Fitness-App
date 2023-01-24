package interfaces;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import models.Product.ProductRequest;
import models.Product.StaticProductResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface ProductService {
    @GET()
    Observable<List<StaticProductResponse>> GetAll();

    @GET("{barCode}")
    Observable<StaticProductResponse> GetById(@Path("barCode") String barCode);

    @POST()
    Observable<StaticProductResponse> Create(@Body ProductRequest request);

    @PUT("{barCode}")
    Observable<StaticProductResponse> Update(@Path("barCode") String barCode, @Body ProductRequest request);

    @DELETE("{barCode}")
    Observable<StaticProductResponse> Delete(@Path("barCode") String barCode);
}

