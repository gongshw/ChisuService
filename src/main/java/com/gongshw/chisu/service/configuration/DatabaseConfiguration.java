/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.chisu.service.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.gongshw.chisu.service.mapper.DelayEmailMapper;
import com.gongshw.chisu.service.mapper.RecipeMapper;

/**
 * @author gongshiwei
 */
@Configuration
public class DatabaseConfiguration extends MybatisConfigurationBase {

    @Value("${chisu.service.database.isEmbedded:true}")
    Boolean databaseIsEmbedded;

    @Value("${chisu.service.database.url:}")
    String databaseUrl;

    @Value("${chisu.service.database.username:}")
    String databaseUsername;

    @Value("${chisu.service.database.password:}")
    String databasePassword;

    private DataSource dataSource;

    @Bean
    @Override
    public DataSource dataSource() {
        if (dataSource == null) {
            if (databaseIsEmbedded) {
                dataSource = getEmbeddedHsqlDataSource("classpath:db/chisu_hsqldb_init.sql");
            } else {
                throw new RuntimeException("not implemented");
            }
        }
        return dataSource;
    }

    public static DataSource getEmbeddedHsqlDataSource(String script) {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                .addScripts(script).build();
    }

    @Bean
    public RecipeMapper recipeMapper() throws Exception {
        return getMapper(RecipeMapper.class).getObject();
    }

    @Bean
    public DelayEmailMapper delayEmailMapper() throws Exception {
        return getMapper(DelayEmailMapper.class).getObject();
    }
}
