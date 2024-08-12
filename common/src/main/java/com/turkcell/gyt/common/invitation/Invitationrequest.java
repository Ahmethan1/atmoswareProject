package com.turkcell.gyt.common.invitation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Invitationrequest {
    private String email;
    private String url;
    private String description;
}
