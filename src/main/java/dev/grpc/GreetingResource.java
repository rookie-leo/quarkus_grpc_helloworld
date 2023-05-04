package dev.grpc;

import io.quarkus.grpc.GrpcClient;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GrpcClient
    HelloGrpc client;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @GET
    @Path("/quarkus")
    @Blocking
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<String> hello(String name) {
        return client.sayHello(HelloRequest.newBuilder()
                    .setName(name)
                    .build()
        )
                .onItem()
                .transform(reply ->
                    reply.getMessage()
                );
    }
}
