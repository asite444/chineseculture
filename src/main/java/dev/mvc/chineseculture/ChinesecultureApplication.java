package dev.mvc.chineseculture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "dev.mvc" })
public class ChinesecultureApplication {

  public static void main(String[] args) {
    SpringApplication.run(ChinesecultureApplication.class, args);
  }

}
