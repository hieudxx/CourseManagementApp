package fpoly.hieudxph21411.assignment.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public static final String dbName = "DANG_KY_MON_HOC";
    public static final int dbVersion = 1;

    public DbHelper(Context context) {
        super(context, dbName, null, dbVersion);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNguoiDung = "CREATE TABLE NguoiDung(id integer primary key autoincrement, userName text, passWord text, name text)";
        db.execSQL(createNguoiDung);

        String createMonHoc = "CREATE TABLE MonHoc(code text primary key, name text, teacher text)";
        db.execSQL(createMonHoc);

        String createThongTin = "CREATE TABLE ThongTin(id integer primary key autoincrement, code text, date text, address text)";
        db.execSQL(createThongTin);

        String createDangKy = "CREATE TABLE DangKy(id integer, code text)";
        db.execSQL(createDangKy);

//      data người dùng (user)
        db.execSQL("INSERT INTO NguoiDung VALUES(1,'hieudx','123','Đỗ Xuân Hiếu'),(2,'khangdv','abc','Đinh Viết Khang')");

//      data môn học (course)
        db.execSQL("INSERT INTO MonHoc VALUES('MOB201','Android Nâng Cao','Nguyễn Văn Lộc'),('MOB306','React Native','Đặng Tuấn Anh'),('MOB2041','Dự Án Mẫu','Hoàng Quang Thắng')");

//      data thông tin lịch học từng môn (info)
        db.execSQL("INSERT INTO ThongTin VALUES(1, 'MOB201', 'Ca 2 - 19/09/2022', 'T1011'),(2, 'MOB201', 'Ca 2 - 21/09/2022', 'T1011'),(3, 'MOB201', 'Ca 2 - 23/09/2022', 'T1011'),(4, 'MOB306', 'Ca 5 - 20/09/2022', 'F204'),(5, 'MOB306', 'Ca 5 - 22/09/2022', 'F204'),(6, 'MOB2041', 'Ca 1 - 20/09/2022', 'Online - https://meet.google.com/rku-beuk-wqu')");

//       data bảng đăng ký
        db.execSQL("INSERT INTO DangKy VALUES(1,'MOB201'),(1,'MOB306'),(2,'MOB306')\n");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i != i1) {
            db.execSQL("DROP TABLE IF EXISTS NguoiDung");
            db.execSQL("DROP TABLE IF EXISTS MonHoc");
            db.execSQL("DROP TABLE IF EXISTS ThongTin");
            db.execSQL("DROP TABLE IF EXISTS DangKy");
            onCreate(db); // gọi lại onCreate để tạo lại bảng
        }
    }
}
