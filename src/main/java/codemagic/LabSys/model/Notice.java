package codemagic.LabSys.model;

public class Notice {
    private Integer noticeId;

    private Integer noticePublisher;

    private String noticeDetails;

    private String noticeTitle;

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public Integer getNoticePublisher() {
        return noticePublisher;
    }

    public void setNoticePublisher(Integer noticePublisher) {
        this.noticePublisher = noticePublisher;
    }

    public String getNoticeDetails() {
        return noticeDetails;
    }

    public void setNoticeDetails(String noticeDetails) {
        this.noticeDetails = noticeDetails == null ? null : noticeDetails.trim();
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle == null ? null : noticeTitle.trim();
    }
}