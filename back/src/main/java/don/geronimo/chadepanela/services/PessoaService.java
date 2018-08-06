package don.geronimo.chadepanela.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import don.geronimo.chadepanela.exceptions.UserNotFoundException;
import don.geronimo.chadepanela.model.pessoa.Pessoa;
import don.geronimo.chadepanela.model.pessoa.PessoaAuthenticationData;
import don.geronimo.chadepanela.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Optional;

@Service
public class PessoaService {
    private PessoaRepository pessoaRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository){
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa getPessoaByLoginAndSenha(String login, String senha) throws UserNotFoundException {
        Pessoa p = pessoaRepository.findByLoginAndSenha(login, senha);
        if(p==null){
            throw new UserNotFoundException();
        }
        return p;
    }

    public String convertPessoaToAuthentiticationData(Pessoa p) throws JsonProcessingException {
        PessoaAuthenticationData pd = new PessoaAuthenticationData(p);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(pd);
    }

    public PessoaAuthenticationData createAuthenticationDataFromJson(String jsonAuthenticationData) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonAuthenticationData, PessoaAuthenticationData.class);
    }

    public Pessoa getPessoaById(String id) throws UserNotFoundException {
        Optional<Pessoa> pop = pessoaRepository.findById(id);
        if(pop.isPresent()){
            return pop.get();
        }
        else{
            throw new UserNotFoundException();
        }

    }
}
