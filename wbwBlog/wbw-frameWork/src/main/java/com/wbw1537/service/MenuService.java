package com.wbw1537.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wbw1537.domain.entity.Menu;

import java.util.List;


/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author makejava
 * @since 2023-11-09 17:21:32
 */
public interface MenuService extends IService<Menu> {

    List<String> selectPermsByUserId(Long id);
}
