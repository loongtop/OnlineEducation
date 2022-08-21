package com.gkhy.servicebase.error;

import com.gkhy.commonutils.result.IAcademyError;
import lombok.AllArgsConstructor;
import lombok.Getter;

/***
 * response code enumeration
 *
 * Error code and error message definition class
 * 1. The error code definition rule is that 5 is a number
 * 2. The first two digits represent business scenarios, and the last three digits represent error codes. For example: 100001. 10: General 001: System unknown exception
 * 3. After maintaining the error code, you need to maintain the error description, and define them as an enumeration
 * Error code list:
 *
 * @author leo
 * @Since 2022-07-01
 */
@AllArgsConstructor
@Getter
public enum AcademyError implements IAcademyError {

//    UPLOAD_FILE_ERROR(20010,"File upload failed"),
//    FILE_EMPTY(20011, "The file is empty"),
//    DELETE_ERROR(20012,"Delete failed"),
//    VALID_CODE_SEND_FAIL(20013,"Send SMS verification code failed"),
//    LOGIN_ERROR(20014,"Login failed, wrong username or password!"),
//    REGISTER_ERROR(20015,"Registration failed"),
//    GET_PLAY_AUTH_FAIL(20016,"Failed to get video credentials"),
//    REMOTE_CALL_COURSE(20017,"Failed to query course information by remote call"),
//    REMOTE_CALL_MEMBER(20018,"Failed to call member module remotely"),
//    WECHAT_PAY_FAIL(20019,"WeChat payment failed"),
//    PARAMS_ERROR(20020,"Parameter verification failed"),
    ITEM_NOT_FOUND_ERROR(20021,"Item was not find in the database!"),
    ;

    private static final long serialVersionUID = -6662001959139322064L;

    private int code;
    private String message;
}
