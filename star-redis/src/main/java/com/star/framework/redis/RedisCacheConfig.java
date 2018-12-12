package com.star.framework.redis;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@PropertySource("classpath:redis.properties")
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {
	
	private Environment environment;
	
//	@Value("${redis.host}")
//	private String host;
//	@Value("${redis.port}")
//	private int port;
	
//	@Bean
//	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
//		return new PropertySourcesPlaceholderConfigurer();
//	}
	
	private String redisHost;
	private int redisPort;
	private int redisDbIndex;
	private int redisMaxIdle;
	private int redisMaxActive;
	private long redisMaxWaitMillis;
	private boolean redisTestOnBorrow;
	private long redisExpiration;
	
	private void init(){
		redisHost = environment.getProperty("redis.host");
		redisPort = Integer.parseInt(environment.getRequiredProperty("redis.port"));
		redisDbIndex = Integer.parseInt(environment.getRequiredProperty("redis.dbIndex"));
		redisMaxIdle = Integer.parseInt(environment.getRequiredProperty("redis.maxIdle"));
		redisMaxActive = Integer.parseInt(environment.getRequiredProperty("redis.maxActive"));
		redisMaxWaitMillis = Long.parseLong(environment.getRequiredProperty("redis.maxWait"));
		redisTestOnBorrow = Boolean.parseBoolean(environment.getRequiredProperty("redis.testOnBorrow"));
		redisExpiration = Long.parseLong(environment.getRequiredProperty("redis.expiration"));
	}
	
	@Bean
	public JedisPoolConfig jedisPoolConfig(){
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(redisMaxIdle);
		poolConfig.setMaxTotal(redisMaxActive);
		poolConfig.setMaxWaitMillis(redisMaxWaitMillis);
		poolConfig.setTestOnBorrow(redisTestOnBorrow);
		return poolConfig;
	}
	
	@Bean
	public JedisConnectionFactory redisConnectionFactory(JedisPoolConfig poolConfig) {
		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory(poolConfig);
		
		redisConnectionFactory.setHostName(redisHost);
		redisConnectionFactory.setPort(redisPort);
		redisConnectionFactory.setDatabase(redisDbIndex);
		return redisConnectionFactory;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory cf) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setConnectionFactory(cf);
		return redisTemplate;
	}

	@Bean
	public CacheManager cacheManager(RedisTemplate<String, Object> redisTemplate) {
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		cacheManager.setDefaultExpiration(redisExpiration);
		return cacheManager;
	}
	
	@Bean
	public KeyGenerator customKeyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object o, Method method, Object... objects) {
				StringBuilder sb = new StringBuilder();
				sb.append(o.getClass().getName());
				sb.append("_");
				sb.append(method.getName());
				for (Object obj : objects) {
					sb.append("_");
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};
	}

	@Autowired
	public void setEnvironment(Environment environment) {
		this.environment = environment;
		init();
	}
}
