package com.example.produitapi.config;

public abstract class SecurityConstants {

    public static final String JWT_KEY = "M@_Cl3F_D'3nCryP710n";
    public static final long EXPIRATION_TIME = 864000000; // 10jours
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_KEY = "Authorization";
    public static final String REGISTER_URL = "/user/sign_up";
    public static final String LOGIN_URL = "/user/sign_in";

}
