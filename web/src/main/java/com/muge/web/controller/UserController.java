package com.muge.web.controller;

import com.muge.web.entity.User;
import com.muge.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list")
    public String list(Model model, Pageable pageable) {
        Page<User> users = this.userService.findAll(pageable);
        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        this.userService.delete(id);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/checkUserName", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody Map<String, Object> checkUserName(String userName) {
        Map<String, Object> datas = new HashMap<>();
        if (this.userService.checkUserName(userName)) {
            datas.put("flag", true);
        } else {
            datas.put("flag", false);
        }
        return datas;
    }

}
