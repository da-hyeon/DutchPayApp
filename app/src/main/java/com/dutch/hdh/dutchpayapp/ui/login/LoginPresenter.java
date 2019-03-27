package com.dutch.hdh.dutchpayapp.ui.login;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.User;
import com.dutch.hdh.dutchpayapp.ui.main.MainActivity;
import com.dutch.hdh.dutchpayapp.ui.register.term.Register_TermsConditionsAgreementFragment;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private Activity mActivity;

    public LoginPresenter(LoginContract.View mView, Context mContext, FragmentManager mFragmentManager, Activity mActivity) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        this.mActivity = mActivity;
    }

    @Override
    public void clickLogin() {
        User user = User.getInstance();
        user.setUserState(true);
        //((MainActivity) mActivity).showUserInfo(user.getUserName(), user.getUserDutchMoney() , user.isUserState());
        ((MainActivity) mActivity).changeTitle("");
        ((MainActivity) mActivity).initData();
        ((MainActivity) mActivity).hideExit();
        ((MainActivity) mActivity).showBell();
        mFragmentManager.popBackStack(null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void clickRegister() {
        ((MainActivity) mActivity).mPresenter.clickRegister();
    }
}
