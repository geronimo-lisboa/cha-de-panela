package don.geronimo.chadepanela.repository;

import don.geronimo.chadepanela.model.presente.Presente;
import don.geronimo.chadepanela.model.presente.PresenteConvidado;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PresenteConvidadoRepository extends MongoRepository<PresenteConvidado, String> {
    PresenteConvidado findByIdPresenteAndIdConvidado(String idPresente, String idConvidado);
    PresenteConvidado findByIdPresente(String idPresente);
}
