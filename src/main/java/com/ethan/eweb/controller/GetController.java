package com.ethan.eweb.controller;

import com.ethan.eweb.pojo.ListItem;
import com.ethan.eweb.response.CommonState;
import com.ethan.eweb.response.ResponseResult;
import com.ethan.eweb.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ethan 2023/1/8
 */
@RestController
@CrossOrigin
@RequestMapping("/get")
public class GetController {
    @Autowired
    private DataService dataService;

    @GetMapping({"/text"})
    public ResponseResult getJson() {
        List<ListItem> list = dataService.generateJson(true);
        ResponseResult result;
        if (list == null || list.size() == 0) {
            result = new ResponseResult(CommonState.FAIL);
        } else {
            result = new ResponseResult(CommonState.SUCCESS);
            result.setData(list);
        }
        return result;
    }

    @GetMapping({"/param"})
    public ResponseResult getParam(@PathParam("language") String language, @PathParam("order") String order) {
        if (!language.equals("ch") && !language.equals("en")) {
            ResponseResult result = new ResponseResult(CommonState.FAIL);
            result.setMsg("language参数非法");
            return result;
        } else if (!order.equals("0") && !order.equals("1")) {
            ResponseResult result = new ResponseResult(CommonState.FAIL);
            result.setMsg("order参数非法");
            return result;
        }

        List<ListItem> list;
        if (language.equals("ch")) {
            list = dataService.generateJson(true);
        } else {
            list = dataService.generateJson(false);
        }

        if (order.equals("1")) {
            Collections.reverse(list);
        }

        Map<String, String> meta = new HashMap<>();
        meta.put("language", language);
        meta.put("order", order.equals("0") ? "顺序" : "逆序");
        ResponseResult result = new ResponseResult(CommonState.SUCCESS);
        result.setMsg("GET带参数请求成功");

        List<Object> wrapper = new ArrayList<>();
        wrapper.add(meta);
        wrapper.add(list);

        result.setData(wrapper);
        return result;
    }
}
