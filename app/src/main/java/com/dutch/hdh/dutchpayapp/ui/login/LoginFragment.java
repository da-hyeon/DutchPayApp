package com.dutch.hdh.dutchpayapp.ui.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dutch.hdh.dutchpayapp.R;

public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Toast.makeText(getContext(), "????", Toast.LENGTH_SHORT).show();
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
}