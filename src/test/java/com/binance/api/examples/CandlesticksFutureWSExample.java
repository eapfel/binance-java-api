package com.binance.api.examples;

import com.binance.api.client.BinanceApiCallback;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.event.CandlestickEvent;
import com.binance.api.client.domain.market.CandlestickInterval;

public class CandlesticksFutureWSExample {

    public static void main(String[] args) {

        //BinanceApiWebSocketClient wsClient = BinanceApiClientFactory.newFutureInstance("YOUR_API_KEY", "YOUR_SECRET").newWebSocketClient();
        BinanceApiWebSocketClient wsClient = BinanceApiClientFactory.newFutureInstance().newWebSocketClient();
        wsClient.onCandlestickEvent("algousdt", CandlestickInterval.ONE_MINUTE,
                new BinanceApiCallback<CandlestickEvent>() {
                    @Override
                    public void onResponse(CandlestickEvent response) {
                        System.out.println(
                                String.format(
                                        "%s(isFinal:%s): [%s, %s, %s, %s, %s, %s]",
                                        response.getSymbol(),
                                        response.getBarFinal(),
                                        response.getOpenTime(),
                                        response.getOpen(),
                                        response.getHigh(),
                                        response.getLow(),
                                        response.getClose(),
                                        response.getVolume()
                                )
                        );
                    }

                    @Override
                    public void onFailure(Throwable cause) {
                        cause.printStackTrace();
                    }
                });
    }
}
