package com.rz.dictionaryapp.ui.fragments.indtoeng;

import com.rz.dictionaryapp.data.model.DictModel;

import java.util.ArrayList;

public interface IndToEngContract {
    interface View{
        void showLoading(Boolean status);
        void setView(ArrayList<DictModel> results);
    }
    interface Presenter{
        void getDataFromDb(String query);
    }
}
