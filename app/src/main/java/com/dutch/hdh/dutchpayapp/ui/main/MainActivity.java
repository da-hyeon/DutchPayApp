package com.dutch.hdh.dutchpayapp.ui.main;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.EventImageSliderAdapter;
import com.dutch.hdh.dutchpayapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private ActivityMainBinding mBinding;
    public MainContract.Presenter mPresenter;

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

        //뒤로 버튼
        mBinding.Appbar.imageMenu2.setOnClickListener(v -> {
                    if (mBinding.Appbar.imageBack.getVisibility() == View.VISIBLE) {
                        mPresenter.clickBack();
                    }
                }
        );

        //나가기 버튼
        mBinding.navigationView.imageExit.setOnClickListener(v ->
                mPresenter.clickExit()
        );

        //로그인 버튼
        mBinding.navigationView.imageLogin.setOnClickListener(v ->
                mPresenter.clickLogin()
        );

        //로그인 글씨
        mBinding.navigationView.tvLogin.setOnClickListener(v ->
                mPresenter.clickLogin()
        );

        //회원가입 버튼
        mBinding.navigationView.imageRegister.setOnClickListener(v ->
                mPresenter.clickRegister()
        );

        //회원가입 글씨
        mBinding.navigationView.tvRegister.setOnClickListener(v ->
                mPresenter.clickRegister()
        );

        mBinding.navigationView.imageCustomerService.setOnClickListener(v ->
                mPresenter.clickLogout()
        );
    }

    /**
     * 객체생성 및 데이터초기화
     */
    @Override
    public void initData() {
        mPresenter = MainPresenter.getInstance();
        mPresenter.setMainPresenter(this, this, getSupportFragmentManager());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mBinding.drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //뷰페이저에 어댑터 연결요청 + TabLayer 붙이기
        mPresenter.setAdapter(mBinding.fragmentMain.vpMainVP, mBinding.fragmentMain.tabMainTL);

        mPresenter.initLoginState();
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

    @Override
    public void showUserInfo(String userName, String userDutchMoney, boolean state) {
        if (state) {
            mBinding.navigationView.layoutLogin.setVisibility(View.GONE);

            mBinding.navigationView.imageLogin.setVisibility(View.GONE);
            mBinding.navigationView.imageRegister.setVisibility(View.GONE);
            mBinding.navigationView.tvLogin.setVisibility(View.GONE);
            mBinding.navigationView.tvRegister.setVisibility(View.GONE);

            mBinding.fragmentMain.layoutSuccessLogin.setVisibility(View.VISIBLE);
            mBinding.fragmentMain.layoutNoneLogin.setVisibility(View.GONE);

            mBinding.fragmentMain.txtUserName.setText(userName + "님, 안녕하세요!");
            mBinding.fragmentMain.txtUserDutchMoney.setText(String.format("%,d", Integer.parseInt(userDutchMoney)) + " ");
        } else {
            mBinding.navigationView.layoutLogin.setVisibility(View.VISIBLE);

            mBinding.navigationView.imageLogin.setVisibility(View.VISIBLE);
            mBinding.navigationView.imageRegister.setVisibility(View.VISIBLE);
            mBinding.navigationView.tvLogin.setVisibility(View.VISIBLE);
            mBinding.navigationView.tvRegister.setVisibility(View.VISIBLE);

            mBinding.fragmentMain.layoutSuccessLogin.setVisibility(View.GONE);
            mBinding.fragmentMain.layoutNoneLogin.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 벨 보이기
     */
    @Override
    public void showBell() {
        mBinding.Appbar.imageBell.setVisibility(View.VISIBLE);
    }

    /**
     * 나가기 보이기
     */
    @Override
    public void showExit() {
        mBinding.Appbar.imageBack.setVisibility(View.VISIBLE);
    }

    /**
     * 메뉴 감추기
     */
    @Override
    public void hideDrawerLayout() {
        mBinding.drawerLayout.closeDrawer(GravityCompat.END);
    }

    /**
     * 벨 감추기
     */
    @Override
    public void hideBell() {
        mBinding.Appbar.imageBell.setVisibility(View.GONE);
    }

    /**
     * 나가기 감추기
     */
    @Override
    public void hideExit() {
        mBinding.Appbar.imageBack.setVisibility(View.GONE);
    }

    /**
     * 타이틀 변경하기
     */
    @Override
    public void changeTitle(String title) {
        mBinding.Appbar.txtAppBar.setText(title);
    }
}