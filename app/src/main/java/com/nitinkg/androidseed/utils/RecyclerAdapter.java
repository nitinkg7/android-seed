package com.nitinkg.androidseed.utils;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nitinkg.androidseed.R;
import com.nitinkg.androidseed.datamodels.StateResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> itemList;
    private Context mContext;

    public RecyclerAdapter(Context context, List<?> states) {
        this.itemList = (List<Object>) states;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder vh = null;
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.state_item_layout, viewGroup, false);
        vh = new StateViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vh, final int i) {

        final StateViewHolder stateHolder = (StateViewHolder) vh;
        StateResult stateItem = (StateResult) itemList.get(i);
        if (stateItem != null) {
            if (stateItem.getName() != null)
                stateHolder.stateName.setText(stateItem.getName());
            if (stateItem.getCapital() != null)
                stateHolder.capitalName.setText(stateItem.getCapital());
        }

    }

    @Override
    public int getItemCount() {
        if (itemList != null && !itemList.isEmpty())
            return itemList.size();
        else
            return 0;
    }

    public class StateViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.stateName)
        TextView stateName;
        @BindView(R.id.capitalName)
        TextView capitalName;

        public StateViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
