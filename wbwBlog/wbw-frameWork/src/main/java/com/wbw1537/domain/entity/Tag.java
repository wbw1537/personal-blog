package com.wbw1537.domain.entity;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


/**
 * 标签(Tag)表实体类
 *
 * @author makejava
 * @since 2023-11-09 11:11:33
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sg_tag")
public class Tag {

    private Long id;
//标签名
    private String name;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;
//删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;
//备注
    private String remark;


    /**
     * 获取主键值
     *
     * @return 主键值
     */

}

