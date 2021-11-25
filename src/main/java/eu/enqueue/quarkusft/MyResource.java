package eu.enqueue.quarkusft;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/")
public class MyResource {

    @RestClient
    MyClient myClient;

    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @GET
    @Path("direct")
    @Produces(MediaType.TEXT_PLAIN)
    public String callDirectly() {
        return myClient.callDirectly();
    }

    @GET
    @Path("fallback")
    @Produces(MediaType.TEXT_PLAIN)
    public String callWithFallback() {
        return myClient.callWithFallback();
    }

    @GET
    @Path("retry")
    @Produces(MediaType.TEXT_PLAIN)
    public String callWithRetry() {
        return myClient.callWithRetry();
    }

}
