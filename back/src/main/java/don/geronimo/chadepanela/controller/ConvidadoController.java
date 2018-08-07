package don.geronimo.chadepanela.controller;

import don.geronimo.chadepanela.exceptions.UserNotFoundException;
import don.geronimo.chadepanela.model.convidado.Convidado;
import don.geronimo.chadepanela.model.convidado.ConvidadoDTO;
import don.geronimo.chadepanela.services.PessoaService;
import don.geronimo.chadepanela.utils.MensagemErro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConvidadoController {
    @Value("${jwtSecret}")
    private String jwtSecret;
    @Value("${claimFieldName}")
    private String claimFieldName;
    private PessoaService pessoaService;
    @Autowired
    public ConvidadoController(PessoaService pessoaService){
        this.pessoaService = pessoaService;
    }

    @GetMapping("/secure/convidados")
    public ResponseEntity<?> getConvidados(){
        List<Convidado> convidadoList = pessoaService.getAllConvidados();
        return ResponseEntity.ok(convidadoList);
    }

    @PostMapping("/secure/convidados")
    public ResponseEntity<?> createNew(@RequestBody ConvidadoDTO convidadoDTO){
        //monta um convidado a partir do dto
        Convidado convidado = pessoaService.createConvidadoFromDTO(convidadoDTO);
        //insere no banco
        convidado = pessoaService.addConvidado(convidado);
        //pega a lista atualizada
        List<Convidado> convidados = pessoaService.getAllConvidados();
        //retorna
        return ResponseEntity.ok(convidados);
    }

    @GetMapping("/secure/convidados/mail/{id}")
    public ResponseEntity<?> sendMail(@PathVariable String id) throws UserNotFoundException {
        Convidado convidado = (Convidado)pessoaService.getPessoaById(id);
        pessoaService.enviarConvite(convidado);
        //pega a lista atualizada
        List<Convidado> convidados = pessoaService.getAllConvidados();
        //retorna
        return ResponseEntity.ok(convidados);
    }

    @DeleteMapping("/secure/convidados/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        pessoaService.deleteConvidado(id);
        List<Convidado> convidados = pessoaService.getAllConvidados();
        return ResponseEntity.ok(convidados);
    }

    @ExceptionHandler(MailException.class)
    public ResponseEntity<?> erroNoEmailException(Exception ex){
        MensagemErro erro = new MensagemErro(HttpStatus.INTERNAL_SERVER_ERROR, "problema no envio do email", ex);
        return new ResponseEntity<MensagemErro>(erro, HttpStatus.FORBIDDEN);
    }

}
