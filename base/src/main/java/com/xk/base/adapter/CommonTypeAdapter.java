package com.xk.base.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.collection.SparseArrayCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class CommonTypeAdapter<V extends ViewBinding,T> extends RecyclerView.Adapter<ViewHolder<V>> {

    private List<T> data;

    private SparseArrayCompat<Class<? extends ViewBinding>> layoutMap = new SparseArrayCompat<>();

    public CommonTypeAdapter(List<T> data) {
        this.data = data;
    }
    @NonNull
    @Override
    public ViewHolder<V> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(getViewBinding(parent));
    }
    public void registerViewType(int viewType, Class<? extends ViewBinding> bindingClass) {
        layoutMap.put(viewType, bindingClass);
    }

    private V getViewBinding(ViewGroup parent) {
        V bind = null;
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Class<V> clazz = (Class<V>) type.getActualTypeArguments()[0];
        try {
            Method inflate = clazz.getDeclaredMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
            bind = (V) inflate.invoke(null, LayoutInflater.from(parent.getContext()), parent, false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bind;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder<V> holder, int position) {
        show(holder.bind, position, data.get(position));
    }

    protected abstract void show(V holder, int position, T t);
    protected abstract int getLayoutType(int position, T t);
    @Override
    public int getItemViewType(int position) {
        // 根据数据项返回相应的布局类型，这需要您在子类中实现
        return getLayoutType(position, data.get(position));
    }


    public void setData(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public CommonTypeAdapter setText(TextView textView, String data){
        textView.setText(data);
        return this;
    }
}
