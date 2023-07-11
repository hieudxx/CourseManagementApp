package fpoly.hieudxph21411.assignment;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import fpoly.hieudxph21411.assignment.databinding.ActivityLoginBinding;
import fpoly.hieudxph21411.assignment.service.LoginService;

public class LoginActivity extends AppCompatActivity {
    IntentFilter intentFilter;
    private ActivityLoginBinding binding;
    private SharedPreferences shared;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        shared = getSharedPreferences("INFO", MODE_PRIVATE);
        boolean isCheck = shared.getBoolean("isChecked", false);
        if (isCheck) {
            binding.edUserName.setText(shared.getString("userLogin", ""));
            binding.edPass.setText(shared.getString("passLogin", ""));
            binding.chkLogin.setChecked(isCheck);
        }

        intentFilter = new IntentFilter();
        intentFilter.addAction("checkLogin");

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = binding.edUserName.getText().toString();
                String pass = binding.edPass.getText().toString();

                Intent i = new Intent(LoginActivity.this, LoginService.class);
                Bundle bundle = new Bundle();
                bundle.putString("user", user);
                bundle.putString("pass", pass);
                i.putExtras(bundle);
                startService(i);
            }
        });

        binding.tvDky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
//                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
//                launcher.launch(i);

            }
        });
    }

    private ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
//                  nơi xử lý dữ liệu từ đăng ký
                }
            });

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myBroadCast, intentFilter);
    }

    public BroadcastReceiver myBroadCast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case "checkLogin":
                    Bundle bundle = intent.getExtras();
                    boolean check = bundle.getBoolean("check");
                    if (check) {
                        if (binding.chkLogin.isChecked()) {
                            shared = getSharedPreferences("INFO", MODE_PRIVATE);
                            SharedPreferences.Editor editor = shared.edit(); // gọi dòng trên và edit vào nó
                            editor.putString("userLogin", binding.edUserName.getText().toString());
                            editor.putString("passLogin", binding.edPass.getText().toString());
                            editor.putBoolean("isChecked", binding.chkLogin.isChecked());
                            editor.apply();
                        } else {
                            shared = getSharedPreferences("INFO", MODE_PRIVATE);
                            SharedPreferences.Editor editor = shared.edit(); // gọi dòng trên và edit vào nó
                            editor.clear();
                            editor.apply();
                        }
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | i.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        finish();

                    } else {
                        Toast.makeText(context, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
    };
}