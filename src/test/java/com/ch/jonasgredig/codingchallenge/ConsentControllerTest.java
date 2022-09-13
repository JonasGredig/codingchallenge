package com.ch.jonasgredig.codingchallenge;

import com.ch.jonasgredig.codingchallenge.controller.ConsentController;
import com.ch.jonasgredig.codingchallenge.dto.ConsentInputDTO;
import com.ch.jonasgredig.codingchallenge.model.Customer;
import com.ch.jonasgredig.codingchallenge.model.Dialog;
import com.ch.jonasgredig.codingchallenge.model.repository.CustomerRepository;
import com.ch.jonasgredig.codingchallenge.model.repository.DialogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Date;

@SpringBootTest
public class ConsentControllerTest {

    @Autowired
    DialogRepository dialogRepository;

    @Autowired CustomerRepository customerRepository;
    ConsentController consentController;

    private int dialogId = 1;
    private int technicalDialogId;

    @BeforeEach
    void beforeEach() {
        this.consentController = new ConsentController(dialogRepository);
        Customer customer = new Customer("Roger", "Federer");
        customerRepository.save(customer);

        Dialog dialog = dialogRepository.save(new Dialog(
                dialogId,
                customer,
                SupportedLanguages.DE,
                "Hello World!",
                false,
                new Timestamp(new Date().getTime())));

        this.technicalDialogId = dialog.getTechnicalId();
    }

    @Test
    void dialogWasSetHappy() {
        assert(dialogRepository.findById(technicalDialogId).isPresent());
    }

    @Test
    void dialogWasSetSad() {
        int notExistantID = 299792458;
        assert(dialogRepository.findById(notExistantID).isPresent() == false);
    }

    @Test
    void setDialogHappy() {
        consentController.setConsent(dialogId, new ConsentInputDTO(true));
        boolean consentFromDB = dialogRepository.findById(technicalDialogId).get().isConsent();
        assert (consentFromDB == true);
    }
}
