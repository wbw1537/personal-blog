package com.wbw1537.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wbw1537.domain.entity.Role;

import java.util.List;


/**
 * 角色信息表(Role)表数据库访问层
 *
 * @author makejava
 * @since 2023-11-09 17:29:07
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRoleKeyByUserId(Long id);

}


