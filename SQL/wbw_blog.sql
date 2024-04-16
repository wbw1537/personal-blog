-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: wbw_blog
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`wbw_blog` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `wbw_blog`;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `is_frame` int DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  `del_flag` char(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2035 DEFAULT CHARSET=utf8mb3 COMMENT='菜单权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'系统管理',0,1,'system',NULL,1,'M','0','0','','system',0,'2021-11-12 10:46:19',0,NULL,'系统管理目录','0'),(100,'用户管理',1,1,'user','system/user/index',1,'C','0','0','system:user:list','user',0,'2021-11-12 10:46:19',1,'2022-07-31 15:47:58','用户管理菜单','0'),(101,'角色管理',1,2,'role','system/role/index',1,'C','0','0','system:role:list','peoples',0,'2021-11-12 10:46:19',0,NULL,'角色管理菜单','0'),(102,'菜单管理',1,3,'menu','system/menu/index',1,'C','0','0','system:menu:list','tree-table',0,'2021-11-12 10:46:19',0,NULL,'菜单管理菜单','0'),(1001,'用户查询',100,1,'','',1,'F','0','0','system:user:query','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1002,'用户新增',100,2,'','',1,'F','0','0','system:user:add','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1003,'用户修改',100,3,'','',1,'F','0','0','system:user:edit','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1004,'用户删除',100,4,'','',1,'F','0','0','system:user:remove','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1005,'用户导出',100,5,'','',1,'F','0','0','system:user:export','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1006,'用户导入',100,6,'','',1,'F','0','0','system:user:import','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1007,'重置密码',100,7,'','',1,'F','0','0','system:user:resetPwd','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1008,'角色查询',101,1,'','',1,'F','0','0','system:role:query','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1009,'角色新增',101,2,'','',1,'F','0','0','system:role:add','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1010,'角色修改',101,3,'','',1,'F','0','0','system:role:edit','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1011,'角色删除',101,4,'','',1,'F','0','0','system:role:remove','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1012,'角色导出',101,5,'','',1,'F','0','0','system:role:export','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1013,'菜单查询',102,1,'','',1,'F','0','0','system:menu:query','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1014,'菜单新增',102,2,'','',1,'F','0','0','system:menu:add','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1015,'菜单修改',102,3,'','',1,'F','0','0','system:menu:edit','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1016,'菜单删除',102,4,'','',1,'F','0','0','system:menu:remove','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(2017,'内容管理',0,4,'content',NULL,1,'M','0','0',NULL,'table',NULL,'2022-01-08 02:44:38',1,'2022-07-31 12:34:23','','0'),(2018,'分类管理',2017,1,'category','content/category/index',1,'C','0','0','content:category:list','example',NULL,'2022-01-08 02:51:45',NULL,'2022-01-08 02:51:45','','0'),(2019,'文章管理',2017,0,'article','content/article/index',1,'C','0','0','content:article:list','build',NULL,'2022-01-08 02:53:10',NULL,'2022-01-08 02:53:10','','0'),(2021,'标签管理',2017,6,'tag','content/tag/index',1,'C','0','0','content:tag:index','button',NULL,'2022-01-08 02:55:37',NULL,'2022-01-08 02:55:50','','0'),(2022,'友链管理',2017,4,'link','content/link/index',1,'C','0','0','content:link:list','404',NULL,'2022-01-08 02:56:50',NULL,'2022-01-08 02:56:50','','0'),(2023,'写博文',0,0,'write','content/article/write/index',1,'C','0','0','content:article:writer','build',NULL,'2022-01-08 03:39:58',1,'2022-07-31 22:07:05','','0'),(2024,'友链新增',2022,0,'',NULL,1,'F','0','0','content:link:add','#',NULL,'2022-01-16 07:59:17',NULL,'2022-01-16 07:59:17','','0'),(2025,'友链修改',2022,1,'',NULL,1,'F','0','0','content:link:edit','#',NULL,'2022-01-16 07:59:44',NULL,'2022-01-16 07:59:44','','0'),(2026,'友链删除',2022,1,'',NULL,1,'F','0','0','content:link:remove','#',NULL,'2022-01-16 08:00:05',NULL,'2022-01-16 08:00:05','','0'),(2027,'友链查询',2022,2,'',NULL,1,'F','0','0','content:link:query','#',NULL,'2022-01-16 08:04:09',NULL,'2022-01-16 08:04:09','','0'),(2028,'导出分类',2018,1,'',NULL,1,'F','0','0','content:category:export','#',NULL,'2022-01-21 07:06:59',NULL,'2022-01-21 07:06:59','','0'),(2034,'111',2023,1,'/user',NULL,1,'M','0','0',NULL,'404',NULL,NULL,NULL,NULL,'','0');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3 COMMENT='角色信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'超级管理员','admin',1,'0','0',0,'2021-11-12 10:46:19',0,NULL,'超级管理员'),(2,'普通角色','common',2,'0','0',0,'2021-11-12 10:46:19',0,'2022-01-01 22:32:58','普通角色'),(11,'嘎嘎嘎','aggag',5,'0','0',NULL,'2022-01-06 14:07:40',NULL,'2022-01-07 03:48:48','嘎嘎嘎'),(12,'友链审核员','link',1,'0','0',NULL,'2022-01-16 06:49:30',NULL,'2022-01-16 08:05:09',NULL);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='角色和菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES (0,0),(2,1),(2,102),(2,1013),(2,1014),(2,1015),(2,1016),(2,2000),(3,2),(3,3),(3,4),(3,100),(3,101),(3,103),(3,104),(3,105),(3,106),(3,107),(3,108),(3,109),(3,110),(3,111),(3,112),(3,113),(3,114),(3,115),(3,116),(3,500),(3,501),(3,1001),(3,1002),(3,1003),(3,1004),(3,1005),(3,1006),(3,1007),(3,1008),(3,1009),(3,1010),(3,1011),(3,1012),(3,1017),(3,1018),(3,1019),(3,1020),(3,1021),(3,1022),(3,1023),(3,1024),(3,1025),(3,1026),(3,1027),(3,1028),(3,1029),(3,1030),(3,1031),(3,1032),(3,1033),(3,1034),(3,1035),(3,1036),(3,1037),(3,1038),(3,1039),(3,1040),(3,1041),(3,1042),(3,1043),(3,1044),(3,1045),(3,1046),(3,1047),(3,1048),(3,1049),(3,1050),(3,1051),(3,1052),(3,1053),(3,1054),(3,1055),(3,1056),(3,1057),(3,1058),(3,1059),(3,1060),(3,2000),(11,1),(11,100),(11,101),(11,102),(11,103),(11,104),(11,105),(11,106),(11,107),(11,108),(11,500),(11,501),(11,1001),(11,1002),(11,1003),(11,1004),(11,1005),(11,1006),(11,1007),(11,1008),(11,1009),(11,1010),(11,1011),(11,1012),(11,1013),(11,1014),(11,1015),(11,1016),(11,1017),(11,1018),(11,1019),(11,1020),(11,1021),(11,1022),(11,1023),(11,1024),(11,1025),(11,1026),(11,1027),(11,1028),(11,1029),(11,1030),(11,1031),(11,1032),(11,1033),(11,1034),(11,1035),(11,1036),(11,1037),(11,1038),(11,1039),(11,1040),(11,1041),(11,1042),(11,1043),(11,1044),(11,1045),(11,2000),(11,2003),(11,2004),(11,2005),(11,2006),(11,2007),(11,2008),(11,2009),(11,2010),(11,2011),(11,2012),(11,2013),(11,2014),(12,2017),(12,2022),(12,2024),(12,2025),(12,2026),(12,2027);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '用户名',
  `nick_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '昵称',
  `password` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '密码',
  `type` char(1) DEFAULT '0' COMMENT '用户类型：0代表普通用户，1代表管理员',
  `status` char(1) DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `phonenumber` varchar(32) DEFAULT NULL COMMENT '手机号',
  `sex` char(1) DEFAULT NULL COMMENT '用户性别（0男，1女，2未知）',
  `avatar` varchar(128) DEFAULT NULL COMMENT '头像',
  `create_by` bigint DEFAULT NULL COMMENT '创建人的用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'sg','wbw1537','$2a$10$Jnq31rRkNV3RNzXe0REsEOSKaYK8UgVZZqlNlNXqn.JeVcj2NdeZy','1','0','23412332@qq.com','18888888888','0','http://s3ls1x3d1.hd-bkt.clouddn.com/2023/11/13/3e261340911b462eb7ffc14b57956792.png',NULL,'2022-01-05 09:01:56',1,'2022-01-30 15:37:03',0),(3,'sg3','weqe','$2a$10$ydv3rLkteFnRx9xelQ7elOiVhFvXOooA98xCqk/omh7G94R.K/E3O','1','0',NULL,NULL,'0',NULL,NULL,'2022-01-05 13:28:43',NULL,'2022-01-05 13:28:43',0),(4,'sg2','dsadd','$2a$10$kY4T3SN7i4muBccZppd2OOkhxMN6yt8tND1sF89hXOaFylhY2T3he','1','0','23412332@qq.com','19098790742','0',NULL,NULL,NULL,NULL,NULL,0),(5,'sg2233','tteqe','','1','0',NULL,'18246845873','1',NULL,NULL,'2022-01-06 03:51:13',NULL,'2022-01-06 07:00:50',0),(6,'sangeng','sangeng','$2a$10$Jnq31rRkNV3RNzXe0REsEOSKaYK8UgVZZqlNlNXqn.JeVcj2NdeZy','1','0','2312321','17777777777','0',NULL,NULL,'2022-01-16 06:54:26',NULL,'2022-01-16 07:06:34',0),(11,'paper','paper','$2a$10$rf.e7M0Lp3A9JyVB9C/TO.MWkwNgutgfVBu/DjRW1Mkco6URiDur.','0','0','ncai9@uwo.ca',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(12,'mingjiyue','mingjiyue','$2a$10$UVc799SGbe2VEFROP5ekqeZAKQrKHVgZovEcMBWmoLJID138PYQlq','0','0','mingjiyue321@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_role` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户和角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (1,1),(2,2),(5,2),(6,12);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wbw_article`
--

DROP TABLE IF EXISTS `wbw_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wbw_article` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(256) DEFAULT NULL COMMENT '标题',
  `content` longtext COMMENT '文章内容',
  `summary` varchar(1024) DEFAULT NULL COMMENT '文章摘要',
  `category_id` bigint DEFAULT NULL COMMENT '所属分类id',
  `thumbnail` varchar(256) DEFAULT NULL COMMENT '缩略图',
  `is_top` char(1) DEFAULT '0' COMMENT '是否置顶（0否，1是）',
  `status` char(1) DEFAULT '1' COMMENT '状态（0已发布，1草稿）',
  `view_count` bigint DEFAULT '0' COMMENT '访问量',
  `is_comment` char(1) DEFAULT '1' COMMENT '是否允许评论 1是，0否',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wbw_article`
--

LOCK TABLES `wbw_article` WRITE;
/*!40000 ALTER TABLE `wbw_article` DISABLE KEYS */;
INSERT INTO `wbw_article` VALUES (1,'test','test','test',1,'','1','0',0,'0',1,'2023-11-11 21:04:37',NULL,'2023-11-11 21:10:00',1),(2,'前台博客接口文档','# 前台博客接口文档\n\n\n## 1 ArticleController\n\n### 1.1 获取热门文章接口\n#### 1.1.1 功能需求\n查询浏览量最高的前10篇文章的信息。需要展示文章标题和浏览量，使用户能自己点击跳转到具体的文章详情进行浏览。\n\n注：草稿以及已删除文章不能被查询出来，要按照浏览量进行降序排序。\n#### 1.1.2 接口设计\n| 请求方法 | 请求路径      |\n| -------- | ------------- |\n| GET方法      | /article/hotArticleList |\n#### 1.1.3 请求参数\n此方法为无参方法\n\n#### 1.1.4 相应格式\n~~~~json\n{\n  \"code\": 200,\n  \"data\": [\n    {\n      \"id\": \"1\",\n      \"title\": \"SpringSecurity从入门到精通\",\n      \"viewCount\": \"115\"\n    },\n    {\n      \"id\": \"5\",\n      \"title\": \"test\",\n      \"viewCount\": \"44\"\n    }\n  ],\n  \"msg\": \"操作成功\"\n}\n~~~~\n#### 1.1.5 代码实现\n~~~~java\n    @Override\n    public ResponseResult hotArticleList() {\n        // 查询热门文章 封装成responseResult返回\n        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();\n        // 要求为正式文章\n        queryWrapper.eq(Article::getStatus,ARTICLE_STATUS_NORMAL);\n        // 降序排序\n        queryWrapper.orderByDesc(Article::getViewCount);\n        // 限制数量（10条）\n        Page<Article> page = new Page<>(1,10);\n        page(page,queryWrapper);\n        List<Article> articles = page.getRecords();\n        // 获取redis中对应id的浏览量并赋值\n        for(Article article : articles){\n            article.setViewCount(getArticleViewCountById(article.getId()).longValue());\n        }\n        // bean拷贝\n        List<HotArticleVo> hotArticleVoList = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);\n        return ResponseResult.okResult(hotArticleVoList);\n    }\n~~~~\n### 1.2 分页获取文章列表\n#### 1.2.1 功能需求\n​	在首页或分类页面查询文章列表。\n\n​	首页：查询所有的文章\n\n​	分类页面：查询对应分类下的文章\n\n​	要求：①只能查询正式发布的文章 ②置顶的文章要显示在最前面 \n#### 1.2.2 接口设计\n|请求方法|请求路径|\n|------|-----|\n|GET方法|/article/articleList|\n#### 1.2.3 请求参数\n| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | \n| -------- | -------- | ----- | -------- | -------- | \n|categoryId|文章分类id|query|false|integer(int64)|\n|pageNum|分页的页数|query|false|integer(int32)|\n|pageSize|分页的数量|query|false|integer(int32)|\n#### 1.2.4 响应格式\n~~~~json\n{\n  \"code\": 200,\n  \"data\": {\n    \"rows\": [\n      {\n        \"categoryName\": \"java\",\n        \"createTime\": \"2022-01-23 23:20:11\",\n        \"id\": \"1\",\n        \"summary\": \"SpringSecurity教程\",\n        \"thumbnail\": \"https://xxxx/xxx/xx.png\",\n        \"title\": \"SpringSecurity\",\n        \"viewCount\": \"115\"\n      }\n    ],\n    \"total\": \"1\"\n  },\n  \"msg\": \"操作成功\"\n}\n~~~~\n#### 1.2.5 代码实现\n~~~~java\n    @Override\n    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {\n        //查询条件:\n        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();\n        //categoryId是否有参\n        lambdaQueryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0,Article::getCategoryId,categoryId);\n        //查询文章是否正式发布\n        lambdaQueryWrapper.eq(Article::getStatus, ARTICLE_STATUS_NORMAL);\n        //筛选置顶文章（对isTop降序排序）\n        lambdaQueryWrapper.orderByDesc(Article::getIsTop);\n        //分页查询\n        Page<Article> page = new Page<>(pageNum,pageSize);\n        page(page,lambdaQueryWrapper);\n        //查询categoryName（分类名称）\n\n        //采用for循环查询\n        List<Article> articles = page.getRecords();\n        //用categoryID查询categoryName\n        for (Article article : articles){\n            Category category = categoryMapper.selectById(article.getCategoryId());\n            article.setCategoryName(category.getName());\n            //获取redis中对应id的浏览量并赋值\n            article.setViewCount(getArticleViewCountById(article.getId()).longValue());\n        }\n        //采用stream流查询\n//        List<Article> articles = page.getRecords();\n//        articles.stream()\n//                .map(article -> article.setCategoryName(categoryMapper.selectById(article.getCategoryId()).getName()))\n//                .collect(Collectors.toList());\n        //封装查询结果\n        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);\n\n        PageVo pageVo = new PageVo(articleListVos, page.getTotal());\n        return ResponseResult.okResult(pageVo);\n    }\n~~~~\n~~~~java\npublic Integer getArticleViewCountById(Long id){\n        //获取redis中对应id的浏览量\n        return redisCache.getCacheMapValue(SystemConstants.ARTICLE_VIEW_COUNT,id.toString());\n    } \n~~~~\n### 1.3 更新浏览量接口\n#### 1.3.1 功能需求\n在访问文章页面时更新文章浏览量，需要将浏览量更新到redis中，再定时同步redis与数据库内的数据。\n#### 1.3.2 接口设计\n|请求方法|请求路径|\n|---|---|\n|PUT|/article/updateViewCount/{id}|\n\n#### 1.3.3 请求参数\n| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | \n| -------- | -------- | ----- | -------- | -------- | \n|id|需要更新浏览量文章的id|path|true|integer(int64)|\n#### 1.3.4 响应格式\n~~~~json\n{\n  \"code\": 200,\n  \"msg\": \"操作成功\"\n}\n~~~~\n#### 1.3.5 代码实现\n~~~~java\n	@Override\n    public ResponseResult updateViewCount(Long id) {\n        //更新redis中对应id的浏览量\n        redisCache.incrementCacheMapValue(SystemConstants.ARTICLE_VIEW_COUNT,id.toString(), 1L);\n        return ResponseResult.okResult();\n    }\n~~~~\n~~~~java\n	public void incrementCacheMapValue(String key,String hKey,Long v){\n        redisTemplate.boundHashOps(key).increment(hKey,v);\n    }\n~~~~\n~~~~java\n    @Scheduled(cron = \"0 0/10 * * * ?\") //\n    public void updateNewCount(){\n        // 获取redis中的浏览量\n        Map<String, Integer> viewCountMap = redisCache.getCacheMap(SystemConstants.ARTICLE_VIEW_COUNT);\n        List<Article> articles = viewCountMap.entrySet()\n                .stream()\n                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))\n                .collect(Collectors.toList());\n        articleService.updateBatchById(articles);\n    }\n~~~~\n~~~~java\n@Component\npublic class ViewCountRunner implements CommandLineRunner {\n    @Autowired\n    private ArticleMapper articleMapper;\n    @Autowired\n    private RedisCache redisCache;\n    @Override\n    public void run(String... args) throws Exception {\n        // 查询博客信息\n        List<Article> articleList = articleMapper.selectList(null);\n        Map<String, Integer> viewCount = articleList.stream().\n                collect(Collectors.toMap(article -> article.getId().toString(),\n                        article -> article.getViewCount().intValue()));\n        // 存储到redis中\n        redisCache.setCacheMap(SystemConstants.ARTICLE_VIEW_COUNT,viewCount);\n    }\n}\n~~~~\n### 1.4 获取文章详情接口\n#### 1.4.1 功能需求\n在文章列表点击全文时访问文章详情页面，可以让用户阅读文章正文\n#### 1.4.2 接口设计\n|请求方法|请求路径|\n|---|---|\n|GET方法|/article/{id}|\n#### 1.4.3 请求参数\n\n\n| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |\n| -------- | -------- | ----- | -------- | -------- | ------ |\n|id|需要获取的文章的id|path|true|integer(int64)||\n\n\n#### 1.4.4 响应格式\n~~~~json\n{\n  \"code\": 200,\n  \"data\": {\n    \"categoryId\": \"1\",\n    \"categoryName\": \"java\",\n    \"content\": \"内容\",\n    \"createTime\": \"2022-01-23 23:20:11\",\n    \"id\": \"1\",\n    \"summary\": \"SpringSecurity框架教程-Spring Security+JWT实现项目级前端分离认证授权\",\n    \"thumbnail\": \"https://xxx/xxx/xxx.png\",\n    \"title\": \"SpringSecurity\",\n    \"viewCount\": \"116\"\n  },\n  \"msg\": \"操作成功\"\n}\n~~~~\n#### 1.4.5 代码实现\n~~~~java\n    @Override\n    public ResponseResult getArticleDetail(Long id) {\n        //根据id查询文章内容\n        Article article = getById(id);\n        //从redis中获取viewCount\n        article.setViewCount(getArticleViewCountById(id).longValue());\n        //转换成vo\n        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article,ArticleDetailVo.class);\n        //根据分类id查询分类名称\n        Long categoryId = articleDetailVo.getCategoryId();\n        Category category = categoryMapper.selectById(categoryId);\n        if(category != null){\n            articleDetailVo.setCategoryName(category.getName());\n        }\n        //封装查询结果\n        return ResponseResult.okResult(articleDetailVo);\n    }\n~~~~\n## 2 LinkController\n### 2.1 获取友链接口\n#### 2.1.1 功能需求\n在友链页面要查询出所有的审核通过的友链。\n#### 2.1.2 接口设计\n| 请求方法 | 请求路径         |\n| -------- | ---------------- |\n| GET方法      | /link/getAllLink |\n#### 2.1.3 请求参数\n此方法为无参请求\n#### 2.1.4 响应格式\n~~~~json\n{\n  \"code\": 200,\n  \"data\": [\n    {\n      \"address\": \"https://www.baidu.com\",\n      \"description\": \"sda\",\n      \"id\": \"1\",\n      \"logo\": \"url\",\n      \"name\": \"sda\"\n    },\n    {\n      \"address\": \"https://www.qq.com\",\n      \"description\": \"dada\",\n      \"id\": \"2\",\n      \"logo\": \"url\",\n      \"name\": \"sda\"\n    },\n  ],\n  \"msg\": \"操作成功\"\n}\n~~~~\n#### 2.1.5 代码实现\n~~~~java\n    @Override\n    public ResponseResult getAllLink() {\n        //查询所以审核通过的链接\n        LambdaQueryWrapper<Link> lambdaQueryWrapper = new LambdaQueryWrapper<>();\n        lambdaQueryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);\n        List<Link> links = list(lambdaQueryWrapper);\n        //转换vo\n        List<LinkVo> linkVo = BeanCopyUtils.copyBeanList(links, LinkVo.class);\n        //封装返回\n        return ResponseResult.okResult(linkVo);\n    }\n~~~~\n## 3 BlogLoginController\n### 3.1 前台博客登录接口\n#### 3.1.1 功能需求\n使用SpringSecurity实现登录、校验和授权，返回用户对应的token。\n\n部分页面需要登陆后才能展示\n\n登录：\n\n①自定义登录接口  \n* 调用ProviderManager的方法进行认证 如果认证通过生成jwt\n* 把用户信息存入redis中\n\n②自定义UserDetailsService \n* 在这个实现类中去查询数据库\n* 注意配置passwordEncoder为BCryptPasswordEncoder\n\n校验：\n\n①定义Jwt认证过滤器\n* 获取token\n* 解析token获取其中的userid\n* 从redis中获取用户信息\n* 存入SecurityContextHolder\n#### 3.1.2 接口设计\n|请求方法|请求路径|请求头|\n|---|---|---|\n|POST方法|/login|不需要token|\n#### 3.1.3 请求参数\n\n```json\n{\n  \"userName\": \"test\",\n  \"password\": \"1234\"\n}\n```\n\n| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |\n| -------- | -------- | ----- | -------- | -------- | ------ |\n|user|user|body|true|User|User|\n|&emsp;&emsp;userName|用户名称（用于登录）||false|string||\n|&emsp;&emsp;password|||false|string||\n\n#### 3.1.4 响应格式\n~~~~json\n{\n  \"code\": 200,\n  \"data\": {\n    \"token\": \"用户对应的token\",\n    \"userInfo\": {\n      \"avatar\": \"用户头像对应url\",\n      \"email\": \"xxxx@qq.com\",\n      \"id\": \"1\",\n      \"nickName\": \"wbw1537\",\n      \"sex\": \"0\"\n    }\n  },\n  \"msg\": \"操作成功\"\n}\n~~~~\n#### 3.1.5 代码实现\n~~~~java\n    @Override\n    public ResponseResult login(User user) {\n        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());\n        // 调用UserDetailsService\n        Authentication authenticate = authenticationManager.authenticate(authenticationToken);\n        // 判断是否认证通过\n        if (Objects.isNull(authenticate)) {\n            throw SystemException(AppHttpCodeEnum.LOGIN_ERROR);\n        }\n        //获取userId生成token\n        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();\n        String userId = loginUser.getUser().getId().toString();\n        String jwt = JwtUtil.createJWT(userId);\n        // 把用户信息存入redis\n        redisCache.setCacheObject(BLOG_LOGIN + userId, loginUser);\n\n        // 将user bean拷贝为userInfoVo\n        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);\n        // 将token和userInfo封装返回给前端\n        BlogUserLoginVo vo = new BlogUserLoginVo(jwt, userInfoVo);\n        return ResponseResult.okResult(vo);\n    }\n~~~~\n~~~~java\n    @Override\n    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {\n        // 根据用户名查询用户信息\n        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();\n        queryWrapper.eq(User::getUserName,username);\n        User user = userMapper.selectOne(queryWrapper);\n        // 判断是否查到用户，没查到抛出异常\n        if(Objects.isNull(user)){\n            throw new RuntimeException(\"用户不存在\");\n        }\n        // 返回用户信息\n        // TODO:查询权限信息封装\n        return new LoginUser(user);\n    }\n~~~~\n~~~~java\n@Override\n    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {\n        // 获取请求头中的token\n        String token = request.getHeader(\"token\");\n        if (!StringUtils.hasText(token)) {\n            // 说明该接口不需要登录  直接放行\n            filterChain.doFilter(request, response);\n            return;\n        }\n        //解析获取userId\n        Claims claims = null;\n        try {\n            claims = JwtUtil.parseJWT(token);\n        } catch (Exception e) {\n            e.printStackTrace();\n            // token超时、token非法\n            //响应前端需要重新登陆\n            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);\n            WebUtils.renderString(response, JSON.toJSONString(result));\n            return;\n        }\n        String userId = claims.getSubject();\n        //从redis中获取用户信息\n        LoginUser loginUser = redisCache.getCacheObject(BLOG_LOGIN + userId);\n        //如果获取不到\n        if(Objects.isNull(loginUser)){\n            //说明登陆过期 提示重新登录\n            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);\n            WebUtils.renderString(response, JSON.toJSONString(result));\n            return;\n        }\n        //存入SecurityContextHolder\n        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,null);\n        SecurityContextHolder.getContext().setAuthentication(authenticationToken);\n        filterChain.doFilter(request,response);\n    }\n~~~~\n### 3.2 退出登录接口\n\n####  3.2.1 功能需求\n\n删除redis中用户的数据，实现退出登录\n\n#### 3.2.2 接口设计\n\n| 请求方法 | 请求地址 | 请求头    |\n| -------- | -------- | --------- |\n| POST方法 | /logout  | 需要token |\n\n#### 3.2.3 请求参数\n\n此方法为无参方法\n\n#### 3.2.4 响应格式\n\n~~~~json\n{\n    \"code\": 200,\n    \"msg\": \"操作成功\"\n}\n~~~~\n\n#### 3.2.5 代码实现\n\n~~~~java\n    @Override\n    public ResponseResult logout() {\n        //获取userId\n        Long userId = SecurityUtils.getUserId();\n        //删除redis中的用户信息\n        redisCache.deleteObject(BLOG_LOGIN + userId);\n\n        return ResponseResult.okResult();\n    }\n~~~~\n\n## 4 CommentController\n\n### 4.1 查询文章评论列表接口\n\n#### 4.1.1 功能需求\n\n根据需求进行评论的查询。该查询为分页查询\n\n评论分为文章评论和友链评论，根据文章id和type字段查询文章评论\n\n文章评论也分为根评论和子评论，返回数据时需要将对应根评论的子评论一并返回\n\n#### 4.1.2 接口设计\n\n| 请求方法 | 请求地址             | 请求头      |\n| -------- | -------------------- | ----------- |\n| GET方法  | /comment/commentList | 不需要token |\n\n#### 4.1.3 请求参数\n\n\n| 参数名称  | 参数说明                 | 请求类型 | 是否必须 | 数据类型       |\n| --------- | ------------------------ | -------- | -------- | -------------- |\n| articleId | 需要查询评论的文章对应id | query    | false    | integer(int64) |\n| pageNum   | 页码                     | query    | false    | integer(int32) |\n| pageSize  | 每页的条数               | query    | false    | integer(int32) |\n\n#### 4.1.4 响应格式\n\n~~~~json\n{\n    \"code\": 200,\n    \"data\": {\n        \"rows\": [\n            {\n                \"articleId\": \"1\",\n                \"children\": [\n                    {\n                        \"articleId\": \"1\",\n                        \"content\": \"你说啥？\",\n                        \"createBy\": \"1\",\n                        \"createTime\": \"2023-11-09 10:06:21\",\n                        \"id\": \"20\",\n                        \"rootId\": \"1\",\n                        \"toCommentId\": \"1\",\n                        \"toCommentUserId\": \"1\",\n                        \"toCommentUserName\": \"wbw\",\n                        \"username\": \"wbw\",\n                        \"avatar\": \"http://s3ls1x3d1.hd-bkt.clouddn.com/2023/11/05/571a45cdca4b487ba7007f08d36239c9.png\"\n                    }\n                ],\n                \"content\": \"asS\",\n                \"createBy\": \"1\",\n                \"createTime\": \"2023-11-09 07:59:22\",\n                \"id\": \"1\",\n                \"rootId\": \"-1\",\n                \"toCommentId\": \"-1\",\n                \"toCommentUserId\": \"-1\",\n                \"username\": \"wbw\",\n                \"avatar\": \"http://s3ls1x3d1.hd-bkt.clouddn.com/2023/11/05/571a45cdca4b487ba7007f08d36239c9.png\"\n            }\n        ],\n        \"total\": \"15\"\n    },\n    \"msg\": \"操作成功\"\n}\n~~~~\n\n#### 4.1.5 代码实现\n\n~~~~java\n    @Override\n    public ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {\n        // 查询对应文章的根评论\n        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();\n        // 对articleId进行判断(如果commentType为ARTICLE_COMMENT，说明是文章评论，需要根据articleId查询)\n        queryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType),Comment::getArticleId, articleId);\n        // 根评论 rootId为-1\n        queryWrapper.eq(Comment::getRootId, -1);\n        queryWrapper.orderByAsc(Comment::getCreateTime);\n        // 评论类型\n        queryWrapper.eq(Comment::getType, commentType);\n        // 分页查询\n        Page<Comment> page = new Page<>(pageNum, pageSize);\n        page = page(page, queryWrapper);\n        List<CommentVo> commentVoList = toCommentVoList(page.getRecords());\n        // 查询所有根根评论对应的子评论的集合，并且赋值给对应的属性\n        for (CommentVo commentVo : commentVoList) {\n            // 查询对应的子评论\n            List<CommentVo> children = getChildren(commentVo.getId());\n            // 赋值\n            commentVo.setChildren(children);\n\n        }\n        return ResponseResult.okResult(new PageVo(commentVoList, page.getTotal()));\n    }\n~~~~\n\n~~~~java\nprivate List<CommentVo> getChildren(Long id) {\n        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();\n        queryWrapper.eq(Comment::getRootId, id);\n        queryWrapper.orderByAsc(Comment::getCreateTime);\n        List<Comment> commentList = list(queryWrapper);\n        List<CommentVo> commentVoList = toCommentVoList(commentList);\n        return commentVoList;\n    }\n~~~~\n\n~~~~java\n    //将commentList转换为commentVoList\n    private List<CommentVo> toCommentVoList(List<Comment> commentList) {\n        List<CommentVo> commentVoList = BeanCopyUtils.copyBeanList(commentList, CommentVo.class);\n        for (CommentVo commentVo : commentVoList) {\n            User user = userService.getById(commentVo.getCreateBy());\n            //通过查询creatBy查询用户名称并赋值\n            String nickName = user.getNickName();\n            commentVo.setNickName(nickName);\n            //通过查询creatBy查询用户头像并赋值\n            String avatar = user.getAvatar();\n            commentVo.setAvatar(avatar);\n            //如果toCommentUserId不为-1，说明是回复评论，需要查询回复的用户名称并赋值\n            //通过toCommentUserId查询用户的名称并赋值\n            if (commentVo.getToCommentUserId() != -1) {\n                String toCommentUserName = userService.getById(commentVo.getToCommentUserId()).getNickName();\n                commentVo.setToCommentUserName(toCommentUserName);\n            }\n        }\n        return commentVoList;\n    }\n~~~~\n\n### 4.2 发表文章评论接口\n\n#### 4.2.1 功能需求\n\n用户登录后可以对文章发表评论，也可以对评论进行回复。\n\n用户登录后也可以在友链页面进行评论。\n\n#### 4.2.2 接口设计\n\n| 请求方法 | 请求地址 | 请求头      |\n| -------- | -------- | ----------- |\n| POST方法 | /comment | 需要token头 |\n\n#### 4.2.3 请求参数\n\n\n| 参数名称                    | 参数说明             | 请求类型 | 是否必须 | 数据类型       |\n| --------------------------- | -------------------- | -------- | -------- | -------------- |\n| comment                     | comment              | body     | true     | Comment        |\n| &emsp;&emsp;articleId       | 回复文章的id         |          | false    | integer(int64) |\n| &emsp;&emsp;content         | 评论内容             |          | false    | string         |\n| &emsp;&emsp;rootId          | 是否为根评论         |          | false    | integer(int64) |\n| &emsp;&emsp;toCommentId     | 回复的评论的id       |          | false    | integer(int64) |\n| &emsp;&emsp;toCommentUserId | 回复的评论的用户的id |          | false    | integer(int64) |\n| &emsp;&emsp;type            | 评论类型             |          | false    | string         |\n\n回复了文章：\n\n~~~~json\n{\n    \"articleId\":1,\n    \"type\":0,\n    \"rootId\":-1,\n    \"toCommentId\":-1,\n    \"toCommentUserId\":-1,\n    \"content\":\"评论了文章\"\n}\n~~~~\n回复了某条评论：\n~~~~json\n{\n    \"articleId\":1,\n    \"type\":0,\n    \"rootId\":\"3\",\n    \"toCommentId\":\"3\",\n    \"toCommentUserId\":\"1\",\n    \"content\":\"回复了某条评论\"\n}\n~~~~\n\n#### 4.2.4 响应格式\n\n~~~~json\n{\n	\"code\":200,\n	\"msg\":\"操作成功\"\n}\n~~~~\n\n#### 4.2.5 代码实现\n\n~~~~java\n@Override\n    public ResponseResult addComment(Comment comment) {\n        // 评论内容不能为空\n        if (!StringUtils.hasText(comment.getContent())) {\n            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);\n        }\n        // TODO: 可以添加敏感词检测类似功能\n        save(comment);\n        return ResponseResult.okResult();\n    }\n~~~~\n\n### 4.3 获取友链页面评论接口\n\n#### 4.3.1 功能需求\n\n查询符合条件为友链评论的评论列表\n\n#### 4.3.2 接口设计\n\n| 请求方法 | 请求地址                 | 请求头      |\n| -------- | ------------------------ | ----------- |\n| GET方法  | /comment/linkCommentList | 不需要token |\n\n#### 4.3.3 请求参数\n\n#### 4.3.4 响应格式\n\n~~~~json\n{\n    \"code\": 200,\n    \"data\": {\n        \"rows\": [\n            {\n                \"articleId\": \"1\",\n                \"children\": [\n                    {\n                        \"articleId\": \"1\",\n                        \"content\": \"回复友链评论3\",\n                        \"createBy\": \"1\",\n                        \"createTime\": \"2022-01-30 10:08:50\",\n                        \"id\": \"23\",\n                        \"rootId\": \"22\",\n                        \"toCommentId\": \"22\",\n                        \"toCommentUserId\": \"1\",\n                        \"toCommentUserName\": \"sg333\",\n                        \"username\": \"sg333\"\n                    }\n                ],\n                \"content\": \"友链评论2\",\n                \"createBy\": \"1\",\n                \"createTime\": \"2022-01-30 10:08:28\",\n                \"id\": \"22\",\n                \"rootId\": \"-1\",\n                \"toCommentId\": \"-1\",\n                \"toCommentUserId\": \"-1\",\n                \"username\": \"sg333\"\n            }\n        ],\n        \"total\": \"1\"\n    },\n    \"msg\": \"操作成功\"\n}\n~~~~\n\n#### 4.3.5 代码实现\n\n~~~~java\n// 友链评论\n    @GetMapping(\"/linkCommentList\")\n    public ResponseResult linkCommentList(Integer pageNum, Integer pageSize) {\n        return commentService.commentList(SystemConstants.LINK_COMMENT,null, pageNum, pageSize);\n    }\n~~~~\n\n~~~~java\n    // 复用代码\n	@Override\n    public ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {\n        // 查询对应文章的根评论\n        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();\n        // 对articleId进行判断(如果commentType为ARTICLE_COMMENT，说明是文章评论，需要根据articleId查询)\n        queryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType),Comment::getArticleId, articleId);\n        // 根评论 rootId为-1\n        queryWrapper.eq(Comment::getRootId, -1);\n        queryWrapper.orderByAsc(Comment::getCreateTime);\n        // 评论类型\n        queryWrapper.eq(Comment::getType, commentType);\n        // 分页查询\n        Page<Comment> page = new Page<>(pageNum, pageSize);\n        page = page(page, queryWrapper);\n        List<CommentVo> commentVoList = toCommentVoList(page.getRecords());\n        // 查询所有根根评论对应的子评论的集合，并且赋值给对应的属性\n        for (CommentVo commentVo : commentVoList) {\n            // 查询对应的子评论\n            List<CommentVo> children = getChildren(commentVo.getId());\n            // 赋值\n            commentVo.setChildren(children);\n\n        }\n        return ResponseResult.okResult(new PageVo(commentVoList, page.getTotal()));\n    }\n~~~~\n\n## 5 UserController\n\n### 5.1 个人信息查询接口\n\n#### 5.1.1 功能需求\n\n进入个人中心的时候需要能够查看当前用户信息\n\n#### 5.1.2 接口设计\n\n| 请求方法 | 请求地址       | 请求头    |\n| -------- | -------------- | --------- |\n| GET方法  | /user/userInfo | 需要token |\n\n#### 5.1.3 请求参数\n\n此方法为无参方法\n\n#### 5.1.4 响应格式\n\n```javascript\n{\n	\"code\": 0,\n	\"data\": {},\n	\"msg\": \"\"\n}\n```\n\n#### 5.1.5 代码实现\n\n~~~~java\n@Override\n    public ResponseResult userInfo() {\n        // 获取当前用户id\n        Long userId = SecurityUtils.getUserId();\n        // 根据id查询用户信息\n        User user = getById(userId);\n        // 封装成userInfoVo\n        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);\n        return ResponseResult.okResult(userInfoVo);\n    }\n~~~~\n\n### 5.2 头像上传接口\n\n#### 5.2.1 功能需求\n\n将从前端接受的头像上传到oss\n\n#### 5.2.2 接口设计\n\n| 请求方法 | 请求地址 | 请求头    |\n| -------- | -------- | --------- |\n| POST方法 | /upload  | 需要token |\n\n#### 5.2.3 请求参数\n\n| 参数名称 | 参数说明           | 请求类型 | 是否必须 | 数据类型 |\n| -------- | ------------------ | -------- | -------- | -------- |\n| img      | 前端返回的图像文件 | formData | false    | file     |\n\n#### 5.2.4 响应格式\n\n~~~~json\n{\n	\"code\":200,\n    \"data\": \"文件访问链接\",\n	\"msg\":\"操作成功\"\n}\n~~~~\n\n#### 5.2.5 代码实现\n\n~~~~java\n@Service\n@Data\n@ConfigurationProperties(prefix = \"oss\")\npublic class OssUploadService implements UploadService {\n    @Override\n    public ResponseResult uploadImg(MultipartFile img) {\n        // TODO:判断文件类型或者文件大小\n        // 获取原始文件名\n        String originalFilename = img.getOriginalFilename();\n        // 对文件名进行判断\n        if ((!originalFilename.endsWith(\".png\") && !originalFilename.endsWith(\".jpg\") && !originalFilename.endsWith(\".jpeg\"))) {\n        //if(!originalFilename.endsWith(\".png\")){\n            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);\n        }\n        // 如果判断通过，上传文件到oss\n        String generateFileName = PathUtils.generateFilePath(originalFilename);\n        String url = uploadOss(img,generateFileName);\n        return ResponseResult.okResult(url);\n    }\n\n\n    private String accessKey;\n    private String secretKey;\n    private String bucket;\n    private String domain;\n\n    private String uploadOss(MultipartFile img, String FileName) {\n        //构造一个带指定 Region 对象的配置类\n        Configuration cfg = new Configuration(Region.autoRegion());\n        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本\n        //...其他参数参考类注释\n\n        UploadManager uploadManager = new UploadManager(cfg);\n        //...生成上传凭证，然后准备上传\n        //默认不指定key的情况下，以文件内容的hash值作为文件名\n        String key = FileName;\n        try {\n            InputStream inputStream = img.getInputStream();\n            Auth auth = Auth.create(accessKey, secretKey);\n            String upToken = auth.uploadToken(bucket);\n            try {\n                Response response = uploadManager.put(inputStream, key, upToken, null, null);\n                //解析上传成功的结果\n                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);\n                System.out.println(putRet.key);\n                System.out.println(putRet.hash);\n                return domain + \'/\' +key;\n            } catch (QiniuException ex) {\n                ex.printStackTrace();\n                if (ex.response != null) {\n                    System.err.println(ex.response);\n                    try {\n                        String body = ex.response.toString();\n                        System.err.println(body);\n                    } catch (Exception ignored) {\n                    }\n                }\n            }\n        } catch (Exception ex) {\n            //ignore\n        }\n        return \"test\";\n    }\n}\n~~~~\n\n~~~~java\npublic class PathUtils {\n    public static String generateFilePath(String fileName){\n        //根据日期生成路径   2022/1/15/\n        SimpleDateFormat sdf = new SimpleDateFormat(\"yyyy/MM/dd/\");\n        String datePath = sdf.format(new Date());\n        //uuid作为文件名\n        String uuid = UUID.randomUUID().toString().replaceAll(\"-\", \"\");\n        //后缀和文件后缀一致\n        int index = fileName.lastIndexOf(\".\");\n        // test.jpg -> .jpg\n        String fileType = fileName.substring(index);\n        return new StringBuilder().append(datePath).append(uuid).append(fileType).toString();\n    }\n}\n\n~~~~\n\n### 5.3 更新个人信息接口\n\n#### 5.3.1 功能需求\n\n编辑完个人资料后点击保存会对个人资料进行更新，将编辑完成的个人信息资料存入数据库\n\n#### 5.3.2 接口设计\n\n| 请求方法 | 请求地址       | 请求头    |\n| -------- | -------------- | --------- |\n| PUT方法  | /user/userInfo | 需要token |\n\n#### 5.3.3 请求参数\n\n| 参数名称             | 参数说明    | 请求类型 | 是否必须 | 数据类型       |\n| -------------------- | ----------- | -------- | -------- | -------------- |\n| user                 | user        | body     | true     | UserInfoDto    |\n| &emsp;&emsp;avatar   | 用户头像url |          | false    | string         |\n| &emsp;&emsp;id       | 用户id      |          | false    | integer(int64) |\n| &emsp;&emsp;nickName | 用户昵称    |          | false    | string         |\n| &emsp;&emsp;sex      | 用户性别    |          | false    | string         |\n\n~~~~json\n{\n    \"avatar\":\"url\",\n    \"email\":\"123123@qq.com\",\n    \"id\":\"1\",\n    \"nickName\":\"name\",\n    \"sex\":\"1\"\n}\n~~~~\n\n#### 5.3.4 响应格式\n\n~~~~json\n{\n	\"code\":200,\n	\"msg\":\"操作成功\"\n}\n~~~~\n\n#### 5.3.5 代码实现\n\n~~~~java\n    @Override\n    public ResponseResult updateUserInfo(UserInfoDto user) {\n        // 获取当前用户id\n        Long userId = SecurityUtils.getUserId();\n        if(userId == user.getId()) {\n            // 根据id更新用户信息\n            User user1 = BeanCopyUtils.copyBean(user, User.class);\n            updateById(user1);\n        } else {\n            throw new SystemException(AppHttpCodeEnum.NO_OPERATOR_AUTH);\n        }\n        return ResponseResult.okResult();\n    }\n~~~~\n\n### 5.4 用户注册接口\n\n#### 5.4.1 功能需求\n\n要求用户能够在注册界面完成用户的注册。\n\n要求用户名，昵称，邮箱不能和数据库中原有的数据重复。如果某项重复了注册失败并且要有对应的提示。\n\n并且要求用户名，密码，昵称，邮箱都不能为空。\n\n注意:密码必须密文存储到数据库中。\n\n#### 5.4.2 接口设计\n\n| 请求方法 | 请求地址       | 请求头      |\n| -------- | -------------- | ----------- |\n| POST方法 | /user/register | 不需要token |\n\n#### 5.4.3 请求参数\n\n| 参数名称             | 参数说明           | 请求类型 | 是否必须 | 数据类型 |\n| -------------------- | ------------------ | -------- | -------- | -------- |\n| user                 | user               | body     | true     | User     |\n| &emsp;&emsp;email    |                    |          | true     | string   |\n| &emsp;&emsp;nickName | 用户昵称（可重复） |          | true     | string   |\n| &emsp;&emsp;password | 用户密码           |          | true     | string   |\n| &emsp;&emsp;userName | 用户名（不可重复） |          | true     | string   |\n\n#### 5.4.4 响应格式\n\n~~~~json\n{\n	\"code\":200,\n	\"msg\":\"操作成功\"\n}\n~~~~\n\n#### 5.4.5 代码实现\n\n~~~~java\n	@Override\n    public ResponseResult register(User user) {\n        // 对数据进行非空判断\n        if(!StringUtils.hasText(user.getUserName())){\n            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);\n        }\n        if(!StringUtils.hasText(user.getPassword())){\n            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);\n        }\n        if(!StringUtils.hasText(user.getNickName())){\n            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);\n        }\n        if(!StringUtils.hasText(user.getEmail())){\n            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);\n        }\n        // 对数据进行是否重复的判断\n        if(userNameExist(user.getUserName())){\n            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);\n        }\n        if(emailExist(user.getEmail())){\n            throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);\n        }\n        // 对密码进行加密\n        String encodePassword = passwordEncoder.encode(user.getPassword());\n        user.setPassword(encodePassword);\n        // 存入数据库\n        save(user);\n        return ResponseResult.okResult();\n    }\n	private boolean emailExist(String email) {\n        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();\n        wrapper.eq(User::getEmail,email);\n        return count(wrapper) > 0;\n    }\n\n    private boolean userNameExist(String userName) {\n        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();\n        wrapper.eq(User::getUserName,userName);\n        return count(wrapper) > 0;\n    }\n~~~~\n\n## 6 BackGroundImgComtroller\n\n### 6.1 获取背景图片接口\n\n#### 6.1.1 功能需求\n\n将url和userId在运行时添加到redis，随后的查询在redis中进行。\n\n#### 6.1.2 接口设计\n\n| 请求方法 | 接口地址            |\n| -------- | ------------------- |\n| GET方法  | /backGroundImg/{id} |\n\n#### 6.1.3 请求参数\n| 参数名称 | 参数说明 | 请求类型 | 是否必须 | 数据类型       |\n| -------- | -------- | -------- | -------- | -------------- |\n| id       | 用户id   | path     | true     | integer(int64) |\n\n#### 6.1.4 响应格式\n\n~~~~json\n{\n  \"code\": 200,\n  \"data\": {\n        \"backGroundImgUrl\": \"url\"\n  },\n  \"msg\": \"操作成功\"\n}\n~~~~\n\n#### 6.1.5 代码实现\n\n~~~~java\n@Service(\"backGroundImgService\")\npublic class BackGroundImgServiceImpl extends ServiceImpl<BackGroundImgMapper,BackGroundImg> implements BackGroundImgService {\n\n    @Autowired\n    private RedisCache redisCache;\n\n    @Override\n    public ResponseResult getBackGroundImgList(Long id) {\n        // 根据token判断用户id是否匹配，如id为-1则说明未登录，返回默认图片（管理员用户的背景图片）\n        if(!id.equals(SystemConstants.NOT_LOGIN_ID)){\n            Long idFromToken = SecurityUtils.getUserId();\n            if(!idFromToken.equals(id)){\n                throw new SystemException(AppHttpCodeEnum.NO_OPERATOR_AUTH);\n            }\n        } else {\n            id = SystemConstants.ROOT_USER_ID;\n        }\n        // 根据用户id从redis中获取图片\n        List<BackGroundImg> backGroundImgList = redisCache.getCacheList(SystemConstants.BACK_GROUND_IMG);\n        List<BackGroundImgVo> backGroundImgVos = new ArrayList<>();\n        //根据用户id查询对应的背景图片\n        for(BackGroundImg backGroundImg : backGroundImgList){\n            if(backGroundImg.getCreateBy().equals(id)){\n                // 将url记录到集合中\n                backGroundImgVos.add(BeanCopyUtils.copyBean(backGroundImg,BackGroundImgVo.class));\n            }\n        }\n        // 获取集合中随机的某一个元素\n        int randomIndex = (int) (Math.random() * backGroundImgVos.size());\n        BackGroundImgVo backGroundImgVo = backGroundImgVos.get(randomIndex);\n        // 返回\n        return ResponseResult.okResult(backGroundImgVo.getContentUrl());\n    }\n}\n~~~~\n\n\n\n','这里是摘要',1,'http://s3ls1x3d1.hd-bkt.clouddn.com/2023/11/11/e57553c7fd3746b58557ce7e3eb9b5e2.jpg','1','0',9,'0',1,'2023-11-11 21:09:51',NULL,'2023-11-14 10:40:00',0),(3,'test2','test','tes2',1,'','1','0',0,'0',1,'2023-11-11 21:14:44',1,'2023-11-11 21:14:44',1),(4,'test3','tt','te',1,'','1','0',0,'0',1,'2023-11-11 21:16:20',1,'2023-11-11 21:16:20',1),(5,'111','11','11',1,'','1','0',0,'0',1,'2023-11-11 21:18:00',1,'2023-11-11 21:18:00',1),(6,'博客项目简介','## 1 工程简介\n### 1.1 框架结构\n\n此博客项目后端基于SpringBoot，版本为2.5.7。前端基于Vue2。\n前端分为两个项目，分别为展示部分以及后台部分。\n后端使用多模块进行构建\n\n### 1.2 后端依赖\n~~~~xml\n    <dependencies>\n        <!-- SpringBoot的依赖配置-->\n        <dependency>\n            <groupId>org.springframework.boot</groupId>\n            <artifactId>spring-boot-starter-web</artifactId>\n        </dependency>\n\n        <!--SpringSecurity启动器-->\n        <dependency>\n            <groupId>org.springframework.boot</groupId>\n            <artifactId>spring-boot-starter-security</artifactId>\n        </dependency>\n\n        <!--redis依赖-->\n        <dependency>\n            <groupId>org.springframework.boot</groupId>\n            <artifactId>spring-boot-starter-data-redis</artifactId>\n        </dependency>\n\n        <!--mybatisPlus依赖-->\n        <dependency>\n            <groupId>com.baomidou</groupId>\n            <artifactId>mybatis-plus-boot-starter</artifactId>\n        </dependency>\n\n        <!--mysql数据库驱动-->\n        <dependency>\n            <groupId>mysql</groupId>\n            <artifactId>mysql-connector-java</artifactId>\n        </dependency>\n\n        <!--lombok-->\n        <dependency>\n            <groupId>org.projectlombok</groupId>\n            <artifactId>lombok</artifactId>\n            <optional>true</optional>\n        </dependency>\n\n        <!--fastjson依赖-->\n        <dependency>\n            <groupId>com.alibaba</groupId>\n            <artifactId>fastjson</artifactId>\n        </dependency>\n\n        <!--jwt依赖-->\n        <dependency>\n            <groupId>io.jsonwebtoken</groupId>\n            <artifactId>jjwt</artifactId>\n        </dependency>\n\n        <dependency>\n            <groupId>javax.xml.bind</groupId>\n            <artifactId>jaxb-api</artifactId>\n        </dependency>\n\n        <!--七牛云-->\n        <dependency>\n            <groupId>com.qiniu</groupId>\n            <artifactId>qiniu-java-sdk</artifactId>\n            <version>[7.13.0, 7.13.99]</version>\n        </dependency>\n\n        <!--junit-->\n        <dependency>\n            <groupId>org.springframework.boot</groupId>\n            <artifactId>spring-boot-starter-test</artifactId>\n        </dependency>\n\n        <!--AOP-->\n        <dependency>\n            <groupId>org.springframework.boot</groupId>\n            <artifactId>spring-boot-starter-aop</artifactId>\n        </dependency>\n\n        <dependency>\n            <groupId>com.google.code.gson</groupId>\n            <artifactId>gson</artifactId>\n            <version>2.9.0</version>\n        </dependency>\n\n        <!--swagger -->\n        <dependency>\n            <groupId>io.springfox</groupId>\n            <artifactId>springfox-swagger2</artifactId>\n        </dependency>\n        <dependency>\n            <groupId>io.springfox</groupId>\n            <artifactId>springfox-swagger-ui</artifactId>\n        </dependency>\n\n        <!--knife4j-->\n        <dependency>\n            <groupId>com.github.xiaoymin</groupId>\n            <artifactId>knife4j-openapi2-spring-boot-starter</artifactId>\n        </dependency>\n    </dependencies>\n~~~~\n## 2 主要工具类\n### 2.1 ResponseResult类\n#### 2.1.1 功能\n对响应数据进行封装，在响应结果中包含状态值以及状态信息，方便调试错误。\n#### 2.1.2 响应格式\n~~~~json\n{\n  \"code\": 200,\n  \"data\": [\"data\"],\n  \"msg\": \"操作成功\"\n}\n~~~~\n#### 2.1.3 代码实现\n~~~~java\npackage com.wbw1537.domain;\n\n@JsonInclude(JsonInclude.Include.NON_NULL)\npublic class ResponseResult<T> implements Serializable {\n    private Integer code;\n    private String msg;\n    private T data;\n\n    public ResponseResult() {\n        this.code = AppHttpCodeEnum.SUCCESS.getCode();\n        this.msg = AppHttpCodeEnum.SUCCESS.getMsg();\n    }\n\n    public ResponseResult(Integer code, T data) {\n        this.code = code;\n        this.data = data;\n    }\n\n    public ResponseResult(Integer code, String msg, T data) {\n        this.code = code;\n        this.msg = msg;\n        this.data = data;\n    }\n\n    public ResponseResult(Integer code, String msg) {\n        this.code = code;\n        this.msg = msg;\n    }\n\n    public static ResponseResult errorResult(int code, String msg) {\n        ResponseResult result = new ResponseResult();\n        return result.error(code, msg);\n    }\n    public static ResponseResult okResult() {\n        ResponseResult result = new ResponseResult();\n        return result;\n    }\n    public static ResponseResult okResult(int code, String msg) {\n        ResponseResult result = new ResponseResult();\n        return result.ok(code, null, msg);\n    }\n\n    public static ResponseResult okResult(Object data) {\n        ResponseResult result = setAppHttpCodeEnum(AppHttpCodeEnum.SUCCESS, AppHttpCodeEnum.SUCCESS.getMsg());\n        if(data!=null) {\n            result.setData(data);\n        }\n        return result;\n    }\n\n    public static ResponseResult errorResult(AppHttpCodeEnum enums){\n        return setAppHttpCodeEnum(enums,enums.getMsg());\n    }\n\n    public static ResponseResult errorResult(AppHttpCodeEnum enums, String msg){\n        return setAppHttpCodeEnum(enums,msg);\n    }\n\n    public static ResponseResult setAppHttpCodeEnum(AppHttpCodeEnum enums){\n        return okResult(enums.getCode(),enums.getMsg());\n    }\n\n    private static ResponseResult setAppHttpCodeEnum(AppHttpCodeEnum enums, String msg){\n        return okResult(enums.getCode(),msg);\n    }\n\n    public ResponseResult<?> error(Integer code, String msg) {\n        this.code = code;\n        this.msg = msg;\n        return this;\n    }\n\n    public ResponseResult<?> ok(Integer code, T data) {\n        this.code = code;\n        this.data = data;\n        return this;\n    }\n\n    public ResponseResult<?> ok(Integer code, T data, String msg) {\n        this.code = code;\n        this.data = data;\n        this.msg = msg;\n        return this;\n    }\n\n    public ResponseResult<?> ok(T data) {\n        this.data = data;\n        return this;\n    }\n\n    public Integer getCode() {\n        return code;\n    }\n\n    public void setCode(Integer code) {\n        this.code = code;\n    }\n\n    public String getMsg() {\n        return msg;\n    }\n\n    public void setMsg(String msg) {\n        this.msg = msg;\n    }\n\n    public T getData() {\n        return data;\n    }\n    public void setData(T data) {\n        this.data = data;\n    }\n}\n~~~~\n### 2.2 BeanCopyUtils类\n#### 2.2.1 功能\n将实体类与vo相对应的数据进行bean拷贝，便于返回响应\n#### 2.2.2 代码实现\n~~~~java\npackage com.wbw1537.utils;\n\npublic class BeanCopyUtils {\n\n    private BeanCopyUtils(){\n    }\n\n    public static <V> V copyBean(Object source, Class<V> clazz) {\n        //创建目标对象\n        V result = null;\n        try {\n            result = clazz.newInstance();\n            //实现属性拷贝\n            BeanUtils.copyProperties(source, result);\n            return result;\n        } catch (Exception e) {\n            e.printStackTrace();\n        }\n        //返回bean拷贝对象\n        return result;\n    }\n\n    public static <O,V> List<V> copyBeanList(List<O> list, Class<V> clazz){\n        return list.stream()\n                .map(o -> copyBean(o,clazz))\n                .collect(Collectors.toList());\n    }\n}\n~~~~\n\n---\n\n### 2.3 WebUtils类\n\n#### 2.3.1 功能\n\n将字符串渲染到前端\n\n#### 2.3.2 代码实现\n\n~~~~java\npublic class WebUtils\n{\n    /**\n     * 将字符串渲染到客户端\n     * @param response 渲染对象\n     * @param string 待渲染的字符串\n     * @return null\n     */\n    public static void renderString(HttpServletResponse response, String string) {\n        try\n        {\n            response.setStatus(200);\n            response.setContentType(\"application/json\");\n            response.setCharacterEncoding(\"utf-8\");\n            response.getWriter().print(string);\n        }\n        catch (IOException e)\n        {\n            e.printStackTrace();\n        }\n    }\n}\n~~~~\n\n### 2.4 JwtAuthenticationTokenFilter类\n\n#### 2.4.1 功能\n\n对请求携带的token进行认证校验，如果校验失败则交由授权失败以及认证失败处理器处理。\n\n#### 2.4.2 代码实现\n\n~~~~java\npackage com.wbw1537.filter;\n\n@Component\npublic class JwtAuthenticationTokenFilter extends OncePerRequestFilter {\n    @Autowired\n    private RedisCache redisCache;\n    @Override\n    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {\n        // 获取请求头中的token\n        String token = request.getHeader(\"token\");\n        if (!StringUtils.hasText(token)) {\n            // 说明该接口不需要登录  直接放行\n            filterChain.doFilter(request, response);\n            return;\n        }\n        //解析获取userId\n        Claims claims = null;\n        try {\n            claims = JwtUtil.parseJWT(token);\n        } catch (Exception e) {\n            e.printStackTrace();\n            // token超时、token非法\n            //响应前端需要重新登陆\n            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);\n            WebUtils.renderString(response, JSON.toJSONString(result));\n            return;\n        }\n        String userId = claims.getSubject();\n        //从redis中获取用户信息\n        LoginUser loginUser = redisCache.getCacheObject(SystemConstants.ADMIN_LOGIN + userId);\n        //如果获取不到\n        if(Objects.isNull(loginUser)){\n            //说明登陆过期 提示重新登录\n            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);\n            WebUtils.renderString(response, JSON.toJSONString(result));\n            return;\n        }\n        //存入SecurityContextHolder\n        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,null);\n        SecurityContextHolder.getContext().setAuthentication(authenticationToken);\n        filterChain.doFilter(request,response);\n    }\n}\n~~~~\n\n#### 2.4.3 授权失败处理器\n\n~~~~java\n// 授权失败处理器\n@Component\npublic class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {\n    @Override\n    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {\n        accessDeniedException.printStackTrace();\n        ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH);\n        //响应给前端\n        WebUtils.renderString(response, JSON.toJSONString(result));\n    }\n}\n~~~~\n\n#### 2.4.4 校验失败处理器\n\n~~~~java\n//认证失败处理器\n@Component\npublic class AuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint {\n    @Override\n    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {\n        authException.printStackTrace();\n        ResponseResult result = null;\n        //对异常类型进行判断\n        if (authException instanceof BadCredentialsException) {\n            result = ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR.getCode(), authException.getMessage());\n        } else if (authException instanceof InsufficientAuthenticationException) {\n            result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN, authException.getMessage());\n        } else {\n            result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),\"认证或授权失败\");\n        }\n        //响应给前端\n        WebUtils.renderString(response, JSON.toJSONString(result));\n    }\n}\n\n~~~~\n\n### 2.5 SecurityUtils类\n\n#### 2.5.1功能\n\n通过securityContextHolder中的登录信息获取用户对象以及用户信息\n\n#### 2.5.2 代码实现\n\n~~~~java\npackage com.wbw1537.utils;\npublic class SecurityUtils\n{\n    \n    /**\n     * 获取用户\n     **/\n    public static LoginUser getLoginUser()\n    {\n        Authentication authentication = getAuthentication();\n        if (authentication != null && authentication.getPrincipal() instanceof LoginUser) {\n            return (LoginUser) authentication.getPrincipal();\n        }\n        return null;\n    }\n    \n    /**\n     * 获取Authentication\n     */\n    public static Authentication getAuthentication() {\n        return SecurityContextHolder.getContext().getAuthentication();\n    }\n    \n    public static Boolean isAdmin(){\n        Long id = getLoginUser().getUser().getId();\n        return id != null && 1L == id;\n    }\n    \n    public static Long getUserId() {\n        LoginUser loginUser = getLoginUser();\n        if (loginUser != null && loginUser.getUser() != null) {\n            return loginUser.getUser().getId();\n        }\n        return null;\n    }\n}\n\n~~~~\n\n','简要介绍项目的构成框架以及主要工具类',1,'http://s3ls1x3d1.hd-bkt.clouddn.com/2023/11/11/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202023-11-12%20205209.png','1','0',5,'0',1,'2023-11-12 20:49:21',NULL,'2023-11-14 10:40:00',0),(8,'测试','测试','测试',1,'','1','0',0,'0',1,'2023-11-14 09:28:47',NULL,'2023-11-14 09:28:53',1);
/*!40000 ALTER TABLE `wbw_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wbw_article_tag`
--

DROP TABLE IF EXISTS `wbw_article_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wbw_article_tag` (
  `article_id` bigint NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `tag_id` bigint NOT NULL DEFAULT '0' COMMENT '标签id',
  PRIMARY KEY (`article_id`,`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章标签关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wbw_article_tag`
--

LOCK TABLES `wbw_article_tag` WRITE;
/*!40000 ALTER TABLE `wbw_article_tag` DISABLE KEYS */;
INSERT INTO `wbw_article_tag` VALUES (2,1),(2,4),(6,1),(8,5);
/*!40000 ALTER TABLE `wbw_article_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wbw_back_ground_img`
--

DROP TABLE IF EXISTS `wbw_back_ground_img`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wbw_back_ground_img` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` bigint DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  `content_url` varchar(512) DEFAULT NULL COMMENT '背景图片url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='背景图表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wbw_back_ground_img`
--

LOCK TABLES `wbw_back_ground_img` WRITE;
/*!40000 ALTER TABLE `wbw_back_ground_img` DISABLE KEYS */;
INSERT INTO `wbw_back_ground_img` VALUES (1,1,0,'http://s3ls1x3d1.hd-bkt.clouddn.com/BackGroundImg/20210919-_DSC3665.jpg'),(2,1,0,'http://s3ls1x3d1.hd-bkt.clouddn.com/BackGroundImg/20220713-_DSC1241.jpg'),(3,1,0,'http://s3ls1x3d1.hd-bkt.clouddn.com/BackGroundImg/20221120-_WBW3563.jpg'),(4,1,0,'http://s3ls1x3d1.hd-bkt.clouddn.com/BackGroundImg/wbw1537-2-166-1.jpg'),(5,1,1,'http://s3ls1x3d1.hd-bkt.clouddn.com/BackGroundImg/_DSC3714.jpg'),(6,1,0,'http://s3ls1x3d1.hd-bkt.clouddn.com/BackGroundImg/000006.jpg'),(7,1,0,'http://s3ls1x3d1.hd-bkt.clouddn.com/BackGroundImg/121.jpg'),(8,1,0,'http://s3ls1x3d1.hd-bkt.clouddn.com/BackGroundImg/20220328-_DSC0051.jpg'),(9,1,0,'http://s3ls1x3d1.hd-bkt.clouddn.com/BackGroundImg/20220708-_DSC0996.jpg'),(10,1,0,'http://s3ls1x3d1.hd-bkt.clouddn.com/BackGroundImg/20230124-_WBW4313.jpg'),(11,1,0,'http://s3ls1x3d1.hd-bkt.clouddn.com/BackGroundImg/20230629-_WBW6213-Pano-1.jpg'),(12,1,0,'http://s3ls1x3d1.hd-bkt.clouddn.com/BackGroundImg/20230711-DJI_0262-Pano-1.jpg'),(13,1,0,'http://s3ls1x3d1.hd-bkt.clouddn.com/BackGroundImg/20230712-000001-1.jpg'),(14,1,0,'http://s3ls1x3d1.hd-bkt.clouddn.com/BackGroundImg/wbw1537-2-166-1.jpg');
/*!40000 ALTER TABLE `wbw_back_ground_img` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wbw_category`
--

DROP TABLE IF EXISTS `wbw_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wbw_category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL COMMENT '分类名',
  `pid` bigint DEFAULT '-1' COMMENT '父分类id，如果没有父分类为-1',
  `description` varchar(512) DEFAULT NULL COMMENT '描述',
  `status` char(1) DEFAULT '0' COMMENT '状态0:正常,1禁用',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wbw_category`
--

LOCK TABLES `wbw_category` WRITE;
/*!40000 ALTER TABLE `wbw_category` DISABLE KEYS */;
INSERT INTO `wbw_category` VALUES (1,'java',-1,'wsd','0',NULL,NULL,NULL,NULL,0),(2,'PHP',-1,'wsd','0',NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `wbw_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wbw_comment`
--

DROP TABLE IF EXISTS `wbw_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wbw_comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` char(1) DEFAULT '0' COMMENT '评论类型（0代表文章评论，1代表友链评论）',
  `article_id` bigint DEFAULT NULL COMMENT '文章id',
  `root_id` bigint DEFAULT '-1' COMMENT '根评论id',
  `content` varchar(512) DEFAULT NULL COMMENT '评论内容',
  `to_comment_user_id` bigint DEFAULT '-1' COMMENT '所回复的目标评论的userid',
  `to_comment_id` bigint DEFAULT '-1' COMMENT '回复目标评论id',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wbw_comment`
--

LOCK TABLES `wbw_comment` WRITE;
/*!40000 ALTER TABLE `wbw_comment` DISABLE KEYS */;
INSERT INTO `wbw_comment` VALUES (1,'0',1,-1,'asS',-1,-1,1,'2022-01-29 07:59:22',1,'2022-01-29 07:59:22',0),(2,'0',1,-1,'[哈哈]SDAS',-1,-1,1,'2022-01-29 08:01:24',1,'2022-01-29 08:01:24',0),(3,'0',1,-1,'是大多数',-1,-1,1,'2022-01-29 16:07:24',1,'2022-01-29 16:07:24',0),(4,'0',1,-1,'撒大声地',-1,-1,1,'2022-01-29 16:12:09',1,'2022-01-29 16:12:09',0),(5,'0',1,-1,'你再说什么',-1,-1,1,'2022-01-29 18:19:56',1,'2022-01-29 18:19:56',0),(6,'0',1,-1,'hffd',-1,-1,1,'2022-01-29 22:13:52',1,'2022-01-29 22:13:52',0),(9,'0',1,2,'你说什么',1,2,1,'2022-01-29 22:18:40',1,'2022-01-29 22:18:40',0),(10,'0',1,2,'哈哈哈哈[哈哈]',1,9,1,'2022-01-29 22:29:15',1,'2022-01-29 22:29:15',0),(11,'0',1,2,'we全文',1,10,3,'2022-01-29 22:29:55',1,'2022-01-29 22:29:55',0),(12,'0',1,-1,'王企鹅',-1,-1,1,'2022-01-29 22:30:20',1,'2022-01-29 22:30:20',0),(13,'0',1,-1,'什么阿是',-1,-1,1,'2022-01-29 22:30:56',1,'2022-01-29 22:30:56',0),(14,'0',1,-1,'新平顶山',-1,-1,1,'2022-01-29 22:32:51',1,'2022-01-29 22:32:51',0),(15,'0',1,-1,'2222',-1,-1,1,'2022-01-29 22:34:38',1,'2022-01-29 22:34:38',0),(16,'0',1,2,'3333',1,11,1,'2022-01-29 22:34:47',1,'2022-01-29 22:34:47',0),(17,'0',1,2,'回复weqedadsd',3,11,1,'2022-01-29 22:38:00',1,'2022-01-29 22:38:00',0),(18,'0',1,-1,'sdasd',-1,-1,1,'2022-01-29 23:18:19',1,'2022-01-29 23:18:19',0),(19,'0',1,-1,'111',-1,-1,1,'2022-01-29 23:22:23',1,'2022-01-29 23:22:23',0),(20,'0',1,1,'你说啥？',1,1,1,'2022-01-30 10:06:21',1,'2022-01-30 10:06:21',0),(21,'0',1,-1,'友链添加个呗',-1,-1,1,'2022-01-30 10:06:50',1,'2022-01-30 10:06:50',0),(22,'1',1,-1,'此评论仅用于展示功能',-1,-1,1,'2022-01-30 10:08:28',1,'2022-01-30 10:08:28',0),(23,'1',1,22,'我的朋友，你说得对',1,22,1,'2022-01-30 10:08:50',1,'2022-01-30 10:08:50',0),(24,'1',1,-1,'友链评论4444',-1,-1,1,'2022-01-30 10:09:03',1,'2022-01-30 10:09:03',1),(25,'1',1,22,'收到的',1,22,1,'2022-01-30 10:13:28',1,'2022-01-30 10:13:28',1),(26,'0',1,-1,'sda',-1,-1,1,'2022-01-30 10:39:05',1,'2022-01-30 10:39:05',0),(27,'0',1,1,'说你咋地',1,20,14787164048662,'2022-01-30 17:19:30',14787164048662,'2022-01-30 17:19:30',0),(28,'0',1,1,'sdad',1,1,14787164048662,'2022-01-31 11:11:20',14787164048662,'2022-01-31 11:11:20',0),(29,'0',1,-1,'你说是的ad',-1,-1,14787164048662,'2022-01-31 14:10:11',14787164048662,'2022-01-31 14:10:11',0),(30,'0',1,1,'撒大声地',1,1,14787164048662,'2022-01-31 20:19:18',14787164048662,'2022-01-31 20:19:18',0),(31,'0',1,-1,'wsdawds ',-1,-1,1,'2023-11-04 20:52:22',1,'2023-11-04 20:52:22',0),(32,'0',1,1,'超市你',1,1,1,'2023-11-04 21:13:28',1,'2023-11-04 21:13:28',0),(33,'1',1,-1,'测试',-1,-1,1,'2023-11-04 21:30:40',1,'2023-11-04 21:30:40',1),(34,'0',6,-1,'aaa',-1,-1,1,'2023-11-11 01:35:05',1,'2023-11-11 01:35:05',1);
/*!40000 ALTER TABLE `wbw_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wbw_img`
--

DROP TABLE IF EXISTS `wbw_img`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wbw_img` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(256) DEFAULT NULL COMMENT '图片标题',
  `content` longtext COMMENT '图片描述内容',
  `summary` varchar(1024) DEFAULT NULL COMMENT '文章摘要',
  `category_id` bigint DEFAULT NULL COMMENT '所属分类id',
  `img_url` varchar(256) DEFAULT NULL COMMENT '图片',
  `is_top` char(1) DEFAULT '0' COMMENT '是否置顶（0否，1是）',
  `status` char(1) DEFAULT '1' COMMENT '状态（0已发布，1草稿）',
  `view_count` bigint DEFAULT '0' COMMENT '访问量',
  `is_comment` char(1) DEFAULT '1' COMMENT '是否允许评论 1是，0否',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wbw_img`
--

LOCK TABLES `wbw_img` WRITE;
/*!40000 ALTER TABLE `wbw_img` DISABLE KEYS */;
/*!40000 ALTER TABLE `wbw_img` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wbw_link`
--

DROP TABLE IF EXISTS `wbw_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wbw_link` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(256) DEFAULT NULL,
  `logo` varchar(256) DEFAULT NULL,
  `description` varchar(512) DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL COMMENT '网站地址',
  `status` char(1) DEFAULT '2' COMMENT '审核状态 (0代表审核通过，1代表审核未通过，2代表未审核)',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='友链';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wbw_link`
--

LOCK TABLES `wbw_link` WRITE;
/*!40000 ALTER TABLE `wbw_link` DISABLE KEYS */;
INSERT INTO `wbw_link` VALUES (1,'github','https://ts4.cn.mm.bing.net/th?id=ODLS.00dbbbda-1b83-40e7-ac3c-2ff2a73b245f&w=32&h=32&qlt=90&pcl=fffffa&o=6&pid=1.2','github.com','https://www.github.com','0',NULL,'2022-01-13 08:25:47',NULL,'2022-01-13 08:36:14',0),(2,'pixiv','https://theme.zdassets.com/theme_assets/1128206/3ba70c55ddd666c31bb10f918c061a50f6308689.gif','pixiv','https://www.pixiv.com','0',NULL,'2022-01-13 09:06:10',NULL,'2022-01-13 09:07:09',0),(3,'sa','https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn1.itc.cn%2Fimg8%2Fwb%2Frecom%2F2016%2F05%2F10%2F146286696706220328.PNG&refer=http%3A%2F%2Fn1.itc.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1646205529&t=f942665181eb9b0685db7a6f59d59975','da','https://www.taobao.com','0',NULL,'2022-01-13 09:23:01',NULL,'2022-01-13 09:23:01',1);
/*!40000 ALTER TABLE `wbw_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wbw_tag`
--

DROP TABLE IF EXISTS `wbw_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wbw_tag` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL COMMENT '标签名',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='标签';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wbw_tag`
--

LOCK TABLES `wbw_tag` WRITE;
/*!40000 ALTER TABLE `wbw_tag` DISABLE KEYS */;
INSERT INTO `wbw_tag` VALUES (1,'Mybatis',NULL,NULL,NULL,'2022-01-11 09:20:50',0,'weqwe'),(2,'asdas',NULL,'2022-01-11 09:20:55',NULL,'2022-01-11 09:20:55',1,'weqw'),(3,'weqw',NULL,'2022-01-11 09:21:07',NULL,'2022-01-11 09:21:07',1,'qweqwe'),(4,'Java',NULL,'2022-01-13 15:22:43',NULL,'2022-01-13 15:22:43',0,'sdad'),(5,'WAD',NULL,'2022-01-13 15:22:47',NULL,'2022-01-13 15:22:47',0,'ASDAD'),(6,'c#',1,'2023-11-10 22:39:31',NULL,'2023-11-10 22:57:53',0,'c+++++');
/*!40000 ALTER TABLE `wbw_tag` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-14 18:49:46
