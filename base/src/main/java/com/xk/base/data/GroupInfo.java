// GroupInfo.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.xk.base.data;
import java.util.List;

public class GroupInfo {
    private String msg;
    private long code;
    private List<Datum> data;

    public String getMsg() { return msg; }
    public void setMsg(String value) { this.msg = value; }

    public long getCode() { return code; }
    public void setCode(long value) { this.code = value; }

    public List<Datum> getData() { return data; }
    public void setData(List<Datum> value) { this.data = value; }

    @Override
    public String toString() {
        return "GroupInfo{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }

    public static class Datum {
        private String groupName;
        private String groupValue;
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGroupName() { return groupName; }
        public void setGroupName(String value) { this.groupName = value; }

        public String getGroupValue() { return groupValue; }
        public void setGroupValue(String value) { this.groupValue = value; }

        @Override
        public String toString() {
            return "Datum{" +
                    "groupName='" + groupName + '\'' +
                    ", groupValue='" + groupValue + '\'' +
                    '}';
        }
    }


}

// Datum.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

