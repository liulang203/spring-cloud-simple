package com.ddnet.cloud.merchant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 商品信息
 * Created by Vinson.Ding on 2017/8/23.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MerchantInfo {
    private long id ;
    private String name;
    private int price;
}
