package don.geronimo.chadepanela;

import don.geronimo.chadepanela.model.dono.Dono;
import don.geronimo.chadepanela.repository.DonoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DonoGenerator {
    @Autowired
    private DonoRepository donoRepository;
    @Before
    public void generateDonos(){
        Dono testeErika = donoRepository.findByEmail("erikaeluciano2016@gmail.com");
        Dono testeLuciano = donoRepository.findByEmail("luciano.geronimo.fnord@gmail.com");
        if(testeErika==null && testeLuciano==null){
            //(String id, String nome, String email, String login, String senha)
            Dono erika = new Dono("Erika", "erikaeluciano2016@gmail.com", "erika", "abc,123");
            Dono luciano = new Dono("Luciano", "luciano.geronimo.fnord@gmail.com", "luciano", "abc,123");
            donoRepository.save(erika);
            donoRepository.save(luciano);
        }
    }

    @Test
    public void testeDonos() {
        Dono erika = donoRepository.findByLoginAndSenha("erika", "abc,123");
        assert (erika!=null);
        Dono luciano = donoRepository.findByLoginAndSenha("luciano", "abc,123");
        assert (luciano!=null);
    }
}
