package com.xk.porject.data;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class ExpandableListItem {
    private String title; // 列表项标题
    private int count; // 子项的数量
    private boolean isExpanded; // 是否展开以显示子项
    private List<ExpandableListItem> children; // 子项列表

    public ExpandableListItem(String title, int count) {
        this.title = title;
        this.count = count;
        this.children = new ArrayList<>();
        this.isExpanded = false;
    }
    public void toggleExpanded() {
        isExpanded = !isExpanded;
    }
    // 添加子项
    public void addChild(ExpandableListItem child) {
        this.children.add(child);
        this.count = this.children.size(); // 更新计数以反映子项列表的当前大小
    }

    // 获取和设置属性的方法

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    // count 的 setter 方法通常不需要，因为 count 应该反映 children 列表的大小

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public List<ExpandableListItem> getChildren() {
        return children;
    }

    // 通常你不需要设置整个子项列表，除非在初始化时

    // 可以添加 toString 方法以方便调试
    @Override
    public String toString() {
        return "ExpandableListItem{" +
                "title='" + title + '\'' +
                ", count=" + count +
                ", isExpanded=" + isExpanded +
                ", children=" + children +
                '}';
    }
}
