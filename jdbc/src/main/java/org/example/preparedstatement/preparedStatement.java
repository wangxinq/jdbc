package org.example.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class preparedStatement {
    public static void main(String[] args) throws Exception {
        //1，注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.next();
        String password = scanner.next();
        //2，获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///wz?user=root&password=wxq147852");
        //2，编写preparedStatement
        /*
         *statement
         *   创建statement
         *   拼接sql语句
         *   发送sql语句，并获取返回结果
         * TODO:
         *   preparedStatement
         *       1，编写sql语句结果，不包含动态值部分的语句，动态值部分用占位符？代替
         *       2，创建preparedStatement,并传入动态值
         *       3，动态值 占位符 赋值 ？ 单独赋值即可
         *       4，发送sql语句即可，并获取返回结果
         * */
        //3，编写sql语句结果
        String sql = "select * from user where user_id = ? and password = ?;";
        //4，创建预编译statement并且设置SQL语句结果
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //5，单独的占位符进行赋值
        preparedStatement.setObject(1, id);
        preparedStatement.setObject(2, password);
        //6，发送sql语句，获取返回结果
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败");
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
