package com.dutch.hdh.dutchpayapp.ui.register.term;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.databinding.FragmentRegisterTermsConditionsAgreementBinding;

public class Register_TermsConditionsAgreementFragment extends Fragment implements Register_TermsConditionsAgreementContract.View{

    private FragmentRegisterTermsConditionsAgreementBinding mBinding;
    private Register_TermsConditionsAgreementPresenter mPresenter;

    private CheckBox mTermsConditions[];
    private ImageView mAllView[];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_register_terms_conditions_agreement, container, false);

        initData();

        //전체 동의 클릭
        mBinding.cbCompleteAgreement.setOnClickListener(v ->
                mPresenter.clickAllTOS(mBinding.cbCompleteAgreement.isChecked())
        );

        //약관 동의 클릭
        for(int i  = 0 ; i < mTermsConditions.length; i++){
            int finalI = i;
            mTermsConditions[i].setOnClickListener(v-> {
                mPresenter.clickTOS(finalI, mTermsConditions[finalI].isChecked());
                    }
            );
        }
        
        //회원가입 클릭
        mBinding.imageViewRegister.setOnClickListener(v->
                mPresenter.clickRegister()
        );

        return mBinding.getRoot();
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {
        mPresenter = new Register_TermsConditionsAgreementPresenter(this, getContext(), getActivity() , getFragmentManager());

        mTermsConditions = new CheckBox[]{
                mBinding.cbTermsConditions1,
                mBinding.cbTermsConditions2,
                mBinding.cbTermsConditions3,
                mBinding.cbTermsConditions4,
                mBinding.cbTermsConditions5,
                mBinding.cbTermsConditions6
        };


        mAllView = new ImageView[]{
                mBinding.viewAll1,
                mBinding.viewAll2,
                mBinding.viewAll3,
                mBinding.viewAll4,
                mBinding.viewAll5,
                mBinding.viewAll6,
        };
    }
    
    @Override
    public void changeAllTOS(boolean state) {
        mBinding.cbCompleteAgreement.setChecked(state);
    }

    @Override
    public void changeTOS(int index, boolean state) {
        mTermsConditions[index].setChecked(state);
    }

    @Override
    public void onResume() {
        super.onResume();
        for(CheckBox checkBox : mTermsConditions){
            checkBox.setChecked(false);
        }
        mBinding.cbCompleteAgreement.setChecked(false);
    }
}
