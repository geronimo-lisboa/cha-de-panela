package don.geronimo.chadepanela.model.convidado;

import don.geronimo.chadepanela.model.pessoa.Pessoa;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.Date;

public class Convidado extends Pessoa {
    private Date ultimoLogin;
    private boolean conviteEnviado;
    @Override
    public String getClaim() {
        return "CONVIDADO";
    }

    public Convidado( String nome, String email, String login, String senha) {
        super(null, nome, email, login, senha);
    }

    public Convidado(){}

    public Date getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(Date ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    public boolean getConviteEnviado() {
        return conviteEnviado;
    }

    public void setConviteEnviado(boolean conviteEnviado) {
        this.conviteEnviado = conviteEnviado;
    }
}
