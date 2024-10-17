package com.example.modules.menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.exception.CustomException;
import com.example.exception.ExceptionType;
import com.example.modules.menu.service.MenuService;
import com.example.modules.menu.dao.MenuDao;
import com.example.modules.menu.entity.Menu;
import com.example.modules.user.dao.UserDao;
import com.example.modules.user.entity.User;
import com.example.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单表(Menu)表服务实现类
 *
 * @author oceans
 * @since 2022-06-07 17:40:44
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    MenuDao menuDao;
    UserDao userDao;

    @Override
    public Result list() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userDao.selectOne(new QueryWrapper<User>().eq("username", authentication.getName()));
        if (null == user) {
            throw new CustomException(ExceptionType.USER_NOT_FOUND);
        }
        List<String> ids = menuDao.getIdsByUserId(user.getId());
        List<Menu> menuList = menuDao.selectList(new QueryWrapper<Menu>().in("id", ids).eq("father_id", 0));
        List<Map<String, Object>> list = new ArrayList<>();
        for (Menu menu : menuList) {
            Map<String, Object> map = new HashMap<>();
            map.put("menu", menu);
            List<Menu> subMenu = menuDao.selectList(new QueryWrapper<Menu>().eq("father_id", menu.getId()));
            map.put("subMenu", subMenu);
            list.add(map);
        }
        return Result.success("菜单列表",list);
    }

    @Autowired
    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
