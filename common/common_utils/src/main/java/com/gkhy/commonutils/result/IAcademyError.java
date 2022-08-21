package com.gkhy.commonutils.result;

import java.io.Serializable;

/***
 * Error code and error message definition class
 * @author leo
 */

public interface IAcademyError extends Serializable {
    int getCode();
    String getMessage();
}
