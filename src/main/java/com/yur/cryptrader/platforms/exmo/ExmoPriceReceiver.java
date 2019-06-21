package com.yur.cryptrader.platforms.exmo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yur.cryptrader.common.currency.CurrencyPair;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ExmoPriceReceiver {

    public String getPrice(CurrencyPair pair) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(URI.create("https://api.exmo.com/v1/ticker/"), String.class);
        ObjectMapper mapper = new ObjectMapper();
        try {
            final Map<String, Map<String, String>> map = mapper.readValue(response, Map.class);
            List<String> noFiatList = map.keySet().stream()
                    .filter(Pattern.compile("^((?!USD|UAH|RUB|TRY|EUR|PLN|XTZ).)*$")
                    .asPredicate())
                    .filter(e -> e.endsWith("ETH"))
                    .map(e -> e.substring(0, e.indexOf("_ETH")))
                    .collect(Collectors.toList());

            Map<String, String> btcPairs = noFiatList.stream().collect(Collectors.toMap(k -> k + "_BTC", v -> map.get(v + "_BTC").get("last_trade")));
            Map<String, String> ethPairs = noFiatList.stream().collect(Collectors.toMap(k -> k + "_ETH", v -> map.get(v + "_ETH").get("last_trade")));
            System.out.println("btc pairs: " + btcPairs);
            System.out.println("eth pairs: " + ethPairs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
