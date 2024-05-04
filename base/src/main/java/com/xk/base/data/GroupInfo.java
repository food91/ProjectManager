// GroupInfoa.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.xk.base.data;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GroupInfo {
    private String msg;
    private long code;
    private Data data;

    public String getMsg() { return msg; }
    public void setMsg(String value) { this.msg = value; }

    public long getCode() { return code; }
    public void setCode(long value) { this.code = value; }

    public Data getData() { return data; }
    public void setData(Data value) { this.data = value; }

   public static class Group {
        private String groupName;

        private long pId;
        private String creatId;
        private int id;
        private String groupValue;

        public String getGroupName() { return groupName; }
        public void setGroupName(String value) { this.groupName = value; }



       public long getPId() { return pId; }
        public void setPId(long value) { this.pId = value; }

        public String getCreatId() { return creatId; }
        public void setCreatId(String value) { this.creatId = value; }

        public int getid() { return id; }
        public void setid(int value) { this.id = value; }

        public String getGroupValue() { return groupValue; }
        public void setGroupValue(String value) { this.groupValue = value; }
    }

    public static class Worker{

    }

    public static class Data {
        private List<Worker> worker;
        private List<Group> group;

        public void init(){
            worker =new ArrayList<>();
            group =new ArrayList<>();
        }

        public List<Worker> getWorker() { return worker; }
        public void setWorker(List<Worker> value) { this.worker = value; }

        public List<Group> getGroup() { return group; }
        public void setGroup(List<Group> value) { this.group = value; }
    }

}

