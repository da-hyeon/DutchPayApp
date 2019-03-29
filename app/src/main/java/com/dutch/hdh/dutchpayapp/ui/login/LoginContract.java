package com.dutch.hdh.dutchpayapp.ui.login;

public interface LoginContract {

    interface View{
        void showSuccessDialog(String content);
        void showFailDialog(String content);
    }

    interface Presenter{
        void onResume();

        void clickLogin(String userID , String userPassword);
        void clickRegister();
        void clickSuccessDialog();
    }
}
