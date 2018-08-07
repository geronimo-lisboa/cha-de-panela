package don.geronimo.chadepanela.model.convidado;

import don.geronimo.chadepanela.model.pessoa.Pessoa;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Pessoa")
public class Convidado extends Pessoa {
    @Override
    public String getClaim() {
        return "CONVIDADO";
    }

    public Convidado( String nome, String email, String login, String senha) {
        super(null, nome, email, login, senha);
    }

    public Convidado(){}
}
