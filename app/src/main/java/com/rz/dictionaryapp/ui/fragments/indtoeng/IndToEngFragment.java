package com.rz.dictionaryapp.ui.fragments.indtoeng;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rz.dictionaryapp.R;
import com.rz.dictionaryapp.data.model.DictModel;
import com.rz.dictionaryapp.util.RvAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IndToEngFragment extends Fragment implements IndToEngContract.View{

    @BindView(R.id.rv_i_to_e) RecyclerView mRv;

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
        return view;
    }

    private void init(){
        mPresenter = new IndToEngPresenter(getActivity(), this);
        mAdapter = new RvAdapter(getActivity());

        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);

        mPresenter.getDataFromDb();
    }

    @Override
    public void setView(ArrayList<DictModel> results) {
        mAdapter.setData(results);
    }
}
