package com.nitinkg.androidseed.model.components;


import com.nitinkg.androidseed.model.modules.ApiModule;
import com.nitinkg.androidseed.model.modules.ApplicationModule;
import com.nitinkg.androidseed.presentor.PresenterLayer;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface ApplicationComponent {
    //Inject class which want the providers to be injected
    void inject(PresenterLayer presenterLayer);

}
