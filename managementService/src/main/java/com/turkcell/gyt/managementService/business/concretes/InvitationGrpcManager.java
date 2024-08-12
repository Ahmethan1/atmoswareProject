package com.turkcell.gyt.managementService.business.concretes;

import com.turkcell.gyt.managementService.api.grpc.InvitationClientService;
import com.turkcell.gyt.managementService.business.abstracts.InvitationGrpcService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import tr.com.turkcell.gyt.InvitationServiceGrpc;
import tr.com.turkcell.gyt.SendMailRequest;
import tr.com.turkcell.gyt.SentMailResponse;

@GrpcService
@RequiredArgsConstructor
public class InvitationGrpcManager extends InvitationServiceGrpc.InvitationServiceImplBase implements InvitationGrpcService {

    private final InvitationClientService invitationClientService;

    @Override
    public void sendMail(SendMailRequest request, StreamObserver<SentMailResponse> responseObserver) {
        SentMailResponse response = this.invitationClientService.sendMail(request);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
