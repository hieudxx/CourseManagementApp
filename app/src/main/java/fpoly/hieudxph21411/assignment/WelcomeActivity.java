package fpoly.hieudxph21411.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //        nếu sử dụng Imageview như bth thì gif sẽ k động đậy,
//        nên ta sử import thêm thư viện glide vào trong Gradle Scripts - > build.gradle -> lấy code trong trang https://github.com/bumptech/glide dán vào dependencies
        ImageView imgBook = findViewById(R.id.imgBook);
        Glide.with(this).load(R.mipmap.splash2).into(imgBook);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                finish();

            }
        }, 3000);
    }
}