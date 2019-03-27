package com.dutch.hdh.dutchpayapp.ui.register.form;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.databinding.FragmentRegisterFormBinding;

public class Register_FormFragment extends Fragment implements Register_FormContract.View{

    private FragmentRegisterFormBinding mBinding;
    private Register_FormContract.Presenter mPresenter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_register_form, container, false);

        mPresenter = new Register_FormPresenter(this , getContext() , getActivity().getSupportFragmentManager());

        mBinding.btnNext.setOnClickListener(v->
                mPresenter.clickRegister()
        );

        return mBinding.getRoot();
    }
}
