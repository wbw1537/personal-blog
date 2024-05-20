package com.wbw1537.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbw1537.constants.SystemConstants;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.AddMenuDto;
import com.wbw1537.domain.entity.Menu;
import com.wbw1537.domain.vo.MenuListVo;
import com.wbw1537.domain.vo.MenuVo;
import com.wbw1537.mapper.MenuMapper;
import com.wbw1537.service.MenuService;
import com.wbw1537.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2023-11-09 17:21:32
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<String> selectPermsByUserId(Long userId) {
        // 如果是管理员返回所有的权限
        if (userId.equals(SystemConstants.ROOT_USER_ID)) {
            LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(Menu::getMenuType, SystemConstants.MENU, SystemConstants.BUTTON);
            wrapper.eq(Menu::getStatus, SystemConstants.STATUS_NORMAL);
            List<Menu> menuList = list(wrapper);
            List<String> perms = menuList.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
            return perms;
        }
        // 否则返回或具有的权限
        return menuMapper.selectPermsByUserId(userId);
    }

    @Override
    public List<Menu> selectRouterTreeByUserId(Long userId) {
        List<Menu> menus = new ArrayList<>();
        // 判断是否是管理员
        if (userId.equals(SystemConstants.ROOT_USER_ID)) {
            // 如果是，返回所有符合要求menu
            menus = menuMapper.selectAllRouterMenu();
        } else {
            // 否则返回对应当前用户具有的menu
            menus = menuMapper.selectRouterMenuByUserId(userId);
        }
        // 构建tree
        // 先找出第一层的菜单，然后再找他们的子菜单设置到children
        List<Menu> menuTree = buildMenuTree(menus, 0L);
        return menuTree;
    }

    @Override
    public List<MenuListVo> searchSystemMenuList(String status, String menuName) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        // 根据请求条件进行条件查询
        wrapper.like(StringUtils.hasText(status), Menu::getStatus, status);
        wrapper.like(StringUtils.hasText(menuName),Menu::getMenuName,menuName);
        List<Menu> menus = list(wrapper);
        // 封装数据
        return BeanCopyUtils.copyBeanList(menus, MenuListVo.class);
    }

    @Override
    public ResponseResult addSystemMenu(AddMenuDto addMenuDto) {
        // 封装数据
        Menu menu = BeanCopyUtils.copyBean(addMenuDto, Menu.class);
        // 存入数据
        save(menu);
        return ResponseResult.okResult();
    }
    private List<Menu> buildMenuTree(List<Menu> menus, Long parentId) {
        List<Menu> menuTree = menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());
        return menuTree;
    }

    /**
     * 获取存入参数的 子Menu集合
     *
     * @param menu
     * @param menus
     * @return
     */
    private List<Menu> getChildren(Menu menu, List<Menu> menus) {
        List<Menu> childrenList = menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .map(m -> m.setChildren(getChildren(m, menus)))
                .collect(Collectors.toList());
        return childrenList;
    }
}
