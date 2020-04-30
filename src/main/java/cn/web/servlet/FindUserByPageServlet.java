package cn.web.servlet;
import cn.domain.PageBean;
import cn.domain.Student;
import cn.service.Impl.StudentServiceImpl;
import cn.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/FindUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        if(currentPage==null || "".equals(currentPage)){
            currentPage = "1";
        }
        if (rows == null || "".equals(rows)){
            rows = "10";
        }
//        Map<String, String[]> condition = request.getParameterMap();
//        UserService  service  = new UserServiceImpl();
//        PageBean<User>  pageBean= service.findUserByPage(currentPage,rows,condition);
////        System.out.println(pageBean);
//        request.setAttribute("pb",pageBean);
//        request.setAttribute("condition",condition);
//        request.getRequestDispatcher("/list.jsp").forward(request,response);
        StudentService  service = new StudentServiceImpl();
        PageBean<Student> pageBean= service.findUserByPage(currentPage,rows);
        System.out.println(pageBean);
        request.setAttribute("pb",pageBean);
        request.getRequestDispatcher("/list.jsp").forward(request,response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
