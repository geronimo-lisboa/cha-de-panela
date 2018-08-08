package don.geronimo.chadepanela.repository;

import don.geronimo.chadepanela.model.presente.Presente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PresenteRepository extends MongoRepository<Presente, String> {
}
