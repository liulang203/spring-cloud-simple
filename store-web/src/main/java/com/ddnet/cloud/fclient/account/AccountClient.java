package com.ddnet.cloud.fclient.account;

import com.ddnet.cloud.account.AccountService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by Vinson.Ding on 2018/5/3.
 */
@FeignClient(value = "account-service",path = "/account")
public interface AccountClient extends AccountService {
}
