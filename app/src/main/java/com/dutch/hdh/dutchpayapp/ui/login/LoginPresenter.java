package com.dutch.hdh.dutchpayapp.ui.login;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.User;
import com.dutch.hdh.dutchpayapp.data.db.UserInfo;
import com.dutch.hdh.dutchpayapp.data.service.ApiService;
import com.dutch.hdh.dutchpayapp.ui.main.MainActivity;
import com.dutch.hdh.dutchpayapp.ui.register.term.Register_TermsConditionsAgreementFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private Activity mActivity;
    Retrofit retrofit;
    ApiService loginRequest;

    public LoginPresenter(LoginContract.View mView, Context mContext, FragmentManager mFragmentManager, Activity mActivity) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        this.mActivity = mActivity;

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        loginRequest = retrofit.create(ApiService.class);
    }

    @Override
    public void onResume() {
        ((MainActivity) mActivity).changeTitle("로그인");
    }

    @Override
    public void clickLogin(String userID , String userPassword) {
        if(userID.equals("") || userPassword.equals("")){
            mView.showFailDialog("빈칸을 채워주세요.");
        } else {
            loginRequest.loginRequest(userID , userPassword).enqueue(new Callback<UserInfo>() {
                @Override
                public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                    if (response.isSuccessful()){

                        UserInfo user = response.body();
                        user.setUserEmail(response.body().getUserEmail());
                        user.setUserPassword(response.body().getUserPassword());
                        user.setUserPaymentPassword(response.body().getUserPaymentPassword());
                        user.setUserName(response.body().getUserName());
                        user.setUserRN(response.body().getUserRN());
                        user.setUserGender(response.body().getUserGender());
                        user.setUserPhone(response.body().getUserPhone());
                        user.setUserMoney(response.body().getUserMoney());
                        //mView.showSuccessDialog("반갑습니다.");

                    }
                }

                @Override
                public void onFailure(Call<UserInfo> call, Throwable t) {
                    mView.showFailDialog("로그인 실패");
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
            fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0,0, R.anim.fade_out);
            fragmentTransaction.replace(R.id.fragment_main, mRegister_termsConditionsAgreementFragment);
            mRegister_termsConditionsAgreementFragment.setArguments(null);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            mFragmentManager.executePendingTransactions();

        }
    }

    @Override
    public void clickSuccessDialog() {
        User user = User.getInstance();
        user.setUserState(true);

        ((MainActivity) mActivity).changeTitle("");
        ((MainActivity) mActivity).initData();
        ((MainActivity) mActivity).hideExit();
        ((MainActivity) mActivity).showMain();
        ((MainActivity) mActivity).showBell();

        mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
