package fpoly.hieudxph21411.assignment.news;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import fpoly.hieudxph21411.assignment.R;
import fpoly.hieudxph21411.assignment.service.NewsService;

public class CarFragment extends Fragment {
    private RecyclerView rcv;
    private NewsService service;

    ServiceConnection sv_conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            NewsService.LocalBinder localBinder = (NewsService.LocalBinder) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {}
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_car, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        service = new NewsService();
        Intent intentService = new Intent(getActivity(), NewsService.class);
        getContext().bindService(intentService, sv_conn, Context.BIND_AUTO_CREATE);
        service = new NewsService(getContext());
        rcv = view.findViewById(R.id.rcvCar);
        service.loadTinTuc("https://vnexpress.net/rss/oto-xe-may.rss", rcv);

    }
}