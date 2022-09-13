package com.ch.jonasgredig.codingchallenge.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Dialog {

    private @Id @GeneratedValue Integer technicalId;
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
    private String conversationLanguage;
    private String conversationText;
    private Boolean consent;
    private Timestamp created;

    public Dialog() {
    }

    public Dialog(Integer id, Customer customer, String conversationLanguage, String conversationText, Boolean consent, Timestamp created) {
        this.id = id;
        this.customer = customer;
        this.conversationLanguage = conversationLanguage;
        this.conversationText = conversationText;
        this.consent = consent;
        this.created = created;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Boolean isConsent() {
        return consent;
    }

    public void setConsent(Boolean consent) {
        this.consent = consent;
    }

    public Integer getTechnicalId() {
        return technicalId;
    }

    public void setTechnicalId(Integer technicalId) {
        this.technicalId = technicalId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getUser() {
        return customer;
    }

    public void setUser(Customer customer) {
        this.customer = customer;
    }

    public String getConversationLanguage() {
        return conversationLanguage;
    }

    public void setConversationLanguage(String conversationLanguage) {
        this.conversationLanguage = conversationLanguage;
    }

    public String getConversationText() {
        return conversationText;
    }

    public void setConversationText(String text) {
        this.conversationText = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dialog dialog = (Dialog) o;
        return Objects.equals(id, dialog.id) && Objects.equals(customer, dialog.customer) && Objects.equals(conversationLanguage, dialog.conversationLanguage) && Objects.equals(conversationText, dialog.conversationText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, conversationLanguage, conversationText);
    }

    @Override
    public String toString() {
        return "Dialog{" +
                "id=" + id +
                ", user=" + customer +
                ", conversationLanguage='" + conversationLanguage + '\'' +
                ", text='" + conversationText + '\'' +
                '}';
    }
}
