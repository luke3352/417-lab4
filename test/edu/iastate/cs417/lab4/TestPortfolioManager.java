package edu.iastate.cs417.lab4;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.exceptions.base.MockitoAssertionError;
import org.mockito.exceptions.verification.VerificationInOrderFailure;
import org.mockito.exceptions.verification.junit.ArgumentsAreDifferent;

import edu.iastate.cs417.lab2.util.FileUtil;
import junit.framework.AssertionFailedError;

public class TestPortfolioManager {

	public static final double tolerance = .0005;
	Map<String,Double> currentPrices = new HashMap<String, Double>();
	StockServiceSessionFactory factory = null; 
	Portfolio holdings = null; 

	@Before
	public void setup(){
		currentPrices = PMTestUtil.loadPrices("current-prices.txt");
		holdings = PMTestUtil.loadPortfolio("portfolio-3.txt");
		MockSessionFactory mockFactory = new MockSessionFactory();
		mockFactory.setCurrentPrices(currentPrices);
		//just a reminder that the production code expects an unadorned factory. 
		factory = mockFactory;		
	}
	

	@Test
    public void test() {
        PortfolioManager pm = new PortfolioManager();
        pm.setPortfolio(holdings);
        pm.setStockService(factory);
        double result = pm.getMarketValue();
        System.out.println("Portfolio value: "+result);
        assertEquals(92120.24, result, tolerance );
    }
	
	//TODO: add other tests to determine if the portfolio manager returns 
	//           correct values and handles sessions correctly with various size
	//           portfolios. 

    @Test
    public void test2() {
        holdings = PMTestUtil.loadPortfolio("portfolio-99.txt");
        PortfolioManager pm = new PortfolioManager();
        pm.setPortfolio(holdings);
        pm.setStockService(factory);
        double result = pm.getMarketValue();
        System.out.println("Portfolio value: "+result);
        assertEquals(7141527.0, result, tolerance );
    }
}
