package fi.academy;

import fi.academy.dao.Color;
import fi.academy.dao.ColorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class JavaS20FshPohjaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaS20FshPohjaApplication.class, args);
    }


    @Bean
    public CommandLineRunner dbSampleCode(@Autowired ColorDAO dao) {
        return args -> {
            List<Color> colors = dao.fetchAllColors();
            System.out.println(colors);
            Optional<Color> c = dao.findByName("blue");
            if (c.isPresent())
                System.out.println(c.get());
            else
                System.out.println("Color \"blue\" not found");
            c = dao.findByName("sininen");
            if (c.isPresent())
                System.out.println(c.get());
            else
                System.out.println("Color \"sininen\" not found");
            Color uusi = new Color("puppuväri", "#100880", new int[]{16, 8, 128});
            System.out.printf("Added a new color: %s\n", uusi = dao.addColor(uusi));
            System.out.printf("find w/ name: %s\n", dao.findByName("puppuväri").get());
            System.out.printf("find w/ id: %s\n", dao.findById(uusi.getId()).get());
            System.out.println("Deleting the color..");
            dao.deleteColor(uusi.getId());
            c = dao.findById(uusi.getId());
            if (c.isPresent()) {
                System.out.println("Deleting failed!!!!!");
            } else {
                System.out.printf("Deleted %s\n", uusi);
                System.out.println(dao.deleteColor(uusi.getId()) ? "Poistui vaikka jo poistettu????" : "ei poistanut jo poistettua");
            }
        };
    }
}

