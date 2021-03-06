package com.dutch.hdh.dutchpayapp.ui.mypage.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentMyPageMainBinding;

public class MyPage_MainFragment extends BaseFragment implements MyPage_MainContract.View{

    private FragmentMyPageMainBinding mBinding;
    private MyPage_MainContract.Presenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_page_main, container, false);
        mPresenter = new MyPage_MainPresenter(this , getContext() , getActivity() ,getFragmentManager());
        initData();

        mBinding.vEmail.setOnClickListener(v->
            mPresenter.clickChangeEmail()
        );

        mBinding.vPassword.setOnClickListener(v->
                mPresenter.clickChangePassword()
        );

        mBinding.vPhone.setOnClickListener(v->
                mPresenter.clickChangePhoneNumber()
        );

        mBinding.vWithdrawal.setOnClickListener(v->
                mPresenter.clickWithdrawal()
        );
        return mBinding.getRoot();
    }

    /**
     * 객체생성 및 데이터초기화
     */
    public void initData() {
        mPresenter.setVIew();
    }

    @Override
    public void changeUserNameText(String name) {
        mBinding.tvUserName.setText(name);
    }

    @Override
    public void changeMoneyText(String content) {
        mBinding.tvUserMoney.setText(content);
    }

    @Override
    public void changeEmailText(String content) {
        mBinding.tvUserEmail.setText(content);
    }

    @Override
    public void changePhoneNumberText(String content) {
        mBinding.tvUserPhone.setText(content);
    }
}