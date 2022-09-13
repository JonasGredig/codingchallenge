package com.ch.jonasgredig.codingchallenge;

import com.ch.jonasgredig.codingchallenge.controller.DataController;
import com.ch.jonasgredig.codingchallenge.controller.dto.DialogInputDTO;
import com.ch.jonasgredig.codingchallenge.model.Customer;
import com.ch.jonasgredig.codingchallenge.model.Dialog;
import com.ch.jonasgredig.codingchallenge.model.SupportedLanguages;
import com.ch.jonasgredig.codingchallenge.model.repository.CustomerRepository;
import com.ch.jonasgredig.codingchallenge.model.repository.DialogRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class DataControllerTest {

    @Autowired
    DialogRepository dialogRepository;

    @Autowired
    DialogRepository dialogRepository2;

    @Autowired
    CustomerRepository customerRepository;

    private DataController dataController;
    private DataController dataController2;

    private List<Customer> testCustomers;

    @BeforeEach
    void beforeEach() {
        this.dataController = new DataController(dialogRepository, customerRepository);
        this.dataController2 = new DataController(dialogRepository2, customerRepository);
        testCustomers = new ArrayList<>();

        testCustomers.add(customerRepository.save(new Customer("Roger", "Federer")));
        testCustomers.add(customerRepository.save(new Customer("Jonas", "Gredig")));
        testCustomers.add(customerRepository.save(new Customer("Christoph", "Aeschlimann")));
    }

    @Test
    void addDialogHappy() {
        ResponseEntity responseEntity = dataController.addDialog(
                testCustomers.get(0).getId(),
                55,
                new DialogInputDTO("Juhuu!", SupportedLanguages.DE));

        assert (responseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    void addDialogSad() {
        int notExistantUserID = 299792458;
        ResponseEntity responseEntity = dataController.addDialog(
                notExistantUserID,
                55,
                new DialogInputDTO("Juhuu!", SupportedLanguages.DE));

        assert (responseEntity.getStatusCode() == HttpStatus.NOT_FOUND);
    }

    @Test
    void getDataUnfilteredHTTPCodeHappy() {
        // when language or customerId are not set Spring web sets in null values and for page spring sets default value 0 if not present
        ResponseEntity<List<Dialog>> response = dataController.getData(null, null, 0);

        assert (response.getStatusCode() == HttpStatus.OK);
    }

    @Test
    void getDataUnfilteredResponseHappy() {

        // testData
        dialogRepository.save(new Dialog(1, testCustomers.get(0), SupportedLanguages.DE, "Hallo Welt", true, new Timestamp(new Date().getTime())));
        dialogRepository.save(new Dialog(1, testCustomers.get(1), SupportedLanguages.CH, "Grüazi", true, new Timestamp(new Date().getTime())));
        dialogRepository.save(new Dialog(1, testCustomers.get(2), SupportedLanguages.EN, "Hello World!", true, new Timestamp(new Date().getTime())));

        //check for consent false logic, it should not pick up this entry
        dialogRepository.save(new Dialog(1, testCustomers.get(0), SupportedLanguages.DE, "Hallo Welt2", false, new Timestamp(new Date().getTime())));


        // when language or customerId are not set Spring web sets in null values and for page spring sets default value 0 if not present
        ResponseEntity<List<Dialog>> response = dataController.getData(null, null, 0);
        // a total of 4 entries are set in beforEach method
        System.out.println(response.getBody().size());
        assert (response.getBody().size() == 3);
    }
}
