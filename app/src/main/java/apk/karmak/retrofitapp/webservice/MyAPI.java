package apk.karmak.retrofitapp.webservice;

import apk.karmak.retrofitapp.auth.modal.DataModel;
import apk.karmak.retrofitapp.patient.PatientModal;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface MyAPI {

    @POST("sendCode")
    Call<DataModel> getMess(@Header("email") String email);

    @POST("signin")
    Call<DataModel> getCode(@Header("email") String email,
                            @Header("code") String code);
    @POST("createProfile")
    Call<PatientModal> create(@Header("Authorization") String token,
                              @Body PatientModal body);



}
