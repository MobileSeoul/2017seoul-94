package com.jmw.src.abs;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;



/**
 * Created by junminwoo on 2016-10-25.
 */
public abstract class abs_binooFragment extends Fragment {

    @Nullable
    @Override
    public View getView() {
        return super.getView();
    }

    protected View preonCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState, ListView listView, Context context){

        return onCreateView(inflater, container, savedInstanceState);
    }
    @Nullable
    @Override
    public abstract View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }
}
