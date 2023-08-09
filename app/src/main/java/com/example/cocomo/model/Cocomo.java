package com.example.cocomo.model;

import java.io.Serializable;

public class Cocomo implements Serializable {
    String _id, projectName, sizeType, language, PREC, FLEX, RESL,
            TEAM, PMAT, RELY, DATA, CPLX, RUSE, DOCU, ACAP,
            PCAP, PCON, AEXP, PEXP, LTEX, TIME, STOR, PVOL,
            TOOL, SITE, SCED;
    double functionPoints, newSize, reusedSize, reusedIM,
            reusedAA, softwareLaborCostPerPM, softwareEAF,
            softwareEffort, softwareSchedule, totalEquivalentSize,
            cost;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSizeType() {
        return sizeType;
    }

    public void setSizeType(String sizeType) {
        this.sizeType = sizeType;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPREC() {
        return PREC;
    }

    public void setPREC(String PREC) {
        this.PREC = PREC;
    }

    public String getFLEX() {
        return FLEX;
    }

    public void setFLEX(String FLEX) {
        this.FLEX = FLEX;
    }

    public String getRESL() {
        return RESL;
    }

    public void setRESL(String RESL) {
        this.RESL = RESL;
    }

    public String getTEAM() {
        return TEAM;
    }

    public void setTEAM(String TEAM) {
        this.TEAM = TEAM;
    }

    public String getPMAT() {
        return PMAT;
    }

    public void setPMAT(String PMAT) {
        this.PMAT = PMAT;
    }

    public String getRELY() {
        return RELY;
    }

    public void setRELY(String RELY) {
        this.RELY = RELY;
    }

    public String getDATA() {
        return DATA;
    }

    public void setDATA(String DATA) {
        this.DATA = DATA;
    }

    public String getCPLX() {
        return CPLX;
    }

    public void setCPLX(String CPLX) {
        this.CPLX = CPLX;
    }

    public String getRUSE() {
        return RUSE;
    }

    public void setRUSE(String RUSE) {
        this.RUSE = RUSE;
    }

    public String getDOCU() {
        return DOCU;
    }

    public void setDOCU(String DOCU) {
        this.DOCU = DOCU;
    }

    public String getACAP() {
        return ACAP;
    }

    public void setACAP(String ACAP) {
        this.ACAP = ACAP;
    }

    public String getPCAP() {
        return PCAP;
    }

    public void setPCAP(String PCAP) {
        this.PCAP = PCAP;
    }

    public String getPCON() {
        return PCON;
    }

    public void setPCON(String PCON) {
        this.PCON = PCON;
    }

    public String getAEXP() {
        return AEXP;
    }

    public void setAEXP(String AEXP) {
        this.AEXP = AEXP;
    }

    public String getPEXP() {
        return PEXP;
    }

    public void setPEXP(String PEXP) {
        this.PEXP = PEXP;
    }

    public String getLTEX() {
        return LTEX;
    }

    public void setLTEX(String LTEX) {
        this.LTEX = LTEX;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public String getSTOR() {
        return STOR;
    }

    public void setSTOR(String STOR) {
        this.STOR = STOR;
    }

    public String getPVOL() {
        return PVOL;
    }

    public void setPVOL(String PVOL) {
        this.PVOL = PVOL;
    }

    public String getTOOL() {
        return TOOL;
    }

    public void setTOOL(String TOOL) {
        this.TOOL = TOOL;
    }

    public String getSITE() {
        return SITE;
    }

    public void setSITE(String SITE) {
        this.SITE = SITE;
    }

    public String getSCED() {
        return SCED;
    }

    public void setSCED(String SCED) {
        this.SCED = SCED;
    }

    public double getFunctionPoints() {
        return functionPoints;
    }

    public void setFunctionPoints(double functionPoints) {
        this.functionPoints = functionPoints;
    }

    public double getNewSize() {
        return newSize;
    }

    public void setNewSize(double newSize) {
        this.newSize = newSize;
    }

    public double getReusedSize() {
        return reusedSize;
    }

    public void setReusedSize(double reusedSize) {
        this.reusedSize = reusedSize;
    }

    public double getReusedIM() {
        return reusedIM;
    }

    public void setReusedIM(double reusedIM) {
        this.reusedIM = reusedIM;
    }

    public double getReusedAA() {
        return reusedAA;
    }

    public void setReusedAA(double reusedAA) {
        this.reusedAA = reusedAA;
    }

    public double getSoftwareLaborCostPerPM() {
        return softwareLaborCostPerPM;
    }

    public void setSoftwareLaborCostPerPM(double softwareLaborCostPerPM) {
        this.softwareLaborCostPerPM = softwareLaborCostPerPM;
    }

    public double getSoftwareEAF() {
        return softwareEAF;
    }

    public void setSoftwareEAF(double softwareEAF) {
        this.softwareEAF = softwareEAF;
    }

    public double getSoftwareEffort() {
        return softwareEffort;
    }

    public void setSoftwareEffort(double softwareEffort) {
        this.softwareEffort = softwareEffort;
    }

    public double getSoftwareSchedule() {
        return softwareSchedule;
    }

    public void setSoftwareSchedule(double softwareSchedule) {
        this.softwareSchedule = softwareSchedule;
    }

    public double getTotalEquivalentSize() {
        return totalEquivalentSize;
    }

    public void setTotalEquivalentSize(double totalEquivalentSize) {
        this.totalEquivalentSize = totalEquivalentSize;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
