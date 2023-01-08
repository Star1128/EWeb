package com.ethan.eweb.controller;

import com.ethan.eweb.pojo.Comment;
import com.ethan.eweb.response.CommonState;
import com.ethan.eweb.response.ResponseResult;
import com.ethan.eweb.worker.IdWorker;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/test")
public class TestController {

    @GetMapping("/text")
    public String getText() {
        new IdWorker();
        return "<h1>Hello World!<h1>";
    }

    @GetMapping("/json")
    public ResponseResult getJson() {
        ResponseResult responseResult = new ResponseResult(CommonState.SUCCESS);
        responseResult.setData(new Comment("132511425", "yo bitch"));
        return responseResult;
    }
}
