package fpoly.hieudxph21411.assignment.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import fpoly.hieudxph21411.assignment.R;
import fpoly.hieudxph21411.assignment.databinding.DialogCourseDetailBinding;
import fpoly.hieudxph21411.assignment.databinding.ItemRcvRegisterBinding;
import fpoly.hieudxph21411.assignment.model.MonHoc;
import fpoly.hieudxph21411.assignment.model.ThongTin;
import fpoly.hieudxph21411.assignment.service.CourseRegisterService;

public class CourseRegisterAdapter extends RecyclerView.Adapter<CourseRegisterAdapter.ViewHolder>{
    private Context context;
    private ArrayList<MonHoc> list;
    private int id;
    private boolean isAll;
    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;
    private DialogCourseDetailBinding detailBinding;

    public CourseRegisterAdapter(Context context, ArrayList<MonHoc> list, int id, boolean isAll) {
        this.context = context;
        this.list = list;
        this.id = id;
        this.isAll = isAll;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemRcvRegisterBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_rcv_register, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.tvCode.setText("Mã môn: " + list.get(position).getCode());
        holder.binding.tvName.setText("Tên môn: " + list.get(position).getName());
        holder.binding.tvTeacher.setText("Giảng viên: " + list.get(position).getTeacher());

        if (list.get(position).getIsRegister() > 0) {
            holder.binding.btnDky.setText("Hủy Đăng Ký");
            holder.binding.btnDky.setBackgroundColor(Color.RED);
        } else {
            holder.binding.btnDky.setText("Đăng Ký Môn Học");
        }

        holder.binding.btnDky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, CourseRegisterService.class);
                Bundle b = new Bundle();
                b.putInt("id", id);
                b.putString("code", list.get(holder.getAdapterPosition()).getCode());
                b.putInt("isRegister", list.get(holder.getAdapterPosition()).getIsRegister());
                b.putBoolean("isAll", isAll);
                i.putExtras(b);
                context.startService(i);
            }
        });

        holder.binding.imgDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(list.get(holder.getAdapterPosition()).getListTT());
            }
        });

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemRcvRegisterBinding binding;

        public ViewHolder(@NonNull ItemRcvRegisterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private void showDialog(ArrayList<ThongTin> list) {
        builder = new AlertDialog.Builder(context); // view.getRootView().getContext()
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.dialog_course_detail, null);
        detailBinding = DialogCourseDetailBinding.inflate(inflater, view, false);

        builder.setView(detailBinding.getRoot());
        alertDialog = builder.create();

//        ListView lvTT = detailBinding.lvTT.
        ArrayList<HashMap<String, Object>> listTT = new ArrayList<>();
        for (ThongTin tt : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("date", "Ngày học: " + tt.getDate());
            hs.put("address", "Giảng đường: " + tt.getAddress());
            listTT.add(hs);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                context,
                listTT,
                android.R.layout.simple_list_item_2,
                new String[]{"date", "address"},
                new int[]{android.R.id.text1, android.R.id.text2});
        detailBinding.lvTT.setAdapter(simpleAdapter);
        alertDialog.show();


    }
}
