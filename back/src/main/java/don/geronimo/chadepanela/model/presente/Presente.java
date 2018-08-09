package don.geronimo.chadepanela.model.presente;

import java.util.Objects;

public class Presente {
    private String id;
    private String nomeDoPresente;
    private String imageAsBase64;
    private boolean escolhido;
    private String idConvidado;

    public String getImageAsBase64() {
        return imageAsBase64;
    }

    public void setImageAsBase64(String imageAsBase64) {
        this.imageAsBase64 = imageAsBase64;
    }

    public String getIdConvidado() {
        return idConvidado;
    }

    public void setIdConvidado(String idConvidado) {
        this.idConvidado = idConvidado;
    }

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

    public Presente(String nomeDoPresente) {

        this.nomeDoPresente = nomeDoPresente;
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

    public boolean getEscolhido() {
        return escolhido;
    }

    public void setEscolhido(boolean escolhido) {
        this.escolhido = escolhido;
    }
}
