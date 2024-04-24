package adminproject.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import adminproject.fragment.OnceFragment;
import adminproject.fragment.OpenFragment;
import adminproject.fragment.TimeFragment;


public class MyPagerAdapter extends FragmentStateAdapter {


    public MyPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new TimeFragment();
            case 1:
                return new OnceFragment();
            case 2:
                return new OpenFragment();
            default:
                throw new IllegalStateException("Unexpected position: " + position);
        }
    }

    @Override
    public int getItemCount() {
        return 3;  // 因为有三个标签页
    }
}
