package org.example.druid;

import com.alibaba.druid.pool.DataSourceClosedException;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DruidUse {
    /*
    * TODO:
    *   直接使用代码设置连接池连接参数方式
    *   1，创建一个Druid连接池对象
    *   2，设置连接池参数
    *   3，获取连接
    *   4，回收连接
    * */
    @Test
    public void testHart() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///wz");
        dataSource.setUsername("root");
        dataSource.setPassword("wxq147852");
        dataSource.setInitialSize(5);
        dataSource.setMaxActive(10);

        //创建连接
        DruidPooledConnection connection = dataSource.getConnection();
        String sql = "select * from user;";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            System.out.println("yes");
        }
        resultSet.close();
        connection.close();
    }
    /*
    * TODO:
    *   读取外部配置文件的方法，实例化druid连接池对象
    * */
    @Test
    public void testSoft() throws Exception{
        Properties properties = new Properties();
        InputStream ips = DruidUse.class.getClassLoader().getResourceAsStream("druid.properties");

        properties.load(ips);
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();

        connection.close();
    }
}
