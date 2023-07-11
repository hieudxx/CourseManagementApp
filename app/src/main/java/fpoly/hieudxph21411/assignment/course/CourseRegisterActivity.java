package fpoly.hieudxph21411.assignment.course;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import fpoly.hieudxph21411.assignment.R;
import fpoly.hieudxph21411.assignment.adapter.CourseRegisterAdapter;
import fpoly.hieudxph21411.assignment.databinding.ActivityCourseRegisterBinding;
import fpoly.hieudxph21411.assignment.model.MonHoc;
import fpoly.hieudxph21411.assignment.service.CourseListService;

public class CourseRegisterActivity extends AppCompatActivity {
    private ActivityCourseRegisterBinding binding;
    int id;
    private ArrayList<MonHoc> list;
    IntentFilter intentFilter;
    boolean isAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCourseRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        intentFilter = new IntentFilter();
        intentFilter.addAction("ListCourse");
        intentFilter.addAction("RegisterCourse");



//        id của ng dùng
        SharedPreferences shared = getSharedPreferences("PROFILE", MODE_PRIVATE);
        id = shared.getInt("id", -1);

//        lấy giá trị isAll
        Intent iIsAll = getIntent();
        Bundle bIsAll = iIsAll.getExtras();
        isAll = bIsAll.getBoolean("isAll");
        if (isAll) {
            binding.toolbar.setTitle("Đăng Ký Môn Học");
        } else {
            binding.toolbar.setTitle("Môn Học Đã Đăng Ký");
        }
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        Intent i = new Intent(CourseRegisterActivity.this, CourseListService.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putBoolean("isAll", isAll);
        i.putExtras(bundle);
        startService(i);
    }

    private void loadData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);  // dạng grid và chia thành 2 cột, ở đây ta phải set vào dòng bên dưới nữa mới chạy đc
//        layoutManager.setOrientation(RecyclerView.VERTICAL); // dạng vuốt ngang
        binding.rcvCourse.setLayoutManager(layoutManager);
        CourseRegisterAdapter adapter = new CourseRegisterAdapter(this, list, id, isAll);
        binding.rcvCourse.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myBroadCast, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myBroadCast);
    }

    public BroadcastReceiver myBroadCast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case "ListCourse":
                case "RegisterCourse":
                    Bundle bundle = intent.getExtras();
                    boolean check = bundle.getBoolean("check", true);

                    if (check) {
                        list = (ArrayList<MonHoc>) bundle.getSerializable("list");
                        loadData();
                    } else {
                        Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    break;
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}