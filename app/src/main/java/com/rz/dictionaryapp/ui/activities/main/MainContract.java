package com.rz.dictionaryapp.ui.activities.main;

import android.support.v4.app.Fragment;

public interface MainContract {
    interface View{
        void setFragment(Fragment fragment);
        void setRvData();
    }

    interface Presenter{
        void changeFragment(String fragmentName);
    }
}
