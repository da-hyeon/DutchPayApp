package com.dutch.hdh.dutchpayapp.data.db;

public class UserInfo {
    private String userEmail;
    private String userPassword;
    private String userPaymentPassword;
    private String userName;
    private int userRN;
    private int userGender;
    private String userPhone;
    private int userMoney;

    public UserInfo(String userEmail, String userPassword, String userPaymentPassword, String userName, int userRN, int userGender, String userPhone, int userMoney) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userPaymentPassword = userPaymentPassword;
        this.userName = userName;
        this.userRN = userRN;
        this.userGender = userGender;
        this.userPhone = userPhone;
        this.userMoney = userMoney;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPaymentPassword() {
        return userPaymentPassword;
    }

    public void setUserPaymentPassword(String userPaymentPassword) {
        this.userPaymentPassword = userPaymentPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserRN() {
        return userRN;
    }

    public void setUserRN(int userRN) {
        this.userRN = userRN;
    }

    public int getUserGender() {
        return userGender;
    }

    public void setUserGender(int userGender) {
        this.userGender = userGender;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public int getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(int userMoney) {
        this.userMoney = userMoney;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userPaymentPassword='" + userPaymentPassword + '\'' +
                ", userName='" + userName + '\'' +
                ", userRN=" + userRN +
                ", userGender=" + userGender +
                ", userPhone='" + userPhone + '\'' +
                ", userMoney=" + userMoney +
                '}';
    }
}
