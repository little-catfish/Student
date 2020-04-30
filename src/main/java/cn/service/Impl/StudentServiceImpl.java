package cn.service.Impl;

import cn.dao.StudentDAO;
import cn.dao.impl.StudentDAOImpl;
import cn.domain.PageBean;
import cn.domain.Student;
import cn.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentDAO studentDAO = new StudentDAOImpl();


    @Override
    public List<Student> findAll() {
       return studentDAO.findAll();
    }

    @Override
    public PageBean<Student> findUserByPage(String _currentPage, String _rows) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        PageBean<Student>  pageBean  = new PageBean<Student>();
        pageBean.setPageSize(rows);
        pageBean.setCurrentPage(currentPage);
          int  totalCount= studentDAO.findTotalCount();
          pageBean.setTotalCount(totalCount);
          int start =  (currentPage - 1) * rows;
          List<Student> list= studentDAO.findByPage(start,rows*currentPage-1);
          pageBean.setList(list);
          int totalPage = (totalCount % rows ==0) ? (totalCount / rows) : (totalCount / rows  +1);
          pageBean.setTotalPage(totalPage);
         return pageBean;
    }

    @Override
    public void Add(Student student) {
        studentDAO.add(student);
    }

    @Override
    public void UpdateUser(Student student) {

        studentDAO.UpdateUser(student);
    }

    @Override
    public Student findUserByid(String id) {
        return studentDAO.findUserById(id);
    }

    @Override
    public void DeleteUser(String id) {
        studentDAO.DeleteUser(id);
    }

    @Override
    public void DeleteByid(String[] ids) {
        for (String id : ids) {
            studentDAO.DeleteUser(id);
        }

    }
}
