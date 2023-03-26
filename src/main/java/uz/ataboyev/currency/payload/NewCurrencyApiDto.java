package uz.ataboyev.currency.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewCurrencyApiDto {

    private String title;
    private String code;
    private String cb_price;
    private String nbu_buy_price;
    private String nbu_cell_price;
    private String date;

    @Override
    public String toString() {
        return "{" + "\n" +
                "\"title\" : \"" + title + '\"' + ",\n" +
                "\"code\" : \"" + code + '\"' + ",\n" +
                "\"cb_price\" : \"" + cb_price + '\"' + ",\n" +
                "\"nbu_buy_price\" : \"" + nbu_buy_price + '\"' + ",\n" +
                "\"nbu_cell_price\" : \"" + nbu_cell_price + '\"' + ",\n" +
                "\"date\" : \"" + date + '\"' + "\n" +
                '}';
    }
}


//{
//        "title": "UAE Dirham",
//        "code": "AED",
//        "cb_price": "2978.51",
//        "nbu_buy_price": "",
//        "nbu_cell_price": "",
//        "date": "03\/25\/2023"
//    },
