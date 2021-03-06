package com.example.joseph.downloadingwebcontent;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    public class DownloadTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {

            String result = "";
            URL url;
            HttpsURLConnection urlConnection = null;

            try{
                url = new URL(params[0]);
                urlConnection = (HttpsURLConnection)url.openConnection();
                InputStream inputStream  = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                int data = inputStreamReader.read();
                while (data != -1){
                    char current = (char) data;
                    result += current;
                    data = inputStreamReader.read();

                }
            }catch (Exception e){
                 e.printStackTrace();
            }


            return result;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DownloadTask task = new DownloadTask();
        String result = null;
        try {
            result = task.execute("https://www.ecowebhosting.co.uk/").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.i("RESULT",result);
    }
}
