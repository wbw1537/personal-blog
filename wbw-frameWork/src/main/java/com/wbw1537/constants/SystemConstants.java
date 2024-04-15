package com.wbw1537.constants;

public class SystemConstants {
    /**
     * 文章是草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;
    /**
     * 文章是正常分布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;
    /*
     *   状态正常
     */
    public static final String STATUS_NORMAL = "0";
    /*
     *   友链状态为审核通过
     */
    public static final String LINK_STATUS_NORMAL = "0";

    /**
     * 评论类型为文章评论
     */
    public static final String ARTICLE_COMMENT = "0";
    /**
     * 评论类型为友链评论
     */
    public static final String LINK_COMMENT = "1";
    /**
     *  redis中文章浏览量的key
     */
    public static final String ARTICLE_VIEW_COUNT = "article:viewCount";
    /**
     *  redis中前台登录用户信息的key
     */
    public static final String BLOG_LOGIN = "bloglogin:";
    /**
     * redis中后台（admin）登录用户信息的key
     */
    public static final String ADMIN_LOGIN = "adminLogin:";
    /**
     *  系统中root用户的id
     */
    public static final Long ROOT_USER_ID = 1L;

    public static final Long NOT_LOGIN_ID = -1L;

    public static final String ADMIN = "1";

    /**
     * redis 中背景图片的key
     */
    public static final String BACK_GROUND_IMG = "Img:backGroundImg";
    /**
     * 菜单类型为“菜单”
     */
    public static final String MENU = "C";
    /**
     * 菜单类型为“按钮”
     */
    public static final String BUTTON = "F";
    /**
     * 菜单类型为“目录”
     */
    public static final String CATALOGUE = "M";
}