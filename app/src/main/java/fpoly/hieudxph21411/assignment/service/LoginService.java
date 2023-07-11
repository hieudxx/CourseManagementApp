package fpoly.hieudxph21411.assignment.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

import fpoly.hieudxph21411.assignment.dao.NguoiDungDAO;
import fpoly.hieudxph21411.assignment.model.NguoiDung;

public class LoginService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        String user = bundle.getString("user");
        String pass = bundle.getString("pass");

        NguoiDungDAO dao = new NguoiDungDAO(this);
        boolean check = dao.checkLogin(user, pass); // để check xem có trong csdl ko
//        sau đó phải đẩy kq này ngược về activity để xử lý bước tiếp theo, vì service k có giao diện ==> dùng broadCastReceiver
        Intent iBroadCast = new Intent();
        Bundle bundleBroadCast = new Bundle();
        bundleBroadCast.putBoolean("check", check);
        iBroadCast.putExtras(bundleBroadCast);
        iBroadCast.setAction("checkLogin");
        sendBroadcast(iBroadCast);

        return super.onStartCommand(intent, flags, startId);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
