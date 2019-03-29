package com.dutch.hdh.dutchpayapp.ui.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.databinding.FragmentLoginBinding;
import com.kinda.alert.KAlertDialog;

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
            mPresenter.clickLogin(mBinding.editUserID.getText().toString() , mBinding.editUserPW.getText().toString())
        );

        mBinding.layoutRegister.setOnClickListener(v->
            mPresenter.clickRegister()
        );


        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void showSuccessDialog(String content) {
        new KAlertDialog(getContext(), KAlertDialog.SUCCESS_TYPE)
                .setTitleText("로그인 성공")
                .setContentText(content)
                .setConfirmText("확인")
                .setConfirmClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();
                    mPresenter.clickSuccessDialog();
                })
                .show();
    }

    @Override
    public void showFailDialog(String content) {
        new KAlertDialog(getContext(), KAlertDialog.WARNING_TYPE)
                .setTitleText("실패")
                .setContentText(content)
                .setConfirmText("확인")
                .setConfirmClickListener(sDialog -> {sDialog.dismissWithAnimation();})
                .show();
    }
}