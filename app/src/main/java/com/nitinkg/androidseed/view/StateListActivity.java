package com.nitinkg.androidseed.view;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.nitinkg.androidseed.R;
import com.nitinkg.androidseed.datamodels.StateResult;
import com.nitinkg.androidseed.utils.RecyclerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StateListActivity extends AppCompatActivity {
    @BindView(R.id.stateRecyclerView)
    RecyclerView stateRecyclerView;
    private RecyclerAdapter adapter;
    private int pastVisiblesItems;
    private int visibleItemCount;
    private int totalItemCount;
    private boolean loading = true;
    private int offSet = 0;
    private int limit = 10;
    LinearLayoutManager mLayoutManager;
    ArrayList<StateResult> stateList;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolBarTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        toolBarTitle.setText("State List");
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        stateRecyclerView.setLayoutManager(mLayoutManager);
        stateRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = mLayoutManager.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();
                if (loading)
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        loading = false;
                        loadMoreStates();
                    }
            }

        });
        if (getIntent() != null && getIntent().getParcelableArrayListExtra("statelist") != null) {
            stateList = getIntent().getParcelableArrayListExtra("statelist");
            setRecyclerView();
        }
    }

    private void loadMoreStates() {

    }

    private void setRecyclerView(){
        adapter = new RecyclerAdapter(this, stateList);
        stateRecyclerView.setAdapter(adapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

    }
}
