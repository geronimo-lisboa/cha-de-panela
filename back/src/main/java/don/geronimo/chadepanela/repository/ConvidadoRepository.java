package don.geronimo.chadepanela.repository;

import don.geronimo.chadepanela.model.convidado.Convidado;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConvidadoRepository extends MongoRepository<Convidado, String> {
}
