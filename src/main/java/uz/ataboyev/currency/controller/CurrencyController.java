package uz.ataboyev.currency.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import uz.ataboyev.currency.payload.CurrencyApiDataDto;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(path = "/api/currency")
@RestController
@RequiredArgsConstructor
public class CurrencyController {

    @GetMapping("/get")
    ResponseEntity<?> getCurrency() {
        try {

            RestTemplate restTemplate = new RestTemplate();
            String url = "https://cbu.uz/uz/arkhiv-kursov-valyut/json/";

            //API DAN MA'LUMOTLAR OLINDI
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            //STRINGNI DTOGA O'RASH JARAYONI
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
            List<CurrencyApiDataDto> result = mapper.readValue(response.getBody(), new TypeReference<List<CurrencyApiDataDto>>() {
            });

            //DTO DAN DOLLARNI AJRATIB OLDIK
            List<CurrencyApiDataDto> res = result.stream().filter(c -> c.getCode().equals("840") && c.getCcy().equals("USD")).collect(Collectors.toList());

            //DOLLARNI RATE NI QAYTARYAPMIZ
            return ResponseEntity.ok(res.get(0).getRate());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //AGAR APIDAN MA'LUMOT KELMASA NULL QAYTARAMIZ
        return ResponseEntity.ok(null);


    }
}
