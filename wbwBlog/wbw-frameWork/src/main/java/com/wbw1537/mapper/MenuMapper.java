package com.wbw1537.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wbw1537.domain.entity.Menu;

import java.util.List;


/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2023-11-09 17:21:32
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long id);
}


