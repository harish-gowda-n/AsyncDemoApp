package com.example.asyncdemoapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

class DownloadImage extends AsyncTask<String, Integer, Bitmap> {

    int count;
    ProgressBar pb; ImageView iv; Context context;
    public DownloadImage(ProgressBar pb, ImageView iv, Context context){
        this.context = context;
        this.pb = pb;
        this.iv = iv;
    }
    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap bitmap = null;
        try{
            URL url = new URL(strings[0]);
            URLConnection con = url.openConnection();
            int fileLength = con.getContentLength();
            InputStream inp = new URL(url.toString()).openStream();
            byte data[] = new byte[1024];
            long total = 0;
            while ((count = inp.read(data)) != -1){
                total += count;
                publishProgress((int)((total*100)/fileLength));
            }
            inp = new URL(url.toString()).openStream();
            bitmap = BitmapFactory.decodeStream(inp);
            inp.close();
        }catch (Exception e){
            Log.i("Error : ", ""+e.getMessage());
        }
        return bitmap;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        iv.setImageBitmap(result);
        Toast.makeText(context, "Download Complete!", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int progress = values[0];
        pb.setProgress(progress);
    }
}