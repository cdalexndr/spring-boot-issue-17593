package example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

@org.springframework.stereotype.Service
public class Service {
    private static final Logger log = LoggerFactory.getLogger( Service.class );

    @Async //commenting this line causes test to pass (2 of 3)
    public CompletableFuture<Integer> asyncMethod( Integer param ) {
        work();
        return new CompletableFuture<>();
    }

    protected Integer work() {
        log.info( "Work" );
        return 0;
    }
}
