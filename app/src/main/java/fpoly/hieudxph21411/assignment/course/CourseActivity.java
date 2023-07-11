package fpoly.hieudxph21411.assignment.course;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import fpoly.hieudxph21411.assignment.R;
import fpoly.hieudxph21411.assignment.databinding.ActivityCourseBinding;

public class CourseActivity extends AppCompatActivity {
    private ActivityCourseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbarCourse);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CourseActivity.this, CourseRegisterActivity.class);
                Bundle b = new Bundle();
                b.putBoolean("isAll", true);
                i.putExtras(b);
                startActivity(i);
            }
        });

        binding.btnRegisted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CourseActivity.this, CourseRegisterActivity.class);
                Bundle b = new Bundle();
                b.putBoolean("isAll", false);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }

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