package com.mediscreen.abernathyapp.assess.dtos;

import java.util.Set;

public class PatHistoryTermsCountDTO {

    private String patientId;
    private Set<String> terminology;
    private int termCount;

    public PatHistoryTermsCountDTO() {
    }

    public PatHistoryTermsCountDTO(String patientId, Set<String> terminology, int termCount) {
        this.patientId = patientId;
        this.terminology = terminology;
        this.termCount = termCount;
    }

    public String getPatientId() {
        return patientId;
    }

    public Set<String> getTerminology() {
        return terminology;
    }

    public int getTermCount() {
        return termCount;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setTerminology(Set<String> terminology) {
        this.terminology = terminology;
    }

    public void setTermCount(int termCount) {
        this.termCount = termCount;
    }
}
