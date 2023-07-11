package fpoly.hieudxph21411.assignment.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import fpoly.hieudxph21411.assignment.news.NewsActivity;
import fpoly.hieudxph21411.assignment.news.TaskXLMParser;


public class NewsService extends IntentService {
    Context context;

    public NewsService() {
        super("NewsService");
    }

    public NewsService(Context context) {
        super("NewsService");
        this.context = context;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
    }

    public class LocalBinder extends Binder {
        LocalBinder getLocalBinder() {
            return LocalBinder.this;
        }
    }

    public void loadTinTuc(String str, RecyclerView rcv) {
        TaskXLMParser taskXLMParser = new TaskXLMParser((NewsActivity) context, rcv);
        taskXLMParser.execute(str);
    }

    public Bitmap getBitmap(byte[] img) {
        return BitmapFactory.decodeByteArray(img, 0, img.length);
    }

}
