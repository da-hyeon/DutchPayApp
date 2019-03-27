package com.dutch.hdh.dutchpayapp.ui.register.term;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.ui.register.form.Register_FormFragment;

public class Register_TermsConditionsAgreementPresenter implements Register_TermsConditionsAgreementContract.Presenter{

    private Register_TermsConditionsAgreementContract.View mView;
    private Context mContext;
    private Activity mActivity;

    private FragmentManager mFragmentManager;

    private boolean mAllTOS;
    private boolean mTOSCheckArray[];

    public Register_TermsConditionsAgreementPresenter(Register_TermsConditionsAgreementContract.View mView, Context mContext, Activity mActivity ,FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mActivity = mActivity;
        this.mFragmentManager = mFragmentManager;

        mTOSCheckArray = new boolean[6];
        mAllTOS = false;
    }

    @Override
    public void clickAllTOS(boolean state) {
        for( int i = 0; i < mTOSCheckArray.length; i++){
            mView.changeTOS(i , state);
            mTOSCheckArray[i] = state;
        }
    }

    @Override
    public void clickTOS(int index, boolean state) {
        mTOSCheckArray[index] = state;

        for( int i = 0; i < mTOSCheckArray.length; i++){
            if(!mTOSCheckArray[i]) {
                mView.changeAllTOS(false);
                return;
            }
        }
        mView.changeAllTOS(true);
    }

    @Override
    public void clickRegister() {

        for(int i = 0; i < 4; i++){
            if(!mTOSCheckArray[i]){
                Toast.makeText(mContext, "필수 항목에 동의해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        Register_FormFragment mRegister_FormFragment = new Register_FormFragment();

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_main, mRegister_FormFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        mFragmentManager.executePendingTransactions();
    }
}
