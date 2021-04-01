package com.binance.api.examples;

import com.binance.api.client.BinanceApiAsyncMarginRestClient;
import com.binance.api.client.domain.TransferType;
import com.binance.api.client.factory.BinanceAbstractFactory;
import com.binance.api.client.factory.BinanceSpotApiClientFactory;

/**
 * Examples on how to get margin account information asynchronously.
 */
public class MarginAccountEndpointsExampleAsync {

    public static void main(String[] args) {
        BinanceSpotApiClientFactory factory = BinanceAbstractFactory.createSpotFactory("YOUR_API_KEY", "YOUR_SECRET");
        BinanceApiAsyncMarginRestClient client = factory.newAsyncMarginRestClient();

        // Get account balances
        client.getAccount(marginAccount -> {
            System.out.println(marginAccount.getUserAssets());
            System.out.println(marginAccount.getAssetBalance("ETH"));
            System.out.println(marginAccount.getMarginLevel());
        });

        // Get list of trades
        client.getMyTrades("NEOETH", System.out::println);

        // Transfer, borrow, repay
        client.transfer("USDT", "1", TransferType.SPOT_TO_MARGIN, transaction -> System.out.println(transaction.getTranId()));
        client.borrow("USDT", "1", transaction -> System.out.println(transaction.getTranId()));
        client.repay("USDT", "1", transaction -> System.out.println(transaction.getTranId()));
        client.transfer("USDT", "1", TransferType.MARGIN_TO_SPOT, transaction -> System.out.println(transaction.getTranId()));
    }
}
