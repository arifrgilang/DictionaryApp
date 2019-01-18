package com.rz.dictionaryapp.ui.activities.load;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import com.rz.dictionaryapp.R;
import com.rz.dictionaryapp.data.model.DictModel;
import com.rz.dictionaryapp.data.db.DatabaseContract;
import com.rz.dictionaryapp.data.db.GetterHelper;
import com.rz.dictionaryapp.ui.activities.main.MainActivity;
import com.rz.dictionaryapp.util.MySharedPreferences;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LoadPresenter implements LoadContract.Presenter {

    private Context ctx;
    LoadContract.View view;

    LoadPresenter(Context ctx, LoadContract.View view){
        this.ctx = ctx;
        this.view = view;
    }
    @Override
    public void loadData() {
        new LoadData().execute();
    }

    private class LoadData extends AsyncTask<Void, Integer, Void> {

        final String TAG = LoadData.class.getSimpleName();
        MySharedPreferences appPreference;
        GetterHelper englishHelper;
        GetterHelper indonesiaHelper;
        double progress;
        double maxprogress = 100;

        @Override
        protected void onPreExecute() {
            appPreference = new MySharedPreferences(ctx);
            englishHelper = new GetterHelper(ctx, DatabaseContract.ENGLISH_TABLE);
            indonesiaHelper = new GetterHelper(ctx, DatabaseContract.INDONESIAN_TABLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Boolean isFirstRun = appPreference.getFirstRun();

            if (isFirstRun) {
                ArrayList<DictModel> englishModels = preloadModel(R.raw.english_indonesia);
                ArrayList<DictModel> indonesiaModels = preloadModel(R.raw.indonesia_english);

                progress = 30;
                publishProgress((int) progress);
                Double progressMaxInsert = 80.0;
                Double progressDiff = (progressMaxInsert - progress) / (englishModels.size() + indonesiaModels.size());

                englishHelper.open();
                englishHelper.beginTransaction();
                try {
                    for (DictModel model : englishModels) {
                        englishHelper.insertTransaction(model);
                        progress += progressDiff;
                        publishProgress((int) progress);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                englishHelper.setTransactionSuccess();
                englishHelper.endTransaction();
                englishHelper.close();

                indonesiaHelper.open();
                indonesiaHelper.beginTransaction();
                try {
                    for (DictModel model : indonesiaModels) {
                        indonesiaHelper.insertTransaction(model);
                        progress += progressDiff;
                        publishProgress((int) progress);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "doInBackground: Exception");
                }
                indonesiaHelper.setTransactionSuccess();
                indonesiaHelper.endTransaction();
                indonesiaHelper.close();

                appPreference.setFirstRun(false);
                publishProgress((int) maxprogress);
            } else {
                publishProgress((int) maxprogress);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            view.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Intent i = new Intent(ctx, MainActivity.class);
            ctx.startActivity(i);
            view.onFinishLoading();
        }
    }

    private ArrayList<DictModel> preloadModel(int resourceId) {
        ArrayList<DictModel> models = new ArrayList<>();
        String line;
        BufferedReader reader;
        try {
            Resources res = ctx.getResources();
            InputStream raw_dict = res.openRawResource(resourceId);

            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;
            while ((line = reader.readLine()) != null) {
                String[] splitstr = line.split("\t");

                DictModel model;

                model = new DictModel(splitstr[0], splitstr[1]);
                models.add(model);
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return models;
    }
}
