package com.pw.wbd;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by arade on 20-Jan-16.
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.pw.wbd.domain.entities"})
@EnableJpaRepositories(basePackages = {"com.pw.wbd.domain.repositories"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
