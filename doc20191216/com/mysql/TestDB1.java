package com.mysql;

public class TestDB1 {

    public static void main(String[] args) {
        DBhandle db = new DBhandle();
        long start = System.currentTimeMillis() / 1000;
        for (int i = 1; i < 70000000; i++) {
            String code = "code" + i;
            String sku = "sku" + i;
            String description = "description" + i;
            String sql = "insert into details (sku,description,part_code) values('" + sku + "','" + description + "','" + code + "')";
            db.insert(sql);

        }
        long end = System.currentTimeMillis() / 1000;
        System.out.println((end - start) / 60 + " minutes");
    }

}
