package codemagic.LabSys.model;

public class Summary {
    private Integer sumId;

    private String sumTitle;

    private Integer sumPubliser;

    private String sumDetails;
    
    private String sumDate;

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
    
    public String getSumDate(){
    	return sumDate;
    }
    
    public void setSumDate(String sumDate){
    	this.sumDate = sumDate == null?null : sumDate.trim();
    }
}