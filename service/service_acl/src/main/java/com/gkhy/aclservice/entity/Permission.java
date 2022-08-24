package com.gkhy.aclservice.entity;

import com.gkhy.servicebase.DateModel;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

/**
 * <p>
 * Permission
 * </p>
 *
 * @author leo
 * @since 2022-07-01
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "acl_permission")
public class Permission extends DateModel {

    private static final long serialVersionUID = -4961118546104218207L;

    @Id
    private Long id;

    private Long pid;

    private String name;

    private Integer type;

    private String permissionValue;

    private String path;

    private String component;

    private String icon;

    private Integer status;

    private Integer level;
    @OneToMany
    private List<Permission> children;

    private boolean isSelect;

    private Boolean isDeleted;

}
