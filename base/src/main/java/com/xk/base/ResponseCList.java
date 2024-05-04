// ResponseCList.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.xk.base;
import java.util.List;

public class ResponseCList {
    private String msg;
    private long code;
    private List<Datum> data;

    public String getMsg() { return msg; }
    public void setMsg(String value) { this.msg = value; }

    public long getCode() { return code; }
    public void setCode(long value) { this.code = value; }

    public List<Datum> getData() { return data; }
    public void setData(List<Datum> value) { this.data = value; }
}

// Datum.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation


class Datum {
    private long lumpSum;
    private String contractNumber;
    private String section;
    private String deleted;

    private String contractName;
    private long id;
    private String projectName;
    private String cId;

    public long getLumpSum() { return lumpSum; }
    public void setLumpSum(long value) { this.lumpSum = value; }

    public String getContractNumber() { return contractNumber; }
    public void setContractNumber(String value) { this.contractNumber = value; }

    public String getSection() { return section; }
    public void setSection(String value) { this.section = value; }

    public String getDeleted() { return deleted; }
    public void setDeleted(String value) { this.deleted = value; }

    public String getContractName() { return contractName; }
    public void setContractName(String value) { this.contractName = value; }

    public long getid() { return id; }
    public void setid(long value) { this.id = value; }

    public String getProjectName() { return projectName; }
    public void setProjectName(String value) { this.projectName = value; }

    public String getCId() { return cId; }
    public void setCId(String value) { this.cId = value; }
}
