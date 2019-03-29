package com.dutch.hdh.dutchpayapp.ui.main;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.EventImageSliderAdapter;
import com.dutch.hdh.dutchpayapp.data.db.User;
import com.dutch.hdh.dutchpayapp.ui.login.LoginFragment;
import com.dutch.hdh.dutchpayapp.ui.register.allview.Register_ViewAllTermsConditionsFragment;
import com.dutch.hdh.dutchpayapp.ui.register.term.Register_TermsConditionsAgreementFragment;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;

    private long mLastTime;

    private User mUser;
    private DrawerLayout mDrawerLayout;

    private LoginFragment mLoginFragment;
    public Register_TermsConditionsAgreementFragment mRegister_termsConditionsAgreementFragment;


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
        mView.showUserInfo(mUser.getUserName(), mUser.getUserMoney(), mUser.isUserState());
    }

    @Override
    public void setMainPresenter(MainContract.View mView, Context mContext, FragmentManager mFragmentManager, DrawerLayout mDrawerLayout) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        this.mDrawerLayout = mDrawerLayout;
        mUser = User.getInstance();

        mLoginFragment = new LoginFragment();
        mRegister_termsConditionsAgreementFragment = Register_TermsConditionsAgreementFragment.getInstance();
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

        List fragmentList = mFragmentManager.getFragments();
        for (int i = 0; i < fragmentList.size(); i++) {
            if (fragmentList.get(i) instanceof Register_ViewAllTermsConditionsFragment) {
                ((Register_ViewAllTermsConditionsFragment) fragmentList.get(i)).onBackPress();
                return;
            }
        }


        int fragmentCount = mFragmentManager.getBackStackEntryCount();

        //메뉴가 열려있으면 닫는다.
        if (mDrawerLayout.isDrawerOpen(GravityCompat.END)) {
            mView.hideDrawerLayout();
        } else {
            if (fragmentCount != 0) {
                mFragmentManager.popBackStack();
                mFragmentManager.executePendingTransactions();
                fragmentCount = mFragmentManager.getBackStackEntryCount();
            }

            if (fragmentCount == 0) {
                mView.showMain();
                mView.showBell();
                mView.hideExit();
                mView.changeTitle("");
            }
        }
    }

    /**
     * 로그인 클릭 이벤트 처리
     */
    @Override
    public void clickLogin() {
        mView.hideDrawerLayout();
        mView.hideMain();
        if (!mLoginFragment.isVisible()) {
            mFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

            mView.changeTitle("로그인");
            mView.hideBell();
            mView.showExit();

            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0,0, R.anim.fade_out);
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
        mView.hideMain();
        if(!mRegister_termsConditionsAgreementFragment.isVisible()){
            mFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

            mView.changeTitle("회원가입");
            mView.hideBell();
            mView.showExit();

            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.fade_in,0,0, R.anim.fade_out);
            fragmentTransaction.replace(R.id.fragment_main, mRegister_termsConditionsAgreementFragment);
            mRegister_termsConditionsAgreementFragment.setArguments(null);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            mFragmentManager.executePendingTransactions();
        }
    }


    @Override
    public void clickLogout() {
        mView.showMain();
        mView.hideDrawerLayout();
        mView.changeTitle("");
        mView.showBell();
        mView.hideExit();
        mUser.setUserState(false);
        mView.initData();
        mFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
