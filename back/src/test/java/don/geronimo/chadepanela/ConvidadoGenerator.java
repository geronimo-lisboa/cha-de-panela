package don.geronimo.chadepanela;

import don.geronimo.chadepanela.exceptions.UserNotFoundException;
import don.geronimo.chadepanela.model.convidado.Convidado;
import don.geronimo.chadepanela.model.pessoa.Pessoa;
import don.geronimo.chadepanela.repository.ConvidadoRepository;
import don.geronimo.chadepanela.services.PessoaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConvidadoGenerator {
    @Autowired
    private PessoaService pessoaService;
    @Before
    public void criarPrimeiroConvidado() throws ParseException {
        if(pessoaService.getAllConvidados().size()==0){
            Convidado c = new Convidado("Charles Bronson", "charles@fake.com", "CB", "aiaiai");
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = dateFormat.parse("23/09/2007");
            long time = date.getTime();
            c.setUltimoLogin(new Timestamp(time));
            c = pessoaService.addConvidado(c);
        }
    }
    @Test
    public void getConvidadoByLogin() throws UserNotFoundException {
        Pessoa p = pessoaService.getPessoaByLoginAndSenha("CB", "aiaiai");
        assert(p!=null && p.getClass().equals(Convidado.class));
    }
}
