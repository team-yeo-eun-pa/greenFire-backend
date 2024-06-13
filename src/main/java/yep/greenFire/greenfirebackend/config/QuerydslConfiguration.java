package yep.greenFire.greenfirebackend.config;

import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class QuerydslConfiguration {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        System.out.println("entityManager : " + entityManager);
        return new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager);
    }
}