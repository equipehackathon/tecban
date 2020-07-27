package com.example.tecban.interface_retrofit;

import com.example.tecban.model.ConsumoSaldo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface RetrofitSaldos {

    /*@Headers({
            "AccountId: 5f1866367af45c38b665fab2",
            "Amount: ",
            "Currency: BRL",
            "CreditDebitIndicator: Credit"
    })*/

    @Headers({
            "Content-Type: application/json",
            "x-fapi-financial-id: c3c937c4-ab71-427f-9b59-4099b7c680ab",
            "x-fapi-interaction-id: eeecf1a3a-52f9-4701-87cc-4e04b7e0470f",
            "Authorization: Bearer 7cce48d6-2702-44fa-8d76-99e0ffed17d7"
    })


    @GET("/saldo/{saldo}")
    Call<ConsumoSaldo> getSaldo(@Field("Content-Type") String contentType,
                                @Field("x-fapi-financial-id") String financialId,
                                @Field("x-fapi-interaction-id") String interactionId,
                                @Field("Authorization") String authorization,
                                @Field("AccountId") String accountId,
                                @Field("Amount") Double amount,
                                @Field("Currency") String currency,
                                @Field("CreditDebitIndicator") String creditDebit);

    @GET("open-banking/v3.1/aisp/balances")
    Call<ConsumoSaldo> getSaldo();

}
