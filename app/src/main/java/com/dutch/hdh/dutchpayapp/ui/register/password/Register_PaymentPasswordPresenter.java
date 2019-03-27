package com.dutch.hdh.dutchpayapp.ui.register.password;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.ui.main.MainContract;
import com.dutch.hdh.dutchpayapp.ui.main.MainPresenter;
import com.dutch.hdh.dutchpayapp.ui.register.success.Register_SuccessFragment;

import java.util.ArrayList;
import java.util.Collections;

public class Register_PaymentPasswordPresenter implements Register_PaymentPasswordContract.Presenter {

    private Register_PaymentPasswordContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private String mPassword;


    public Register_PaymentPasswordPresenter(Register_PaymentPasswordContract.View mView, Context mContext, FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        mPassword = "";
    }

    @Override
    public void initRandomNumber() {
        //난수생성
        ArrayList<Integer> randomNumber = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            randomNumber.add(i);
        }
        Collections.shuffle(randomNumber);
        for (int i = 0; i < 10; i++) {
            mView.showRandomNumber(i , randomNumber.get(i).toString());
        }
    }

    @Override
    public void clickNumber(String numberText) {

        if (mPassword.length() < 6) {
            mPassword += numberText;
        }

        for (int i = 0; i < 6; i++) {
            if (i < mPassword.length()) {
                mView.dotImagesUpdate(i, true);
            } else {
                mView.dotImagesUpdate(i, false);
            }
        }
    }

    @Override
    public void clickDeleteButton() {
        mPassword = "";
        for (int i = 0; i < 6; i++) {
            mView.dotImagesUpdate(i,   false);
        }
    }

    @Override
    public void clickOKButton() {
        if (mPassword.length() == 6) {
            //프래그먼트 이동
            Register_SuccessFragment register_successFragment = new Register_SuccessFragment();

            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_main, register_successFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            mFragmentManager.executePendingTransactions();

        } else {
            mView.Fail();
        }
    }
}
