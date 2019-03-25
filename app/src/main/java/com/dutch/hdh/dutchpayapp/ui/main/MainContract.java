package com.dutch.hdh.dutchpayapp.ui.main;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;

public interface MainContract {
    interface View{
        //show
        void showDrawerLayout();
        void showBefore();

        //hide
        void hideDrawerLayout();
    }
    interface Presenter{
        //set
        void setMainPresenter(MainContract.View mView, Context mContext, FragmentManager mFragmentManager, DrawerLayout mDrawerLayout);
        void setAdapter(ViewPager viewPager , TabLayout tabLayout);

        //click
        void clickMenu();
        void clickExit();
        void clickBack();
        void clickLogin();
        void clickRegister();

    }
}
