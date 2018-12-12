package com.star.framework.redis;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import redis.clients.jedis.JedisPoolConfig;

//@Configuration
@PropertySource("classpath:redis.pool.properties")
@EnableCaching
public class RedisPoolCacheConfig extends CachingConfigurerSupport {
	
	private Environment environment;
	
	private List<Host> hosts;
	private int redisMaxIdle;
	private int redisMaxActive;
	private long redisMaxWaitMillis;
	private boolean redisTestOnBorrow, testWhileIdle, blockWhenExhausted;
	private long redisExpiration;
	private long timeBetweenEvictionRunsMillis;
	private long minEvictableIdleTimeMillis;
	private long softMinEvictableIdleTimeMillis;
	private int numTestsPerEvictionRun;
	private int maxRedirects;
	
	private void init(){
		String redisHost = environment.getProperty("redis.host");
		try {
			String[] temp = redisHost.split(",");
			hosts = new ArrayList<>();
			for (int i = 0; i < temp.length; i++) {
				String[] t = temp[i].split(":");
				hosts.add(new Host(t[0], Integer.parseInt(t[1])));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		maxRedirects = Integer.parseInt(environment.getRequiredProperty("redis.maxRedirects"));
		redisMaxIdle = Integer.parseInt(environment.getRequiredProperty("redis.pool.maxIdle"));
		redisMaxActive = Integer.parseInt(environment.getRequiredProperty("redis.pool.maxTotal"));
		numTestsPerEvictionRun = Integer.parseInt(environment.getRequiredProperty("redis.pool.numTestsPerEvictionRun"));
		redisMaxWaitMillis = Long.parseLong(environment.getRequiredProperty("redis.pool.maxWaitMillis"));
		redisTestOnBorrow = Boolean.parseBoolean(environment.getRequiredProperty("redis.pool.testOnBorrow"));
		testWhileIdle = Boolean.parseBoolean(environment.getRequiredProperty("redis.pool.testWhileIdle"));
		blockWhenExhausted = Boolean.parseBoolean(environment.getRequiredProperty("redis.pool.blockWhenExhausted"));
		redisExpiration = Long.parseLong(environment.getRequiredProperty("redis.expiration"));
		timeBetweenEvictionRunsMillis = Long.parseLong(environment.getRequiredProperty("redis.pool.timeBetweenEvictionRunsMillis"));
		minEvictableIdleTimeMillis = Long.parseLong(environment.getRequiredProperty("redis.pool.minEvictableIdleTimeMillis"));
		softMinEvictableIdleTimeMillis = Long.parseLong(environment.getRequiredProperty("redis.pool.softMinEvictableIdleTimeMillis"));
	}
	
	private class Host {
		private String host;
		private int port;
		
		public Host(String host, int port){
			this.host = host;
			this.port = port;
		}

		public String getHost() {
			return host;
		}

		public int getPort() {
			return port;
		}
	}
	
	@Bean
	public JedisPoolConfig jedisPoolConfig(){
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(redisMaxIdle);
		poolConfig.setMaxTotal(redisMaxActive);
		poolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
		poolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		poolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		poolConfig.setSoftMinEvictableIdleTimeMillis(softMinEvictableIdleTimeMillis);
		poolConfig.setMaxWaitMillis(redisMaxWaitMillis);
		poolConfig.setTestOnBorrow(redisTestOnBorrow);
		poolConfig.setTestWhileIdle(testWhileIdle);
		poolConfig.setBlockWhenExhausted(blockWhenExhausted);
		return poolConfig;
	}
	
	@Bean
	public RedisClusterConfiguration clusterConfig(){
		RedisClusterConfiguration clusterConfig = new RedisClusterConfiguration();
		clusterConfig.setMaxRedirects(maxRedirects);
		List<RedisNode> nodes = new ArrayList<>();
		for (Host host : hosts) {
			RedisNode node = new RedisNode(host.getHost(), host.getPort());
			nodes.add(node);
		}
		clusterConfig.setClusterNodes(nodes);
		return clusterConfig;
	}
	
	@Bean
	public JedisConnectionFactory redisConnectionFactory(RedisClusterConfiguration clusterConfig, JedisPoolConfig poolConfig) {
		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory(clusterConfig, poolConfig);
		return redisConnectionFactory;
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
		redisTemplate.setConnectionFactory(cf);
		return redisTemplate;
	}

	@Bean
	public CacheManager cacheManager(RedisTemplate<String, String> redisTemplate) {
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
