package br.com.ifba.pgr03springjeovanigama;

import br.com.ifba.curso.view.CursoListar; // Sua tela principal
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication 
public class IfbaJeovaniApplication {

	public static void main(String[] args) {
		// Inicializa o Spring em modo "não-headless" para aceitar telas Swing
        ConfigurableApplicationContext context = new SpringApplicationBuilder(MinhaAplicacao.class)
                .headless(false).run(args); // Conforme explicado no PDF [cite: 163, 164]

        java.awt.EventQueue.invokeLater(() -> {
            
            CursoIController cursoController = context.getBean(CursoIController.class);
            new CursoListar(cursoController).setVisible(true);
        });
}

}
