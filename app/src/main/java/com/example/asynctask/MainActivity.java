package com.example.asynctask;

/* 參考網址：https://gitlab.com/pie9/Async/-/blob/master/app/src/main/java/com/tom/async/MainActivity.java
 * 參考文件：Android 入門這樣學 P.265
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void go1(View v) {
        new Job1Task().execute();
    }

    public void go2(View v) {

    }

    public void go3(View v) {

    }

    // 在畫面按下GO1按鈕，工作5秒鐘，結束後在TextView中顯示"DONE"
    // 10-6-2 需求1 - job1Task內部類別 p.265
    class Job1Task extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(5000); // 模擬五秒延遲後改TextView中的文字
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override   //  延遲五秒後改TextView中的文字
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            TextView info = findViewById(R.id.info);
            info.setText("DONE");
        }
    }


}