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
        Dono testeErika = (Dono) donoRepository.findByEmail("erikaeluciano2016@gmail.com");
        Dono testeLuciano = (Dono)donoRepository.findByEmail("luciano.geronimo.fnord@gmail.com");
        if(testeErika==null && testeLuciano==null){
            //(String id, String nome, String email, String login, String senha)
            Dono erika = new Dono("Erika", "erikaeluciano2016@gmail.com", "erika", "abc,123");
            erika.setTeste("aaaa");
            Dono luciano = new Dono("Luciano", "luciano.geronimo.fnord@gmail.com", "luciano", "abc,123");
            erika.setTeste("bbb");
            donoRepository.save(erika);
            donoRepository.save(luciano);
        }
    }

    @Test
    public void testeExistenciaDeDonos(){
        Dono d1  = donoRepository.findByEmail("erikaeluciano2016@gmail.com");
        Dono d2  = donoRepository.findByEmail("luciano.geronimo.fnord@gmail.com");
        assert(d1!=null);
        assert(d2!=null);

    }

}
