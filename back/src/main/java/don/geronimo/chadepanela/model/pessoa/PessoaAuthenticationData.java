package don.geronimo.chadepanela.model.pessoa;

public class PessoaAuthenticationData {
    @Override
    public String toString() {
        return "PessoaAuthenticationData{" +
                "id='" + id + '\'' +
                ", claim='" + claim + '\'' +
                '}';
    }

    public PessoaAuthenticationData(Pessoa p){
        this.id = p.getId();
        this.claim = p.getClaim();
    }

    public PessoaAuthenticationData() {
    }

    private String id;
    private String claim;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClaim() {
        return claim;
    }

    public void setClaim(String claim) {
        this.claim = claim;
    }
}
