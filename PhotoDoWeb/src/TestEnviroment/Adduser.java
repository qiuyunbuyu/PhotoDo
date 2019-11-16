package TestEnviroment;

import util.JDBCTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import util.JDBCTools;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

@WebServlet(name = "Adduser",urlPatterns = {"/addUser"})
public class Adduser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String userpwd = request.getParameter("userpwd");

//        测试返回数据
        System.out.println(username+"========="+userpwd);
        //添加数据
        Driver driver = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            driver = new com.mysql.jdbc.Driver();
            String url = "jdbc:mysql://112.124.4.48:3306/photodo";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "123456");
            connection = driver.connect(url, info);

            String sql = "INSERT INTO  photouser VALUES(?,?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, userpwd);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //6. 关闭数据库资源.
            JDBCTools.release(preparedStatement, connection);
        }

        //返回最简单字符串
        PrintWriter out =null ;
        out =response.getWriter() ;
        out.write("ok");
        out.close();
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
