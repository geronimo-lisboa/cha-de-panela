package don.geronimo.chadepanela;

import don.geronimo.chadepanela.model.dono.Dono;
import don.geronimo.chadepanela.model.pessoa.Pessoa;
import don.geronimo.chadepanela.repository.PessoaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
