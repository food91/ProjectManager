package adminproject.data;

public class ProjectDetails {
    private String projectTitle;
    private String fundingAmount;
    private String paymentAmount;
    private String paymentRatio;
    private int totalStaff;
    private String statusChange;
    private String reminderFrequency;

    public ProjectDetails(String projectTitle, String fundingAmount, String paymentAmount,
                          String paymentRatio, int totalStaff, String statusChange, String reminderFrequency) {
        this.projectTitle = projectTitle;
        this.fundingAmount = fundingAmount;
        this.paymentAmount = paymentAmount;
        this.paymentRatio = paymentRatio;
        this.totalStaff = totalStaff;
        this.statusChange = statusChange;
        this.reminderFrequency = reminderFrequency;
    }

    // Getters and Setters
    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getFundingAmount() {
        return fundingAmount;
    }

    public void setFundingAmount(String fundingAmount) {
        this.fundingAmount = fundingAmount;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentRatio() {
        return paymentRatio;
    }

    public void setPaymentRatio(String paymentRatio) {
        this.paymentRatio = paymentRatio;
    }

    public int getTotalStaff() {
        return totalStaff;
    }

    public void setTotalStaff(int totalStaff) {
        this.totalStaff = totalStaff;
    }

    public String getStatusChange() {
        return statusChange;
    }

    public void setStatusChange(String statusChange) {
        this.statusChange = statusChange;
    }

    public String getReminderFrequency() {
        return reminderFrequency;
    }

    public void setReminderFrequency(String reminderFrequency) {
        this.reminderFrequency = reminderFrequency;
    }

    // toString method for debugging purposes
    @Override
    public String toString() {
        return "ProjectDetails{" +
                "projectTitle='" + projectTitle + '\'' +
                ", fundingAmount='" + fundingAmount + '\'' +
                ", paymentAmount='" + paymentAmount + '\'' +
                ", paymentRatio='" + paymentRatio + '\'' +
                ", totalStaff=" + totalStaff +
                ", statusChange='" + statusChange + '\'' +
                ", reminderFrequency='" + reminderFrequency + '\'' +
                '}';
    }
}
