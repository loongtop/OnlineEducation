package com.gkhy.educentre.entity.form;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: leo
 * @create: 2022-08-01
 */

@Data
public class MemberForm implements Serializable {

    private String nickname;

    private String mobile;

    private String password;

    private String code;
}