package apk.karmak.retrofitapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MyAPI {

//    @POST("sendCode")
//    Call<DataModel> getMessage();

    @Headers({"email: its@worked.why"})
    @POST("sendCode")
    Call<DataModel> getMes(@Body DataModel dataModel);

}
