package apk.karmak.retrofitapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyAPI {

//    @Headers({"email: its@worked.why"})
//    @POST("sendCode")
//    Call<DataModel> getMes(@Body DataModel dataModel);

    @POST("sendCode")
    Call<DataModel> getMess(@Header("email") String email);

    @POST("signin")
    Call<DataModel> getCode(@Header("email") String email,
                            @Header("code") String code);



}
