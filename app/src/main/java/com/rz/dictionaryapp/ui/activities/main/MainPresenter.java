package com.rz.dictionaryapp.ui.activities.main;

import com.rz.dictionaryapp.ui.fragments.engtoind.EngToIndFragment;
import com.rz.dictionaryapp.ui.fragments.indtoeng.IndToEngFragment;

public class MainPresenter implements MainContract.Presenter{
    public static final String I_TO_E = "itoe";
    public static final String E_TO_I = "etoi";

    private MainContract.View view;

    MainPresenter(MainContract.View view){
        this.view = view;
    }

    @Override
    public void changeFragment(String fragmentName) {
        switch (fragmentName) {
            case I_TO_E:
                view.setFragment(new IndToEngFragment());
                break;
            case E_TO_I:
                view.setFragment(new EngToIndFragment());
                break;
        }
    }
}
