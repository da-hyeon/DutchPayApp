package com.dutch.hdh.dutchpayapp.ui.register.form;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.ui.register.password.Register_PaymentPasswordFragment;

public class Register_FormPresenter implements Register_FormContract.Presenter {

    private Register_FormContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;

    public Register_FormPresenter(Register_FormContract.View mView, Context mContext, FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
    }

    @Override
    public void clickRegister() {
        Register_PaymentPasswordFragment mRegister_paymentPasswordFragment = new Register_PaymentPasswordFragment();

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_main, mRegister_paymentPasswordFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        mFragmentManager.executePendingTransactions();
    }
}
