package com.rz.dictionaryapp.ui.fragments.indtoeng;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.rz.dictionaryapp.data.model.DictModel;
import com.rz.dictionaryapp.data.db.DatabaseContract;
import com.rz.dictionaryapp.data.db.GetterHelper;

import java.util.ArrayList;

public class IndToEngPresenter implements IndToEngContract.Presenter{
    private GetterHelper mIndoHelper;
    private Context context;
    IndToEngContract.View view;

    IndToEngPresenter(Context ctx, IndToEngContract.View view){
        this.context = ctx;
        this.view = view;
        mIndoHelper = new GetterHelper(this.context, DatabaseContract.INDONESIAN_TABLE);
    }

    @Override
    public void getDataFromDb() {
        new LoadDicts().execute("");
    }

    @SuppressLint("StaticFieldLeak")
    private class LoadDicts extends AsyncTask<String, Void, ArrayList<DictModel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            view.showLoading(true);
            mIndoHelper.open();
        }

        @Override
        protected ArrayList<DictModel> doInBackground(String... strings) {
            if (strings[0].isEmpty()) {
                return mIndoHelper.getAllData();
            }
            return mIndoHelper.getDataByName(strings[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<DictModel> words) {
            super.onPostExecute(words);
            view.setView(words);
            mIndoHelper.close();
            view.showLoading(false);
        }
    }
}
