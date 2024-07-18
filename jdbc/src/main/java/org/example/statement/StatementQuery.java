package org.example.statement;

import com.mysql.cj.jdbc.Driver;
import java.sql.*;

/*
*  TODO:
*       DriverManager
*       Connection
*       Statement
*       ResultSet
* */
public class StatementQuery {
    public static void main(String[] args) throws SQLException {
        /*  1，注册驱动
        * TODO:
        *  注册驱动
        *      依赖：驱动版本 8+ com.mysql.cj.jdbc.Driver
        *           驱动版本 5+ com.mysql.jdbc.Driver
        * */
        DriverManager.registerDriver(new Driver());
        /*  2，获取连接
        *   TODO:
        *    java程序和数据库连接
        *    调用某个方法，填入数据库的基本信息：
        *       数据库ip地址 127.0.0.1
        *       数据库端口号 3306
        *       账号 root
        *       密码 password
        *       连接数据库的名称
        */
        /*
         * 参数1:url
         *          jdbc:数据库厂商名://ip地址:port/数据库名
         *          jdbc:mysql://127.0.0.1:3306/wz
         * 参数2:username
         * 参数3:password
         */
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/wz","root","wxq147852");
        /*  3，创建statement
        * */
        Statement statement = connection.createStatement();
        /*  4，发送sql语句，并获取返回结果
        * */
        String sql = "select * from sheet2";
        ResultSet resultSet = statement.executeQuery(sql);
        /*  5，结果解析
        * */
        while(resultSet.next()){
            String name = resultSet.getString("name");
            String course = resultSet.getString("course");
            String class1 = resultSet.getString("class");
            System.out.println(name + "  " + course + "  " + class1);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
