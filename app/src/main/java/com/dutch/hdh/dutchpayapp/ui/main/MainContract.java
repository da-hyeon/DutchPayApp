package com.dutch.hdh.dutchpayapp.ui.main;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

public interface MainContract {
    interface View{
        //init
        void initData();

        //show
        void showDrawerLayout();
        void showBefore();
        void showUserInfo(String userName , String userDutchMoney , boolean state);
        void showBell();
        void showExit();

        //hide
        void hideDrawerLayout();
        void hideBell();
        void hideExit();

        //change
        void changeTitle(String title);
    }

    interface Presenter{
        //init
        void initLoginState();

        //set
        void setMainPresenter(MainContract.View mView, Context mContext, FragmentManager mFragmentManager);
        void setAdapter(ViewPager viewPager , TabLayout tabLayout);

        //click
        void clickMenu();
        void clickExit();
        void clickBack();
        void clickLogin();
        void clickRegister();
        void clickLogout();

    }
}
