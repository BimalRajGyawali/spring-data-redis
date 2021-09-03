package com.practice.springdataredis.config;

        import com.practice.springdataredis.models.Book;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.context.annotation.Primary;
        import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
        import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
        import org.springframework.data.redis.core.RedisTemplate;
        import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
        import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
        import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

    @Bean
    public JedisConnectionFactory connectionFactory(){
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName("localhost");
        configuration.setPort(6379);

        return new JedisConnectionFactory(configuration);
    }

    @Bean
    @Primary
    public RedisTemplate<String, Book> bookRedisTemplate(){
        RedisTemplate<String , Book> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory());
        return redisTemplate;
    }

}
