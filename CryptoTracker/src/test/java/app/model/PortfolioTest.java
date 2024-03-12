package app.model;

import app.CoinAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PortfolioTest {
    @Mock
    private CoinAPI coinAPI;
    @InjectMocks
    private Portfolio portfolio;

    @BeforeEach
    void startUp() {
        portfolio.purgeDb();
    }

    @Test
    void shouldAddTrade() {
        // GIVEN
        Trade trade = new Trade("BTC", 50000.0, 0.0025);
        // WHEN
        portfolio.addTrade(trade);
        // THEN
        assertThat(portfolio.getTrades().size()).isEqualTo(1);
        assertThat(portfolio.getTrades()).contains(trade);
        assertThat(portfolio.getContent().get("BTC")).isEqualTo(0.0025);
    }

    @Test
    void shouldDeleteTrade() {
        // GIVEN
        Trade trade = new Trade("ETH", 50000.0, 0.0025);
        portfolio.addTrade(trade);
        // WHEN
        portfolio.deleteTrade(trade);
        // THEN
        assertThat(portfolio.getTrades().size()).isEqualTo(0);
    }

    @Test
    void shouldGetValue() {
        // GIVEN
        Trade trade1 = new Trade("BTC", 50000.0, 0.0025);
        portfolio.addTrade(trade1);
        Trade trade2 = new Trade("ETH", 3000.0, 0.05);
        portfolio.addTrade(trade2);
        // WHEN
        when(portfolio.getCoinAPI().getCoinExchangeRate("BTC")).thenReturn(45000.0);
        when(portfolio.getCoinAPI().getCoinExchangeRate("ETH")).thenReturn(2500.0);
        double portfolioValue = portfolio.getValue();
        // THEN
        assertThat(portfolioValue).isEqualTo(45000.0 * 0.0025 + 2500.0 * 0.05);
    }

    @Test
    void shouldGetTradeById() {
        // GIVEN
        Trade trade = new Trade("BTC", 50000.0, 0.0025);
        portfolio.addTrade(trade);
        // WHEN
        Trade retrievedTrade = portfolio.getTradeById(1);
        // THEN
        assertThat(retrievedTrade).isEqualTo(trade);
    }

    @Test
    void shouldReturnCoinOccurences() {
        // GIVEN
        Trade trade1 = new Trade("BTC", 50000.0, 0.0025);
        portfolio.addTrade(trade1);
        Trade trade2 = new Trade("BTC", 3000.0, 0.05);
        portfolio.addTrade(trade2);
        // WHEN
        int result = portfolio.getCoinOccurrences("BTC");
        // THEN
        assertThat(result).isEqualTo(2);
    }
}