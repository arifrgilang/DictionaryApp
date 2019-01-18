package com.rz.dictionaryapp.ui.activities.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.rz.dictionaryapp.R;
import com.rz.dictionaryapp.data.model.DictModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String WORD_MODEL = "model";

    @BindView(R.id.detail_word) TextView tvWord;
    @BindView(R.id.detail_translation) TextView tvTranslation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        if(getSupportActionBar() != null)
        getSupportActionBar().setTitle(getString(R.string.description));

        DictModel model = getIntent().getParcelableExtra(WORD_MODEL);

        tvWord.setText(model.getDict());
        tvTranslation.setText(model.getTranslation());
    }
}
