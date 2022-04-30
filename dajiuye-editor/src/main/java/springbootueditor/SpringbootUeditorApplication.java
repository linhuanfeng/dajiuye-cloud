package springbootueditor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "springbootueditor.dao")
public class SpringbootUeditorApplication {
//
    public static void main(String[] args) {
        SpringApplication.run(SpringbootUeditorApplication.class, args);
    }

}
