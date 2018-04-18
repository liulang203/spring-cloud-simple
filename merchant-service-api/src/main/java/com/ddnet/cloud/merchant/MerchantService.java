package com.ddnet.cloud.merchant;

import com.ddnet.cloud.merchant.dto.MerchantInfo;
import com.ddnet.cloud.merchant.dto.MerchanttSearchCriteria;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品服务类
 * Created by Vinson.Ding on 2017/8/23.
 */

public interface MerchantService {
    @RequestMapping(method = RequestMethod.POST, path = "/search")
    @ResponseBody
    List<MerchantInfo> search(@RequestBody MerchanttSearchCriteria searchCriteria);

    @RequestMapping(method = RequestMethod.GET, path = "/id/{id}")
    @ResponseBody
    MerchantInfo findOne(@PathVariable("id") Long id);

    @RequestMapping(method = RequestMethod.POST,path = "/save")
    @ResponseBody
    Long save(@RequestBody MerchantInfo merchant);

    @RequestMapping("/delete/{id}")
    @ResponseBody
    boolean delete(@PathVariable("id") Long id);
}
