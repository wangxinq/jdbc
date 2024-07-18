package org.example.preparedstatement;

import com.mysql.cj.jdbc.Driver;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PSCurdPart {
    @Test
    public void testInsert() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///wz", "root", "wxq147852");
        String sql = "insert into user(user_id,password) values(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,"test_id");
        preparedStatement.setObject(2,"user_password");
        int rows = preparedStatement.executeUpdate();
        if(rows > 0){
            System.out.println("数据插入成功");
        }
        else{
            System.out.println("数据插入失败");
        }
        preparedStatement.close();
        connection.close();
    }
    @Test
    public void testUpdate(){

    }
    @Test
    public void testDelete(){

    }
    @Test
    public void testSelect() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///wz?user=root&password=wxq147852");
        String sql = "select * from user;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Map<String,Object>> list = new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        while(resultSet.next()){
            Map<String,Object> map = new HashMap<>();
            for(int i = 1; i <= columnCount; i++){
                Object object = resultSet.getObject(i);
                String columnLabel = metaData.getColumnLabel(i);
                map.put(columnLabel,object);
            }
            list.add(map);
        }

        System.out.println(list);
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
