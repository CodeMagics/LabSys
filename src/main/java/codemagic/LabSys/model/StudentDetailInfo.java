package codemagic.LabSys.model;

public class StudentDetailInfo {
	private Integer studNum;
	private String userRealname;

	private Integer userType;

	private String userPhone;

	private String userEMail;
	private String studClass;

	private Integer studUserid;
	private String studMajor;

	public String getUserRealname() {
		return userRealname;
	}

	public void setUserRealname(String userRealname) {
		this.userRealname = userRealname == null ? null : userRealname.trim();
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone == null ? null : userPhone.trim();
	}

	public String getUserEMail() {
		return userEMail;
	}

	public void setUserEMail(String userEMail) {
		this.userEMail = userEMail == null ? null : userEMail.trim();
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

	public String getStudMajor() {
		return studMajor;
	}

	public void setStudMajor(String studMajor) {
		this.studMajor = studMajor;
	}

}
