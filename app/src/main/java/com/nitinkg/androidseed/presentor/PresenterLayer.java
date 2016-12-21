package com.nitinkg.androidseed.presentor;

import com.nitinkg.androidseed.AndroidSeedApplication;
import com.nitinkg.androidseed.datamodels.StateListResponse;
import com.nitinkg.androidseed.model.apis.RetroFitRestService;
import com.nitinkg.androidseed.model.utils.ObservableCache;
import com.nitinkg.androidseed.view.MainActivity;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

@SuppressWarnings("unchecked")
public class PresenterLayer implements PresenterInteractor {

    private MainActivity view;
    private Subscription subscription;
    //inject retrofitservice
    @Inject
    RetroFitRestService retroFitRestService;
    //inject observale cache
    @Inject
    ObservableCache observableCache;

    public PresenterLayer(MainActivity view) {
        this.view = view;
        AndroidSeedApplication.getApp().getApplicationComponents().inject(this);
    }
    // method to get the states from api and pass them to UI
    public void loadStateList(String searchText) {
        view.showProgress();
        Observable<StateListResponse> stateListResponseObservable = (Observable<StateListResponse>)
                observableCache.getPreparedObservable(retroFitRestService.getStateList(searchText), StateListResponse.class, true, false);
        subscription = stateListResponseObservable.subscribe(new Observer<StateListResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                //handle error
                view.showFailure(e);
            }

            @Override
            public void onNext(StateListResponse response) {
                //set the states on list
                view.showStateResut(response);
            }
        });
    }

   /* //meethod to get the location from ip address and pass the result to UI
    public void loadLocationFromIp(String ipAddress) {
        view.showProgress();
        Observable<LocationModel> locationModelObservable = (Observable<LocationModel>)
                observableCache.getPreparedObservable(retroFitRestService.getLocationFromIP(ipAddress), LocationModel.class, true, false);
        subscription = locationModelObservable.subscribe(new Observer<LocationModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                //handle error
                view.showFailure(e);
            }

            @Override
            public void onNext(LocationModel response) {
                //set the location on map
                view.showLocationResult(response);
            }
        });
    }*/

    //unsubscribe the observable
    public void rxUnSubscribe() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }


}
