package com.minorm.spring.database.repository;

import com.minorm.spring.bpp.InjectBean;
import com.minorm.spring.database.pool.ConnectionPool;

public class CompanyRepository {

    @InjectBean
    private ConnectionPool connectionPool;

}
