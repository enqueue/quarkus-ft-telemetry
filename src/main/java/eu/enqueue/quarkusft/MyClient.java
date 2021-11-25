package eu.enqueue.quarkusft;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@ApplicationScoped
@RegisterRestClient
public interface MyClient {

    @GET
    @Path("/")
    String callDirectly();

    @Fallback(fallbackMethod = "fallback", applyOn = { WebApplicationException.class })
    @GET
    @Path("/")
    String callWithFallback();

    @Retry(maxRetries = 3)
    @GET
    @Path("/")
    String callWithRetry();

    default String fallback() {
        return "I fell back";
    }

}
