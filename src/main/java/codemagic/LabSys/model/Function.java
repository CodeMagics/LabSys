package codemagic.LabSys.model;

public class Function {
    private Integer funcId;

    private String funcName;

    private Integer funcPareid;

    private String funcUrl;

    private String funcType;

    private Integer funcOrdinal;

    private String funcDescription;

    public Integer getFuncId() {
        return funcId;
    }

    public void setFuncId(Integer funcId) {
        this.funcId = funcId;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName == null ? null : funcName.trim();
    }

    public Integer getFuncPareid() {
        return funcPareid;
    }

    public void setFuncPareid(Integer funcPareid) {
        this.funcPareid = funcPareid;
    }

    public String getFuncUrl() {
        return funcUrl;
    }

    public void setFuncUrl(String funcUrl) {
        this.funcUrl = funcUrl == null ? null : funcUrl.trim();
    }

    public String getFuncType() {
        return funcType;
    }

    public void setFuncType(String funcType) {
        this.funcType = funcType == null ? null : funcType.trim();
    }

    public Integer getFuncOrdinal() {
        return funcOrdinal;
    }

    public void setFuncOrdinal(Integer funcOrdinal) {
        this.funcOrdinal = funcOrdinal;
    }

    public String getFuncDescription() {
        return funcDescription;
    }

    public void setFuncDescription(String funcDescription) {
        this.funcDescription = funcDescription == null ? null : funcDescription.trim();
    }
}