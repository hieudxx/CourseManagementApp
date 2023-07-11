package fpoly.hieudxph21411.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import fpoly.hieudxph21411.assignment.course.CourseActivity;
import fpoly.hieudxph21411.assignment.news.NewsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void in4(View view) {
        startActivity(new Intent(MainActivity.this, in4Activity.class));
    }

    public void logOut(View view) {
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | i.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }

    public void Course(View view) {
        startActivity(new Intent(MainActivity.this, CourseActivity.class));
    }

    public void News(View view) {
        startActivity(new Intent(MainActivity.this, NewsActivity.class));
    }

    public void Maps(View view) {
        startActivity(new Intent(MainActivity.this, MapsActivity2.class));
    }

    public void social(View view) {
        startActivity(new Intent(MainActivity.this, SocialActivity.class));
    }
}