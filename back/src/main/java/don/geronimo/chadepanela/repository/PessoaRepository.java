package don.geronimo.chadepanela.repository;

import don.geronimo.chadepanela.model.convidado.Convidado;
import don.geronimo.chadepanela.model.dono.Dono;
import don.geronimo.chadepanela.model.pessoa.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaRepository {
    @Autowired
    private ConvidadoRepository convidadoRepository;
    @Autowired
    private DonoRepository donoRepository;
    public Pessoa findByLoginAndSenha(String login, String senha){
        Dono d = donoRepository.findByLoginAndSenha(login, senha);
        if(d!=null){
            return d;
        }
        Convidado c = convidadoRepository.findByLoginAndSenha(login, senha);
        if(c!=null){
            return c;
        }
        return null;
    }

    public Pessoa findByEmail(String email){
        Dono d = donoRepository.findByEmail(email);
        if(d!=null){
            return d;
        }
        Convidado c = convidadoRepository.findByEmail(email);
        if(c!=null){
            return c;
        }
        return null;
    }

    public Pessoa findById(String id) {
        Optional<Dono> d = donoRepository.findById(id);
        if(d.isPresent()){
            return d.get();
        }
        Optional<Convidado> c = convidadoRepository.findById(id);
        if(c.isPresent()){
            return c.get();
        }
        return null;
    }
}
