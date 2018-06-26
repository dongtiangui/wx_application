package com.spring.config.mybatis;
import com.github.pagehelper.PageInterceptor;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = "com.spring.Mappers")
@EnableTransactionManagement
public class SqlSessionFactory implements TransactionManagementConfigurer {
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value(value = "${jdbc.username}")
    private String username;
    @Value(value = "${jdbc.password}")
    private String password;
    @Value(value = "${jdbc.ConfigLocation}")
    private String ConfigLocation;
    @Value(value = "${jdbc.mappers}")
    private String mappers;
    @Value(value = "${jdbc.package}")
    private String packageEntiry;
    @Bean(name = "{dataSources")
    public ComboPooledDataSource dataSources() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setMaxPoolSize(20);
        dataSource.setMaxConnectionAge(50);
        dataSource.setMaxIdleTime(60000);
        dataSource.setInitialPoolSize(5);
        dataSource.setAutoCommitOnClose(false);
        return dataSource;
    }
    @Bean(name = "sqlSessionFactoryBeanLocal")
    @Scope(value = "prototype")
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws PropertyVetoException, IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSources());
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(ConfigLocation));
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        String mappersPackage = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX+mappers;
        sqlSessionFactoryBean.setMapperLocations(patternResolver.getResources(mappersPackage));
        sqlSessionFactoryBean.setTypeAliasesPackage(packageEntiry);
//        Interceptor[] interceptor = new Interceptor[2];
//        interceptor[1]=pageInterceptor();
//        List<Interceptor> interceptorList = new ArrayList<>();
//        sqlSessionFactoryBean.setPlugins(interceptor);
        return sqlSessionFactoryBean;
    }

    @Override
    @Bean(name = "annotationDrivenTransactionManagerLocal")
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        try {
            dataSourceTransactionManager.setDataSource(dataSources());
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSourceTransactionManager;
    }
//    @Bean
//    public MapperScannerConfigurer scannerConfigurer(){
//
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        mapperScannerConfigurer.setAnnotationClass(Repository.class);
//        mapperScannerConfigurer.setBasePackage("com.spring.Mappers");
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBeanLocal");
//        return mapperScannerConfigurer;
//
//    }

    @Bean
    public PageInterceptor pageInterceptor(){

        PageInterceptor interceptor = new PageInterceptor();
        
        interceptor.setProperties(new Properties());
        return interceptor;
    }

}
