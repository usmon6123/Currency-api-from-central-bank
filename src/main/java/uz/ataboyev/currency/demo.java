package uz.ataboyev.currency;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import uz.ataboyev.currency.payload.CurrencyApiDataDto;

import java.util.List;
import java.util.stream.Collectors;

public class demo {
    public static void main(String[] args) {
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
            System.out.println(res.get(0).getRate());

        } catch (Exception e) {

            //AGAR APIDAN MA'LUMOT KELMASA 0 QAYTARAMIZ
            System.out.println(0);
        }
  }
}
