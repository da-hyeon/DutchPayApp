package com.dutch.hdh.dutchpayapp.ui.register.term;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.ui.register.allview.Register_ViewAllTermsConditionsFragment;
import com.dutch.hdh.dutchpayapp.ui.register.form.Register_FormFragment;
import com.kinda.alert.KAlertDialog;

public class Register_TermsConditionsAgreementPresenter implements Register_TermsConditionsAgreementContract.Presenter {

    private Register_TermsConditionsAgreementContract.View mView;
    private Context mContext;

    private FragmentManager mFragmentManager;

    private boolean mAllTOS;
    private boolean mTOSCheckArray[];

    Register_TermsConditionsAgreementPresenter(Register_TermsConditionsAgreementContract.View mView, Context mContext, FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;

        mTOSCheckArray = new boolean[6];
        mAllTOS = false;
    }

    @Override
    public void refreshData(Bundle bundle) {
        if (bundle != null) {
            boolean allCheck = true;
            mTOSCheckArray = bundle.getBooleanArray("checkArray");

            for (int i = 0; i < mTOSCheckArray.length; i++) {
                mView.changeTOS(i, mTOSCheckArray[i]);
                if ( !mTOSCheckArray[i] ){
                    allCheck = false;
                }
            }
            mAllTOS = allCheck;
        } else {
            for (int i = 0; i < mTOSCheckArray.length; i++) {
                mView.changeTOS(i, false);
                mTOSCheckArray[i] = false;
            }
            mAllTOS = false;
        }

        mView.changeAllTOS(mAllTOS);
    }

    @Override
    public void onResume() {
        mView.initData();

        boolean allCheck = true;
        for (int i = 0; i < mTOSCheckArray.length; i++) {
            mView.changeTOS(i, mTOSCheckArray[i]);
            if (mTOSCheckArray[i]) {
                mView.changeAllView(i, R.drawable.view_all_on);
            } else {
                mView.changeAllView(i, R.drawable.view_all_off);
                allCheck = false;
            }
        }
        mView.changeAllTOS(allCheck);
    }

    @Override
    public void clickAllTOS(boolean state) {
        for (int i = 0; i < mTOSCheckArray.length; i++) {
            mView.changeTOS(i, state);
            mTOSCheckArray[i] = state;
            if (state) {
                mView.changeAllView(i, R.drawable.view_all_on);
            } else {
                mView.changeAllView(i, R.drawable.view_all_off);
            }
        }
    }

    @Override
    public void clickTOS(int index, boolean state) {
        mTOSCheckArray[index] = state;

        if (state) {
            mView.changeAllView(index, R.drawable.view_all_on);
        } else {
            mView.changeAllView(index, R.drawable.view_all_off);
        }

        for (int i = 0; i < mTOSCheckArray.length; i++) {
            if (!mTOSCheckArray[i]) {
                mView.changeAllTOS(false);
                return;
            }
        }
        mView.changeAllTOS(true);
    }

    @Override
    public void clickRegister() {

        for (int i = 0; i < 4; i++) {
            if (!mTOSCheckArray[i]) {
                mView.showDialog("필수 항목에 동의해주세요.");
                return;
            }
        }
        Register_FormFragment mRegister_FormFragment = new Register_FormFragment();

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in,0,0,  R .anim.fade_out);
        fragmentTransaction.replace(R.id.fragment_main, mRegister_FormFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        mFragmentManager.executePendingTransactions();
    }

    @Override
    public void clickAllView(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt("num", index);
        bundle.putBooleanArray("checkArray", mTOSCheckArray);

        Register_ViewAllTermsConditionsFragment register_viewAllTermsConditionsFragment = new Register_ViewAllTermsConditionsFragment();
        register_viewAllTermsConditionsFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0,0, R.anim.fade_out);
        fragmentTransaction.replace(R.id.fragment_main, register_viewAllTermsConditionsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        mFragmentManager.executePendingTransactions();
    }
}
