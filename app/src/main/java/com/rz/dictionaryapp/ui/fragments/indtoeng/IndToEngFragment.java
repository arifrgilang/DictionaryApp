package com.rz.dictionaryapp.ui.fragments.indtoeng;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rz.dictionaryapp.R;
import com.rz.dictionaryapp.data.model.DictModel;
import com.rz.dictionaryapp.ui.activities.main.MainActivity;
import com.rz.dictionaryapp.util.RvAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IndToEngFragment extends Fragment implements IndToEngContract.View{

    @BindView(R.id.rv_i_to_e) RecyclerView mRv;
    @BindView(R.id.loading_i_to_e) ProgressBar mLoading;

    private IndToEngContract.Presenter mPresenter;
    private RvAdapter mAdapter;

    public IndToEngFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ind_to_eng, container, false);
        ButterKnife.bind(this, view);
        init();
        setHasOptionsMenu(true);
        return view;
    }

    private void init(){
        mPresenter = new IndToEngPresenter(getActivity(), this);
        mAdapter = new RvAdapter(getActivity());

        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);

        mPresenter.getDataFromDb("");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.main, menu);

        MenuItem item = menu.findItem(R.id.menu_search);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_IF_ROOM);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d("ind to eng submit", "submitted");
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mPresenter.getDataFromDb(s);
                return true;
            }
        });

    }

    @Override
    public void showLoading(Boolean status) {
        mLoading.setVisibility(status ? View.VISIBLE: View.INVISIBLE);
    }

    @Override
    public void setView(ArrayList<DictModel> results) {
        mAdapter.setData(results);
    }
}
