package don.geronimo.chadepanela.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import don.geronimo.chadepanela.exceptions.UserNotFoundException;
import don.geronimo.chadepanela.model.pessoa.Pessoa;
import don.geronimo.chadepanela.model.pessoa.PessoaAuthenticationData;
import don.geronimo.chadepanela.model.pessoa.PessoaDTO;
import don.geronimo.chadepanela.services.PessoaService;
import don.geronimo.chadepanela.utils.MensagemErro;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Value("${jwtSecret}")
    private String jwtSecret;
    @Value("${claimFieldName}")
    private String claimFieldName;
    private PessoaService pessoaService;
    @Autowired
    public LoginController(PessoaService pessoaService){
        this.pessoaService = pessoaService;
    }

    @GetMapping("/secure/teste")
    public ResponseEntity<?> testeJWT(final HttpServletRequest request) throws UserNotFoundException {
        PessoaAuthenticationData pessoaAuthenticationData = (PessoaAuthenticationData) request.getAttribute("pessoaAuthenticationData");
        Pessoa p =pessoaService.getPessoaById(pessoaAuthenticationData.getId());
        return ResponseEntity.ok( p );
    }

    @PostMapping("/login")
    public ResponseEntity<?> getToker(@RequestBody PessoaDTO pdto) throws UserNotFoundException, JsonProcessingException {
        //tenta pegar a pessoa dada. Se não encontrar vai dar exceção UserNotFoundException
        Pessoa pessoa = pessoaService.getPessoaByLoginAndSenha(pdto.getLogin(), pdto.getSenha());
        //Encontrou a pessoa, vê se é convidado ou se é dono e seta os claims de acordo.
        String authenticationData = pessoaService.convertPessoaToAuthentiticationData(pessoa);
        String token =
                Jwts.builder()
                        .setSubject("")
                        .claim(claimFieldName, authenticationData)
                        .setIssuedAt(new Date())
                        .signWith(SignatureAlgorithm.HS256, jwtSecret)
                        .compact();

        Map<String,Object> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> pessoaNaoEncontradaException(Exception ex){
        MensagemErro erro = new MensagemErro(HttpStatus.FORBIDDEN, "Usuario não encontrado", ex);
        return new ResponseEntity<MensagemErro>(erro, HttpStatus.FORBIDDEN);
    }
}
