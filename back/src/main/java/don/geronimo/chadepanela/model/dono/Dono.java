package don.geronimo.chadepanela.model.dono;

import don.geronimo.chadepanela.model.pessoa.Pessoa;

/**
 * Dono é o dono da lista do chá de panela, quem pode mexer nela,
 * no caso sou eu e a erika*/
public class Dono extends Pessoa {
    @Override
    public String getClaim() {
        return "DONO";
    }

    public Dono(String id, String nome, String email, String login, String senha) {
        super(id, nome, email, login, senha);
    }

    public Dono(String nome, String email, String login, String senha) {
        super(null, nome, email, login, senha);
    }

    public Dono() {
    }
}
