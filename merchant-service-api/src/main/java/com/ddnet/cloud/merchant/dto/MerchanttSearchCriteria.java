package com.ddnet.cloud.merchant.dto;

import com.ddnet.cloud.search.SearchPage;
import lombok.Getter;
import lombok.Setter;

/**
 * 商品查询条件
 * Created by Vinson.Ding on 2017/8/23.
 */
@Setter
@Getter
public class MerchanttSearchCriteria {
    private String name;
    private SearchPage searchPage;
}
