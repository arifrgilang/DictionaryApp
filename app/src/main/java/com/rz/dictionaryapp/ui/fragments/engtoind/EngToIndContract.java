package com.rz.dictionaryapp.ui.fragments.engtoind;

import com.rz.dictionaryapp.data.model.DictModel;

import java.util.ArrayList;

public interface EngToIndContract {
    interface View{
        void setView(ArrayList<DictModel> results);
    }
    interface Presenter{
        void getDataFromDb();
    }
}
