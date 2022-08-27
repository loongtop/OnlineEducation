package com.gkhy.aclservice.entity;

import com.gkhy.servicebase.DateModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
public class PermissionEntity extends DateModel {

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
    private List<PermissionEntity> children;

    private boolean isSelect;

    private Boolean isDeleted;

}
