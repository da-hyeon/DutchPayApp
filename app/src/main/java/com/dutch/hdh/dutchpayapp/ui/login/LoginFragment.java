package com.dutch.hdh.dutchpayapp.ui.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment implements LoginContract.View{

    private FragmentLoginBinding mBinding;
    private LoginContract.Presenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_login, container, false);

        mPresenter = new LoginPresenter(this , getContext() ,getFragmentManager() , getActivity());

        mBinding.btnLogin.setOnClickListener(v->
            mPresenter.clickLogin()
        );

        mBinding.imageUserRegister.setOnClickListener(v->
            mPresenter.clickRegister()
        );


        return mBinding.getRoot();
    }
}