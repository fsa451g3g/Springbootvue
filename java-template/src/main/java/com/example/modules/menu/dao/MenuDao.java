package com.example.modules.menu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules.menu.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 菜单表(Menu)表数据库访问层
 *
 * @author oceans
 * @since 2022-06-07 17:40:44
 */
@Mapper
public interface MenuDao extends BaseMapper<Menu> {

    @Select("select menu_id from user_role natural join role_menu where user_id = #{userId}")
    List<String> getIdsByUserId(String userId);

}
