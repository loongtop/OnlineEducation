package com.gkhy.aclservice.helper;

import com.gkhy.aclservice.entity.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  Build menu data from permission data
 * </p>
 *
 * @author leo
 * @since 2022-07-01
 */
public class PermissionHelper {

    /**
     * 使用递归方法建菜单
     * @param treeNodes :
     * @return
     */
    public static List<Permission> create(List<Permission> treeNodes) {
        List<Permission> trees = new ArrayList<>();
        for (Permission treeNode : treeNodes) {
            if ("0".equals(treeNode.getPid())) {
                treeNode.setLevel(1);
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }

    /**
     * Find child nodes recursively
     * @param treeNodes
     * @return
     */
    public static Permission findChildren(Permission treeNode,List<Permission> treeNodes) {
        treeNode.setChildren(new ArrayList<Permission>());

        for (Permission it : treeNodes) {
            if(treeNode.getId().equals(it.getPid())) {
                int level = treeNode.getLevel() + 1;
                it.setLevel(level);
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return treeNode;
    }
}
