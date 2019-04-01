package com.dutch.hdh.dutchpayapp.ui.login;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.UserInfo;
import com.dutch.hdh.dutchpayapp.data.util.ServerAPI;
import com.dutch.hdh.dutchpayapp.ui.main.MainActivity;
import com.dutch.hdh.dutchpayapp.ui.register.term.Register_TermsConditionsAgreementFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;

    private Context mContext;
    private FragmentManager mFragmentManager;
    private Activity mActivity;

    private MyApplication mMyApplication;

    public LoginPresenter(LoginContract.View mView, Context mContext, FragmentManager mFragmentManager, Activity mActivity) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        this.mActivity = mActivity;

        mMyApplication = MyApplication.getInstance();
    }

    @Override
    public void onResume() {
        ((MainActivity) mActivity).changeTitle("로그인");
    }

    @Override
    public void clickLogin(String userID, String userPassword) {
        if (userID.equals("") || userPassword.equals("")) {
            mView.showFailDialog("빈칸을 채워주세요.");
        } else {
            Call<UserInfo> userInfo = MyApplication
                    .getRestAdapter()
                    .getFineUser(userID, userPassword);

            userInfo.enqueue(new Callback<UserInfo>() {
                @Override
                public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                    if (response.body() != null) {
                        mMyApplication.setUserInfo(response.body());
                        mMyApplication.getUserInfo().setUserState(true);
                        mView.showSuccessDialog(mMyApplication.getUserInfo().getUserName()+"님 환영합니다.");
                    } else {
                        mView.showFailDialog("아이디와 비밀번호를 확인해주세요.");
                    }
                }

                @Override
                public void onFailure(Call<UserInfo> call, Throwable t) {
                    mView.showFailDialog(t.getMessage());
                }
            });
        }
    }

    @Override
    public void clickRegister() {
        Register_TermsConditionsAgreementFragment mRegister_termsConditionsAgreementFragment = Register_TermsConditionsAgreementFragment.getInstance();

        ((MainActivity) mActivity).hideDrawerLayout();
        if (!mRegister_termsConditionsAgreementFragment.isVisible()) {
            ((MainActivity) mActivity).changeTitle("회원가입");
            ((MainActivity) mActivity).hideBell();
            ((MainActivity) mActivity).showExit();

            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);
            fragmentTransaction.replace(R.id.fragment_main, mRegister_termsConditionsAgreementFragment);
            mRegister_termsConditionsAgreementFragment.setArguments(null);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            mFragmentManager.executePendingTransactions();

        }
    }

    @Override
    public void clickSuccessDialog() {

        ((MainActivity) mActivity).changeTitle("");
        ((MainActivity) mActivity).initData();
        ((MainActivity) mActivity).hideExit();
        ((MainActivity) mActivity).showMain();
        ((MainActivity) mActivity).showBell();

        mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
