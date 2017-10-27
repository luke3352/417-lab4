package edu.iastate.cs417.lab4;

import org.junit.Test;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.exceptions.verification.VerificationInOrderFailure;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.verification.VerificationMode;

/**
 * Created by Luke Sternhagen on 10/26/2017.
 */
public class ExploreMockito {

    public static interface Mintf{
        public boolean init();
        public boolean work();
    }

    @Test
    public void test(){
        Mintf mMock = mock(ExploreMockito.Mintf.class);
        when(mMock.init()).thenReturn(true);

        mMock.work();
        mMock.init();
        mMock.work();
        mMock.work();
        verify(mMock,times(1)).init();
        verify(mMock,atMost(3)).work();

        InOrder right = inOrder(mMock);
        right.verify(mMock).init();
        right.verify(mMock,atLeast(2)).work();
        //right.verify(mMock,atLeast(3)).work();
        try {
            InOrder wrong = inOrder(mMock);
            wrong.verify(mMock, times(1)).work();
            wrong.verify(mMock).init();
            fail("Work before init");
        }
        catch(Exception e){

        }
    }
}
