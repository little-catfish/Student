package cn.dao.impl;

import cn.dao.StudentDAO;
import cn.domain.Student;
import cn.util.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StudentDAOImpl implements StudentDAO {

    private  Jedis jedis = JedisUtil.getJedis();

    @Override
    public List<Student> findAll() {
        ArrayList<Student>  arrayList = new ArrayList<>();
        jedis.auth("root");
        Set<String> studentScore = jedis.zrevrange("studentScore", 0, -1);
        for (String id : studentScore) {
            String studentId = id.substring(9);
//            System.out.println(studentId);
            Map<String, String> user = jedis.hgetAll("user00"+studentId);
            Student student = new Student();
            // keyset
            Set<String> keySet = user.keySet();
            for (String key : keySet) {
                //获取value
                String value = user.get(key);
                if (key.equals("id")) {
                    student.setId(value);
                }
                if (key.equals("birthday")){
                    student.setBirthday(value);
                }
                if (key.equals("name")){
                    student.setName(value);
                }
                if (key.equals("description"))
                {
                    student.setDescription(value);
                }
                if (key.equals("avgScore")){
                    student.setAvgScore(value);
                }
            }
            arrayList.add(student);
        }
        jedis.close();
        return  arrayList;
    /*    //        ArrayList<Student>  arrayList = new ArrayList<>();
//        Jedis jedis = JedisUtil.getJedis();
//        for (int i = 1; i <31 ; i++) {
//            // 获取hash的所有map中的数据
//            Map<String, String> user = jedis.hgetAll("user00"+i);
//            Student student = new Student();
//            // keyset
//            Set<String> keySet = user.keySet();
//            for (String key : keySet) {
//                //获取value
//                String value = user.get(key);
//                 if (key.equals("id")) {
//                    student.setId(value);
//                }
//                if (key.equals("birthday")){
//                    student.setBirthday(value);
//                }
//                if (key.equals("name")){
//                    student.setName(value);
//                }
//                if (key.equals("description"))
//                {
//                    student.setDescription(value);
//                }
//                if (key.equals("avgScore")){
//                    student.setAvgScore(value);
//                }
//            }
//            arrayList.add(student);
//            System.out.println(arrayList);
//        }
//        jedis.close();
//        return arrayList;*/
    }

    @Override
    public int findTotalCount() {
        List<Student> list = findAll();
        return  list.size();
    }

    @Override
    public List<Student> findByPage(int start, int rows) {

        Jedis jedis = JedisUtil.getJedis();
        jedis.auth("root");
        ArrayList<Student>  arrayList = new ArrayList<>();
        Set<String> studentScore = jedis.zrevrange("studentScore", start, rows);
        for (String id : studentScore) {
            String studentId = id.substring(9);
//            System.out.println(studentId);
            Map<String, String> user = jedis.hgetAll("user00"+studentId);
            Student student = new Student();
            // keyset
            Set<String> keySet = user.keySet();
            for (String key : keySet) {
                //获取value
                String value = user.get(key);
                if (key.equals("id")) {
                    student.setId(value);
                }
                if (key.equals("birthday")){
                    student.setBirthday(value);
                }
                if (key.equals("name")){
                    student.setName(value);
                }
                if (key.equals("description"))
                {
                    student.setDescription(value);
                }
                if (key.equals("avgScore")){
                    student.setAvgScore(value);
                }
            }
            arrayList.add(student);
        }
        // 获取hash的所有map中的数据
//        System.out.println(arrayList);
        jedis.close();
        return  arrayList;
    }



    @Override
    public void add(Student student) {
        jedis.auth("root");
        jedis.hset("user00"+student.getId(),"id",student.getId());
        jedis.hset("user00"+student.getId(),"name",student.getName());
        jedis.hset("user00"+student.getId(),"birthday",student.getBirthday());
        jedis.hset("user00"+student.getId(),"description",student.getDescription());
        jedis.hset("user00"+student.getId(),"avgScore",student.getAvgScore());
        jedis.zadd("studentScore",Integer.parseInt(student.getAvgScore()),"StudentId"+student.getId());
        //        for (int i = 1; i < 33; i++) {
//            jedis.hset("user00"+i,"id",""+i);
//            jedis.hset("user00"+i,"name","ceshi00"+i);
//            jedis.hset("user00"+i,"birthday","2000"+":03:"+i);
//            jedis.hset("user00"+i,"description","zheshibeichu"+i);
//            jedis.hset("user00"+i,"avgScore",""+i*3);
//
//        }
//

//        for (int i = 1; i <33 ; i++) {
//            jedis.zadd("studentScore",3*i,"StudentId"+i);
//
//        }
    }

    @Override
    public void UpdateUser(Student student) {
        add(student);
    }

    @Override
    public Student findUserById(String id) {
        jedis.auth("root");
        Map<String, String> user = jedis.hgetAll("user00"+id);
        Student student = new Student();
        // keyset
        Set<String> keySet = user.keySet();
        for (String key : keySet) {
            //获取value
            String value = user.get(key);
            if (key.equals("id")) {
                student.setId(value);
            }
            if (key.equals("birthday")){
                student.setBirthday(value);
            }
            if (key.equals("name")){
                student.setName(value);
            }
            if (key.equals("description"))
            {
                student.setDescription(value);
            }
            if (key.equals("avgScore")){
                student.setAvgScore(value);
            }
        }
        return student;
    }


    @Override
    public void DeleteUser(String id) {
        jedis.auth("root");
        jedis.del("user00"+id);
        jedis.zrem("studentScore","StudentId"+id);

      }
}
