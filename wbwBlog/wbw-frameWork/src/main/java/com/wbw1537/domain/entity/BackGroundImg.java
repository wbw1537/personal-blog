package com.wbw1537.domain.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


/**
 * 背景图表(BackGroundImg)表实体类
 *
 * @author makejava
 * @since 2023-11-08 11:36:22
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("wbw_back_ground_img")
public class BackGroundImg {

    private Long id;

    private Long createBy;
//删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;
//背景图片url
    private String contentUrl;


    /**
     * 获取主键值
     *
     * @return 主键值
     */

}

