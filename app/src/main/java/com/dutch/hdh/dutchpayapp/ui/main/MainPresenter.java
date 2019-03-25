package com.dutch.hdh.dutchpayapp.ui.main;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.widget.Toast;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.EventImageSliderAdapter;
import com.dutch.hdh.dutchpayapp.data.User;
import com.dutch.hdh.dutchpayapp.ui.login.LoginFragment;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private long mLastTime;
    private User mUser;
    private DrawerLayout mDrawerLayout;

    private MainPresenter() {}
    private static MainPresenter itMe;

    public static MainPresenter getInstance() {
        if (itMe == null) {
            itMe = new MainPresenter();
        }
        return itMe;
    }

    @Override
    public void setMainPresenter(MainContract.View mView, Context mContext, FragmentManager mFragmentManager, DrawerLayout mDrawerLayout) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        this.mDrawerLayout = mDrawerLayout;
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
        mView.showBefore();
    }

    /**
     * 로그인 클릭 이벤트 처리
     */
    @Override
    public void clickLogin() {
        mView.hideDrawerLayout();

        LoginFragment loginFragment = new LoginFragment();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragment_main , loginFragment);
        fragmentTransaction.commit();
    }

    /**
     * 회원가입 클릭 이벤트 처리
     */
    @Override
    public void clickRegister() {

    }
}
