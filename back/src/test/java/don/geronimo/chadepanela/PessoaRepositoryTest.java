package don.geronimo.chadepanela;
import java.util.logging.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import don.geronimo.chadepanela.model.dono.Dono;
import don.geronimo.chadepanela.model.pessoa.Pessoa;
import don.geronimo.chadepanela.model.pessoa.PessoaDTO;
import don.geronimo.chadepanela.repository.PessoaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PessoaRepositoryTest {

    private final static Logger LOGGER = Logger.getLogger(PessoaRepositoryTest.class.getName());

    @Autowired
    private PessoaRepository pessoaRepository;
    @Test
    public void testeFindByLoginAndSenha() {
        Pessoa p1 = pessoaRepository.findByLoginAndSenha("erika", "abc,123");
        Pessoa p2 = pessoaRepository.findByLoginAndSenha("luciano", "abc,123");
        assert(p1.getClass().equals(Dono.class));
    }

    @Test
    public void testeToken() throws JsonProcessingException {
        PessoaDTO dto = new PessoaDTO("erika","abc,123");
        String url = "http://localhost:8080/login/";
        RestTemplate restTemplate = new RestTemplate();
        Map<String,String> result = restTemplate.postForObject(url, dto, HashMap.class);
        assert ( result.containsKey("token")==true);
        LOGGER.info("TOKEN GERADO = "+result.get("token"));
    }
}
