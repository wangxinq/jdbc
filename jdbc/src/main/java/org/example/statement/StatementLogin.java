package org.example.statement;
/*
* 模拟用户登录
* */

import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.Scanner;

/*
* TODO:
*   1，明确jdbc的使用流程 和 详细讲解内部设计api方法
*   2，发现问题，引出prepareStatement
* TODO:
*   1，输入账号和密码
*   2，进行数据库信息查询
*   3，反馈登录成功还是失败
* TODO:
*   1，键盘输入事件，收集账号密码
*   2，注册驱动
*   3，获取连接
*   4，创建statement
*   5，发送查询sql语句，获取查询结果
*   6，结果判断，显示登录成功还是失败
*   7，关闭资源
* */
public class StatementLogin {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        String id = scanner.next();
        String password = scanner.next();
        /*
        * 问题出现:注册两次驱动
        *       1，DriverManager.registerDriver()方法本身会注册一次
        *       2，Driver.static{DriverManager.registerDriver()}静态代码块，也会注册一次
        * 解决方法
        *       只触发静态代码块:
        *           类加载机制:类加载的时刻，会触发静态代码块
        *                    加载 [class文件 -> jvm虚拟机的class对象]
        *                    连接 [验证（检查文件类型）-> 准备（静态变量默认值）-> 解析（触发静态代码块）]
        *                    初始化（静态属性赋真实值）
        *           触发类加载:
        *                    1，new 关键字
        *                    2，调用静态方法
        *                    3，调用静态属性
        *                    4，接口1.8 default默认实现
        *                    5，反射
                             6，子类触发父类
                             7，程序的入口main
        * */
        //方案一
        //DriverManager.registerDriver(new Driver());
        //方案二
        //new Driver();
        //方案三  //反射
        Class.forName("com.mysql.cj.jdbc.Driver");
        /*
        * 详解Connection的参数
        *   三个参数
        *       url,user_id,password
        *       url:jdbc:mysql://127.0.0.1:3306/wz
        *           默认省略:jdbc:mysql:///wz
        *   两个参数
        *       url
        *       info: key:user_id key:password
        *   一个参数
        *       url:
        *           jdbc:mysql://127.0.0.1:3306/wz?user=root&password=root
        * */
        Connection connection = DriverManager.getConnection("jdbc:mysql:///wz","root","wxq147852");
        Statement statement = connection.createStatement();
        String sql = "select * from user where user_id = '"+id+"' and password = '"+password+"';";
        /**
         * SQL分类: DDL(容器创建，修改，删除) DML(插入，修改，删除) DQL(查询) DCL(权限控制) TPL(事务控制语言)
         *
         * 参数：sql 非DQL
         * 返回 int
         *      情况1: DML 返回影响的行数
         *      情况2: 非DML return 0
         *
         * 参数: sql DQL
         * 返回: resultSet 结果封装对象
         * ResultSet resultSet = executeQuery(sql);
         * int i = executeUpdate(sql);
         */

        ResultSet resultSet = statement.executeQuery(sql);
        /**
         * java是一种面向对象的思维，将查询结果封装成了resultSet对象，我们应该理解，内部也是有行和列的
         * 想要进行数据解析，我们需要:1，移动游标指定获取数据行 2，获取指定数据行的列数据
         * 1，游标移动问题
         *      resultSet内部包含一个游标，指定行数据
         *      初始在第一行数据之前
         *      调用next()方法移动下一行
         *      boolean next()   true 可以下移
         *                       false 没有更多数据
         * 2，获取列的数据问题
         *
         */
        while(resultSet.next()){
            String id1 = resultSet.getString("user_id");
            String password1 = resultSet.getString("password");
            System.out.println(id1 + "  " + password1 + "登录成功");
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
