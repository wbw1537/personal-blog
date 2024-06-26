package com.wbw1537.domain.entity;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


/**
 * 角色信息表(Role)表实体类
 *
 * @author makejava
 * @since 2023-11-09 17:29:21
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_role")
public class Role {
    @TableId(type = IdType.AUTO)
//角色ID
    private Long id;
//角色名称
    private String roleName;
//角色权限字符串
    private String roleKey;
//显示顺序
    private Integer roleSort;
//角色状态（0正常 1停用）
    private String status;
//删除标志（0代表存在 1代表删除）
    private String delFlag;
//创建者
    private Long createBy;
//创建时间
    private Date createTime;
//更新者
    private Long updateBy;
//更新时间
    private Date updateTime;
//备注
    private String remark;


    /**
     * 获取主键值
     *
     * @return 主键值
     */

}

