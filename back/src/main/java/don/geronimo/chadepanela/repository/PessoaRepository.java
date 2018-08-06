package don.geronimo.chadepanela.repository;

import don.geronimo.chadepanela.model.pessoa.Pessoa;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PessoaRepository extends MongoRepository<Pessoa, String> {
    public Pessoa findByLoginAndSenha(String login, String senha);
    public Pessoa findByEmail(String email);
}
