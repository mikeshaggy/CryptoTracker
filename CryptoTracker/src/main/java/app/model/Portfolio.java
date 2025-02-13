package app.model;

import app.CoinAPI;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Portfolio implements Serializable {
    private final String name;
    private final List<Trade> trades = new ArrayList<>();
    private final Map<String, Double> content = new HashMap<>();
    private double investedAmount;
    private final CoinAPI coinAPI;

    public Portfolio(String name) {
        this.name = name;
        this.coinAPI = new CoinAPI();
    }

    public Portfolio(String name, CoinAPI coinAPI) {
        this.name = name;
        this.coinAPI = coinAPI;
    }

    public void addTrade(Trade trade) {
        trades.add(trade);
        String coinName = trade.getCoinName();
        double coinQuantity = trade.getQuantity();
        investedAmount += trade.getValue();
        if (content.containsKey(coinName)) {
            content.put(coinName, content.get(coinName) + coinQuantity);
        } else {
            content.put(coinName, coinQuantity);
        }
    }

    public void printTradeList() {
        if (!trades.isEmpty()) {
            for (int i = 0; i < trades.size(); i++) {
                System.out.printf("ID: %d | %s%n", i+1, trades.get(i));
            }
            return;
        }
        System.out.println("Trade list is empty");
    }

    public double getValue() {
        double value = 0;
        for (Map.Entry<String, Double> entry : content.entrySet()) {
            String coinName = entry.getKey();
            double coinAmount = entry.getValue();
            double coinPrice = coinAPI.getCoinExchangeRate(coinName);
            value += coinPrice * coinAmount;
        }

        return value;
    }

    private double getPercentageChange() {
        double currentValue = getValue();

        return ((currentValue - investedAmount) / investedAmount) * 100;
    }

    public void getDetails() {

        if (trades.isEmpty()) {
            System.out.println("Portfolio is empty!");
            return;
        }
        double percentageChange = getPercentageChange();

        String result = percentageChange >= 0 ? "Gain" : "Loss";
        double profit = getValue() - investedAmount;

        System.out.printf("%-5s | %-5s | %-15s%n", "Coin Name", "Held Quantity", "Estimated Value in USD [$]");
        for (Map.Entry<String, Double> entry : content.entrySet()) {
            String coinName = entry.getKey();
            double coinAmount = entry.getValue();
            double coinPrice = coinAPI.getCoinExchangeRate(coinName);
            double coinValue = coinPrice * coinAmount;
            System.out.printf("%-9s | %-13.9f | %-15.2f%n", coinName, coinAmount, coinValue);
        }

        System.out.println("-".repeat(60));
        System.out.printf("Invested amount: %.2f$%n", investedAmount);
        System.out.printf("Total portfolio value: %.2f$%n", getValue());
        System.out.printf("%s: %.2f$ (%s%.2f%%)%n", result, profit, (profit > 0) ? "+" : "", percentageChange);
    }

    public Trade getTradeById(int id) {
        Trade tradeToReturn = null;

        try {
            tradeToReturn = trades.get(id - 1);
            return tradeToReturn;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR: Wrong trade ID");
        }

        return tradeToReturn;
    }

    public void deleteTrade(Trade trade) {
        String coinName = trade.getCoinName();
        int coinCount = getCoinOccurrences(coinName);
        if (coinCount == 1) {
            content.remove(trade.getCoinName());
        } else if (coinCount > 1) {
            double coinQuantity = trade.getQuantity();
            content.compute(coinName, (k, currentQuantity) -> currentQuantity - coinQuantity);
        }
        trades.remove(trade);
        investedAmount -= trade.getPrice() * trade.getQuantity();
    }

    protected int getCoinOccurrences(String coinName) {
        coinName = coinName.toUpperCase();
        int result = 0;
        for (Trade trade : trades) {
            if (trade.getCoinName().equals(coinName)) {
                result++;
            }
        }

        return result;
    }

    void purgeDb() {
        trades.clear();
    }

    public String getName() {
        return name;
    }

    public List<Trade> getTrades() {
        return trades;
    }

    public double getInvestedAmount() {
        return investedAmount;
    }

    public Map<String, Double> getContent() {
        return content;
    }

    public CoinAPI getCoinAPI() {
        return coinAPI;
    }
}
