package cn.dao;

import cn.domain.Student;

import java.util.List;

public interface StudentDAO {
    List<Student> findAll();


    int findTotalCount();

    List<Student> findByPage(int start, int rows);

    void add(Student student);

    void UpdateUser(Student student);

    Student findUserById(String id);

    void DeleteUser(String id);
}
