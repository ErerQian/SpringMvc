package com.gaussic.repository;

import com.gaussic.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by HP-HQ on 2017/2/8.
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Modifying      // 说明该方法是修改操作
    @Transactional  // 说明该方法是事务性操作
    // 定义查询
    // @Param注解用于提取参数
    @Query("update UserEntity us set us.nickname=:qNickname,us.firstname=:qFirstName,us.lastname=:qLastName,us.password=:qPassword where us.id=:qId")
    public void updateUser(@Param("qNickname") String nickname, @Param("qFirstName") String firstname,
                           @Param("qLastName") String lastname, @Param("qPassword") String password, @Param("qId") Integer id);

//    @Transactional
//    @Query("select all FROM UserEntity order by id limit m=:qm,n=:qn")
//    void queryForPage(@Param("qm")int pageSize,@Param("qn") int currentPage);
}
