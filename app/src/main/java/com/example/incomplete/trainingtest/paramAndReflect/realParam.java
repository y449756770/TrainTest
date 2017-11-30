package com.example.incomplete.trainingtest.paramAndReflect;

/**
 * Created by incomplete on 17/7/21.
 */

public class RealParam extends BaseParam {

    @Param
    public String id;
    @Param(1)
    public int age;
    @Param
    public String name;
    @Param
    public boolean graduate;
}
