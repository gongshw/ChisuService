/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.chisu.service.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.DateTypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gongshw.chisu.service.model.DelayEmail;
import com.gongshw.chisu.service.model.Recipe;

/**
 * @author gongshiwei
 */
public abstract class MybatisConfigurationBase {
    private Logger logger = LoggerFactory.getLogger(getClass());

    protected abstract DataSource dataSource();

    private SqlSessionFactory sqlSessionFactory;

    private SqlSessionFactory getSessionFactory() throws Exception {
        if (sqlSessionFactory == null) {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource());
            Class[] types = new Class[] {
                    Recipe.class,
                    DelayEmail.class,
                    DateTypeHandler.class
            };
            sqlSessionFactoryBean.setTypeAliases(types);
            sqlSessionFactory = sqlSessionFactoryBean.getObject();
            sqlSessionFactory.getConfiguration().addMappers("com.gongshw.chisu.service.mapper");
            sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
        }
        return sqlSessionFactory;
    }

    protected <T> MapperFactoryBean<T> getMapper(Class<T> mapperInterface) {
        MapperFactoryBean<T> mapperFactoryBean = new MapperFactoryBean<>();
        try {
            mapperFactoryBean.setSqlSessionFactory(getSessionFactory());
            mapperFactoryBean.setMapperInterface(mapperInterface);
        } catch (Exception ex) {
            logger.error("error when create mapper: ", ex);
            throw new RuntimeException(ex);
        }
        return mapperFactoryBean;
    }
}
