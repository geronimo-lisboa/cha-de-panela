package don.geronimo.chadepanela.model.presente;

import java.util.Objects;

public class Presente {
    private String id;
    private String nomeDoPresente;
    private String idConvidado;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Presente)) return false;
        Presente presente = (Presente) o;
        return Objects.equals(id, presente.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    public Presente() {

    }

    public Presente(String nomeDoPresente, String idConvidado) {

        this.nomeDoPresente = nomeDoPresente;
        this.idConvidado = idConvidado;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeDoPresente() {
        return nomeDoPresente;
    }

    public void setNomeDoPresente(String nomeDoPresente) {
        this.nomeDoPresente = nomeDoPresente;
    }

    public String getIdConvidado() {
        return idConvidado;
    }

    public void setIdConvidado(String idConvidado) {
        this.idConvidado = idConvidado;
    }
}
