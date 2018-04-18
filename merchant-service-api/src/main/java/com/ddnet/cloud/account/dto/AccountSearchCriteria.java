package com.ddnet.cloud.account.dto;

import com.ddnet.cloud.search.SearchPage;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户查询类
 * Created by Vinson.Ding on 2017/8/23.
 */
@Setter
@Getter
public class AccountSearchCriteria {
    private String name;
    private SearchPage searchPage;
}
