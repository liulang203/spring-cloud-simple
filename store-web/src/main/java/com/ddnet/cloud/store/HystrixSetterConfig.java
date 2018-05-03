package com.ddnet.cloud.store;

import com.netflix.hystrix.*;
import feign.Feign;
import feign.Target;
import feign.hystrix.SetterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * Created by Vinson.Ding on 2017/8/30.
 */
@Configuration
public class HystrixSetterConfig {
//    @Bean("accountSetterFactory")
    public SetterFactory accountSetter() {
        return (Target<?> target, Method method) -> HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("account-service"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("account-pool"))
                .andCommandKey(HystrixCommandKey.Factory.asKey(Feign.configKey(target.type(), method)))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.defaultSetter()
                        .withCoreSize(20).withMaxQueueSize(100))
                .andCommandPropertiesDefaults(HystrixCommandProperties.defaultSetter()
                        .withExecutionTimeoutInMilliseconds(300));
    }
//    @Bean("merchantSetterFactory")
    public SetterFactory merchantSetter() {
        return (Target<?> target, Method method) -> HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("merchant-service"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("merchant-service"))
                .andCommandKey(HystrixCommandKey.Factory.asKey(Feign.configKey(target.type(), method)))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.defaultSetter()
                        .withCoreSize(15).withMaximumSize(20).withMaxQueueSize(1000))
                .andCommandPropertiesDefaults(HystrixCommandProperties.defaultSetter()
                        .withExecutionTimeoutInMilliseconds(300));
    }
}
