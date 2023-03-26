package uz.ataboyev.currency;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import uz.ataboyev.currency.payload.CurrencyApiDataDto;
import uz.ataboyev.currency.payload.NewCurrencyApiDto;

import java.util.List;
import java.util.stream.Collectors;

public class demo {
    public static void main(String[] args) {

        //VALYUTANI TAFSILOTI BILAN OLIB KELADI
//        System.out.println(getCurrency());

        //FAQAT VALYUTANI NARXINI OLIB KELADI
//        System.out.println(getCurrencyRate());

        System.out.println(getDollar().toString());



    }

    public static NewCurrencyApiDto getDollar(){
        try {

            RestTemplate restTemplate = new RestTemplate();
            String url = "https://nbu.uz/en/exchange-rates/json/";

            //API DAN MA'LUMOTLAR OLINDI
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            //STRINGNI DTOGA O'RAB DOLLARNI AJRATIB OLDIK JAMI VALYUTALAR ORASIDAN
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
            List<NewCurrencyApiDto> res = mapper.readValue(response.getBody(), new TypeReference<List<NewCurrencyApiDto>>() {
            }).stream().filter(c -> c.getCode().equals("USD")).collect(Collectors.toList());

            //DOLLARNI RATE NI QAYTARYAPMIZ
            return res.get(0);
//             System.out.println(res.get(0).getRate());


        } catch (Exception e) {

            //AGAR APIDAN MA'LUMOT KELMASA 0 QAYTARAMIZ
            return new NewCurrencyApiDto();
//             System.out.println(0);
        }
    }
    //    VALYUTANI TAFSILOTI BILAN OLIB KELADI
    public static CurrencyApiDataDto getCurrency(){
        try {

            RestTemplate restTemplate = new RestTemplate();
            String url = "https://cbu.uz/uz/arkhiv-kursov-valyut/json/";

            //API DAN MA'LUMOTLAR OLINDI
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            //STRINGNI DTOGA O'RAB DOLLARNI AJRATIB OLDIK JAMI VALYUTALAR ORASIDAN
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
            List<CurrencyApiDataDto> res = mapper.readValue(response.getBody(), new TypeReference<List<CurrencyApiDataDto>>() {
            }).stream().filter(c -> c.getCode().equals("840") && c.getCcy().equals("USD")).collect(Collectors.toList());

            //DOLLARNI RATE NI QAYTARYAPMIZ
            return res.get(0);
//             System.out.println(res.get(0).getRate());


        } catch (Exception e) {

            //AGAR APIDAN MA'LUMOT KELMASA 0 QAYTARAMIZ
            return new CurrencyApiDataDto();
//             System.out.println(0);
        }
    }

    //FAQAT VALYUTANI NARXINI OLIB KELADI
     public static String getCurrencyRate(){
         try {

             RestTemplate restTemplate = new RestTemplate();
             String url = "https://cbu.uz/uz/arkhiv-kursov-valyut/json/";

             //API DAN MA'LUMOTLAR OLINDI
             ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

             //STRINGNI DTOGA O'RAB DOLLARNI AJRATIB OLDIK JAMI VALYUTALAR ORASIDAN
             ObjectMapper mapper = new ObjectMapper();
             mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
             mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
             List<CurrencyApiDataDto> res = mapper.readValue(response.getBody(), new TypeReference<List<CurrencyApiDataDto>>() {
             }).stream().filter(c -> c.getCode().equals("840") && c.getCcy().equals("USD")).collect(Collectors.toList());

            return res.get(0).getRate();

         } catch (Exception e) {

             //AGAR APIDAN MA'LUMOT KELMASA 0 QAYTARAMIZ
//             System.out.println(0);
             return "0";
         }
     }


}
