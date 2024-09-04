package com.prod.pms;

import com.prod.pms.constants.MessageConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static com.prod.pms.constants.MessageConstants.*;


@EnableJpaAuditing
@SpringBootApplication
@Slf4j
public class PmsApplication {

	public static void main(String[] args) {

		SpringApplication.run(PmsApplication.class, args);

	}

}
