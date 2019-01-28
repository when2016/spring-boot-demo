package com.code.db;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.Properties;

public class TestBlob {
    public static void main(String[] args) {
        insertBlob();
        getBlob();

    }

    public static void getBlob(){//读取Blob数据
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = JDBCTools.getConnection();
            String sql = "SELECT id,task_id,data,test FROM abc WHERE id=1";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int age = rs.getInt(3);

                Blob picture = rs.getBlob(3);//得到Blob对象
                //开始读入文件
                InputStream in = picture.getBinaryStream();
                OutputStream out = new FileOutputStream("cat.png");
                byte[] buffer = new byte[1024];
                int len = 0;
                while((len = in.read(buffer)) != -1){
                    out.write(buffer, 0, len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertBlob(){//插入Blob
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = JDBCTools.getConnection();
            String sql = "INSERT INTO abc(task_id,data,test) VALUES(?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, 8);
            ps.setString(2, "aaaaaaaaaaaa");
            //ps.setString(3, "bbbbbbbbbbbbbbbb");
            InputStream in = new FileInputStream("J:/test1/TomCat.png");//生成被插入文件的节点流
            //设置Blob
            ps.setBlob(3, in);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JDBCTools.release(con, ps);
        }
    }


    static class JDBCTools {//JDBC工具类  用来建立连接和释放连接
        public static Connection getConnection() throws Exception{//连接数据库
            String driverClass = null;
            String url = null;
            String user = null;
            String password = null;

            Properties properties = new Properties();

            //InputStream in = Review.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //properties.load(in);


            driverClass = "com.mysql.jdbc.Driver";
            url = "jdbc:mysql://localhost:3306/test";
            user = "root";
            password = "123456";
            Class.forName(driverClass);
            return DriverManager.getConnection(url, user, password);
        }

        public static void release(Connection con , Statement state){//关闭数据库连接
            if(state != null){
                try {
                    state.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        public static void release(ResultSet rs , Connection con , Statement state){//关闭数据库连接
            if(rs != null)
            {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(state != null){
                try {
                    state.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
