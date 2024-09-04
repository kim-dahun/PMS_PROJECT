package com.prod.pms.config.dataConfig;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class JPAConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public EntityManager getEntityManager(){
        return entityManager;
    }


}
