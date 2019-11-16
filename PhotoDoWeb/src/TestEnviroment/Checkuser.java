package TestEnviroment;

import util.JDBCTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Properties;

@WebServlet(name = "Checkuser",urlPatterns = {"/checkUser"})
public class Checkuser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String userpwd = request.getParameter("userpwd");

        PrintWriter out =null ;
        out =response.getWriter() ;

//        测试返回数据
//        System.out.println(username+"========="+userpwd);
        //添加数据
        Driver driver = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            driver = new com.mysql.jdbc.Driver();
            String url = "jdbc:mysql://112.124.4.48:3306/photodo";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "123456");
            connection = driver.connect(url, info);

            String sql = "SELECT * FROM photouser WHERE username = ? "
                    + "AND userpwd = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, userpwd);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("登录成功!");
                out.write(username);
            } else {
                System.out.println("用户名和密码不匹配或用户名不存在. ");
                out.write("no");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //6. 关闭数据库资源.
            JDBCTools.release(resultSet,preparedStatement, connection);
            out.close();
        }
        //返回最简单字符串

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);

        //        response.setContentType("application/json;charset=utf-8");//指定返回的格式为JSON格式
//        response.setCharacterEncoding("UTF-8");//setContentType与setCharacterEncoding的顺序不能调换，否则还是无法解决中文乱码的问题
//        String jsonStr ="{\"username\":"+username+",\"userpwd\":"+userpwd+"}";
//        PrintWriter out =null ;
//        out =response.getWriter() ;
//        out.write(jsonStr);
//        out.close();

    }
}
