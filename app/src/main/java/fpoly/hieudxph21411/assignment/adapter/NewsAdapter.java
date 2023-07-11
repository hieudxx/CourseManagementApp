package fpoly.hieudxph21411.assignment.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import fpoly.hieudxph21411.assignment.R;
import fpoly.hieudxph21411.assignment.news.WebViewActivity;
import fpoly.hieudxph21411.assignment.databinding.ItemRcvNewsBinding;
import fpoly.hieudxph21411.assignment.model.TinTuc;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private ArrayList<TinTuc> list;
    private Context context;
    int LAYOUT;
    public NewsAdapter(ArrayList<TinTuc> list, Context context, int LAYOUT) {
        this.list = list;
        this.context = context;
        this.LAYOUT = LAYOUT;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemRcvNewsBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_rcv_news, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.tvTitle.setText(list.get(position).getTitle());
        holder.binding.tvDesc.setText(list.get(position).getDesc());
        Picasso.get().load(list.get(position).getImg()).fit().into(holder.binding.imgNews);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebViewActivity.class);
                Bundle bundle = new Bundle();
                TinTuc tinTuc = list.get(holder.getLayoutPosition());
                bundle.putString("title", tinTuc.getTitle());
                bundle.putString("link", tinTuc.getLink());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemRcvNewsBinding binding;

        public ViewHolder(@NonNull ItemRcvNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
