package com.dutch.hdh.dutchpayapp.ui.main;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;

public interface MainContract {
    interface View{
        //init
        void initData();

        //show
        void showDrawerLayout();
        void showBefore();
        void showUserInfo(String userName , int userDutchMoney , boolean state);
        void showBell();
        void showExit();
        void showMain();
        void showDialog();

        //change
        void changeTitle(String title);

        //hide
        void hideDrawerLayout();
        void hideBell();
        void hideExit();
        void hideMain();

        //remove
        void removeDialog();
    }

    interface Presenter{
        //init
        void initLoginState();

        //set
        void setMainPresenter(MainContract.View mView, Context mContext, FragmentManager mFragmentManager, DrawerLayout mDrawerLayout);
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
