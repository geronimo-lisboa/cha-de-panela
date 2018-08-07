package don.geronimo.chadepanela;

import don.geronimo.chadepanela.services.PessoaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TesteEmail {
    @Autowired
    private PessoaService pessoaService;
    @Test
    public void helloEmail(){
    }
}
