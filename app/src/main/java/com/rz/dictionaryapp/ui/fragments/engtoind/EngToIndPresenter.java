package com.rz.dictionaryapp.ui.fragments.engtoind;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.rz.dictionaryapp.data.model.DictModel;
import com.rz.dictionaryapp.data.db.DatabaseContract;
import com.rz.dictionaryapp.data.db.GetterHelper;

import java.util.ArrayList;

public class EngToIndPresenter implements EngToIndContract.Presenter{
    private GetterHelper mEngHelper;
    private Context context;
    EngToIndContract.View view;

    EngToIndPresenter(Context ctx, EngToIndContract.View view){
        this.context = ctx;
        this.view = view;
        mEngHelper = new GetterHelper(this.context, DatabaseContract.ENGLISH_TABLE);
    }

    @Override
    public void getDataFromDb(String query) {
        new EngToIndPresenter.LoadDicts().execute(query);
    }

    @SuppressLint("StaticFieldLeak")
    private class LoadDicts extends AsyncTask<String, Void, ArrayList<DictModel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            view.showLoading(true);
            mEngHelper.open();
        }

        @Override
        protected ArrayList<DictModel> doInBackground(String... strings) {
            if (strings[0].isEmpty()) {
                return mEngHelper.getAllData();
            }
            return mEngHelper.getDataByName(strings[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<DictModel> words) {
            super.onPostExecute(words);
            view.setView(words);
            mEngHelper.close();
            view.showLoading(false);
        }
    }
}
