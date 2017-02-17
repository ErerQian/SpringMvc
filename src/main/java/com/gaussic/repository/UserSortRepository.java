package com.gaussic.repository;

import com.gaussic.model.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by HP-HQ on 2017/2/14.
 */
public interface UserSortRepository extends PagingAndSortingRepository<UserEntity,Integer> {
}
