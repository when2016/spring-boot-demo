package com.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
 * mysql数据库操作
 * @@author
 * 创建时间：
 * 更新时间：
 *
 */
public class DBhandle {

    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/";
    private static String db = "test";
    private static String character = "?useUnicode=true&characterEncoding=utf-8";
    private static String user = "root";
    private static String pass = "123456";

    static Connection conn = null;
    static Statement statement = null;
    static PreparedStatement ps = null;
    static ResultSet rs = null;
    List<Map> list = new ArrayList<Map>();//返回所有记录

    /*
     * 连接数据库
     */
    public static void connDB() {
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+db+character, user, pass);
            if (!conn.isClosed()) {
                System.out.println("Succeeded connecting to MySQL!");
            }

            statement = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 关闭数据库
     */
    public static void closeDB() {
        if(rs != null ){
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
                System.out.println("Database connection terminated!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * 查询数据表
     */
    public List query(String sql){
        connDB();
        int count;
        try {
            rs = statement.executeQuery(sql);
            ResultSetMetaData rsmd;
            rsmd = rs.getMetaData();
            count = rsmd.getColumnCount();
            while(rs.next()){
                Map  map = new HashMap();
                for(int i=1;i<=count;i++){
                    //获取指定列的表目录名称
                    String label=rsmd.getColumnLabel(i);
                    //以 Java 编程语言中 Object 的形式获取此 ResultSet 对象的当前行中指定列的值
                    Object object= rs.getObject(i);
                    //把数据库中的字段名和值对应为一个map对象中的一个键值对
                    map.put(label.toLowerCase(), object);
                }
                list.add(map);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return list;
    }

    /*
     * 数据插入
     */
    public void insert(String sql){
        connDB();
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            closeDB();
        }
    }

    /*
     * 数据更新
     */
    public void update(String sql){
        connDB();
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            closeDB();
        }
    }

    /*
     * 数据删除
     */
    public void delete(String sql){
        connDB();
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            closeDB();
        }
    }
}
