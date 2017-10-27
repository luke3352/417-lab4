package edu.iastate.cs417.lab4;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.inOrder;


import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.exceptions.verification.VerificationInOrderFailure;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.verification.VerificationMode;

import junit.framework.AssertionFailedError;

public class TrackingMockSession implements ValidatingStockServiceSession {

	private int maxSessionRequests;
	private long maxSessionDuration;
	private StockServiceSession delegate; 
	private Map<String,Double> currentPrices = null; 

	
	public TrackingMockSession(int maxRequests, long maxDuration, Map<String,Double> prices) {
		maxSessionRequests = maxRequests;
		maxSessionDuration = maxDuration;
		delegate = mock(StockServiceSession.class);
		currentPrices = prices;
		configureMock();
	}

	@Override
	public boolean login(String username, String password) {
		return delegate.login(username, password);
	}

	@Override
	public double getCurrentPrice(Stock stock) 
			throws RequestLimitExceededException
	{
		return delegate.getCurrentPrice(stock);
	}

	TrackingMockSession mock = mock(TrackingMockSession.class);


	/*
	Notice the includes in TrackingMockSession. The includes are a big hint about which Mockito
    methods/objects you will need to understand to complete this lab.

    While many of the inorder examples show method calls against different objects, you can construct an inOrder
    verifier that monitors only a single object.

    Despite how most of the documentation reads, when inOrder verifies that one method call
    happens before the other. It does not verify that the earlier method call happens first on the
    object instance.

    In our case, showing that login() happened first is equivalent to showing that login() happened
    before getCurrentPrice() and that getCurrentPrice() never happened before login().

	 How many times should login() happen in a single session? Test for that.

	 You can define more than one InOrder validator for the same object instance.

	You can call as many standalone verify methods as you like without interfering with the
	behavior of InOrder.

	While you want the validation method to throw an exception when the protocol is violated, you
	want your test method to pass only when the required exception is thrown. I think it works best
	to have the test fail while you are trying to get the validation to work and then add “expected”
	once the validator is working properly so that the test passes.

	You ABSOLUTELY CANNOT determine if the validator is working by calling only
	PortfolioManger.getMarketValue(). You must write tests that call the mock object directly so
	that you can call it wrongly. That is why we have two different test classes. One tests whether
	the protocol monitor is working correctly. The other (given a working session ), tests whether
	the PortfolioManger returns the correct value.
	*/
	@Override
	public boolean validate() 
	{
		//TODO: this method examines the Mockito history of the mockito object
		//to determine if the way it was called conforms to the protocol
		//described in the lab instructions.
		//this is where you put "verify() and InOrder()" stuff.

		//verify(mock);
		//TrackingMockSession track = new TrackingMockSession();
		//Mockito.doNothing().when(track).login("","");
		//Mockito.doNothing().when(track).login("","");
		//inOrder(mock);
		//InOrder(mock);

		return false;
	}
	

	private void configureMock() 
	{
		//TODO: this method configures the Mockito object so that it
		//expects login and getCurrentPrice calls and it knows how to 
		//return appropriate responses to both. 
		//this is where you put "when(...)" stuff.
		//when(mock.login()).thenReturn(true);

		//when(mock.getCurrentPrice()).thenAnswer(){

		//}


		// PortfolioBase base = new PortfolioBase();
		//List<Stock> stocks = base.getHoldings();
		//for(Stock stock: stocks){
		//	when(mock.getCurrentPrice(stock));
		//}

	}

}
