package com.mysql;

import java.sql.*;

public class Test {
    static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    // MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称
    static final String USER = "root";
    static final String PASS = "123456";

    public static void main(String[] args) throws SQLException,Exception{
        Connection conn = null;
        Statement stat = null;

        // 注册驱动
        Class.forName("com.mysql.jdbc.Driver");

        // 创建链接
        conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);

        // 执行查询
        stat = conn.createStatement();
        String sql = "SELECT * FROM parts";
        ResultSet rs = stat.executeQuery(sql);
        // 输出查询结果
        while(rs.next()){
            System.out.print(rs.getInt("id")+",");
            System.out.print(rs.getString("code")+",");
            System.out.print(rs.getString("slug")+",");
            System.out.print(rs.getString("title"));
            System.out.print("\n");
        }
        // 关闭
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
