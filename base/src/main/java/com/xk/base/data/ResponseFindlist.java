// ResponseFindlist.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.xk.base.data;
import java.time.OffsetDateTime;
import java.util.List;

public class ResponseFindlist {
    private String msg;
    private long code;
    private List<Datum> data;

    public String getMsg() { return msg; }
    public void setMsg(String value) { this.msg = value; }

    public long getCode() { return code; }
    public void setCode(long value) { this.code = value; }

    public List<Datum> getData() { return data; }
    public void setData(List<Datum> value) { this.data = value; }


    public static class Datum {
        private String creatTiem;
        private long companyProject;
        private long id;
        private String projectName;
        private long projectPrice;

        public String getCreatTiem() { return creatTiem; }
        public void setCreatTiem(String value) { this.creatTiem = value; }

        public long getCompanyProject() { return companyProject; }
        public void setCompanyProject(long value) { this.companyProject = value; }

        public long getid() { return id; }
        public void setid(long value) { this.id = value; }

        public String getProjectName() { return projectName; }
        public void setProjectName(String value) { this.projectName = value; }

        public long getProjectPrice() { return projectPrice; }
        public void setProjectPrice(long value) { this.projectPrice = value; }

        @Override
        public String toString() {
            return "Datum{" +
                    "creatTiem='" + creatTiem + '\'' +
                    ", companyProject=" + companyProject +
                    ", id=" + id +
                    ", projectName='" + projectName + '\'' +
                    ", projectPrice=" + projectPrice +
                    '}';
        }
    }
}

// Datum.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation



