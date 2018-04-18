package com.ddnet.cloud.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Vinson.Ding on 2017/8/23.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfo {
    private long id ;
    private String name;
    private int sex;
}
