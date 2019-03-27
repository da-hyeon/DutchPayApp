package com.dutch.hdh.dutchpayapp.ui.register.password;

public interface Register_PaymentPasswordContract {

    interface View{
        void showRandomNumber(int index ,String randomNumber);

        void dotImagesUpdate(int index, boolean checkState);
        void Fail();
    }

    interface Presenter{
        void initRandomNumber();

        void clickNumber(String numberText);
        void clickDeleteButton();
        void clickOKButton();
    }
}
