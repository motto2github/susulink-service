package ink.qwer.susulinkservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("ink.qwer.susulinkservice.mapper")
public class SusulinkServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SusulinkServiceApplication.class, args);
    }

}
