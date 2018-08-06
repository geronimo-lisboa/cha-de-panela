package don.geronimo.chadepanela.model.pessoa;

import java.util.Objects;

public class PessoaDTO {
    private String login;
    private String senha;

    @Override
    public String toString() {
        return "PessoaDTO{" +
                "login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PessoaDTO)) return false;
        PessoaDTO pessoaDTO = (PessoaDTO) o;
        return Objects.equals(login, pessoaDTO.login) &&
                Objects.equals(senha, pessoaDTO.senha);
    }

    @Override
    public int hashCode() {

        return Objects.hash(login, senha);
    }

    public String getLogin() {

        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public PessoaDTO() {

    }

    public PessoaDTO(String login, String senha) {

        this.login = login;
        this.senha = senha;
    }
}
