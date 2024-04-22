package com.demo.lms.service.easterEggService;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.lms.dto.EasterEggDTO;
import com.demo.lms.exception.BadDataProvidedException;
import com.demo.lms.exception.NumberAPIException;

@Service
public class EasterEggServiceImpl implements EasterEggService {
    private final String url = "http://numbersapi.com";

    @Override
    public EasterEggDTO getNumberFact(String number) {
        String response = fetchDataFromExternalApi(number);
        EasterEggDTO easterEggDTO = new EasterEggDTO();
        easterEggDTO.setFact(response);
        easterEggDTO.setNumber(number);
        return easterEggDTO;
    }

    private String fetchDataFromExternalApi(String number) {
        isStringContainsDigitOnly(number);
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = url + "/" + number;
        String response;
        try {
            response = restTemplate.getForObject(apiUrl, String.class);
        } catch (Exception e) {
            throw new NumberAPIException("There some issue... try again later");
        }

        return response;

    }

    private void isStringContainsDigitOnly(String s) {
        for (Character ch : s.toCharArray()) {
            if (Character.isLetter(ch)) {
                throw new BadDataProvidedException("Number should contain number only not alphabets");
            }
        }
    }

}
