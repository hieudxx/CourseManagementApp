package fpoly.hieudxph21411.assignment.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import fpoly.hieudxph21411.assignment.dao.DkyMonHocDAO;
import fpoly.hieudxph21411.assignment.model.MonHoc;

public class CourseListService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        int id = bundle.getInt("id");
        boolean isAll = bundle.getBoolean("isAll");
        DkyMonHocDAO dao = new DkyMonHocDAO(this);
        ArrayList<MonHoc> list = dao.getData(id,isAll);

        Intent iBroadCast = new Intent();
        Bundle bBroadCast = new Bundle();
        bBroadCast.putSerializable("list",list);

        iBroadCast.putExtras(bBroadCast);
        iBroadCast.setAction("ListCourse");
        sendBroadcast(iBroadCast);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
