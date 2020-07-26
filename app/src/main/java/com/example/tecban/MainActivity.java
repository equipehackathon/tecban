package com.example.tecban;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.tecban.fragment.ExtractFragment;
import com.example.tecban.fragment.PlanningFragment;
import com.example.tecban.fragment.ReportFragment;
import com.example.tecban.interface_retrofit.RetrofitCredentials;
import com.example.tecban.model.CredentialsResponse;
import com.example.tecban.service.ServiceGenerator;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private CredentialsResponse credentialsResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        credentialsResponse = new CredentialsResponse();
        retrofitConverter();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.nav_extract:
                    selectedFragment = new ExtractFragment();
                    break;
                case R.id.nav_report:
                    selectedFragment = new ReportFragment();
                    break;
                case R.id.nav_planning:
                    selectedFragment = new PlanningFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        });

        bottomNavigationView.setSelectedItemId(R.id.nav_extract);
    }

    public void retrofitConverter() {
        RetrofitCredentials service = ServiceGenerator.createService(RetrofitCredentials.class, getApplicationContext());

        Call<CredentialsResponse> call = service.getCredentials("client_credentials", "accounts openid");

        call.enqueue(new Callback<CredentialsResponse>() {
            @Override
            public void onResponse(Call<CredentialsResponse> call, Response<CredentialsResponse> response) {

                if (response.isSuccessful()) {

                    CredentialsResponse responseServer = response.body();

                    //verifica aqui se o corpo da resposta não é nulo
                    if (responseServer != null) {

                        credentialsResponse.setAccessToken(responseServer.getAccessToken());
                        credentialsResponse.setTokenType(responseServer.getTokenType());
                        credentialsResponse.setExpiresIn(responseServer.getExpiresIn());

                        /*Toast.makeText(getApplicationContext(), "access_token: " + credentialsResponse.getAccessToken() + "\n"
                                                                + "token_type: " + credentialsResponse.getTokenType() + "\n"
                                                                + "expires_in: " + credentialsResponse.getExpiresIn(), Toast.LENGTH_SHORT).show();*/

                    }

                } else {

                    Toast.makeText(getApplicationContext(),"Resposta não foi sucesso", Toast.LENGTH_SHORT).show();
                    // segura os erros de requisição
                    ResponseBody errorBody = response.errorBody();
                }

            }

            @Override
            public void onFailure(Call<CredentialsResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }
}