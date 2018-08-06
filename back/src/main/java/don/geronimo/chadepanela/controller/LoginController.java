package don.geronimo.chadepanela.controller;

import don.geronimo.chadepanela.model.pessoa.PessoaDTO;
import don.geronimo.chadepanela.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Value("jwtSecret")
    private String jwtSecret;
    private PessoaService pessoaService;
    @Autowired
    public LoginController(PessoaService pessoaService){
        this.pessoaService = pessoaService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> getToker(@RequestBody PessoaDTO pdto){

    }
}
