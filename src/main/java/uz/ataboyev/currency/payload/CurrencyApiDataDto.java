package uz.ataboyev.currency.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CurrencyApiDataDto {
    private Integer id;
    private String Code;
    private String Ccy;
    private String CcyNm_RU;
    private String CcyNm_UZ;
    private String CcyNm_UZC;
    private String CcyNm_EN;
    private String Nominal;
    private String Rate;
    private String Diff;
    private String Date;


}

// "id":69,
// "Code":"840",
// "Ccy":"USD",
// "CcyNm_RU":"Доллар США",
// "CcyNm_UZ":"AQSH dollari",
// "CcyNm_UZC":"АҚШ доллари",
// "CcyNm_EN":"US Dollar",
// "Nominal":"1",
// "Rate":"11408.02",
// "Diff":"16.79",
// "Date":"25.03.2023"