package com.wbw1537.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.AddMenuDto;
import com.wbw1537.domain.entity.Menu;
import com.wbw1537.domain.vo.MenuListVo;
import com.wbw1537.domain.vo.MenuVo;
import org.springframework.http.ResponseEntity;

import java.util.List;


/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author makejava
 * @since 2023-11-09 17:21:32
 */
public interface MenuService extends IService<Menu> {

    List<String> selectPermsByUserId(Long id);

    List<Menu> selectRouterTreeByUserId(Long userId);

    ResponseEntity getSystemMenuList(String status, String menuName);

    ResponseEntity addSystemMenu(AddMenuDto addMenuDto);
}
