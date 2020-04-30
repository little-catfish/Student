package cn.web.servlet;

import cn.domain.Student;
import cn.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class JedisDemo {
    public static void main(String[] args) {
        Jedis jedis = JedisUtil.getJedis();
        ArrayList<Student>  arrayList = new ArrayList<>();
        /*//        A:学生数据包含:
//         Id 字符串类型长度 40,
//        姓名 (name)字符串类型长度 40,
//        出生日期(birthday) 日期类型,
//        备注 (description)字符串类型长度 255,
//        平均分(avgscore) 整数类型,*/
        //
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
        // 获取hash的所有map中的数据
        System.out.println(arrayList);
        jedis.close();




//       System.out.println(studentScore);




    }
}
