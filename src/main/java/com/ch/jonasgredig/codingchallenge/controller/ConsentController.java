package com.ch.jonasgredig.codingchallenge.controller;

import com.ch.jonasgredig.codingchallenge.controller.dto.ConsentInputDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsentController {


    @PostMapping("/consents/{dialogId}")
    private ResponseEntity checkConsent(@PathVariable Integer dialogId, @RequestBody ConsentInputDTO consentInputDto) {

        throw new RuntimeException("Not implemented");
    }
}
