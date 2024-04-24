package adminproject.data;

public class ProjectInfo {
    private String projectTitle;
    private String numberLabel;
    private String number;
    private String registrationTimeLabel;
    private String registrationTime;
    private String companyStatus;

    public ProjectInfo(String projectTitle, String numberLabel, String number,
                       String registrationTimeLabel, String registrationTime,
                       String companyStatus) {
        this.projectTitle = projectTitle;
        this.numberLabel = numberLabel;
        this.number = number;
        this.registrationTimeLabel = registrationTimeLabel;
        this.registrationTime = registrationTime;
        this.companyStatus = companyStatus;
    }

    // Getters
    public String getProjectTitle() {
        return projectTitle;
    }

    public String getNumberLabel() {
        return numberLabel;
    }

    public String getNumber() {
        return number;
    }

    public String getRegistrationTimeLabel() {
        return registrationTimeLabel;
    }

    public String getRegistrationTime() {
        return registrationTime;
    }

    public String getCompanyStatus() {
        return companyStatus;
    }

    // Setters
    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public void setNumberLabel(String numberLabel) {
        this.numberLabel = numberLabel;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setRegistrationTimeLabel(String registrationTimeLabel) {
        this.registrationTimeLabel = registrationTimeLabel;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }

    public void setCompanyStatus(String companyStatus) {
        this.companyStatus = companyStatus;
    }

    @Override
    public String toString() {
        return "ProjectInfo{" +
                "projectTitle='" + projectTitle + '\'' +
                ", numberLabel='" + numberLabel + '\'' +
                ", number='" + number + '\'' +
                ", registrationTimeLabel='" + registrationTimeLabel + '\'' +
                ", registrationTime='" + registrationTime + '\'' +
                ", companyStatus='" + companyStatus + '\'' +
                '}';
    }
}
