package com.gkhy.serviceoauth2.error;

public final class Oauth2Error {

    public static final String Invalid_signature = "Invalid JWT signature!";
    public static final String Invalid_JWT_token = "Invalid JWT token!";
    public static final String Expired_JWT_token = "Expired JWT token!";
    public static final String Unsupported_JWT_token = "Unsupported JWT token!";
    public static final String JWT_is_Empty = "JWT claims string is empty!";
    public static final String Set_Authentication_Failed = "Could not set user authentication in security context";
    public static final String Email_Already_Occupied = "Email address already occupied.";
    public static final String Email_Not_Find_From_OAuth2 = "Email not found from OAuth2 provider";
}
