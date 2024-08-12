package com.turkcell.gyt.managementService.business.abstracts;

import io.grpc.stub.StreamObserver;
import tr.com.turkcell.gyt.SendMailRequest;
import tr.com.turkcell.gyt.SentMailResponse;

public interface InvitationGrpcService {
    void sendMail(SendMailRequest request, StreamObserver<SentMailResponse> responseObserver);
}
