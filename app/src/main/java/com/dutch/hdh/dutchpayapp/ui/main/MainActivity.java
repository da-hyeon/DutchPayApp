package com.dutch.hdh.dutchpayapp.ui.main;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.EventImageSliderAdapter;
import com.dutch.hdh.dutchpayapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private ActivityMainBinding mBinding;
    private MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setMainActiviy(this);

        //객체생성 및 데이터 초기화
        initData();

        //메뉴버튼
        mBinding.Appbar.imageMenu.setOnClickListener(v ->
                mPresenter.clickMenu()
        );

        //나가기 버튼
        mBinding.navigationView.imageExit.setOnClickListener(v ->
                mPresenter.clickExit()
        );

        //로그인 버튼
        mBinding.navigationView.imageLogin.setOnClickListener(v ->
                mPresenter.clickLogin()
        );

        //회원가입 버튼
        mBinding.navigationView.imageRegister.setOnClickListener(v ->
                {}
        );
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {
        mPresenter = MainPresenter.getInstance();
        mPresenter.setMainPresenter(this, this, getSupportFragmentManager(), mBinding.drawerLayout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mBinding.drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        //뷰페이저에 어댑터 연결요청 + TabLayer 붙이기
        mPresenter.setAdapter( mBinding.fragmentMain.vpMainVP , mBinding.fragmentMain.tabMainTL);
    }


    /**
     * 뒤로가기 클릭
     */
    @Override
    public void onBackPressed() {
        mPresenter.clickBack();
    }

    /**
     * 메뉴 보이기
     */
    @Override
    public void showDrawerLayout() {
        mBinding.drawerLayout.openDrawer(GravityCompat.END);
    }

    /**
     * 이전화면 보이기
     */
    @Override
    public void showBefore() {
        super.onBackPressed();
    }

    /**
     * 메뉴 감추기
     */
    @Override
    public void hideDrawerLayout() {
        mBinding.drawerLayout.closeDrawer(GravityCompat.END);
    }


}