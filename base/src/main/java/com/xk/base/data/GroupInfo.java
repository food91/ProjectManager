// GroupInfo.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.xk.base.data;
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

// Data.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

   public static class Data {
        private List<Worker> worker;
        private List<Group> group;
        public void  init(){
            worker =new ArrayList<>();
            group = new ArrayList<>();
        }

        public List<Worker> getWorker() { return worker; }
        public void setWorker(List<Worker> value) { this.worker = value; }

        public List<Group> getGroup() { return group; }
        public void setGroup(List<Group> value) { this.group = value; }
    }

// Group.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation



  public  static   class Group {
        private String groupName;
        private String creatTime;
        private long pId;
        private String creatId;
        private long id;
        private String groupValue;

        public String getGroupName() { return groupName; }
        public void setGroupName(String value) { this.groupName = value; }

        public String getCreatTime() { return creatTime; }
        public void setCreatTime(String value) { this.creatTime = value; }

        public int getPId() { return (int) pId; }
        public void setPId(long value) { this.pId = value; }

        public String getCreatId() { return creatId; }
        public void setCreatId(String value) { this.creatId = value; }

        public int getid() { return (int) id; }
        public void setid(long value) { this.id = value; }

        public String getGroupValue() { return groupValue; }
        public void setGroupValue(String value) { this.groupValue = value; }
    }

// Worker.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation


   public static class Worker {
        private String img;
        private String groupId;
        private String bankDeposit;
        private String workCondit;
        private String reputationScore;
        private long checkoutType;
        private String password;
        private String familyName;
        private String safetyScore;
        private long rank;
        private String bankAccountNumber;
        private long id;
        private String workService;
        private String cId;
        private long wage;
        private long wageType;
        private long pieceworkWage;
        private String founder;
        private long sex;
        private String pId;
        private String employId;
        private String userName;
        private String onbordTime;
        private String createTime;
        private String name;
        private String workType;
        private long isremove;
        private long age;

        public String getImg() { return img; }
        public void setImg(String value) { this.img = value; }

        public String getGroupId() { return groupId; }
        public void setGroupId(String value) { this.groupId = value; }

        public String getBankDeposit() { return bankDeposit; }
        public void setBankDeposit(String value) { this.bankDeposit = value; }

        public String getWorkCondit() { return workCondit; }
        public void setWorkCondit(String value) { this.workCondit = value; }

        public String getReputationScore() { return reputationScore; }
        public void setReputationScore(String value) { this.reputationScore = value; }

        public long getCheckoutType() { return checkoutType; }
        public void setCheckoutType(long value) { this.checkoutType = value; }

        public String getPassword() { return password; }
        public void setPassword(String value) { this.password = value; }

        public String getFamilyName() { return familyName; }
        public void setFamilyName(String value) { this.familyName = value; }

        public String getSafetyScore() { return safetyScore; }
        public void setSafetyScore(String value) { this.safetyScore = value; }

        public long getRank() { return rank; }
        public void setRank(long value) { this.rank = value; }

        public String getBankAccountNumber() { return bankAccountNumber; }
        public void setBankAccountNumber(String value) { this.bankAccountNumber = value; }

        public long getid() { return id; }
        public void setid(long value) { this.id = value; }

        public String getWorkService() { return workService; }
        public void setWorkService(String value) { this.workService = value; }

        public String getCId() { return cId; }
        public void setCId(String value) { this.cId = value; }

        public long getWage() { return wage; }
        public void setWage(long value) { this.wage = value; }

        public long getWageType() { return wageType; }
        public void setWageType(long value) { this.wageType = value; }

        public long getPieceworkWage() { return pieceworkWage; }
        public void setPieceworkWage(long value) { this.pieceworkWage = value; }

        public String getFounder() { return founder; }
        public void setFounder(String value) { this.founder = value; }

        public long getSex() { return sex; }
        public void setSex(long value) { this.sex = value; }

        public String getPId() { return pId; }
        public void setPId(String value) { this.pId = value; }

        public String getEmployId() { return employId; }
        public void setEmployId(String value) { this.employId = value; }

        public String getUserName() { return userName; }
        public void setUserName(String value) { this.userName = value; }

        public String getOnbordTime() { return onbordTime; }
        public void setOnbordTime(String value) { this.onbordTime = value; }

        public String getCreateTime() { return createTime; }
        public void setCreateTime(String value) { this.createTime = value; }

        public String getName() { return name; }
        public void setName(String value) { this.name = value; }

        public String getWorkType() { return workType; }
        public void setWorkType(String value) { this.workType = value; }

        public long getIsremove() { return isremove; }
        public void setIsremove(long value) { this.isremove = value; }

        public long getAge() { return age; }
        public void setAge(long value) { this.age = value; }
    }

}
