package com.gkhy.aclservice.entity;

import com.gkhy.servicebase.DateModel;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * User
 * </p>
 *
 * @author leo
 * @since 2022-07-12
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "acl_user")
public class UserEntity extends DateModel {

    private static final long serialVersionUID = -9025024233371566953L;

    @Id
    private Long id;

    private String username;

    private String password;

    private String nickName;

    private String salt;

    private String token;

    private Boolean isDeleted;

}

