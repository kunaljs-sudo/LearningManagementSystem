package com.demo.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.lms.dto.EasterEggDTO;
import com.demo.lms.exception.BadDataProvidedException;
import com.demo.lms.service.easterEggService.EasterEggService;

@RestController
@RequestMapping("/hidden-feature")
public class EasterEggController {

    @Autowired
    private EasterEggService easterEggService;

    @GetMapping("/{number}")
    public EasterEggDTO getNumberFact(@PathVariable String number) {
        // remove all leading and trailing whitespaces
        number = number.trim();
        if (number == null || number.length() == 0) {
            throw new BadDataProvidedException("Please provide atleast number of length 1");
        }
        // if number len is grater than
        if (number.length() > 18) {
            throw new BadDataProvidedException("Too Long Number given");
        }

        return easterEggService.getNumberFact(number);
    }
}
