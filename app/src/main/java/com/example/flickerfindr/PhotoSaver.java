package com.example.flickerfindr;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PhotoSaver {

    public static File savePhoto(ImageView iv) {
        //to get the image from the ImageView (say iv)
        BitmapDrawable draw = (BitmapDrawable) iv.getDrawable();
        Bitmap bitmap = draw.getBitmap();
        File outFile = null;
        try {
            FileOutputStream outStream = null;
            File sdCard = Environment.getExternalStorageDirectory();
            File dir = new File(sdCard.getAbsolutePath() + "/FickrFinder");
            dir.mkdirs();
            String fileName = String.format("%d.jpg", System.currentTimeMillis());
            outFile = new File(dir, fileName);
            outStream = new FileOutputStream(outFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();
            return outFile;
        } catch (FileNotFoundException ex) {
            return outFile;
        } catch (IOException e) {
            e.printStackTrace();
            return outFile;
        }

    }
//    public void savePhoto() {
//        FileOutputStream fileOutputStream = null;
//        File file = getdisc();
//        if (!file.exists() && !file.mkdirs()) {
//            return;
//        }
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmsshhmmss");
//        String date = simpleDateFormat.format(new Date());
//        String name = "img" + date + ".jpeg";
//        String file_name = file.getAbsolutePath() + "/" + name;
//        File new_file = new File(file_name);
//        try {
//            fileOutputStream = new FileOutputStream(new_file);
//            Bitmap bitmap = viewToBitmap(img, img.getWidth(), img.getHeight());
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
//
//            fileOutputStream.flush();
//            fileOutputStream.close();
//        } catch (FileNotFoundException e) {
//
//        } catch (IOException e) {
//
//        }
//        refreshGallary(file);
//    }


    private static Bitmap viewToBitmap(View view, int widh, int hight) {
        Bitmap bitmap = Bitmap.createBitmap(widh, hight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }
}
