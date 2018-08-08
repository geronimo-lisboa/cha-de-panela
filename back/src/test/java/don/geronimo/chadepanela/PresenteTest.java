package don.geronimo.chadepanela;

import don.geronimo.chadepanela.model.presente.Presente;
import don.geronimo.chadepanela.services.PresenteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PresenteTest {
    @Autowired
    private PresenteService presenteService;

    @Before
    public void setup(){
        if(presenteService.findAllPresentes().size()==0){
            presenteService.addPresente(new Presente("foobar", null));
        }
    }
    @Test
    public void getAllPresentes(){
        List<Presente> presenteList = presenteService.findAllPresentes();
        assert (presenteList.size()>0);
    }
}
