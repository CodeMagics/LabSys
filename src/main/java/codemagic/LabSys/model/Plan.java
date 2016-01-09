package codemagic.LabSys.model;

public class Plan {
    private Integer planId;

    private String planTitle;

    private Integer planPubliser;

    private String planDetails;

	private String planDate;

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getPlanTitle() {
        return planTitle;
    }

    public void setPlanTitle(String planTitle) {
        this.planTitle = planTitle == null ? null : planTitle.trim();
    }

    public Integer getPlanPubliser() {
        return planPubliser;
    }

    public void setPlanPubliser(Integer planPubliser) {
        this.planPubliser = planPubliser;
    }

    public String getPlanDetails() {
        return planDetails;
    }

    public void setPlanDetails(String planDetails) {
        this.planDetails = planDetails == null ? null : planDetails.trim();
    }

    public String getPlanDate() {
        return planDate;
    }
    
    public void setPlanDate(String planDate) {
        this.planDate = planDate==null?null:planDate.trim();
    }
}