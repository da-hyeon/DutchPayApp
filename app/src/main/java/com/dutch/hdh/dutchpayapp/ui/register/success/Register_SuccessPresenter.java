package com.dutch.hdh.dutchpayapp.ui.register.success;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.dutch.hdh.dutchpayapp.ui.main.MainActivity;

public class Register_SuccessPresenter implements Register_SuccessContract.Presenter {

    private Register_SuccessContract.View mView;
    private Context mContext;
    private Activity mActivity;
    private FragmentManager mFragmentManager;

    Register_SuccessPresenter(Register_SuccessContract.View mView, Context mContext, Activity mActivity , FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mActivity = mActivity;
        this.mFragmentManager = mFragmentManager;

        ((MainActivity)mActivity).changeTitle("회원가입 완료");
        ((MainActivity) mActivity).initData();
    }

    @Override
    public void clickAppStart() {

        ((MainActivity) mActivity).changeTitle("");
        ((MainActivity) mActivity).hideExit();
        ((MainActivity) mActivity).showMain();
        ((MainActivity) mActivity).showBell();
        mFragmentManager.popBackStack(null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
