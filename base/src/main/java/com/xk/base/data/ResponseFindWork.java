// ResponseFindWork.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.xk.base.data;
import java.util.List;

public class ResponseFindWork {
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
        private String concrete;
        private String workerId;
        private long workerStatus;
        private String strong_point;
        private String name;
        private String native_place;

        private String phone;
        private String image;
        private long id;


      public String getPhone() {
          return phone;
      }

      public void setPhone(String phone) {
          this.phone = phone;
      }

      public String getImage() {
          return image;
      }

      public void setImage(String image) {
          this.image = image;
      }

      public String getConcrete() { return concrete; }
        public void setConcrete(String value) { this.concrete = value; }

        public String getWorkerId() { return workerId; }
        public void setWorkerId(String value) { this.workerId = value; }

        public int getWorkerStatus() { return (int) workerStatus; }
        public void setWorkerStatus(long value) { this.workerStatus = value; }

        public String getStrongPoint() { return strong_point; }
        public void setStrongPoint(String value) { this.strong_point = value; }

        public String getName() { return name; }
        public void setName(String value) { this.name = value; }

        public String getNativePlace() { return native_place; }
        public void setNativePlace(String value) { this.native_place = value; }

        public long getid() { return id; }
        public void setid(long value) { this.id = value; }
    }

}

// Row.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation
