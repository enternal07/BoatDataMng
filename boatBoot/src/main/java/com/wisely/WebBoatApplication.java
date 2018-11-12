package com.wisely;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.wisely.support.CustomRepositoryFactoryBeans;

/*import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.wisely.support.CustomRepositoryFactoryBean;


@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class)
public class WebBoatApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(WebBoatApplication.class, args);
        
    }
}
*/

@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomRepositoryFactoryBeans.class)
public class WebBoatApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(WebBoatApplication.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebBoatApplication.class);
    }
}


