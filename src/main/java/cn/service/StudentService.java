package cn.service;

import cn.domain.PageBean;
import cn.domain.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();

    PageBean<Student> findUserByPage(String currentPage, String rows);

    void Add(Student student);

    void UpdateUser(Student student);

    Student findUserByid(String id);

    void DeleteUser(String id);

    void DeleteByid(String[] ids);
}
