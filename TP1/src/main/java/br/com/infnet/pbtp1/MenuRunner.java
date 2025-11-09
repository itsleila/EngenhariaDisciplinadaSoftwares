package br.com.infnet.pbtp1;

import br.com.infnet.pbtp1.repository.GatoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MenuRunner implements CommandLineRunner {

    private final GatoRepository gatoRepository;
    private final GatoService gatoService;

    public MenuRunner(GatoRepository gatoRepository, GatoService gatoService) {
        this.gatoRepository = gatoRepository;
        this.gatoService = gatoService;
    }

    @Override
    public void run(String... args) throws Exception {
        Menu menu = new Menu(gatoService);
        menu.entrarOpcoes();
    }

    public static void main(String[] args) {
        //System.setProperty("spring.profiles.active", "h2");
        System.setProperty("spring.profiles.active", "mysql");
        SpringApplication.run(MenuRunner.class, args);
    }

}
