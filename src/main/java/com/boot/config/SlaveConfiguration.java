package com.boot.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;

/**
 * Created by yeguoxing on 2018/3/23.
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.boot.mapper.slave", sqlSessionTemplateRef  = "slaveSqlSessionTemplate")

public class SlaveConfiguration extends AbstractDruidDBConfig {
    @Value("${slave.datasource.url}")
    private String url;

    @Value("${slave.datasource.username}")
    private String username;

    @Value("${slave.datasource.password}")
    private String password;


    // 注册 datasourceOne
    @Bean(name = "datasourceSlave", initMethod = "init", destroyMethod = "close")
    public DruidDataSource dataSource() {
        return super.createDataSource(url, username, password);
    }

    @Bean(name = "sqlSessionFactortSlave")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        return super.sqlSessionFactory(dataSource());
    }

    @Bean(name = "slaveTransactionManager")
    public PlatformTransactionManager transactionManager() throws SQLException {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "slaveSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }
}
