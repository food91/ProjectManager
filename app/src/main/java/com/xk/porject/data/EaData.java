package com.xk.porject.data;

public class EaData {
    String username;
    String workname;
    String workkind;

    public EaData(String username, String workname, String workkind) {
        this.username = username;
        this.workname = workname;
        this.workkind = workkind;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWorkname() {
        return workname;
    }

    public void setWorkname(String workname) {
        this.workname = workname;
    }

    public String getWorkkind() {
        return workkind;
    }

    public void setWorkkind(String workkind) {
        this.workkind = workkind;
    }
}
