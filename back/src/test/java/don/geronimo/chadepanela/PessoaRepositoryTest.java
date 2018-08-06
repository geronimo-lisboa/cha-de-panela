package don.geronimo.chadepanela;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class PessoaRepositoryTest {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Test
    public void testeFindByLoginAndSenha() {
        Pessoa p1 = pessoaRepository.findByLoginAndSenha("erika", "abc,123");
        Pessoa p2 = pessoaRepository.findByLoginAndSenha("luciano", "abc,123");
        assert(p1.getClass().equals(Dono.class));
    }

//    @Test
//    public void testeToken() throws JsonProcessingException {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        PessoaDTO dto = new PessoaDTO("erika","abc,123");
//        ObjectMapper mapper = new ObjectMapper();
//        String myJson = mapper.writeValueAsString(dto);
//
//
//
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> response = restTemplate.postForEntity("url","myJson",String.class); //( "http://localhost:8080/login", request , String.class );
//        System.out.println("aaaa");
//    }
}
