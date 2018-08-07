package don.geronimo.chadepanela.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import don.geronimo.chadepanela.exceptions.UserNotFoundException;
import don.geronimo.chadepanela.model.convidado.Convidado;
import don.geronimo.chadepanela.model.convidado.ConvidadoDTO;
import don.geronimo.chadepanela.model.pessoa.Pessoa;
import don.geronimo.chadepanela.model.pessoa.PessoaAuthenticationData;
import don.geronimo.chadepanela.repository.ConvidadoRepository;
import don.geronimo.chadepanela.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


//TODO: Separar em DonoService, ConvidadoService e PessoaService;
@Service
public class PessoaService {
    private PessoaRepository pessoaRepository;
    private ConvidadoRepository convidadoRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository, ConvidadoRepository convidadoRepository){

        this.pessoaRepository = pessoaRepository;
        this.convidadoRepository = convidadoRepository;
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
        Pessoa pop = pessoaRepository.findById(id);
        if(pop!=null){
            return pop;
        }else{
            throw new UserNotFoundException();
        }

    }

    public List<Convidado> getAllConvidados() {
        List<Convidado> convidados = convidadoRepository.findAll();
        return convidados;
    }

    public Convidado addConvidado(Convidado c) {
        return convidadoRepository.save(c);
    }

    public Convidado createConvidadoFromDTO(ConvidadoDTO convidadoDTO) {
        Convidado convidado = new Convidado();
        convidado.setNome(convidadoDTO.getNome());
        convidado.setEmail(convidadoDTO.getEmail());
        convidado.setLogin(convidadoDTO.getLogin());
        convidado.setId(convidadoDTO.getId());
        return convidado;
    }

    public void deleteConvidado(String id) {
        Convidado c = convidadoRepository.findById(id).get();
        convidadoRepository.delete(c);
    }
}
