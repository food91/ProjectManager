package adminproject.data;

public class ManagerInfo {
    private String projectName;
    private String managerName;
    private String contactNumber;
    private boolean isVisible;

    // Constructor
    public ManagerInfo(String projectName, String managerName, String contactNumber) {
        this.projectName = projectName;
        this.managerName = managerName;
        this.contactNumber = contactNumber;
        this.isVisible = false; // Initially not visible
    }

    // Getters and Setters
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    // toString method for easy printing/debugging
    @Override
    public String toString() {
        return "ManagerInfo{" +
                "projectName='" + projectName + '\'' +
                ", managerName='" + managerName + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", isVisible=" + isVisible +
                '}';
    }
}

