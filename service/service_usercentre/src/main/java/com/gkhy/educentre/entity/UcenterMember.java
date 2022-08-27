package com.gkhy.educentre.entity;

import com.gkhy.servicebase.DateModel;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

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
    private String id;

    private String openid;

    private String mobile;

    private String password;

    private String nickname;

    private Integer sex;

    private Integer age;

    private String avatar;

    private String sign;

    private Boolean isDisabled;

    private Boolean isDeleted;
}
