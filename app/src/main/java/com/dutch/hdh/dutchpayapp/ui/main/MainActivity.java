package com.dutch.hdh.dutchpayapp.ui.main;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.databinding.ActivityMainBinding;
import com.kinda.alert.KAlertDialog;
import com.takusemba.spotlight.OnSpotlightStateChangedListener;
import com.takusemba.spotlight.Spotlight;
import com.takusemba.spotlight.shape.Circle;
import com.takusemba.spotlight.target.SimpleTarget;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private ActivityMainBinding mBinding;
    public MainContract.Presenter mPresenter;

    public KAlertDialog mLodingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setMainActiviy(this);

        //객체생성 및 데이터 초기화
        initData();

        //메뉴버튼
        mBinding.Appbar.loMenu.setOnClickListener(v ->
                mPresenter.clickMenu()
        );

        //뒤로 버튼
        mBinding.Appbar.loLeftIcon.setOnClickListener(v -> {
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
        mBinding.navigationView.layoutLogin.setOnClickListener(v ->
                mPresenter.clickLogin()
        );

        //회원가입 버튼
        mBinding.navigationView.layoutRegister.setOnClickListener(v ->
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
        mPresenter.setMainPresenter(this, this, getSupportFragmentManager(), mBinding.drawerLayout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mBinding.drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mLodingDialog = new KAlertDialog(this, KAlertDialog.PROGRESS_TYPE);
        mLodingDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mLodingDialog.setTitleText("Loading");
        mLodingDialog.setCancelable(false);

        //뷰페이저에 어댑터 연결요청 + TabLayer 붙이기
        mPresenter.setAdapter(mBinding.fragmentMain.vpMainVP, mBinding.fragmentMain.tabMainTL);

        mPresenter.initLoginState();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

//        /*
//         * loMenu 튜토리얼
//         */
//        LinearLayout viewMore = mBinding.Appbar.loMenu;
//        int[] viewMoreLocation = new int[2];
//        viewMore.getLocationInWindow(viewMoreLocation);
//        float viewMoreX = viewMoreLocation[0] + viewMore.getWidth() / 2f;
//        float viewMoreY = viewMoreLocation[1] + viewMore.getHeight() / 2f;
//        float viewMoreRadius = viewMore.getWidth() / 2f;
//
//        SimpleTarget viewMoreTarget = new SimpleTarget.Builder(this)
//                .setPoint(viewMoreX, viewMoreY)
//                .setShape(new Circle(viewMoreRadius))
//                .setTitle("더보기 메뉴")
//                .setDescription("여러 메뉴를 볼 수 있습니다.")
//                .build();
//
//        /*
//          loLeftIcon 튜토리얼
//         */
//        LinearLayout bell = mBinding.Appbar.loLeftIcon;
//        int[] bellLocation = new int[2];
//        bell.getLocationInWindow(bellLocation);
//        float bellX = bellLocation[0] + bell.getWidth() / 2f;
//        float bellY = bellLocation[1] + bell.getHeight() / 2f;
//        float bellRadius = bell.getWidth() / 2f;
//
//        SimpleTarget bellTarget = new SimpleTarget.Builder(this)
//                .setPoint(bellX, bellY)
//                .setShape(new Circle(bellRadius))
//                .setTitle("알람")
//                .setDescription("새로운 알림을 표시합니다.")
//                .build();
//
//        /*
//         * ivSoloPay 튜토리얼
//         */
//        ImageView soloPay = mBinding.fragmentMain.ivSoloPay;
//        int[] soloPayLocation = new int[2];
//        soloPay.getLocationInWindow(soloPayLocation);
//        float soloPayX = soloPayLocation[0] + soloPay.getWidth() / 2f;
//        float soloPayY = soloPayLocation[1] + soloPay.getHeight() / 2f;
//        float soloPayRadius = soloPay.getWidth() / 2f;
//
//        SimpleTarget soloPayTarget = new SimpleTarget.Builder(this)
//                .setPoint(soloPayX, soloPayY)
//                .setShape(new Circle(soloPayRadius))
//                .setTitle("개인결제")
//                .setDescription("개인으로 결제를 할 수 있습니다.")
//                .build();
//
//        /*
//         * ivDutchPay 튜토리얼
//         */
//        ImageView dutchPay = mBinding.fragmentMain.ivDutchPay;
//        int[] dutchPayLocation = new int[2];
//        dutchPay.getLocationInWindow(dutchPayLocation);
//        float dutchPayX = dutchPayLocation[0] + dutchPay.getWidth() / 2f;
//        float dutchPayY = dutchPayLocation[1] + dutchPay.getHeight() / 2f;
//        float dutchPayRadius = dutchPay.getWidth() / 2f;
//
//        SimpleTarget dutchPayTarget = new SimpleTarget.Builder(this)
//                .setPoint(dutchPayX, dutchPayY)
//                .setShape(new Circle(dutchPayRadius))
//                .setTitle("더치페이")
//                .setDescription("원하는 사람들과 금액을 나눠서 결제를 할 수 있습니다.")
//                .build();
//
//        /*
//         * 튜토리얼 시작
//         */
//
//        Spotlight.with(this)
//                .setOverlayColor(R.color.background1)
//                .setDuration(1000L)
//                .setAnimation(new DecelerateInterpolator(2f))
//                .setTargets(viewMoreTarget , bellTarget , soloPayTarget , dutchPayTarget)
//                .setClosedOnTouchedOutside(true)
//                .setOnSpotlightStateListener(new OnSpotlightStateChangedListener() {
//                    @Override
//                    public void onStarted() {
//                    }
//
//                    @Override
//                    public void onEnded() {
//                    }
//                })
//                .start();

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

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void showUserInfo(String userName, int userDutchMoney, boolean state) {
        if (state) {
            mBinding.navigationView.layoutLogin.setVisibility(View.GONE);

            mBinding.navigationView.imageLogin.setVisibility(View.GONE);
            mBinding.navigationView.imageRegister.setVisibility(View.GONE);
            mBinding.navigationView.tvLogin.setVisibility(View.GONE);
            mBinding.navigationView.tvRegister.setVisibility(View.GONE);

            mBinding.fragmentMain.layoutSuccessLogin.setVisibility(View.VISIBLE);
            mBinding.fragmentMain.layoutNoneLogin.setVisibility(View.GONE);

            mBinding.fragmentMain.txtUserName.setText(userName + "님, 안녕하세요!");
            mBinding.fragmentMain.txtUserDutchMoney.setText(String.format("%,d", userDutchMoney) + " ");
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
        mBinding.Appbar.ivBell.setVisibility(View.VISIBLE);
    }

    /**
     * 나가기 보이기
     */
    @Override
    public void showExit() {
        mBinding.Appbar.imageBack.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMain() {
        mBinding.fragmentMain.layoutMain.setBackgroundResource(R.color.transparent);
    }

    @Override
    public void showDialog() {
        mLodingDialog.show();
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
        mBinding.Appbar.ivBell.setVisibility(View.GONE);
    }

    /**
     * 나가기 감추기
     */
    @Override
    public void hideExit() {
        mBinding.Appbar.imageBack.setVisibility(View.GONE);
    }

    @Override
    public void hideMain() {
        mBinding.fragmentMain.layoutMain.setBackgroundResource(R.color.layoutBG);
    }

    @Override
    public void removeDialog() {
        mLodingDialog.dismissWithAnimation();
    }

    /**
     * 타이틀 변경하기
     */
    @Override
    public void changeTitle(String title) {
        mBinding.Appbar.txtAppBar.setText(title);
    }
}