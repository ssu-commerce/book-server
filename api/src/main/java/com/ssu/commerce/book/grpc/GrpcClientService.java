package com.ssu.commerce.book.grpc;

import com.ssu.commerce.grpc.HelloReply;
import com.ssu.commerce.grpc.HelloRequest;
import com.ssu.commerce.grpc.SimpleGrpc;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GrpcClientService {

    @GrpcClient("test")
    private SimpleGrpc.SimpleBlockingStub simpleStub;

    public String sendMessage(final String name) {
        try {
            HelloReply response = this.simpleStub.sayHello(HelloRequest.newBuilder().setName(name).build());
            return response.getMessage();
        } catch (StatusRuntimeException e) {
            log.info(String.valueOf(e));
            return "FAILED with " + e.getStatus().getCode().name();
        }
    }
}