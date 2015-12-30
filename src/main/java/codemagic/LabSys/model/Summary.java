package codemagic.LabSys.model;

public class Summary {
    private Integer sumId;

    private String sumTitle;

    private Integer sumPubliser;

    private String sumDetails;

    public Integer getSumId() {
        return sumId;
    }

    public void setSumId(Integer sumId) {
        this.sumId = sumId;
    }

    public String getSumTitle() {
        return sumTitle;
    }

    public void setSumTitle(String sumTitle) {
        this.sumTitle = sumTitle == null ? null : sumTitle.trim();
    }

    public Integer getSumPubliser() {
        return sumPubliser;
    }

    public void setSumPubliser(Integer sumPubliser) {
        this.sumPubliser = sumPubliser;
    }

    public String getSumDetails() {
        return sumDetails;
    }

    public void setSumDetails(String sumDetails) {
        this.sumDetails = sumDetails == null ? null : sumDetails.trim();
    }
}