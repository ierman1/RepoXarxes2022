import java.io.Serializable;

/**
 * @author Marc Fernández Parra
 * @author Germán Pérez Bordera
 *
 * Classe que defineix el tipus d'acció que s'envia pel Socket.
 */
public class Command implements Serializable {

    /**
     * Tipus de d'acció a realitzar:
     *  - Enviar missatge
     *  - Finalitzar la connexió
     */
    private final CommandType type;

    // Missatge paràmetre que s'envia amb l'acció
    private final String message;

    public Command(CommandType type, String message) {
        this.type = type;
        this.message = message;
    }

    public CommandType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

}
