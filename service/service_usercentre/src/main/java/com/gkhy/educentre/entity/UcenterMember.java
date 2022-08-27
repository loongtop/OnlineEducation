package com.gkhy.educentre.entity;

import java.io.Serializable;

import com.gkhy.servicebase.DateModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <p>
 * Membership Form
 * </p>
 *
 * @author leo
 * @since 2022-7-21
 */
@Setter
@Getter
@Accessors(chain = true)
@ApiModel(value="UcenterMember object", description="Membership Form")
@Entity
public class UcenterMember extends DateModel implements Serializable {

    private static final long serialVersionUID = -1189345636731463168L;

    @Id
    @ApiModelProperty(value = "Membership ID")
    private String id;

    @ApiModelProperty(value = "Wechat openid")
    private String openid;

    @ApiModelProperty(value = "Phone")
    private String mobile;

    @ApiModelProperty(value = "Password")
    private String password;

    @ApiModelProperty(value = "nickname")
    private String nickname;

    @ApiModelProperty(value = "性别 1 女，2 男")
    private Integer sex;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "用户签名")
    private String sign;

    @ApiModelProperty(value = "是否禁用 1（true）已禁用，  0（false）未禁用")
    private Boolean isDisabled;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Boolean isDeleted;
}
