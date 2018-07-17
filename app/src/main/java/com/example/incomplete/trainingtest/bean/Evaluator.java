package com.example.incomplete.trainingtest.bean;

import android.animation.TypeEvaluator;

/**
 * Created by liuyong on 2017/12/19.
 */

public class Evaluator implements TypeEvaluator<Float> {
    @Override
    public Float evaluate(float fraction, Float startValue, Float endValue) {
        float mResult;
        mResult = startValue + fraction * (endValue.floatValue() - startValue.floatValue());
        return mResult;
    }
}
