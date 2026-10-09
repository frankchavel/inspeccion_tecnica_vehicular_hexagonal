package org.centro.springcloud.msvc.msvc_inspeccion_tecnica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcInspeccionTecnicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcInspeccionTecnicaApplication.class, args);
	}

}
