package com.xk.porject.home.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.xk.base.adapter.CommonAdapter;
import com.xk.civilengineering.home.vm.NotificationsViewModel;
import com.xk.porject.R;
import com.xk.porject.data.AnData;
import com.xk.porject.data.NoteWorker;
import com.xk.porject.databinding.ActivityCmactivityBinding;
import com.xk.porject.databinding.ItemNoteBinding;
import com.xk.porject.databinding.LayoutWithTitleAndTextBinding;
import com.xk.porject.home.ChatActivity;
import com.xk.porject.home.EASorceActivity;
import com.xk.porject.home.EmployeeEvaluationActivity;
import com.xk.porject.home.EmployeeManagementActivity;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private ActivityCmactivityBinding binding;

    private CommonAdapter<LayoutWithTitleAndTextBinding, AnData> commonAdapter;
    private List<AnData> list;
    private List<NoteWorker> list2,list3;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = ActivityCmactivityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        initView();
        initData();
        return root;
    }

   void  setSomeData() {
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();

        // 示例添加一些数据到list2
       list2.add(new NoteWorker("avatar1.png", "John Doe", "Marketing", "Manager", "Active"));
       list2.add(new NoteWorker("avatar2.png", "Jane Smith", "HR", "Recruiter", "Active"));

       // Add sample data to list3
       list3.add(new NoteWorker("avatar3.png", "Mike Brown", "IT", "Developer", "Active"));
       list3.add(new NoteWorker("avatar4.png", "Sara Wilson", "Finance", "Accountant", "On Leave"));
    }

    private void initView(){
        binding.rv1.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rv2.setLayoutManager(new LinearLayoutManager(getActivity()));
        list =new ArrayList<>();
        list.add(new AnData("标题 1", "这里是一些文本 1"));
        list.add(new AnData("标题 2", "这里是一些文本 2"));
        list.add(new AnData("标题 3", "这里是一些文本 3"));
        list.add(new AnData("标题 4", "这里是一些文本 4"));
        list.add(new AnData("标题 5", "这里是一些文本 5"));
        binding.rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.tvMessages.setSelected(true);
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        binding.tvAnnouncements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(true);
                binding.tvMessages.setTextColor(Color.GRAY);
                binding.tvAnnouncements.setTextColor(ContextCompat.getColor(getActivity(), R.color.orange));
                binding.tvAnnouncements.setBackgroundResource(R.drawable.rectangle_three_sides);
                binding.tvMessages.setBackgroundResource(R.drawable.rectangle_three_sides_bottom);
                binding.clContain.setVisibility(View.GONE);
                binding.rv.setBackgroundResource(R.drawable.rectangle_three_sides_top);
                binding.rv.setVisibility(View.VISIBLE);
                commonAdapter =new CommonAdapter<LayoutWithTitleAndTextBinding, AnData>(list) {
                    @Override
                    protected void show(LayoutWithTitleAndTextBinding holder, int position, AnData anData) {
                        holder.titleTextView.setText(anData.getTitle()); // 假设布局中标题TextView的ID是titleTextView
                        holder.contentTextView.setText(anData.getText());
                        holder.main.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(position==0){
                                    Intent intent = new Intent(getActivity(), EmployeeManagementActivity.class);
                                    startActivity(intent);
                                }else if(position==1){
                                    Intent intent = new Intent(getActivity(), EASorceActivity.class);
                                    startActivity(intent);
                                }else if(position == 2){
                                    Intent intent = new Intent(getActivity(), EmployeeEvaluationActivity.class);
                                    startActivity(intent);
                                }

                            }
                        });
                    }
                };
                binding.rv.setAdapter(commonAdapter);
            }
        });
        binding.tvMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(true);
                binding.clContain.setVisibility(View.VISIBLE);
                binding.rv.setVisibility(View.GONE);
                binding.tvAnnouncements.setTextColor(Color.GRAY);
                binding.tvMessages.setTextColor(ContextCompat.getColor(getActivity(), R.color.orange));
                binding.tvMessages.setBackgroundResource(R.drawable.rectangle_three_sides);
                binding.tvAnnouncements.setBackgroundResource(R.drawable.rounded_textview_background);
            }
        });
    }
    CommonAdapter<ItemNoteBinding,NoteWorker> commonAdapter1,commonAdapter2;
    private void initData(){
        setSomeData();
        commonAdapter1 = new CommonAdapter<ItemNoteBinding, NoteWorker>(list2) {
            @Override
            protected void show(ItemNoteBinding holder, int position, NoteWorker noteWorker) {
                        holder.textUserName.setText(noteWorker.getName());
                        holder.getRoot().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent =new Intent(getActivity(), ChatActivity.class);
                                startActivity(intent);
                            }
                        });
            }
        };
        commonAdapter2 = new CommonAdapter<ItemNoteBinding, NoteWorker>(list3) {
            @Override
            protected void show(ItemNoteBinding holder, int position, NoteWorker noteWorker) {
                holder.textUserName.setText(noteWorker.getName());
            }
        };
        binding.rv1.setAdapter(commonAdapter1);
        binding.rv2.setAdapter(commonAdapter2);
        binding.clRv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int  b= binding.rv1.getVisibility();
                if(b==View.GONE){
                    binding.rv1.setVisibility(View.VISIBLE);
                }
            }
        });
        binding.clRv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int  b= binding.rv2.getVisibility();
                if(b==View.GONE){
                    binding.rv2.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}