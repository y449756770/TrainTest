package com.example.incomplete.trainingtest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.incomplete.trainingtest.R;
import com.example.incomplete.trainingtest.grneric.GenericClass;
import com.example.incomplete.trainingtest.grneric.GrnericInter;
import com.example.incomplete.trainingtest.grneric.SubclassGr;
import com.example.incomplete.trainingtest.grneric.SubclassGr2;
import com.example.incomplete.trainingtest.paramAndReflect.BaseResult;
import com.example.incomplete.trainingtest.paramAndReflect.NetOperater;
import com.example.incomplete.trainingtest.paramAndReflect.RealParam;
import com.example.incomplete.trainingtest.paramAndReflect.RealResult;
import com.example.incomplete.trainingtest.testextends.TestChildClass;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 范型测试
 */

public class GenericActivity extends AppCompatActivity {
    RealParam realParam;
    public String jsonString = "{\"starcraft\":{\"INC\":\"Blizzard\",\"price\":60}}";

    NetOperater<RealParam, BaseResult> netOperater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic);

        GrnericInter<String> genericClass = new SubclassGr();
        genericClass.showGe("dtr");

        GenericClass<String> testClass = new GenericClass<>();
        testClass.setT("test");
        testClass.showGene("string");

        SubclassGr2<String> subClass1 = new SubclassGr2<>();
        SubclassGr2<Integer> subClass2 = new SubclassGr2<>();

        test(subClass1);
//        test(subClass2);

        /**
         * 反射获取参数
         */

        testAnnotation();


        /**
         * 测试类的初始化
         */

        testClass();


    }


    /**
     * 设置类的上限
     *
     * @param subClass
     */

    void test(SubclassGr2<? extends String> subClass) {


    }

    void testAnnotation() {
        netOperater = new NetOperater();
        realParam = new RealParam();
        realParam.age = 10;
        realParam.graduate = true;
        realParam.name = "time";
        realParam.id = "liuyong";
        /**
         * 设置参数
         */
        netOperater.setParam(realParam);
        /**
         * 反射获取param
         */

        netOperater.toParam();

        /**
         * 设置返回的类类型
         */
        netOperater.setResultClass(RealResult.class);

        BaseResult result = (BaseResult) new Gson().fromJson(jsonString, netOperater.mResultClass);
        RealResult realResult = (RealResult) result;
        Log.e("result", realResult.starcraft.INC);


    }

    public void testClass() {
        TestChildClass childClass = new TestChildClass();

    }
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public void testOkHttp() {

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, "json");
        Request request = new Request.Builder().url("www.baidu.com").post(body).build();

        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
