package com.ddnet.cloud.account;

import com.ddnet.cloud.account.dao.AccountDao;
import com.ddnet.cloud.account.dto.AccountInfo;
import com.ddnet.cloud.account.dto.AccountSearchCriteria;
import com.ddnet.cloud.account.dto.AccountSignInfo;
import com.ddnet.cloud.account.dto.AccountUpdatePassInfo;
import com.ddnet.cloud.account.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 用户对外接口类
 * Created by Vinson.Ding on 2017/8/23.
 */
@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
    public List<AccountInfo> searchAccounts(@RequestBody AccountSearchCriteria accountSearchCriteria) {
        //测试断路器
//        if (new Random().nextInt(2) == 1) {
//            log.warn("search account error");
//            throw new InvalidParameterException("parameter error");
//        }
        return AccountTransfor.fromAccount(accountDao.searchAccounts(accountSearchCriteria));
    }

    @Override
    public AccountInfo findOneAccount(@PathVariable("name") String name) {
        return AccountTransfor.fromAccount(accountDao.searchAccount(name));
    }

    @Override
    public boolean sign(@RequestBody AccountSignInfo signInfo) {
        Account account = accountDao.searchAccount(signInfo.getName());

        return account != null && account.getPassword().equals(signInfo.getPassword());
    }

    @Override
    public boolean updateAccountPasswd(@RequestBody AccountUpdatePassInfo accountUpdatePassInfo) {
        Account account = accountDao.searchAccount(accountUpdatePassInfo.getName());
        if (account != null && account.getPassword().equals(accountUpdatePassInfo.getOldPassword())) {
            account.setPassword(accountUpdatePassInfo.getPassword());
            return true;
        }
        return false;
    }

    @Override
    public Long newAccount(@RequestBody AccountInfo accountInfo) {
        return accountDao.newAccount(new Account(accountInfo.getId(), accountInfo.getName(), accountInfo.getSex(), "", 0));
    }

    @Override
    public boolean deleteAccount(@PathVariable("id") long id) {
        return accountDao.delete(id);
    }

    static class AccountTransfor {
        static AccountInfo fromAccount(Account account) {
            return account == null ? null : new AccountInfo(account.getId(), account.getName(), account.getSex());
        }

        static List<AccountInfo> fromAccount(List<Account> accounts) {
            List<AccountInfo> accountInfos = new ArrayList<>(accounts.size());
            accounts.forEach(a -> accountInfos.add(fromAccount(a)));
            return accountInfos;
        }
    }
}
