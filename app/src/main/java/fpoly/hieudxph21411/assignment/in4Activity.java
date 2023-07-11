package fpoly.hieudxph21411.assignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import fpoly.hieudxph21411.assignment.dao.NguoiDungDAO;
import fpoly.hieudxph21411.assignment.databinding.ActivityIn4Binding;
import fpoly.hieudxph21411.assignment.databinding.DialogChangePassBinding;

public class in4Activity extends AppCompatActivity {
    private ActivityIn4Binding binding;
    private DialogChangePassBinding editBinding;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIn4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences shared = getSharedPreferences("PROFILE", MODE_PRIVATE);
        String user = shared.getString("userName", "");
        String pass = shared.getString("passWord", "");
        String name = shared.getString("name", "");

        binding.tvTitle.setText("Xin chào: " + name);
        binding.tvTK.setText(user);
        binding.tvTen.setText(name);
        binding.btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(in4Activity.this, RegisterActivity.class));
            }
        });

        binding.btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.tvPassChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(in4Activity.this); // view.getRootView().getContext()
                builder.setCancelable(false);
                LayoutInflater inflater = getLayoutInflater();
                view = getLayoutInflater().inflate(R.layout.dialog_change_pass, null);
                editBinding = DialogChangePassBinding.inflate(inflater, (ViewGroup) view, false);

                builder.setView(editBinding.getRoot());
                AlertDialog alertDialog = builder.create();
                alertDialog.show();


                editBinding.btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String oldpass = editBinding.edOldPass.getText().toString();
                        String newpass = editBinding.edNewPass.getText().toString();
                        String repass = editBinding.edRePass.getText().toString();
                        if (oldpass.isEmpty() || newpass.isEmpty() || repass.isEmpty()) {
                            Toast.makeText(in4Activity.this, "Vui lòng nhập đủ thông in", Toast.LENGTH_SHORT).show();
                        } else if (repass.equals(newpass)) {
                            SharedPreferences share = getSharedPreferences("PROFILE", Context.MODE_PRIVATE);
                            String username = share.getString("userName", "");
                            NguoiDungDAO dao = new NguoiDungDAO(in4Activity.this);
                            int check = dao.updatePass(username, oldpass, newpass);
                            switch (check) {
                                case 1:
                                    Toast.makeText(in4Activity.this, "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(in4Activity.this, LoginActivity.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(i);
                                    break;
                                case 0:
                                    Toast.makeText(in4Activity.this, "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                                    break;
                                case -1:
                                    Toast.makeText(in4Activity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                            }
                        } else {
                            Toast.makeText(in4Activity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                editBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });


            }
        });

    }
}