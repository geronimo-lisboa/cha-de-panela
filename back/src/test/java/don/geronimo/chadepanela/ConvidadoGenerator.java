package don.geronimo.chadepanela;

import don.geronimo.chadepanela.exceptions.UserNotFoundException;
import don.geronimo.chadepanela.model.convidado.Convidado;
import don.geronimo.chadepanela.model.pessoa.Pessoa;
import don.geronimo.chadepanela.services.PessoaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConvidadoGenerator {
    @Autowired
    private PessoaService pessoaService;
    @Before
    public void criarPrimeiroConvidado(){
        if(pessoaService.getAllConvidados().size()==0){
            Convidado c = new Convidado("Charles Bronson", "charles@fake.com", "CB", "aiaiai");
            c = pessoaService.addConvidado(c);
        }
    }
    @Test
    public void getConvidadoByLogin() throws UserNotFoundException {
        Pessoa p = pessoaService.getPessoaByLoginAndSenha("CB", "aiaiai");
        assert(p!=null && p.getClass().equals(Convidado.class));
    }
}
