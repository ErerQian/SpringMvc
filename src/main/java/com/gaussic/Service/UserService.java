package com.gaussic.Service;

import com.gaussic.model.PageBean;

/**
 * Created by HP-HQ on 2017/2/14.
 */
public interface UserService {
    PageBean queryForPage(int pageSize,int page);
}
