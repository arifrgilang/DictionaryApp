package com.rz.dictionaryapp.ui.activities.load;

public interface LoadContract {
    interface View{
        void setProgress(Integer i);
        void onFinishLoading();
    }

    interface Presenter{
        void loadData();
    }
}
