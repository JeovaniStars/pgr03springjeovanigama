package br.com.ifba.infrastructure.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    // A EntityManagerFactory é um objeto pesado e caro.
    // Devemos criar apenas UMA instância para toda a aplicação.
    private static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("meuPU"); // O nome "meuPU" deve ser o mesmo do seu persistence.xml

    /**
     * Retorna uma instância de EntityManager.
     * Lembre-se que cada EntityManager é de curta duração e deve ser fechado
     * na camada de serviço após o uso.
     * @return EntityManager
     */
    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }

    /**
     * Fecha a factory de EntityManagers.
     * Este método deve ser chamado apenas uma vez, quando a aplicação for encerrada.
     */
    public static void shutdown() {
        if (FACTORY != null && FACTORY.isOpen()) {
            FACTORY.close();
        }
    }
}