package com.example.incomplete.trainingtest.bean;

/**
 * Created by incomplete on 17/8/21.
 * <p>
 * 每天学习的bean
 * <p>
 * 类型分为四类
 */

public class StudyBean {
    public static final int Type_unstudy = 1;  //未开始
    public static final int Type_studing = 2;  //正在进行
    public static final int Type_studyed = 3;  //已经完成
    public static final int Type_waiting = 4;   //未开通

    public int type;    //类型
    public String index; // 期数
    public String count; // 已经学习／总共的小节数

}
