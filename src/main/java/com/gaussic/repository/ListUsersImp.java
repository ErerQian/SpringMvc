package com.gaussic.repository;

import com.gaussic.model.UserEntity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by HP-HQ on 2017/2/13.
 */
public class ListUsersImp extends HibernateDaoSupport implements IListUsers {
    public List<UserEntity> queryForPage(String hql, int offset, int length) {
        SessionFactory session = this.getSessionFactory();
        Query q = session.getCurrentSession().createQuery(hql);
        q.setFirstResult(offset);
        q.setMaxResults(length);
        List<UserEntity> list = q.list();
        System.out.println(list.size());
        session.close();
        return list;
    }

    public int getCount(String hql) {
        return this.getHibernateTemplate().find(hql).size();
    }
}
