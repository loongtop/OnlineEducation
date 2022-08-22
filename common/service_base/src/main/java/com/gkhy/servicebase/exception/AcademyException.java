package com.gkhy.servicebase.exception;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AcademyException extends RuntimeException {
    private Integer code;
    private String msg;
}
