package com.turkcell.gyt.managementService;


import com.atmosware.core.annotation.EnableSecurity;
import com.turkcell.gyt.grpc_core.GrpcCoreApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableSecurity
@Import(GrpcCoreApplication.class)
public class ManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagementServiceApplication.class, args);
    }


}
