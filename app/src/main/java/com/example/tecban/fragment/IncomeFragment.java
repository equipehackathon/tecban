package com.example.tecban.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tecban.R;
import com.example.tecban.adapter.CategoryAdapter;
import com.example.tecban.interface_retrofit.RetrofitCredentials;
import com.example.tecban.interface_retrofit.RetrofitSaldos;
import com.example.tecban.model.Category;
import com.example.tecban.model.ConsumoSaldo;
import com.example.tecban.model.CredentialsResponse;
import com.example.tecban.service.ServiceGenerator;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IncomeFragment extends Fragment {

    private ListView lstItems;
    private CategoryAdapter categoryAdapter;
    private ArrayList<Category> categoryList;
    private ConsumoSaldo consumoSaldo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_income, container, false);

        categoryList = new ArrayList<>();
        /*Category category = new Category();
        category.setName("Teste 1");
        category.setValue("R$ 0");
        categoryList.add(category);*/

        lstItems = rootView.findViewById(R.id.lst_items);

        categoryAdapter = new CategoryAdapter(categoryList, getContext());
        lstItems.setAdapter(categoryAdapter);

        retrofitConverter();

        return rootView;
    }

    public void retrofitConverter() {
        RetrofitSaldos service = ServiceGenerator.createService(RetrofitSaldos.class, getContext(), true);

        Call<ConsumoSaldo> call = service.getSaldo();

        call.enqueue(new Callback<ConsumoSaldo>() {
            @Override
            public void onResponse(Call<ConsumoSaldo> call, Response<ConsumoSaldo> response) {

                if (response.isSuccessful()) {

                    ConsumoSaldo responseServer = response.body();

                    //verifica aqui se o corpo da resposta não é nulo
                    if (responseServer != null) {

                        consumoSaldo.setAmount(responseServer.getAmount());
                        /*consumoSaldo.setTokenType(responseServer.getTokenType());
                        consumoSaldo.setExpiresIn(responseServer.getExpiresIn());*/

                        /*Toast.makeText(getApplicationContext(), "access_token: " + credentialsResponse.getAccessToken() + "\n"
                                                                + "token_type: " + credentialsResponse.getTokenType() + "\n"
                                                                + "expires_in: " + credentialsResponse.getExpiresIn(), Toast.LENGTH_SHORT).show();*/

                    }

                } else {

                    Toast.makeText(getContext(),"Resposta não foi sucesso", Toast.LENGTH_SHORT).show();
                    // segura os erros de requisição
                    ResponseBody errorBody = response.errorBody();
                }

            }

            @Override
            public void onFailure(Call<ConsumoSaldo> call, Throwable t) {
                Toast.makeText(getContext(),"Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
