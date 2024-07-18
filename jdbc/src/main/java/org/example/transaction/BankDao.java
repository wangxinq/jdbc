package org.example.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/*
* bank表的数据库操作
* */
public class BankDao {
    /*
    * 加载数据库的操作方法（jdbc）
    * account 加钱的行号
    * money   加钱的金额
    * */
    public void add(String account,int money,Connection connection) throws Exception{
        String sql = "update bank set money = money + ? where account = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setObject(1,money);
        statement.setObject(2,account);
        statement.executeUpdate();
        statement.close();
        System.out.println("加钱成功");
    }
    /*
     * 加载数据库的操作方法（jdbc）
     * account 减钱的行号
     * money   减钱的金额
     * */
    public void sub(String account,int money,Connection connection) throws Exception{
        String sql = "update bank set money = money - ? where account = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setObject(1,money);
        statement.setObject(2,account);
        statement.executeUpdate();
        statement.close();
        System.out.println("减钱成功");
    }

}
