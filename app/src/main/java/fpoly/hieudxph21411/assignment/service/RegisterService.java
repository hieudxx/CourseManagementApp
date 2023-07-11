package fpoly.hieudxph21411.assignment.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

import fpoly.hieudxph21411.assignment.dao.NguoiDungDAO;

public class RegisterService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        String name = bundle.getString("name");
        String user = bundle.getString("user");
        String pass = bundle.getString("pass");

        NguoiDungDAO dao = new NguoiDungDAO(this);
        int check = dao.insert(user, pass, name); // để check xem có trong csdl ko
//        sau đó phải đẩy kq này ngược về activity để xử lý bước tiếp theo, vì service k có giao diện ==> dùng broadCastReceiver
        Intent iBroadCast = new Intent();
        Bundle bundleBroadCast = new Bundle();
        bundleBroadCast.putInt("check", check);
        iBroadCast.putExtras(bundleBroadCast);
        iBroadCast.setAction("checkRegis");
        sendBroadcast(iBroadCast);

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
