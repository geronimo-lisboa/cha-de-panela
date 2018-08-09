package don.geronimo.chadepanela.model.presente;

import java.util.Objects;

public class PresenteConvidado {
    private String id;
    private String idConvidado;
    private String idPresente;

    public PresenteConvidado() {
    }

    public PresenteConvidado(String idConvidado, String idPresente) {

        this.idConvidado = idConvidado;
        this.idPresente = idPresente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PresenteConvidado)) return false;
        PresenteConvidado that = (PresenteConvidado) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(idConvidado, that.idConvidado) &&
                Objects.equals(idPresente, that.idPresente);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, idConvidado, idPresente);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdConvidado() {
        return idConvidado;
    }

    public void setIdConvidado(String idConvidado) {
        this.idConvidado = idConvidado;
    }

    public String getIdPresente() {
        return idPresente;
    }

    public void setIdPresente(String idPresente) {
        this.idPresente = idPresente;
    }
}
