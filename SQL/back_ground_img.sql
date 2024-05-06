use wbw_blog;

DROP TABLE IF EXISTS `wbw_back_ground_img`;

CREATE TABLE `wbw_back_ground_img` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_by` bigint(20) DEFAULT NULL,
  `del_flag` int(1) DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  `content_url` varchar(512) DEFAULT NULL COMMENT '背景图片url',
  PRIMARY KEY (`id`)
  )ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COMMENT='背景图表';
