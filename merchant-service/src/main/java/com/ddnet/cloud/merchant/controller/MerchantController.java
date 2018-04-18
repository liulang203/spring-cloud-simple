package com.ddnet.cloud.merchant.controller;

import com.ddnet.cloud.merchant.MerchantService;
import com.ddnet.cloud.merchant.dto.MerchantInfo;
import com.ddnet.cloud.merchant.dto.MerchanttSearchCriteria;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Created by Vinson.Ding on 2018/4/18.
 */
@Controller
@RequestMapping("/merchant")
@Slf4j
public class MerchantController implements MerchantService, InitializingBean {
    List<MerchantInfo> merchantInfos = new ArrayList<>();
    MerchantInfo defaultMer = new MerchantInfo();
    AtomicLong atomicLong = new AtomicLong(1L);

    @Override
    public List<MerchantInfo> search(@RequestBody MerchanttSearchCriteria searchCriteria) {
        return merchantInfos.stream()
                .filter(mer -> StringUtils.isEmpty(searchCriteria.getName())
                        || mer.getName().contains(searchCriteria.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public MerchantInfo findOne(@PathVariable("id") Long id) {
        return merchantInfos.stream().filter(mer -> mer.getId() == id).findFirst().orElse(defaultMer);
    }

    @Override
    public Long save(@RequestBody MerchantInfo merchant) {
        if (merchant.getId() > 0) {
            merchantInfos.forEach(mer -> {
                if (merchant.getId() == mer.getId()) {
                    mer.setName(merchant.getName());
                    mer.setPrice(merchant.getPrice());
                    return;
                }
            });
        } else {
            merchant.setId(atomicLong.getAndIncrement());
            merchantInfos.add(merchant);
        }
        return merchant.getId();
    }

    @Override
    public boolean delete(@PathVariable("id") Long id) {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        merchantInfos.add(new MerchantInfo(atomicLong.getAndIncrement(), "罗马帝国", 3000));
        merchantInfos.add(new MerchantInfo(atomicLong.getAndIncrement(), "海泊利安四部曲", 3000));
        merchantInfos.add(new MerchantInfo(atomicLong.getAndIncrement(), "哈利波特", 3000));

    }
}
