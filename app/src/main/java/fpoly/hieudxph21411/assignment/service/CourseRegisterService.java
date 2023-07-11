package fpoly.hieudxph21411.assignment.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import fpoly.hieudxph21411.assignment.dao.DkyMonHocDAO;
import fpoly.hieudxph21411.assignment.model.MonHoc;

public class CourseRegisterService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle b = intent.getExtras();
        int id = b.getInt("id", -1);
        String code = b.getString("code", "");
        int isRegister = b.getInt("isRegister", -1);
        boolean isAll = b.getBoolean("isAll");

        boolean check;
        DkyMonHocDAO dao = new DkyMonHocDAO(this);
        if (isRegister > 0) {
            check = dao.delete(id, code);
        } else {
            check = dao.insert(id, code);
        }

        ArrayList<MonHoc> list = new ArrayList<>();
        if (check){
            list = dao.getData(id, isAll);
        }
        Intent iBr = new Intent();
        Bundle bBr = new Bundle();
        bBr.putBoolean("check",check);
        bBr.putSerializable("list",list);
        iBr.putExtras(bBr);
        iBr.setAction("RegisterCourse");
        sendBroadcast(iBr);


        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
