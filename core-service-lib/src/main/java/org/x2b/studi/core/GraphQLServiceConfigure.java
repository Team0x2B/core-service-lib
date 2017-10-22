package org.x2b.studi.core;


import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SubjectDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.x2b.studi.core.security.jwt.JWTUserTokenVerifier;
import org.x2b.studi.core.security.jwt.SharedSecretProvider;
import org.x2b.studi.core.security.shiro.GenericAuthenticatingRealm;

import java.io.File;

public abstract class GraphQLServiceConfigure {

    @Value("#{graphql.schema.schemaFileLocation}")
    public static String schemaFileLocation = "schema.gql";


    @Autowired
    protected ApplicationContext applicationContext;


    @Bean
    public GraphQLSchema schema() {
        SchemaParser parser = new SchemaParser();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        TypeDefinitionRegistry tdr = parser.parse(getSchemaFile());
        RuntimeWiring runtimeWiring = createRuntimeWiring();
        if (runtimeWiring == null) {
            return null;
        }
        return schemaGenerator.makeExecutableSchema(tdr, runtimeWiring);
    }

    @Bean
    public GenericAuthenticatingRealm authenticatingRealm() {
        return new GenericAuthenticatingRealm();
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authenticatingRealm());
        securityManager.setSubjectDAO(createStatelessSubjectDao());
        return securityManager;
    }

    private SubjectDAO createStatelessSubjectDao() {
        DefaultSessionStorageEvaluator sessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        subjectDAO.setSessionStorageEvaluator(sessionStorageEvaluator);
        return subjectDAO;
    }

    @Bean
    public FilterRegistrationBean shrioFilterRegistration() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
        delegatingFilterProxy.setTargetBeanName("shiroFilter");
        filterRegistration.setFilter(delegatingFilterProxy);
        filterRegistration.setName("shiroFilter");
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilterFactory = new ShiroFilterFactoryBean();
        shiroFilterFactory.setSecurityManager(securityManager());
        return shiroFilterFactory;
    }

    @Bean
    public SharedSecretProvider jwtAuthKeyProvider() {
        return new SharedSecretProvider();
    }

    @Bean
    public JWTUserTokenVerifier jwtUserTokenVerifier() {
        return new JWTUserTokenVerifier((SharedSecretProvider) applicationContext.getBean("jwtAuthKeyProvider"));
    }

    @Bean
    public MongoClientFactoryBean authorizationDatasource() {
        return createAuthDatasourceFactory();
    }

    protected abstract MongoClientFactoryBean createAuthDatasourceFactory();

    private File getSchemaFile() {
        return new File(this.getClass().getClassLoader().getResource(schemaFileLocation).getFile());
    }


    protected abstract RuntimeWiring createRuntimeWiring();
}
