package com.dutch.hdh.dutchpayapp.data.service;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.data.db.User;
import com.dutch.hdh.dutchpayapp.data.db.UserInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    String URL = "http://kjg123kg.cafe24.com";

    @GET(Constants.USER_LOGIN_REQUEST_URL)
    Call<UserInfo> loginRequest(@Query("userEmail") String userEmail
            , @Query("userPassword") String userPassword);

}
