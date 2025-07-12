package br.com.ifba;

import br.com.ifba.curso.controller.CursoIController;
import br.com.ifba.curso.view.CursoListar; // Sua tela principal
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication 
public class IfbaJeovaniApplication {

	public static void main(String[] args) {
                ConfigurableApplicationContext context = new SpringApplicationBuilder(IfbaJeovaniApplication.class)
                .headless(false).run(args);

            java.awt.EventQueue.invokeLater(() -> {
            
            CursoIController cursoController = context.getBean(CursoIController.class);
            new CursoListar(cursoController).setVisible(true); 
        });
}

}
