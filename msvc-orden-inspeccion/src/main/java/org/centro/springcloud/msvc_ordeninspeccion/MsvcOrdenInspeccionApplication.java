package org.centro.springcloud.msvc_ordeninspeccion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcOrdenInspeccionApplication {

	public static void main(String[] args) {
		SpringApplication.run(
				MsvcOrdenInspeccionApplication.class,
				args
		);
	}
}