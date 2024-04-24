package com.xk.porject.home.adaoter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xk.porject.R;
import com.xk.porject.home.AttendanceActivity;
import com.xk.porject.home.MyBillActivity;
import com.xk.porject.home.WorkerActivity;

import java.util.List;

public class HomeBottomAdapter extends RecyclerView.Adapter<HomeBottomAdapter.ViewHolder> {
    private Context context;
    private List<HomeBottomAdapterList> listItems;

    public HomeBottomAdapter(Context context, List<HomeBottomAdapterList> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeBottomAdapterList listItem = listItems.get(position);
        holder.titleTextView.setText(listItem.getTitle());
        holder.textView.setText(listItem.getText());
        if(position==0){
            holder.iconImageView.setImageResource(R.drawable.work_icon);
            holder.button.setVisibility(View.VISIBLE);
            holder.button.setText("开工");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(context, WorkerActivity.class);
                    context.startActivity(intent);
                }
            });
        }
        if(position==1){
            holder.iconImageView.setImageResource(R.drawable.count_icon);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(context, WorkerActivity.class);
                    intent.putExtra("mode","count");
                    context.startActivity(intent);
                }
            });
        }
        if (position==2){
            holder.iconImageView.setImageResource(R.drawable.money_icon);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(context, WorkerActivity.class);
                    intent.putExtra("mode","salary");
                    context.startActivity(intent);
                }
            });
        }
        if (position==3){
            holder.iconImageView.setImageResource(R.drawable.bill_icon);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(context, MyBillActivity.class);
                    context.startActivity(intent);
                }
            });
        }
        if(position==5){
            holder.button.setVisibility(View.VISIBLE);
            holder.button.setText("签到   签退");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(context, AttendanceActivity.class);
                    context.startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView textView;
        public ImageView iconImageView;

        public Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title);
            textView = itemView.findViewById(R.id.text);
            iconImageView = itemView.findViewById(R.id.icon);
            button = itemView.findViewById(R.id.bt);
        }
    }
}
