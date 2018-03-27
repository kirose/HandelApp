package com.handel.web;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.handel.util.UtilRutes;
import com.handel.vo.ParametersVO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by Marco Antonio on 17/02/2018.
 */

public class FileManager {
    public static final String LOG_CLASS = "FileManager";
    private static String BASE_URL = UtilRutes.FILE_URL + "file/";
    private static File imgDir = null;

    public FileManager(Context ctx){
        if (imgDir == null) {
            ContextWrapper cw = new ContextWrapper(ctx.getApplicationContext());
            // /data/user/0/com.handel.handel/app_cache
            imgDir = cw.getDir(UtilRutes.CACHE_DIR.substring(0,UtilRutes.CACHE_DIR.length()-1), Context.MODE_PRIVATE);
            Log.i(LOG_CLASS,"Cache Directory: "+imgDir.getAbsolutePath());
        }
    }

    public void executeAsync(String nameImg, HandlerFM handler, ParametersVO params){
        Bitmap img = getImageFromStorage(nameImg, params);
        if (img != null){
            handler.onSuccess(img);
            return;
        }
        new FileManager.FileManagerTask().execute(BASE_URL + nameImg, handler, params, nameImg);
    }
    private Bitmap execute(String url, ParametersVO params, String nameImg) throws IOException {

        // Download Image from URL
        url = addParameters(url, params);
        InputStream input = new java.net.URL(url).openStream();
        // Decode Bitmap
        Bitmap bitmap = BitmapFactory.decodeStream(input);
        saveToInternalStorage(bitmap, nameImg, params);

        return bitmap;
    }
    private class FileManagerTask extends AsyncTask<Object, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(Object... args) {
            if (args == null || args.length == 0){
                return null;
            }
            String url = BASE_URL + args[0];
            HandlerFM handler = (HandlerFM) args[1];
            ParametersVO params = (ParametersVO) args[2];
            String nameImg = (String) args[3];
            Bitmap bitmap = null;
            try{
                bitmap = FileManager.this.execute(url,params,nameImg);
                if (handler != null) {
                    handler.onSuccess(bitmap);
                }
            } catch (Exception e) {
                if (handler != null) {
                    handler.onError(e);
                }
                Log.e(LOG_CLASS,"Descargando imagen: "+url,e);
            }

            return bitmap;
        }
    }
    private String addParameters(String url, ParametersVO params) throws UnsupportedEncodingException {
        if (params == null || params.getMap().isEmpty()){
            return url;
        }
        StringBuilder s = new StringBuilder().append(url).append('?');
        for (Map.Entry<String, Object> e : params.getMap().entrySet()) {
            s
            .append(URLEncoder.encode(e.getKey(), "UTF-8"))
            .append("=")
            .append(URLEncoder.encode(e.getValue() == null ? "null" : e.getValue().toString(), "UTF-8"))
            .append('&');
        }
        s.delete(s.length()-1,s.length());
        return s.toString();
    }
    private void saveToInternalStorage(Bitmap img, String nameImg, ParametersVO params){

        String ext = nameImg.substring(nameImg.lastIndexOf('.')+1).toLowerCase();
        nameImg = nameImg.substring(0,nameImg.lastIndexOf('.')) + "_" + params.get("width") + "x" + params.get("height")+"."+ext;
        File imgFile = new File(imgDir,nameImg);
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(imgFile);
            // Use the compress method on the BitMap object to write image to the OutputStream
            if ("png".equals(ext)) {
                img.compress(Bitmap.CompressFormat.PNG, 100, fos);
            } else if ("jpg".equals(ext) || "jpeg".equals(ext)) {
                img.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            } else {
                throw new RuntimeException("Image format not supported");
            }
            fos.close();
            fos = null;
        } catch (Exception e) {
            Log.i(LOG_CLASS, e.getMessage(), e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    Log.i(LOG_CLASS, e.getMessage(), e);
                }
            }
        }
    }
    private Bitmap getImageFromStorage(String nameImg, ParametersVO params) {
        String ext = nameImg.substring(nameImg.lastIndexOf('.')+1).toLowerCase();
        nameImg = nameImg.substring(0,nameImg.lastIndexOf('.')) + "_" + params.get("width") + "x" + params.get("height")+"."+ext;

        try {
            File img = new File(imgDir, nameImg);
            if (!img.exists()){
                return null;
            }
            return BitmapFactory.decodeStream(new FileInputStream(img));
        } catch (FileNotFoundException e){
            Log.e(LOG_CLASS,e.getMessage(),e);
        }
        return null;
    }
}
