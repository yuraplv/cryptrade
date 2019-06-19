package com.yur.cryptrader.platforms.exmo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yur.cryptrader.common.currency.CurrencyPair;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

@Service
public class ExmoPriceReceiver {

    public String getPrice(CurrencyPair pair) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(URI.create("https://api.exmo.com/v1/ticker/"), String.class);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Map<String, String>> map = null;
        try {
            map = mapper.readValue(response, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map.get(pair.name()).get("last_trade");
    }


}
