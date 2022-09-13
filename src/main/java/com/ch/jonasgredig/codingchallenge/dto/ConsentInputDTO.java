package com.ch.jonasgredig.codingchallenge.dto;

public class ConsentInputDTO {

    private boolean consent;

    public ConsentInputDTO(boolean consent) {
        this.consent = consent;
    }

    public ConsentInputDTO() {
    }

    public boolean isConsent() {
        return consent;
    }

    public void setConsent(boolean consent) {
        this.consent = consent;
    }
}
