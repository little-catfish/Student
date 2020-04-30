package cn.domain;

import java.io.Serializable;

public class Student implements Serializable {
    private String id;//用户id
    private String name;//真实姓名
    private String birthday;//出生日期
    private String description;//备注
    private String avgScore;  //平均分


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(String avgScore) {
        this.avgScore = avgScore;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", description='" + description + '\'' +
                ", avgScore='" + avgScore + '\'' +
                '}';
    }
}
