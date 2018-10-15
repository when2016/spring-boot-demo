package com.code.test.utils;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public enum TemplateEnum {

    ACTIVITY_JOIN("已报名活动参加提醒", "3mpb6Xqo8z63qf8aXGSekZfhvhJjgtNQMXWvXGpsrPI"),
    ORDER_DELIVERY("订单发货提醒", "QVgfXcDa06_rC0-OZS6TND7KWzNloB57KyEn9yNQh6Q"),
    FRIEND_HELP("好友帮助结果通知", "tRnZ5L0yG2yNFBhANXKhpb5FqMaLHXm-1JRnYfJJCeg"),
    INVITER_SUCCESS("邀请成功通知", "umizF1Rb-LknrJI0boleZWXH3bdQFNTEIc8toSrEfNU");

    private String name;
    private String id;

    TemplateEnum(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public static Map getData(String id) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("keyword1", TemplateMsg.item("步数赚钱"));
        // 根据具体模板参数组装
        if (StringUtils.equals(id, TemplateEnum.ACTIVITY_JOIN.id)) {
            params.put("keyword2", TemplateMsg.item("每日点击黄色圆圈把步数兑Bu币"));
            params.put("keyword3", TemplateMsg.item("今日步数即将清零，兑成Bu币可购换抖音神器和扫地机"));
        } else if (StringUtils.equals(id, TemplateEnum.INVITER_SUCCESS.id)) {
            params.put("keyword2", TemplateMsg.item("恭喜你，已经成功邀请1位好友成功，距离获得您心仪的礼品越来越近了"));
            params.put("keyword3", TemplateMsg.item("分离到群有助于找到更多互助好友"));
        }

        return params;
    }

}
