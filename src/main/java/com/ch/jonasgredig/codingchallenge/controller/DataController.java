package com.ch.jonasgredig.codingchallenge.controller;

import com.ch.jonasgredig.codingchallenge.controller.dto.DialogInputDTO;
import com.ch.jonasgredig.codingchallenge.model.Dialog;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DataController {


    @PostMapping("/data/{customerId}/{dialogId}")
    public ResponseEntity addDialog(@PathVariable Integer customerId,
                                    @PathVariable Integer dialogId,
                                    @RequestBody DialogInputDTO dialogInput) {
        throw new RuntimeException("Not Implemented");
    }

    @GetMapping("/data/")
    public ResponseEntity<Iterable<Dialog>> getData(@RequestParam(required = false) String language,
                                                    @RequestParam(required = false) Integer customerId,
                                                    @RequestParam(required = false, defaultValue = "0") Integer page) {
        throw new RuntimeException("not implemented");
    }

}
