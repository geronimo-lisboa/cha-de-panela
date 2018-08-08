package don.geronimo.chadepanela.services;

import don.geronimo.chadepanela.model.presente.Presente;
import don.geronimo.chadepanela.repository.PresenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class PresenteService {
    private PresenteRepository presenteRepository;
    @Autowired
    public PresenteService(PresenteRepository presenteRepository){
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
}
