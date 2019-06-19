package com.yur.cryptrader.platforms.binance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yur.cryptrader.common.currency.CurrencyPair;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Vegas on 6/19/19.
 */
@Service
public class BinancePriceReceiver {

    public String getPrice(CurrencyPair pair) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("https://api.binance.com/api/v3/ticker/price?symbol={pair}", String.class, pair.name().replace("_", ""));

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = null;

        try {
            map = mapper.readValue(response, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map.get("price");
    }






}
