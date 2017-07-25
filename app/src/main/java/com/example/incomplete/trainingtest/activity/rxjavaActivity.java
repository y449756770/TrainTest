package com.example.incomplete.trainingtest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.incomplete.trainingtest.R;

import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableOperator;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class rxjavaActivity extends AppCompatActivity {
    String article = "fkjdsalijfofldaJFOIEjfldanlJR2OnfldajilwafkndaIUPO32,LFKjlijuJFLMA";
    char[] chars = article.toCharArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        /**
         * RxJava 2.0以后的订阅方法，区别与1.0
         */
//        Observable observable = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter e) throws Exception {
//                e.onNext("hello");
//                e.onNext("hellohello");
//                e.onNext("hellohellohellohello");
//
//
//                e.onComplete();
//
//            }
//        });
//        testMap();
//        testFlatMap();
//        testConcatMap();
//        testFilter();
//        testTake();
//        testDistinct();
        testZip();

        /**
         *
         */
        Observable obj = Observable.just("string");
        obj.lift(new ObservableOperator<String, Integer>() {
            @Override
            public Observer<? super Integer> apply(@NonNull Observer<? super String> observer) throws Exception {
                return null;
            }
        });
    }

    public void testMap() {
        Observable observable = Observable.create(new ObservableOnSubscribe<Character>() {
            @Override
            public void subscribe(ObservableEmitter<Character> e) throws Exception {
                for (int i = 0; i < chars.length; i++) {
                    e.onNext(chars[i]);
                }
            }
            //delay  延时5秒发送
        });
        observable
                .map(new Function<Character, String>() {
                    @Override
                    public String apply(@NonNull Character character) throws Exception {
                        return character.toString() + "1";
                    }

                }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e("TAG", "onSubscribe: ");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.e("TAG", "onSubscribe: " + s);

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    /**
     * 1,2,3各发送5遍
     */

    public void testFlatMap() {
        Observable observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
                e.onNext(5);
                e.onNext(6);
                e.onNext(7);
                e.onNext(8);
                e.onNext(9);
                e.onNext(10);
                e.onNext(11);
                e.onNext(12);

            }
        });
        observable
                .flatMap(new Function<Integer, ObservableSource<String>>() {

                    @Override
                    public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
                        List<String> list = new ArrayList<String>();
                        for (int i = 0; i < 5; i++) {
                            list.add("我是变换过的" + integer);
                        }
                        return Observable.fromIterable(list);
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String o) throws Exception {
                Log.e("XYK", o);

            }
        });

    }

    public void testConcatMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList<String>();
                for (int i = 0; i < 5; i++) {
                    list.add("我是变换过的" + integer);
                }
                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e("XYK", s);
            }
        });
    }

    /**
     * Filter操作富
     * 实现打印7的倍数
     */
    public void testFilter() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0; i < 10000; i++) {
                    e.onNext(i);
                }
            }
        }).observeOn(Schedulers.io())
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer % 7 == 0;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e("XYK", integer + "");
            }
        });
    }

    /**
     * take和takeList方法可以将上游事件中的前N项或者最后N项发送到下游,其他事件则进行过滤
     */

    public void testTake() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0; ; i++) {
                    e.onNext(i);
                }
            }
        }).take(3)
                //.takeList(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e("XYK", integer + "");
                    }
                });

    }

    /**
     * distinct方法,可以将重复对象去除重复对象,这里我们要用到一个方法,repeat(),产生重复事件,这里重复事件,再去除有些多余,
     */

    public void testDistinct() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0; i < 50; i++) {
                    e.onNext(i);
                }
            }
        }).take(3)
                //生成重复事件
                .repeat(3)
                .distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e("XYK", integer + "");
                    }
                });
    }

    public void testZip() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
            }
        });

        Observable<String> observable1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("这是");
                e.onNext("这个是");
                e.onNext("这个则是");
            }
        });

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("个");
                e.onNext("只");
                e.onNext("条");
                e.onNext("张");
                e.onNext("本");
                e.onNext("副");
            }
        });

        Observable.zip(observable, observable1, observable2, new Function3<Integer, String, String, String>() {
            @Override
            public String apply(Integer integer, String s, String s2) throws Exception {
                Log.e("pre_XYK", s + integer + s2);
                return s + integer + s2;

            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e("XYK", s);
            }
        });
    }
}
