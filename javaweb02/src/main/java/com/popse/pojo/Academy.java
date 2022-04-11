package com.popse.pojo;

/**
 * @Description : TODO
 * @Author : PoPse
 * @Date : 2022/4/8 9:17
 * @Version : 1.0
 **/
public class Academy {
    private int academyId = 0;
    private String academyName;

    public Academy() {
    }

    public Academy(String academyName) {
        this.academyName = academyName;
    }

    public Academy(int academyId, String academyName) {
        this.academyId = academyId;
        this.academyName = academyName;
    }

    public int getAcademyId() {
        return academyId;
    }

    public void setAcademyId(int academyId) {
        this.academyId = academyId;
    }

    public String getAcademyName() {
        return academyName;
    }

    public void setAcademyName(String academyName) {
        this.academyName = academyName;
    }

    @Override
    public String toString() {
        return "Academy{" +
                "academyId=" + academyId +
                ", academyName='" + academyName + '\'' +
                '}';
    }
}
