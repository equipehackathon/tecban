package com.example.tecban.service;

import android.content.Context;

import com.example.tecban.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    //URL base do endpoint. Deve sempre terminar com /
    public static final String API_BASE_URL = "https://as1.tecban-sandbox.o3bank.co.uk/";
    public static final String API_BASE_URL_RS = "https://rs1.tecban-sandbox.o3bank.co.uk/";
    private static final String PASSWORD = "123456";

    private static OkHttpClient generateSecureOkHttpClient(Context context) {
        try {
            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS);

            InputStream caFileInputStream = context.getResources().openRawResource(R.raw.client_certificate);
            SSLContext sslContext = SSLContext.getInstance("TLS");

            // We're going to put our certificates in a Keystore
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(caFileInputStream, PASSWORD.toCharArray());

            // Create a KeyManagerFactory with our specific algorithm our our public keys
            // Most of the cases is gonna be "X509"
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("X509");
            keyManagerFactory.init(keyStore, PASSWORD.toCharArray());

            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(keyStore);

            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {}

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {}

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            sslContext.init(keyManagerFactory.getKeyManagers(), trustAllCerts, new SecureRandom());

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            return httpClientBuilder
                    .sslSocketFactory(sslContext.getSocketFactory(), systemDefaultTrustManager())
                    .hostnameVerifier((hostname, session) -> true)
                    .addInterceptor(loggingInterceptor)
                    .build();

        } catch (NoSuchAlgorithmException | CertificateException | KeyStoreException | KeyManagementException | IOException | UnrecoverableKeyException e) {
            e.printStackTrace();
        }

        return null;

    }

    private static X509TrustManager systemDefaultTrustManager() {

        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
            }
            return (X509TrustManager) trustManagers[0];
        } catch (GeneralSecurityException e) {
            throw new AssertionError(); // The system has no TLS. Just give up.
        }

    }

    public static <S> S createService(Class<S> serviceClass, Context context) {

        //Instancia do interceptador das requisições
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS);

        httpClient.addInterceptor(loggingInterceptor);
        //Instância do retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(generateSecureOkHttpClient(context))
                .build();

        return retrofit.create(serviceClass);
    }

    public static <S> S createService(Class<S> serviceClass, Context context, boolean rs) {

        //Instancia do interceptador das requisições
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        String url = "";

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS);

        if (!rs) {
            url = API_BASE_URL;
        } else {
            url = API_BASE_URL_RS;
        }

        httpClient.addInterceptor(loggingInterceptor);
        //Instância do retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(generateSecureOkHttpClient(context))
                .build();

        return retrofit.create(serviceClass);
    }

}
