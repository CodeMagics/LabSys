package codemagic.LabSys.model;

public class Student {
    private Integer studId;

    private Integer studNum;

    private String studClass;

    private Integer studUserid;
    
    private String studMajor;

    public Integer getStudId() {
        return studId;
    }

    public String getStudMajor() {
		return studMajor;
	}

	public void setStudMajor(String studMajor) {
		this.studMajor = studMajor;
	}

	public void setStudId(Integer studId) {
        this.studId = studId;
    }

    public Integer getStudNum() {
        return studNum;
    }

    public void setStudNum(Integer studNum) {
        this.studNum = studNum;
    }

    public String getStudClass() {
        return studClass;
    }

    public void setStudClass(String studClass) {
        this.studClass = studClass == null ? null : studClass.trim();
    }

    public Integer getStudUserid() {
        return studUserid;
    }

    public void setStudUserid(Integer studUserid) {
        this.studUserid = studUserid;
    }
}