package com.nitinkg.androidseed.model.utils;
import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
@Singleton
public class AuthInterceptor implements Interceptor {


    @Inject
    public AuthInterceptor() {

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        //this is where we will add whatever we want to our request headers.
        builder.addHeader("Accept", "application/json");
        return chain.proceed(builder.build());
    }
}
