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
        new Job2Task().execute(3);  // 產生Job2Task物件並執行execute方法，將秒數3傳入
    }

    public void go3(View v) {
        new Job3Task().execute(6);
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

    // Job2Task 類別 10-6-3 需求2
    // 在畫面上按下GO2按鈕，工作n秒後，結束後在 TextView 中顯示DONE2
    class Job2Task extends AsyncTask<Integer, Void, Void> {
        // 非同步工作執行的工作方法，因參數宣告為Integer，傳入的值放在
        // 參數 integers，資料型態為整數陣列，傳入的3即放在integers[0]
        @Override
        protected Void doInBackground(Integer... integers) {
            try {
                Thread.sleep(integers[0] * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            TextView info = findViewById(R.id.info);
            info.setText("DONE2");
        }
    }

    // 10-6-4 需求3 - Job3Task類別
    // 按下GO3按鈕，延遲六秒，需每秒更新秒數到TextView
    class Job3Task extends AsyncTask<Integer, Integer, Void> {
        private TextView info;
        public Job3Task() {
            info = findViewById(R.id.info);
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            for (int i = 0; i < integers[0]; i++) {
                publishProgress(i); // 使用此AsyncTask的方法，會自動執行 onProgressUpdate(Progress...)
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            info.setText(String.valueOf(values[0]));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            info.setText("DONE3");
        }
    }

}