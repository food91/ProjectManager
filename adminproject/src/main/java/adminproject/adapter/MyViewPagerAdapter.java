package adminproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.xk.adminproject.databinding.AdItemVerpagerBinding;

import java.util.List;

import adminproject.ContractorListActivity;
import adminproject.data.ProjectInfo;

public class MyViewPagerAdapter extends RecyclerView.Adapter<MyViewPagerAdapter.ProjectViewHolder> {

    private List<ProjectInfo> projectList;
    private Context context;

    public MyViewPagerAdapter(List<ProjectInfo> projectList, Context context) {
        this.projectList = projectList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 使用ViewBinding来加载布局
        AdItemVerpagerBinding binding = AdItemVerpagerBinding.
                inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProjectViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        ProjectInfo project = projectList.get(position);
        // 直接通过binding访问视图
        holder.binding.tvProjectTitle.setText(project.getProjectTitle());
        holder.binding.tvNumber.setText(project.getNumber());
        holder.binding.tvRegistrationTime.setText(project.getRegistrationTime());
        holder.binding.tvCompanyStatus.setText(project.getCompanyStatus());
        holder.binding.main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, ContractorListActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return projectList != null ? projectList.size() : 0;
    }

    public static class ProjectViewHolder extends RecyclerView.ViewHolder {
        // 使用ViewBinding
        final AdItemVerpagerBinding binding;

        public ProjectViewHolder(AdItemVerpagerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}


