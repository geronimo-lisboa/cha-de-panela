package don.geronimo.chadepanela.services;

import don.geronimo.chadepanela.exceptions.PresenteJaEscolhidoException;
import don.geronimo.chadepanela.model.presente.Presente;
import don.geronimo.chadepanela.model.presente.PresenteConvidado;
import don.geronimo.chadepanela.repository.PresenteConvidadoRepository;
import don.geronimo.chadepanela.repository.PresenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class PresenteService {
    private PresenteRepository presenteRepository;
    private PresenteConvidadoRepository presenteConvidadoRepository;
    @Autowired
    public PresenteService(PresenteRepository presenteRepository, PresenteConvidadoRepository presenteConvidadoRepository){
        this.presenteConvidadoRepository = presenteConvidadoRepository;
        this.presenteRepository = presenteRepository;
    }

    public Presente addPresente(Presente newPresente, byte[] imageData){

        String encoded = Base64.getEncoder().encodeToString(imageData);
        newPresente.setImageAsBase64(encoded);
        return presenteRepository.save(newPresente);
    }

    public List<Presente> findAllPresentes(){
        return presenteRepository.findAll();
    }

    public Presente findById(String id) {
        return presenteRepository.findById(id).get();
    }

    public List<Presente> deletePresenteAndGetUpdatedList(String id){
        Presente p = findById(id);
        presenteRepository.delete(p);
        return findAllPresentes();
    }
    //1)Criar o objeto de ligação convidado-presente
    //2)Marcar o presente como escolhido
    public void atribuirAConvidado(String idConvidado, String idPresente) throws PresenteJaEscolhidoException {
        //antes de sair botando, verificar se já não tem um Presente-Convidado pra esse presente
        PresenteConvidado testeJaEscolhido = presenteConvidadoRepository.findByIdPresente(idPresente);
        if(testeJaEscolhido!=null){
            throw new PresenteJaEscolhidoException();
        }


        PresenteConvidado presenteConvidado = new PresenteConvidado(idConvidado, idPresente);
        presenteConvidadoRepository.save(presenteConvidado);
        Presente presente = findById(idPresente);
        presente.setIdConvidado(idConvidado);
        presente.setEscolhido(true);
        presenteRepository.save(presente);
    }

    public void desligarPresenteDeConvidado(String idConvidado, String idPresente) {
        PresenteConvidado presenteConvidado = presenteConvidadoRepository.findByIdPresenteAndIdConvidado(idPresente, idConvidado);
        presenteConvidadoRepository.delete(presenteConvidado);
        Presente presente = findById(idPresente);
        presente.setEscolhido(false);
        presente.setIdConvidado(null);
        presenteRepository.save(presente);
    }
}
