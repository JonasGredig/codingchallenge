package com.ch.jonasgredig.codingchallenge.controller.dto;

public class DialogInputDTO {
    private String text;
    private String language;

    public DialogInputDTO() {
    }

    public DialogInputDTO(String text, String language) {
        this.text = text;
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
