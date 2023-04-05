package com.jojoldu.book.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing //JPA 활성화 -> @WebMvcTest는 일반적인 @Configuration 스캔하지 않기 때문
public class JpaConfig {
}
