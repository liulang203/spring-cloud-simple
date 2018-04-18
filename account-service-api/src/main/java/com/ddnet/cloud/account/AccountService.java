package com.ddnet.cloud.account;

import com.ddnet.cloud.account.dto.AccountInfo;
import com.ddnet.cloud.account.dto.AccountSearchCriteria;
import com.ddnet.cloud.account.dto.AccountSignInfo;
import com.ddnet.cloud.account.dto.AccountUpdatePassInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 用户操作服务
 * Created by Vinson.Ding on 2017/8/23.
 */

public interface AccountService {
    /**
     * 查询用户
     *
     * @param accountSearchCriteria 查询条件
     * @return 用户信息列表
     */
    @RequestMapping(method = RequestMethod.POST, path = "/search")
    List<AccountInfo> searchAccounts(@RequestBody AccountSearchCriteria accountSearchCriteria);

    /**
     * 根据用户名查询用户信息
     *
     * @param name 用户名
     * @return 用户信息
     */
    @RequestMapping(method = RequestMethod.GET, path = "/findByName/{name}")
    AccountInfo findOneAccount(@PathVariable("name") String name);

    /**
     * 用户登录
     *
     * @param signInfo 登录信息
     * @return 是否登录成功
     */
    @RequestMapping(method = RequestMethod.POST, path = "/sign")
    boolean sign(@RequestBody AccountSignInfo signInfo);

    /**
     * 更新用户密码
     *
     * @param accountUpdatePassInfo 更新用户密码
     * @return 是否更新成功
     */
    @RequestMapping(method = RequestMethod.POST, path = "/updatePasswd")
    boolean updateAccountPasswd(@RequestBody AccountUpdatePassInfo accountUpdatePassInfo);

    /**
     * 创建新用户
     *
     * @param accountInfo 用户信息
     * @return 新用户
     */
    @RequestMapping(method = RequestMethod.POST)
    Long newAccount(@RequestBody AccountInfo accountInfo);

    /**
     * 删除账户
     * @param id
     */
    @RequestMapping("/delete/{id}")
    boolean deleteAccount(@PathVariable("id") long id);
}
