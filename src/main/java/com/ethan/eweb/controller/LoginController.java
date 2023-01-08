package com.ethan.eweb.controller;

import com.ethan.eweb.response.CommonState;
import com.ethan.eweb.response.ResponseResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author Ethan 2023/1/8
 */
@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    @PostMapping
    public ResponseResult doLogin(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        ResponseResult result = new ResponseResult(CommonState.SUCCESS);
        result.setMsg("登录成功：" + userName);
        result.setData(UUID.randomUUID().toString());
        return result;
    }
}
