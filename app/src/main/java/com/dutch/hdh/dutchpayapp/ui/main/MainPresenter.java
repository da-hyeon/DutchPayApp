package com.dutch.hdh.dutchpayapp.ui.main;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.widget.Toast;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.EventImageSliderAdapter;
import com.dutch.hdh.dutchpayapp.data.User;
import com.dutch.hdh.dutchpayapp.ui.login.LoginFragment;
import com.dutch.hdh.dutchpayapp.ui.register.form.Register_FormFragment;
import com.dutch.hdh.dutchpayapp.ui.register.term.Register_TermsConditionsAgreementFragment;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private long mLastTime;
    private User mUser;

    private LoginFragment mLoginFragment = new LoginFragment();
    private Register_TermsConditionsAgreementFragment mRegister_termsConditionsAgreementFragment = new Register_TermsConditionsAgreementFragment();


    private MainPresenter() {
    }

    private static MainPresenter itMe;

    public static MainPresenter getInstance() {
        if (itMe == null) {
            itMe = new MainPresenter();
        }
        return itMe;
    }

    @Override
    public void initLoginState() {
        mView.showUserInfo(mUser.getUserName(), mUser.getUserDutchMoney(), mUser.isUserState());
    }

    @Override
    public void setMainPresenter(MainContract.View mView, Context mContext, FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        mUser = User.getInstance();
    }

    @Override
    public void setAdapter(ViewPager viewPager, TabLayout tabLayout) {
        //슬라이드 이미지 저장
        List<Drawable> imageArray = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            imageArray.add(ContextCompat.getDrawable(mContext, R.drawable.dutchpay_event));
        }
        EventImageSliderAdapter mEventImageSliderAdapter = new EventImageSliderAdapter(mContext, imageArray);
        viewPager.setAdapter(mEventImageSliderAdapter);

        tabLayout.setupWithViewPager(viewPager, true);
    }

    /**
     * 메뉴 클릭 이벤트 처리
     */
    @Override
    public void clickMenu() {
        mView.showDrawerLayout();
    }

    /**
     * 나가기 클릭 이벤트 처리
     */
    @Override
    public void clickExit() {
        mView.hideDrawerLayout();
    }

    /**
     * 뒤로가기 이벤트 처리
     */
    @Override
    public void clickBack() {
        int fragmentCount = mFragmentManager.getBackStackEntryCount();

        if (fragmentCount != 0) {
            mFragmentManager.popBackStack();
            mFragmentManager.executePendingTransactions();
            fragmentCount = mFragmentManager.getBackStackEntryCount();
        }

        if (fragmentCount == 0) {
            mView.showBell();
            mView.hideExit();
            mView.changeTitle("");
        }
    }

    /**
     * 로그인 클릭 이벤트 처리
     */
    @Override
    public void clickLogin() {
        mView.hideDrawerLayout();
        if (!mLoginFragment.isVisible()) {
            mView.changeTitle("로그인");
            mView.hideBell();
            mView.showExit();

            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

            mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentTransaction.replace(R.id.fragment_main, mLoginFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            mFragmentManager.executePendingTransactions();
        }
    }

    /**
     * 회원가입 클릭 이벤트 처리
     */
    @Override
    public void clickRegister() {
        mView.hideDrawerLayout();
        if (!mRegister_termsConditionsAgreementFragment.isVisible()) {
            mView.changeTitle("회원가입");
            mView.hideBell();
            mView.showExit();

            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentTransaction.replace(R.id.fragment_main, mRegister_termsConditionsAgreementFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            mFragmentManager.executePendingTransactions();
        }
    }

    @Override
    public void clickLogout() {
        mView.hideDrawerLayout();
        mView.changeTitle("");
        mView.showBell();
        mView.hideExit();
        mUser.setUserState(false);
        mView.initData();
        mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
