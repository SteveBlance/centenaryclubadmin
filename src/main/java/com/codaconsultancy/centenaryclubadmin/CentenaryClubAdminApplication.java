package com.codaconsultancy.centenaryclubadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan({"com.codaconsultancy"})
@EntityScan("com.codaconsultancy.centenaryclubadmin.domain")
@EnableJpaRepositories("com.codaconsultancy.centenaryclubadmin.repositories")
@EnableScheduling
public class CentenaryClubAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentenaryClubAdminApplication.class, args);
	}

}
