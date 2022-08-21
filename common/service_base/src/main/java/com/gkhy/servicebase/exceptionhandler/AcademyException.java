package com.gkhy.servicebase.exceptionhandler;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AcademyException extends RuntimeException {
    private Integer code;
    private String msg;
}
