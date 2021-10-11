package kapitaz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FinalApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =SpringApplication.run(FinalApplication.class, args);


        Interaction interaction = context.getBean("interaction", Interaction.class);
        System.out.println("----------------------");
        System.out.println(interaction.addUser().getBody());
        System.out.println("--------------------");
        System.out.println(interaction.updateUser().getBody());
        System.out.println("-----------------------");
        System.out.println(interaction.deleteUser().getBody());
    }

}
