package com.ddnet.cloud.store;

import com.ddnet.cloud.account.AccountService;
import com.ddnet.cloud.account.dto.AccountInfo;
import com.ddnet.cloud.account.dto.AccountSearchCriteria;
import com.ddnet.cloud.account.dto.AccountSignInfo;
import com.ddnet.cloud.account.dto.AccountUpdatePassInfo;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandKey;
import feign.Client;
import feign.Feign;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.hystrix.FallbackFactory;
import feign.hystrix.HystrixFeign;
import feign.hystrix.SetterFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.cloud.netflix.feign.ribbon.FeignRibbonClientAutoConfiguration;
import org.springframework.cloud.netflix.feign.ribbon.LoadBalancerFeignClient;
import org.springframework.cloud.netflix.feign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 * Created by Vinson.Ding on 2017/8/30.
 */
@Configuration
@Import({FeignClientsConfiguration.class, FeignRibbonClientAutoConfiguration.class})
@Slf4j
public class FeiginConfig {

    @Autowired
    private Client client;
    @Autowired
    private  Decoder feignDecoder;
    @Autowired
    private Encoder feignEncoder;

    /**
     * 该配置办法会导致Zipkin无法进行调用链跟踪
     * @param accountSetterFactory
     * @return
     */
//    @Bean
    AccountService accountService(SetterFactory accountSetterFactory) {
        return HystrixFeign.builder()
                .setterFactory(accountSetterFactory)
                .contract(new SpringMvcContract())
                .encoder(feignEncoder)
                .decoder(feignDecoder)
                .client(client)
                .target(AccountService.class, "http://account-service/account", (FallbackFactory<AccountService>) cause -> {
                    //系统错误调用用的默认实现，发生熔断后调用该接口。
                    log.error("account service error",cause);
                    return new AccountService() {
                        @Override
                        public List<AccountInfo> searchAccounts(AccountSearchCriteria accountSearchCriteria) {
                            return Collections.EMPTY_LIST;
                        }

                        @Override
                        public AccountInfo findOneAccount(String name) {
                            return null;
                        }

                        @Override
                        public boolean sign(AccountSignInfo signInfo) {
                            return false;
                        }

                        @Override
                        public boolean updateAccountPasswd(AccountUpdatePassInfo accountUpdatePassInfo) {
                            return false;
                        }

                        @Override
                        public Long newAccount(AccountInfo accountInfo) {
                            return null;
                        }

                        @Override
                        public boolean deleteAccount(long id) {
                            return false;
                        }
                    };
                });
    }
}
