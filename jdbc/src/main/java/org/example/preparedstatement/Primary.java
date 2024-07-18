package org.example.preparedstatement;

import org.junit.Test;

import java.sql.*;

/**
 * 主键自增长
 */
public class Primary {
    /*
    * TODO:
    *   user表插入一条数据，并且获取数据库自增长的主键
    * TODO:
    *   使用总结
    *       1，创建preparedStatement的时候，告知，携带回数据库自增长的主键(sql,Statement.RETURN_GENERATED_KEYS)
    *       2，获取主键结果集对象 ResultSet resultSet = statement.getGeneratedKeys();
    * */
    @Test
    public void PrimaryKey() throws Exception{
        //1，注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2，获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///wz","root","wxq147852");
        //3，编写SQL语句
        String sql = "insert into user(user_id,password) values(?,?)";
        //4，创建preparedStatement
        PreparedStatement statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        //5，占位符取值
        for (int i = 0; i < 10; i++) {
            statement.setObject(1,"test_id"+i+2);
            statement.setObject(2,"test_password"+i+2);
            statement.addBatch();
        }
        statement.executeBatch();
        //发送sql语句，并获取结果
        int i = statement.executeUpdate();
        if(i > 0){
            System.out.println("插入数据成功");
            /*//获取回显的主键
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            int id = resultSet.getInt(1);
            System.out.println(id);*/
        }
        else{
            System.out.println("失败");
        }
        statement.close();
        connection.close();
    }
}
