package proj.ssm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan(basePackages = {"proj.ssm.mapper"})
//@MapperScan("proj.ssm.mapper")
@SpringBootApplication
public class SsmApplication {
    public static void main(String[] args){
        SpringApplication.run(SsmApplication.class,args);
    }

}
