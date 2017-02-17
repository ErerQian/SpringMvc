package com.gaussic.repository;

import com.gaussic.model.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by HP-HQ on 2017/2/9.
 */
public interface BlogRepository extends JpaRepository<BlogEntity, Integer> {
    @Modifying
    @Transactional
    @Query("update BlogEntity blog set blog.title=:qTitle,blog.content=:qContent," +
            "blog.pubDate=:qPubDate,blog.userByUserId.id=:qUserId where blog.id=:qId")
    public void updateBlog(@Param("qTitle") String title, @Param("qContent") String content, @Param("qPubDate") Date pubDate,
                           @Param("qUserId") int userId, @Param("qId") int id);
}
