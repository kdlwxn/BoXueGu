package com.boxuegu.bean;

import java.io.Serializable;

public class ExercisesDetailBean implements Serializable {
    //序列化时保持ExercisesDetailBean类版本的兼容性
    private static final long serialVersionUID = 1L;
    private int subjectId;  	//习题Id
    private String subject; //题干
    private String a;        	// A选项
    private String b;        	// B选项
    private String c;        	// C选项
    private String d;        	// D选项
    private int answer;      //正确答案
    /**
     * select为0表示所选项是对的，1表示选中的A选项是错的，2表示选中的B选项是错的，
     * 3表示选中的C选项是错的，4表示选中的D选项是错的
     */
    private int select;

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getSelect() {
        return select;
    }

    public void setSelect(int select) {
        this.select = select;
    }
}
