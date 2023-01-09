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
        if (userName != null && userName.length() != 0) {
            ResponseResult result;
            if (password != null && password.length() != 0) {
                result = new ResponseResult(CommonState.LOGIN_SUCCESS);
                result.setMsg("登录成功：" + userName);
                result.setData(UUID.randomUUID().toString());
            } else {
                result = new ResponseResult(CommonState.LOGIN_FAILED);
                result.setMsg("密码不能为空");
            }
            return result;
        } else {
            ResponseResult result = new ResponseResult(CommonState.LOGIN_FAILED);
            result.setMsg("用户名不能为空");
            return result;
        }
    }
}
