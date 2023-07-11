package fpoly.hieudxph21411.assignment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fpoly.hieudxph21411.assignment.database.DbHelper;
import fpoly.hieudxph21411.assignment.model.NguoiDung;

public class NguoiDungDAO {
    DbHelper dbHelper;
    SharedPreferences shared;


    public NguoiDungDAO(Context context) {
        dbHelper = new DbHelper(context);
        shared = context.getSharedPreferences("PROFILE", Context.MODE_PRIVATE);
    }

    //    ktra thông tin đăng nhập
    public boolean checkLogin(String userName, String passWord) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM NguoiDung WHERE userName = ? AND passWord = ?", new String[]{userName, passWord});
        if (c.getCount() != 0) {
            c.moveToFirst();
            SharedPreferences.Editor editor = shared.edit();
            editor.putInt("id", c.getInt(0));
            editor.putString("userName", c.getString(1));
            editor.putString("passWord", c.getString(2));
            editor.putString("name", c.getString(3));
            editor.apply();
//            lưu thông tin ng dùng
            return true;
        }
        return false;
//        sau khi trả về kq là true hay false sẽ gán cho biến check bên Login service
    }

    //    1: dky thành công - 0: dky thất bại - -1: đã tồn tại tài khoản
    public int insert(String userName, String passWord, String name) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        Cursor c = db.rawQuery("SELECT * FROM NguoiDung WHERE userName = ?", new String[]{userName});
        if (c.getCount() != 0) {
            return -1;
        }
        values.put("userName", userName);
        values.put("passWord", passWord);
        values.put("name", name);
        long check = db.insert("NguoiDung", null, values);
        if (check == -1) {
            return 0;
        }
        return 1;
    }

    public int updatePass(String userName, String oldPass, String newPass) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM NguoiDung WHERE userName = ? AND passWord = ?", new String[]{userName, String.valueOf(oldPass)}); // xem có nhập đúng oldPass ko
        if (c.getCount() > 0) {
//            nếu nhập đúng thì:
            ContentValues contentValues = new ContentValues();
            contentValues.put("passWord", String.valueOf(newPass));
            long check = db.update("NguoiDung", contentValues, "userName = ?", new String[]{userName});
            if (check == -1) {
                return -1; // không thành công
            } else {
                return 1; // thành công
            }
        } else {
            return 0; // không tìm thấy oldpass
        }
    }


}
