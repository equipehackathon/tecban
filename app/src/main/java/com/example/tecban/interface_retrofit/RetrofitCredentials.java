package com.example.tecban.interface_retrofit;

import com.example.tecban.model.CredentialsResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitCredentials {

    String BASIC_TOKEN = "MTE5Yjg5OWQtMDc5MS00MTFhLTk3YTEtNTBmMDJlNzVlODg2OjdiYzRiMTBiLTg2ZjYtNGE3Mi05Mjc1LWE0YmFiMjdkOThiNA==";

    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "Authorization: Basic " + BASIC_TOKEN
    })

    @FormUrlEncoded
    @POST("token")
    Call<CredentialsResponse> getCredentials(@Field("grant_type") String grantType,
                                             @Field("scope") String scope);

}
