package com.example.incomplete.trainingtest.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.incomplete.trainingtest.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 测试异步任务的实现
 */

public class AsyncActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_async_serial;
    Button btn_async_parallel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
        btn_async_serial = (Button) findViewById(R.id.btn_async_serial);
        btn_async_serial.setOnClickListener(this);

        btn_async_parallel = (Button) findViewById(R.id.btn_async_parallel);
        btn_async_parallel.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_async_serial:
                new MyAsyncTask("Async#1").execute("param@1");
                new MyAsyncTask("Async#2").execute("param@2");
                new MyAsyncTask("Async#3").execute("param@3");
                new MyAsyncTask("Async#4").execute("param@4");
                new MyAsyncTask("Async#5").execute("param@5");
                new MyAsyncTask("Async#6").execute("param@6");
                new MyAsyncTask("Async#7").execute("param@7");


                break;

            case R.id.btn_async_parallel:
                new MyAsyncTask("AsyncTask#1").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
                new MyAsyncTask("AsyncTask#2").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
                new MyAsyncTask("AsyncTask#3").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
                new MyAsyncTask("AsyncTask#4").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
                new MyAsyncTask("AsyncTask#5").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
                new MyAsyncTask("AsyncTask#6").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
                new MyAsyncTask("AsyncTask#7").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
                new MyAsyncTask("AsyncTask#8").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");


                break;

        }

    }

    private static class MyAsyncTask extends AsyncTask<String, Integer, String> {
        private String mName = "AsyncTask";
        private String TAG = getClass().getSimpleName();

        public MyAsyncTask(String name) {
            super();
            mName = name;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Log.e(TAG, df.format(new Date()));
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep(3000);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return mName;
        }
    }
}
