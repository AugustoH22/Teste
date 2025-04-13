package com.adesp.festival.authentication.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.authentication.domain.entities.Invite;
import com.adesp.festival.authentication.domain.exceptions.NotFoundInviteException;
import com.adesp.festival.authentication.domain.repositories.InviteRepository;

@UseCase
public class FindInviteByDestinationUseCase {

    private final InviteRepository inviteRepository;

    public FindInviteByDestinationUseCase(InviteRepository inviteRepository) {
        this.inviteRepository = inviteRepository;
    }

    public Invite execute(String inviteToken){
        return this.inviteRepository.findActiveInviteToken(inviteToken)
                .orElseThrow(() -> new NotFoundInviteException());
    }
}
