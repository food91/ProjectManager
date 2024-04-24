package com.xk.porject.data;

public class NoteWorker {
    private String avatarResource;
    private String name;
    private String department;
    private String position;
    private String status;

    public NoteWorker(String avatarResource, String name, String department, String position, String status) {
        this.avatarResource = avatarResource;
        this.name = name;
        this.department = department;
        this.position = position;
        this.status = status;
    }

    public String getAvatarResource() {
        return avatarResource;
    }

    public void setAvatarResource(String avatarResource) {
        this.avatarResource = avatarResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "avatarResource='" + avatarResource + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
