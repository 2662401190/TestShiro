package com.mall.controller;


import com.mall.common.JsonData;
import com.mall.param.TestVo;
import com.mall.util.BeanValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @GetMapping("/hello.json")
    public JsonData hello(){

        log.info("hello");
        return  JsonData.success("hrllo");
    }


    @GetMapping("/validate.json")
    public JsonData validate(TestVo testVo) {
        log.info("validate");
        try {
            Map<String, String> map = BeanValidator.validateObject(testVo);
            if (map != null && map.entrySet().size() > 0) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    log.info("{}->{}", entry.getKey(), entry.getValue());
                }
            }
        } catch (Exception e) {
        }
        return  JsonData.success("test validate");
    }
}
