package com.ethan.eweb.controller;

import com.ethan.eweb.pojo.Comment;
import com.ethan.eweb.response.CommonState;
import com.ethan.eweb.response.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * @author Ethan 2023/1/8
 */
@RestController
@RequestMapping("/post")
public class PostController {

    @PostMapping("/query")
    public ResponseResult postQuery(@PathParam("content") String content) {
        ResponseResult result = new ResponseResult(CommonState.SUCCESS);
        result.setMsg("提交文本成功：" + content);
        return result;
    }

    @PostMapping("/body")
    public ResponseResult postBody(@RequestBody Comment comment) {
        ResponseResult result = new ResponseResult(CommonState.SUCCESS);
        result.setMsg("提交文本成功：" + comment.getCommentContent());
        return result;
    }
}
