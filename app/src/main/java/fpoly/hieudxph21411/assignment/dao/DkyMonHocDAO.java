package fpoly.hieudxph21411.assignment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.hieudxph21411.assignment.database.DbHelper;
import fpoly.hieudxph21411.assignment.model.MonHoc;
import fpoly.hieudxph21411.assignment.model.ThongTin;

public class DkyMonHocDAO {
    private DbHelper dbHelper;

    public DkyMonHocDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public boolean insert(int id, String code) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("code", code);
        long check = db.insert("DangKy", null, contentValues);
        if (check == -1)
            return false;
        return true;
    }

    public boolean delete(int id, String code) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long check = db.delete("DangKy", "id =? and code =?", new String[]{String.valueOf(id), code});
        if (check == -1)
            return false;
        return true;
    }

    //    lấy danh sách môn học
    public ArrayList<MonHoc> getData(int id, boolean isAll) { // thêm id để xem sv nào đang đăng nhập vào
        ArrayList<MonHoc> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c;
        if (isAll) {
            c = db.rawQuery("SELECT mh.code, mh.name, mh.teacher, dky.id FROM MonHoc mh LEFT JOIN DangKy dky ON mh.code = dky.code AND dky.id = ?", new String[]{String.valueOf(id)});
        } else {
            c = db.rawQuery("SELECT mh.code, mh.name, mh.teacher, dky.id FROM MonHoc mh INNER JOIN DangKy dky ON mh.code = dky.code WHERE dky.id = ?", new String[]{String.valueOf(id)});
        }

        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                list.add(new MonHoc(c.getString(0), c.getString(1), c.getString(2), c.getInt(3), getDataThongTinMonHoc(c.getString(0))));
            } while (c.moveToNext());
        }
        return list;
    }

    public ArrayList<ThongTin> getDataThongTinMonHoc(String code) {
        ArrayList<ThongTin> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT date, address FROM ThongTin WHERE code = ?", new String[]{code});
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                list.add(new ThongTin(c.getString(0), c.getString(1)));
            } while (c.moveToNext());
        }
        return list;
    }
}
