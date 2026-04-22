package com.mborodin.javacourse;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Конфігурація для налаштування in memory бази даних, яка використовується в тестах.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.mborodin.javacourse")
@EnableTransactionManagement
public class TestJpaConfig {
}
