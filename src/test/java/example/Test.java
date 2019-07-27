package example;

import org.mockito.internal.util.MockUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.testng.Assert.*;

@SpringBootTest
@TestExecutionListeners(MockitoTestExecutionListener.class)
public class Test extends AbstractTestNGSpringContextTests {
    private static final Logger log = LoggerFactory.getLogger( Test.class );
    @SpyBean Service service;

    @org.testng.annotations.Test
    public void test() {
        assertTrue( MockUtil.isSpy( service ) );
        doReturn( new CompletableFuture<>() ).when( service ).asyncMethod( any( Integer.class ) );
        doReturn( null ).when( service ).work(); //commenting this line causes test to pass (3 of 3)
        CompletableFuture<Integer> future = service.asyncMethod( 1 );
        log.info( "Success" );
    }
}
