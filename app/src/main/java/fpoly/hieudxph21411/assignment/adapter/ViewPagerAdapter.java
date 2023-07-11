package fpoly.hieudxph21411.assignment.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import fpoly.hieudxph21411.assignment.news.CarFragment;
import fpoly.hieudxph21411.assignment.news.SportFragment;
import fpoly.hieudxph21411.assignment.news.WorldFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CarFragment();
            case 1:
                return new WorldFragment();
            case 2:
                return new SportFragment();
            default:
                return new CarFragment();
        }

    }

    @Override
    public int getCount() { return 3; }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title= "";
        switch (position){
            case 0:
                title = "Xe";
                break;
            case 1:
                title = "Thế giới";
                break;
            case 2:
                title = "Thể thao";
                break;
        }
        return title;
    }
}
