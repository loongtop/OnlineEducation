package com.gkhy.educentre.error;

import com.gkhy.servicebase.error.IAcademyError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserCentreError implements IAcademyError {

    LOGIN_ERROR(20014,"Login failed, wrong username or password!"),
    REGISTER_ERROR(20015,"Registration failed"),
    ;

    private static final long serialVersionUID = -6662001959139322064L;

    private final int code;
    private final String message;
}
