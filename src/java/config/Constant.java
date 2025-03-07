/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

/**
 *
 * @author DAT
 */
public class Constant {

    public static final String CLIENT_ID = System.getenv("GOOGLE_CLIENT_ID");
    public static final String CLIENT_SECRET = System.getenv("GOOGLE_CLIENT_SECRET");
    public static final String REDIRECT_URL = "http://localhost:8080/dang-kiem-khi-thai/dang-nhap/oauth2/google";
    public static final String SCOPE = "https://www.googleapis.com/auth/userinfo.profile";
    public static final String GOOGLE_GRANT_TYPE = "authorization_code";
    public static final String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
    public static final String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
    public static final String GOOLE_LINK_TO_SIGN_UP = "https://accounts.google.com/v3/signin/identifier?btmpl=mobile_tier2&hl=vi&ifkv=ASSHykpnu9OqLnJJtEkqJ2By3O9JSp4duXJA4bdhYx5-tPSHyNzvDSyC7q0cuTL5_ltF7c__Q1UkhA&service=mail&flowName=GlifWebSignIn&flowEntry=ServiceLogin&dsh=S-1962649873%3A1741331990935790&ddm=1";
}
