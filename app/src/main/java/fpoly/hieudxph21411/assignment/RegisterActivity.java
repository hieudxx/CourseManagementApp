package fpoly.hieudxph21411.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import fpoly.hieudxph21411.assignment.dao.NguoiDungDAO;
import fpoly.hieudxph21411.assignment.databinding.ActivityLoginBinding;
import fpoly.hieudxph21411.assignment.databinding.ActivityRegisterBinding;
import fpoly.hieudxph21411.assignment.model.NguoiDung;
import fpoly.hieudxph21411.assignment.service.LoginService;
import fpoly.hieudxph21411.assignment.service.RegisterService;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private NguoiDungDAO dao;
    IntentFilter intentFilter;
    private int temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = findViewById(R.id.toolbarRegis);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

//        intentFilter = new IntentFilter();
//        intentFilter.addAction("checkRegis");


        binding.edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    binding.tvName.setError("Vui lòng nhập tên");
                } else {
                    binding.tvName.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        binding.edtUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    binding.tvUserName.setError("Vui lòng nhập tài khoản");
                } else {
                    binding.tvUserName.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        binding.edtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    binding.tvPass.setError("Vui lòng nhập mật khẩu");
                } else {
                    binding.tvPass.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        binding.edtRePass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    binding.tvRePass.setError("Vui lòng nhập lại mật khẩu");
                } else {
                    binding.tvRePass.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        binding.btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.tvName.getEditText().getText().toString();
                String user = binding.tvUserName.getEditText().getText().toString();
                String pass = binding.tvPass.getEditText().getText().toString();
                String repass = binding.tvRePass.getEditText().getText().toString();
                if (user.isEmpty() || pass.isEmpty() || repass.isEmpty() || name.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Hãy nhập đủ dữ liệu", Toast.LENGTH_SHORT).show();
                    if (user.equals("")) {
                        binding.tvUserName.setError("Vui lòng nhập tài khoản");
                    } else {
                        binding.tvUserName.setError(null);
                    }
                    if (name.equals("")) {
                        binding.tvName.setError("Vui lòng nhập họ tên");
                    } else {
                        binding.tvName.setError(null);

                    }
                    if (pass.equals("")) {
                        binding.tvPass.setError("Vui lòng nhập mật khẩu");
                    } else {
                        binding.tvPass.setError(null);
                    }
                    temp = repass.compareTo(pass);
                    Toast.makeText(RegisterActivity.this, temp+"Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                    if (repass.equals("")) {
                        binding.tvRePass.setError("Vui lòng nhập lại mật khẩu");
                    } else {
                        binding.tvRePass.setError(null);
                    }
                } else if ( binding.tvPass.getEditText().getText().toString().equals(binding.tvRePass.getEditText().getText().toString())){
                    dao = new NguoiDungDAO(RegisterActivity.this);
                    int check = dao.insert(user, pass, name);

                    switch (check){
                        case 1:
                            Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | i.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);
                            finish();

                            break;
                        case 0: Toast.makeText(RegisterActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show(); break;
                        case -1: Toast.makeText(RegisterActivity.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show(); break;
                        default: break;
                    }

//                    Intent i = new Intent(RegisterActivity.this, RegisterService.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("name", name);
//                    bundle.putString("user", user);
//                    bundle.putString("pass", pass);
//                    i.putExtras(bundle);
//                    startService(i);
                } else {
                    Toast.makeText(RegisterActivity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        registerReceiver(myBroadCast, intentFilter);
//    }

//    public BroadcastReceiver myBroadCast = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            switch (intent.getAction()) {
//                case "checkRegis":
//                    Bundle bundle = intent.getExtras();
//                    int check = bundle.getInt("check");
//                    switch (check) {
//                        case 1:
//                            Toast.makeText(context, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//                            break;
//                        case 0:
//                            Toast.makeText(context, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
//                            break;
//                        case -1:
//                            Toast.makeText(context, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
//                        default:
//                            break;
//                    }
//                    break;
//                default:
//                    break;
//            }
//        }
//    };

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