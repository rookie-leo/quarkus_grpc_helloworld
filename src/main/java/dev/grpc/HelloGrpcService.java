package dev.grpc;

import dev.grpc.MutinyHelloGrpcGrpc.HelloGrpcImplBase;
import io.quarkus.grpc.GrpcService;

import io.smallrye.mutiny.Uni;

@GrpcService
public class HelloGrpcService extends HelloGrpcImplBase {

    @Override
    public Uni<HelloReply> sayHello(HelloRequest request) {
        return Uni.createFrom().item("Hello " + request.getName() + "!")
                .map(msg -> HelloReply.newBuilder().setMessage(msg).build());
    }

}
