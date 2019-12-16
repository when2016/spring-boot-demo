package com.mysql;

public class TestDB {
    public static void main(String[] args) {
        DBhandle db = new DBhandle();
        long start = System.currentTimeMillis() / 1000;
        for (int i = 428848; i < 7000000; i++) {
            String code = "code" + i;
            String slug = "slug" + i;
            String title = "title" + i;
            String sql = "insert into parts (code,slug,title) values('" + code + "','" + slug + "','" + title + "')";
            db.insert(sql);

        }
        long end = System.currentTimeMillis() / 1000;
        System.out.println((end - start) / 60 + " minutes");
    }
}
