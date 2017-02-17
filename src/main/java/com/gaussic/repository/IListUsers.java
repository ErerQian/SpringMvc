package com.gaussic.repository;

import com.gaussic.model.UserEntity;

import java.util.List;

/**
 * Created by HP-HQ on 2017/2/13.
 */
public interface IListUsers {
    //分页查询
    public List<UserEntity> queryForPage(String hql, int offset, int length);

    //总记录条数
    public int getCount(String hql);
}
