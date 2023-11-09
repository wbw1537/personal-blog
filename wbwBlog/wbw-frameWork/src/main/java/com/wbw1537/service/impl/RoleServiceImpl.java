package com.wbw1537.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbw1537.constants.SystemConstants;
import com.wbw1537.domain.entity.Role;
import com.wbw1537.mapper.RoleMapper;
import com.wbw1537.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author makejava
 * @since 2023-11-09 17:29:07
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        // 判断用户是否是管理员 如果是，返回集合中只需要有admin
        if(id.equals(SystemConstants.ROOT_USER_ID)){
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        // 否则查询当前用户具有的角色信息
        return getBaseMapper().selectRoleKeyByUserId(id);
    }
}
