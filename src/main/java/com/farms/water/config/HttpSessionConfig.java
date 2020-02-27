package com.farms.water.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

/**
 * Created by Java Developer Zone on 13-11-2017.
 */
/*
 * @Configuration
 * 
 * @EnableRedisHttpSession public class HttpSessionConfig extends
 * AbstractHttpSessionApplicationInitializer {
 * 
 * 
 * @Bean public JedisConnectionFactory connectionFactory() { // It will create
 * filter for Redis store which will override default Tomcat Session return new
 * JedisConnectionFactory(); }
 * 
 * @Bean public HttpSessionStrategy httpSessionStrategy() { // Header Strategy
 * indicate that session will be manage using header return new
 * HeaderHttpSessionStrategy(); } }
 */