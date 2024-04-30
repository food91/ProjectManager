// ResponseProjectPostInfo.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.xk.base.data;
import java.time.LocalDate;
import java.util.List;

public class ResponseProjectPostInfo {
    private String msg;
    private long total;
    private long code;
    private long totalPage;
    private List<Row> rows;

    public String getMsg() { return msg; }
    public void setMsg(String value) { this.msg = value; }

    public long getTotal() { return total; }
    public void setTotal(long value) { this.total = value; }

    public long getCode() { return code; }
    public void setCode(long value) { this.code = value; }

    public long getTotalPage() { return totalPage; }
    public void setTotalPage(long value) { this.totalPage = value; }

    public List<Row> getRows() { return rows; }
    public void setRows(List<Row> value) { this.rows = value; }


  public static   class Row {
        private String demand;
        private String technologyDemand;

        private String phone;
        private String publish;
        private String projectAddress;
        private String completionDate;
        private long id;
        private String projectName;
        private long currentState;
        private long status;

        private String images;

      public String getImages() {
          return images;
      }

      public void setImages(String images) {
          this.images = images;
      }

      public String getDemand() { return demand; }
        public void setDemand(String value) { this.demand = value; }

        public String getTechnologyDemand() { return technologyDemand; }
        public void setTechnologyDemand(String value) { this.technologyDemand = value; }

        public String getPhone() { return phone; }
        public void setPhone(String value) { this.phone = value; }

        public String getPublish() { return publish; }
        public void setPublish(String value) { this.publish = value; }

        public String getProjectAddress() { return projectAddress; }
        public void setProjectAddress(String value) { this.projectAddress = value; }

        public String getCompletionDate() { return completionDate; }
        public void setCompletionDate(String value) { this.completionDate = value; }

        public long getid() { return id; }
        public void setid(long value) { this.id = value; }

        public String getProjectName() { return projectName; }
        public void setProjectName(String value) { this.projectName = value; }

        public long getCurrentState() { return currentState; }
        public void setCurrentState(long value) { this.currentState = value; }

        public long getStatus() { return status; }
        public void setStatus(long value) { this.status = value; }
    }

}

// Row.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

