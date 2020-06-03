package com.how2java.tmall.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 转为json，返回对象
 */
public class ResultInfo {
    private String msg;
    private Map<String, Object> data; //存储的数据
    private boolean flag;

    public ResultInfo(){
        data = new HashMap<>();
    }

    /**
     * 成功
     * @return
     */
    public static ResultInfo success(){
        ResultInfo info = new ResultInfo();
        info.setFlag(true);
        info.setMsg("处理成功!");
        return info;
    }

    /**
     * 失败
     * @return
     */
    public static ResultInfo error(){
        ResultInfo info = new ResultInfo();
        info.setFlag(false);
        info.setMsg("处理失败!");
        return info;
    }

    /**
     * 添加数据
     * @param key
     * @param obj
     * @return
     */
    public ResultInfo add(String key, Object obj){
        getData().put(key, obj);
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
