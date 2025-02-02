package com.turkcell.gyt.invitationService;

import com.turkcell.gyt.grpc_core.GrpcCoreApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(GrpcCoreApplication.class)
public class InvitationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvitationServiceApplication.class, args);
	}

}
