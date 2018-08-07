package don.geronimo.chadepanela.model.convidado;

public class ConvidadoDTO {
    private String id;
    private String nome;
    private String email;
    private String login;

    public ConvidadoDTO() {
    }

    public ConvidadoDTO(String nome, String email, String login) {

        this.nome = nome;
        this.email = email;
        this.login = login;
    }

    public ConvidadoDTO(String id, String nome, String email, String login) {

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.login = login;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
