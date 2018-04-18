package com.ddnet.cloud.account.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 会员信息，
 * Created by Vinson.Ding on 2017/8/23.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {
    private Long id ;
    private String name;
    private int sex;
    private String password;
    //用于乐观锁
    private int version;
}
