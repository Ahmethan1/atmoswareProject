package com.turkcell.gyt.invitationService.invitation;

import net.devh.boot.grpc.client.inject.GrpcClient;
import tr.com.turkcell.gyt.InvitationServiceGrpc;
import tr.com.turkcell.gyt.SendMailRequest;
import tr.com.turkcell.gyt.SentMailResponse;

public class InvitationClient implements InvitationAdapter{
    @GrpcClient("managementService")
    InvitationServiceGrpc.InvitationServiceBlockingStub invitationServiceBlockingStub;

    public SentMailResponse getFromClient(SendMailRequest request) {
        return this.invitationServiceBlockingStub.sendMail(request);
    }
}
