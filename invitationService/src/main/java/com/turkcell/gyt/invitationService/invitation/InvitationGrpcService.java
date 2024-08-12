package com.turkcell.gyt.invitationService.invitation;


import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import tr.com.turkcell.gyt.InvitationServiceGrpc;
import tr.com.turkcell.gyt.SendMailRequest;
import tr.com.turkcell.gyt.SentMailResponse;

@GrpcService
@RequiredArgsConstructor
public class InvitationGrpcService extends InvitationServiceGrpc.InvitationServiceImplBase {


    @Override
    public void sendMail(SendMailRequest request, StreamObserver<SentMailResponse> responseObserver) {


        final String invitedMessage = "Invite email sent to : " + request.getEmail();


        final var response = SentMailResponse.newBuilder().setMessage(invitedMessage).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}



