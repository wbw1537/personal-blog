package com.wbw1537.domain.entity;

import java.util.Date;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * 菜单权限表(Menu)表实体类
 *
 * @author makejava
 * @since 2023-11-09 17:21:44
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)//启用链式编程
@TableName("sys_menu")
public class Menu {
    @TableId(type = IdType.AUTO)
//菜单ID
    private Long id;
//菜单名称
    private String menuName;
//父菜单ID
    private Long parentId;
//显示顺序
    private Integer orderNum;
//路由地址
    private String path;
//组件路径
    private String component;
//是否为外链（0是 1否）
    private Integer isFrame;
//菜单类型（M目录 C菜单 F按钮）
    private String menuType;
//菜单状态（0显示 1隐藏）
    private String visible;
//菜单状态（0正常 1停用）
    private String status;
//权限标识
    private String perms;
//菜单图标
    private String icon;
//创建者
@TableField(fill = FieldFill.INSERT)
    private Long createBy;
//创建时间
@TableField(fill = FieldFill.INSERT)
    private Date createTime;
//更新者
@TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
//更新时间
@TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
//备注
    private String remark;

    private String delFlag;

    @TableField(exist = false)
    private List<Menu> children;

    /**
     * 获取主键值
     *
     * @return 主键值
     */

}

