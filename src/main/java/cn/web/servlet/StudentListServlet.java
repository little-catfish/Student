package cn.web.servlet;

import cn.domain.Student;
import cn.service.Impl.StudentServiceImpl;
import cn.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/StudentListServlet")
public class StudentListServlet extends HttpServlet {
    private StudentService service = new StudentServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> list = service.findAll();
        request.setAttribute("list",list);

// Id 字符串类型长度 40,
//姓名 (name)字符串类型长度 40,
//出生日期(birthday) 日期类型,
//备注 (description)字符串类型长度 255,
//平均分() 整数类型,

        String c= "aaa";
        request.setAttribute("list",list);
        request.getRequestDispatcher("/list.jsp").forward(request,response);
        //2.序列化json返回
//        ObjectMapper mapper = new ObjectMapper();
//        response.setContentType("application/json;charset=utf-8");
//        mapper.writeValue(response.getOutputStream(),list);



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
