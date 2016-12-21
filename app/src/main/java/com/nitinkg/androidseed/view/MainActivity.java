package com.nitinkg.androidseed.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.nitinkg.androidseed.R;
import com.nitinkg.androidseed.datamodels.StateListResponse;
import com.nitinkg.androidseed.presentor.PresenterInteractor;
import com.nitinkg.androidseed.presentor.PresenterLayer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    private static final String EXTRA_RX = "CALLINWORK";
    private static final String EXTRA_SEARCH_TEXT = "SEARCHTEXT";
    private boolean rxCallInWorks = false;
    private String searchText = "";
    private PresenterInteractor presenter;

    //Bind View using butterKnife
    @BindView(R.id.textSearch)
    EditText textSearchEditText;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.searchStateButton)
    Button searchStateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new PresenterLayer(this);
        if (savedInstanceState != null) {
            rxCallInWorks = savedInstanceState.getBoolean(EXTRA_RX);
            searchText = savedInstanceState.getString(EXTRA_SEARCH_TEXT);
        }
    }

    //Click listener for state search button binded using ButterKnife
    @OnClick(R.id.searchStateButton)
    public void searchState() {
        searchStateButton.setEnabled(false);
        if (textSearchEditText.getText() != null && !"".equals(textSearchEditText.getText().toString())) {
            rxCallInWorks = true;
            searchText = textSearchEditText.getText().toString();
            //send the request to presenter for data
            presenter.loadStateList(searchText);
        } else {
            searchStateButton.setEnabled(true);
            Snackbar.make(this.findViewById(android.R.id.content), "Please enter some text to search state", Snackbar.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        presenter.rxUnSubscribe();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //save state for activity recreation
        outState.putBoolean(EXTRA_RX, rxCallInWorks);
        outState.putString(EXTRA_SEARCH_TEXT, searchText);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //resume request if activity recreated due to any reason
        if (rxCallInWorks && searchText != null && !"".equals(searchText))
            presenter.loadStateList(searchText);

    }

    //method to show states on list after response is received from presenter
    public void showStateResut(StateListResponse response) {
        progressBar.setVisibility(View.GONE);
        searchStateButton.setEnabled(true);
        if (response != null && response.getRestResponse() != null && response.getRestResponse().getResult() != null && !response.getRestResponse().getResult().isEmpty()) {
            Intent intent = new Intent(MainActivity.this, StateListActivity.class);
            intent.putParcelableArrayListExtra("statelist", response.getRestResponse().getResult());
            startActivity(intent);
        } else {
            Snackbar.make(this.findViewById(android.R.id.content), "No state found!", Snackbar.LENGTH_SHORT).show();
        }
        rxCallInWorks = false;
    }

    //hanlde request failure
    public void showFailure(Throwable throwable) {
        progressBar.setVisibility(View.GONE);
        searchStateButton.setEnabled(true);
        rxCallInWorks = false;
    }

    //show progress
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }


}
