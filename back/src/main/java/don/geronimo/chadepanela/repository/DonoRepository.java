package don.geronimo.chadepanela.repository;

import don.geronimo.chadepanela.model.dono.Dono;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DonoRepository extends MongoRepository<Dono, String> {
    public Dono findByLoginAndSenha(String login, String senha);
    public Dono findByEmail(String email);
}
