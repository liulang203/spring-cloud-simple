package com.ddnet.cloud.account.dao;

import com.ddnet.cloud.account.dto.AccountSearchCriteria;
import com.ddnet.cloud.account.entity.Account;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟数据操作。
 * Created by Vinson.Ding on 2017/8/23.
 */
@Service
public class AccountDao implements InitializingBean {
    private List<Account> accounts = new ArrayList<>();

    public long newAccount(Account account) {
        //此处省去各种校验
        accounts.add(account);
        return account.getId();
    }

    public Account searchAccount(String name) {
        Preconditions.checkArgument(name != null, "姓名不能为空");
        for (Account account : accounts) {
            if (name.equals(account.getName())) {
                return account;
            }
        }
        return null;
    }

    public List<Account> searchAccounts(AccountSearchCriteria searchCriteria) {
        return new ArrayList<>(accounts);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        accounts.add(new Account(1L, "jason", 1, "jason", 1));
        accounts.add(new Account(2L, "vison", 1, "vison", 2));
        accounts.add(new Account(3L, "sun", 1, "sun", 4));
    }

    public boolean delete(long id) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getId() == id) {
                accounts.remove(i);
                return true;
            }
        }
        return false;
    }
}
