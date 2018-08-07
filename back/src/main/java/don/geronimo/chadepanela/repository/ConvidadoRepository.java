package don.geronimo.chadepanela.repository;

import don.geronimo.chadepanela.model.convidado.Convidado;
import don.geronimo.chadepanela.model.dono.Dono;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ConvidadoRepository extends MongoRepository<Convidado, String> {
   // public List<Convidado> findAll();

    Convidado findByLoginAndSenha(String login, String senha);

    Convidado findByEmail(String email);
}
