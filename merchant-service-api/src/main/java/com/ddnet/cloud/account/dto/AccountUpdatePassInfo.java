package com.ddnet.cloud.account.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 更新密码信息
 * Created by Vinson.Ding on 2017/8/23.
 */
@Getter
@Setter
public class AccountUpdatePassInfo {
    private String name;
    private String password;
    private String oldPassword;
}
