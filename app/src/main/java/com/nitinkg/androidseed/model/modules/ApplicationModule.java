package com.nitinkg.androidseed.model.modules;

import android.content.Context;

import com.nitinkg.androidseed.AndroidSeedApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final AndroidSeedApplication app;

    public ApplicationModule(AndroidSeedApplication app) {
        this.app = app;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return app;
    }

}
