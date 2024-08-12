package com.turkcell.gyt.managementService.api.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import tr.com.turkcell.gyt.InvitationServiceGrpc;
import tr.com.turkcell.gyt.SendMailRequest;
import tr.com.turkcell.gyt.SentMailResponse;

@Service
public class InvitationClientService {
    @GrpcClient("invitationService")
    InvitationServiceGrpc.InvitationServiceBlockingStub blockingStub;

    ManagedChannel channel  = ManagedChannelBuilder.
            forAddress("localhost", 10080).
            usePlaintext().
            build();

    public SentMailResponse sendMail(SendMailRequest request){
        blockingStub = InvitationServiceGrpc.newBlockingStub(channel);
        return this.blockingStub.sendMail(request);
    }
}
