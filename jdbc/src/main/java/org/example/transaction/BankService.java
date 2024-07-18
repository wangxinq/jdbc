package org.example.transaction;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class BankService {
    @Test
    public void test() throws Exception{
        transfer("4","1",100);
    }
    public void transfer(String addAccount,String subAccount,int money) throws Exception {
        BankDao bankDao = new BankDao();
        /*
         *TODO:
         *  一个事务最基本的要求，必须是同一个连接对象 connection
         *  一个转账方法，属于一个事务
         */
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///wz","root","wxq147852");
        try{
            //开启事务，关闭事务提交
            connection.setAutoCommit(false);
            //执行数据库
            bankDao.add(addAccount,money,connection);
            bankDao.sub(subAccount,money,connection);
            //事务提交
            connection.commit();
        }catch(Exception e){
            //事务回滚
            connection.rollback();
            throw e;
        }finally {
            connection.close();
        }

    }
}
