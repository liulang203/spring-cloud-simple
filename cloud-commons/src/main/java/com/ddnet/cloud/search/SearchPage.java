package com.ddnet.cloud.search;

import lombok.Data;

/**
 * 查询分页
 * Created by Vinson.Ding on 2017/8/23.
 */
@Data
public abstract class SearchPage {
    private int page;
    private int pageSize;
}
