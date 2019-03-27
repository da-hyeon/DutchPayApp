package com.dutch.hdh.dutchpayapp.ui.register.term;

import android.support.annotation.DrawableRes;
import android.widget.ImageView;

public interface Register_TermsConditionsAgreementContract {
    interface View{
        void changeAllTOS(boolean state);
        void changeTOS(int index, boolean state);
    }

    interface Presenter{

        void clickAllTOS(boolean state);
        void clickTOS(int index , boolean state);
        void clickRegister();
    }
}
