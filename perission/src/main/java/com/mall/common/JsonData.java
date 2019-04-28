package com.mall.common;


import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 自动生成 get set 方法的注解
 */
@Setter
@Getter
public class JsonData {

    /**
     * 返回的结果
     */
    private Boolean ret;

    /**
     * 返回的消息
     */
    private String msg;

    /**
     * 返回给前台的数据
     */
    private Object data;


    public JsonData(boolean ret) {
        this.ret = ret;
    }

    /**
     * 成功时的回调
     *
     * @param data
     * @param msg
     * @return
     */
    public static JsonData success(Object data, String msg) {
        JsonData jsonData = new JsonData(true);
        jsonData.data = data;
        jsonData.msg = msg;
        return jsonData;
    }

    public static JsonData success(Object data) {
        JsonData jsonData = new JsonData(true);
        jsonData.data = data;
        return jsonData;
    }


    public static JsonData success() {
        return new JsonData(true);
    }

    /**
     * 失败时的回调
     *
     * @param msg
     * @return
     */
    public static JsonData fail(String msg) {
        JsonData jsonData = new JsonData(false);
        jsonData.msg = msg;
        return jsonData;
    }


    public Map<String, Object> toMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("ret", ret);
        map.put("msg", msg);
        map.put("data", data);

        return map;
    }


}
