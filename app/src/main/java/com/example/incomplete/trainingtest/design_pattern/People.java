package com.example.incomplete.trainingtest.design_pattern;

/**
 * Created by incomplete on 17/10/11.
 * <p>
 * Builder设计模式
 * <p>
 * Builder的内部类构造方法中只接收必传的参数，并且该必传的参数适用了final修饰符
 */

public class People {


    private final String age;
    private final String firstName;
    private final String lastName;
    private final String midName;
    private final String address;


    private People(Builder builder) {
        this.age = builder.age;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.midName = builder.midName;
        this.address = builder.address;
    }

    public static class Builder {
        private final String age;  // final修饰必传的参数,也可以不用final修饰
        private String firstName;
        private String lastName;
        private String midName;
        private String address;

        public Builder(String age) {
            this.age = age;
        }

//        public Builder age(String age) {
//            this.age = age;
//            return this;
//        }

        public Builder firstName(String firstname) {
            this.firstName = firstname;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder midName(String midName) {
            this.midName = midName;
            return this;

        }

        public Builder address(String address) {
            this.address = address;
            return this;

        }

        public People build() {
            return new People(this);
        }


    }
}
