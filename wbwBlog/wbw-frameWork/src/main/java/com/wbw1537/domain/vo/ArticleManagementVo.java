package com.wbw1537.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleManagementVo {
    private Long id;
    //标题
    private String title;
    //文章摘要
    private String summary;
    private Date createTime;
}
