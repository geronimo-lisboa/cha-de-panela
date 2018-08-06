package don.geronimo.chadepanela.model.convidado;

import don.geronimo.chadepanela.model.pessoa.Pessoa;

public class Convidado extends Pessoa {
    @Override
    public String getClaim() {
        return "CONVIDADO";
    }
}
