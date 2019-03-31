package com.git.kinomax094;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
@ComponentScan(basePackages = "com.git.kinomax094")
@EnableJpaRepositories(basePackages = "com.git.kinomax094.repository")
public class TestContext {
}
