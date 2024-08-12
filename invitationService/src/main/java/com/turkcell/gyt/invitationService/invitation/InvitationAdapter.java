package com.turkcell.gyt.invitationService.invitation;


import tr.com.turkcell.gyt.SendMailRequest;
import tr.com.turkcell.gyt.SentMailResponse;

public interface InvitationAdapter {

    SentMailResponse getFromClient(SendMailRequest request);
}
