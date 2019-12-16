package com.code.test.utils;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class TemplateMsg {
    private String template_id;//模板ID
    private String touser;//目标客户：微信openid
    private String form_id;
    private String url;//用户点击模板信息的跳转页面,有值就跳转,没有Ios空白页,Android不跳转
    private String topcolor;//字体颜色
    private Map<String, Object> data; //模板里的数据

    public TemplateMsg() {
    }

    public TemplateMsg(String template_id, String touser, String form_id, Map<String, Object> data) {
        this.template_id = template_id;
        this.touser = touser;
        this.form_id = form_id;
        this.data = data;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getTouser() {
        return touser;
    }

    public String getForm_id() {
        return form_id;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopcolor() {
        return topcolor;
    }

    public void setTopcolor(String topcolor) {
        this.topcolor = topcolor;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    //模板显示值,模板显示颜色
    public static Map<String, String> item(String value, String color) {
        return ImmutableMap.of("value", value, "color", color);
    }

    public static Map<String, String> item(String value) {
        return ImmutableMap.of("value", value);
    }
}
