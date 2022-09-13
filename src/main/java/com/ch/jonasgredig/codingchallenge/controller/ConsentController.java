package com.ch.jonasgredig.codingchallenge.controller;

import com.ch.jonasgredig.codingchallenge.dto.ConsentInputDTO;
import com.ch.jonasgredig.codingchallenge.model.Dialog;
import com.ch.jonasgredig.codingchallenge.model.repository.DialogRepository;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConsentController {

    private final DialogRepository dialogRepository;

    public ConsentController(DialogRepository dialogRepository) {
        this.dialogRepository = dialogRepository;
    }


    @PostMapping("/consents/{dialogId}")
    public ResponseEntity setConsent(@PathVariable Integer dialogId,
                                     @RequestBody ConsentInputDTO consentDto) {

        Dialog exampleDialog = new Dialog();
        exampleDialog.setId(dialogId);
        List<Dialog> dialogs = dialogRepository.findAll(Example.of(exampleDialog));

        if (dialogs == null || dialogs.isEmpty()) {
            return new ResponseEntity("Dialog with ID " + dialogId + " Not Found", HttpStatus.NOT_FOUND);
        }

        dialogs.stream().parallel().forEach(dialog -> {
            dialog.setConsent(consentDto.isConsent());
            dialogRepository.save(dialog);
        });
        return new ResponseEntity(HttpStatus.OK);
    }
}
