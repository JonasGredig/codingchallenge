package com.ch.jonasgredig.codingchallenge.controller;

import com.ch.jonasgredig.codingchallenge.controller.dto.DialogInputDTO;
import com.ch.jonasgredig.codingchallenge.model.Dialog;
import com.ch.jonasgredig.codingchallenge.model.repository.CustomerRepository;
import com.ch.jonasgredig.codingchallenge.model.repository.DialogRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.*;

@RestController
public class DataController {

    private DialogRepository dialogRepository;
    private CustomerRepository customerRepository;

    private int pageSize = 50;

    public DataController(DialogRepository dialogRepository, CustomerRepository customerRepository) {
        this.dialogRepository = dialogRepository;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/data/{customerId}/{dialogId}")
    public ResponseEntity addDialog(@PathVariable Integer customerId,
                                    @PathVariable Integer dialogId,
                                    @RequestBody DialogInputDTO dialogInput) {
        try {
            Dialog dialog = dialogRepository.save(
                    new Dialog(
                            dialogId,
                            customerRepository.findById(customerId).orElseThrow(),
                            dialogInput.getLanguage(),
                            dialogInput.getText(),
                            false,
                            new Timestamp(new Date().getTime())));
            return dialog != null ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (NoSuchElementException e) {
            // CustomerId not does not Exist
            return new ResponseEntity<>("Customer with ID " + customerId + " not Found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/data/")
    public ResponseEntity<List<Dialog>> getData(@RequestParam(required = false) String language,
                                                    @RequestParam(required = false) Integer customerId,
                                                    @RequestParam(required = false, defaultValue = "0") Integer page) {
        try {
            //create Filter Critera
            Dialog dialog = new Dialog();
            if (customerId != null) {
                dialog.setCustomer(customerRepository.findById(customerId).orElseThrow());
            }
            dialog.setConversationLanguage(language);
            dialog.setConsent(true);

            Pageable pageable = PageRequest.of(
                    page,
                    pageSize,
                    Sort.by("created").descending());

            return new ResponseEntity<>(dialogRepository
                    .findAll(Example.of(dialog), pageable)
                    .getContent(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
    }

}
