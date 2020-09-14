package com.wwc.spring.cloud.client1.config.autoConfig.rabbitmq;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
/**
 * 前缀spring.rabbitmq,属性名称为use,既是spring.rabbitmq.cfc.use属性,
 * havingValue:是指spring.rabbitmq.cfc.use对应的value是否和havingValue进行匹配,符合则返回true;
 * matchIfMissing:在没有配置spring.rabbitmq.cfc.use属性时,若matchIfMissing=true,默认返回true;
 */
@ConditionalOnProperty(prefix = "spring.rabbitmq.cfc",name ="use",havingValue = "true", matchIfMissing =false )
@ConfigurationProperties(prefix = "spring.rabbitmq.cfc")
public class RabbitMQServerConfigProperty {

    private String host;

    private int port;

    private String username;

    private String password;

    private int heartbeat;

    //@Value("${spring.rabbitmq.channelCacheSize}")
    private int channelCacheSize;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getHeartbeat() {
        return heartbeat;
    }

    public void setHeartbeat(int heartbeat) {
        this.heartbeat = heartbeat;
    }

    public int getChannelCacheSize() {
        return channelCacheSize;
    }

    public void setChannelCacheSize(int channelCacheSize) {
        this.channelCacheSize = channelCacheSize;
    }
}
