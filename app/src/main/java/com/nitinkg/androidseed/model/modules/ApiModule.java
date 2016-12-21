package com.nitinkg.androidseed.model.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nitinkg.androidseed.model.apis.RetroFitRestService;
import com.nitinkg.androidseed.model.utils.AuthInterceptor;
import com.nitinkg.androidseed.model.utils.ObservableCache;

import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    public static final String baseUrl = "http://services.groupkt.com/state/search/";

    private Retrofit configureRetrofit(String url,
                                       OkHttpClient okHttpClient,
                                       Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.disableHtmlEscaping();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        builder.setDateFormat(format.toLocalizedPattern());
        return builder.create();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(AuthInterceptor authInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.addNetworkInterceptor(authInterceptor);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        return builder.build();
    }

    @Provides
    @Singleton
    public RetroFitRestService provideRetroFitRestService(OkHttpClient okHttpClient,
                                                          Gson gson) {
        Retrofit retrofit = configureRetrofit(baseUrl,
                okHttpClient,
                gson);
        return retrofit.create(RetroFitRestService.class);
    }

    @Provides
    @Singleton
    public ObservableCache provideObservableCache(){
        return new ObservableCache();
    }


}
