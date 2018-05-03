package com.ddnet.cloud.store.web;

import com.ddnet.cloud.account.AccountService;
import com.ddnet.cloud.account.dto.AccountSearchCriteria;
import com.ddnet.cloud.fclient.account.AccountClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Vinson.Ding on 2017/8/24.
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountClient accountService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("accounts", accountService.searchAccounts(new AccountSearchCriteria()));
        return "/account/list";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/name/{name}")
    public String detailById(@PathVariable String name, Model model) {
        model.addAttribute("account", accountService.findOneAccount(name));
        return "/account/detail";
    }
    @RequestMapping(method = RequestMethod.GET, path = "/name/{name}/edit")
    public String editForm(@PathVariable String name, Model model) {
        model.addAttribute("account", accountService.findOneAccount(name));
        return "/account/edit";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/id/{id}/delete")
    public String deleteById(@PathVariable long id) {
        accountService.deleteAccount(id);
        return "redirect:/account";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{name}")
    public String list(@PathVariable String name, Model model) {
        model.addAttribute("account", accountService.findOneAccount(name));
        return "/account/detail";
    }

}
