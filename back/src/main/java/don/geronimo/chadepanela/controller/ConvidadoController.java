package don.geronimo.chadepanela.controller;

import don.geronimo.chadepanela.exceptions.PresenteJaEscolhidoException;
import don.geronimo.chadepanela.exceptions.UserNotFoundException;
import don.geronimo.chadepanela.model.convidado.Convidado;
import don.geronimo.chadepanela.model.convidado.ConvidadoDTO;
import don.geronimo.chadepanela.model.pessoa.Pessoa;
import don.geronimo.chadepanela.model.pessoa.PessoaAuthenticationData;
import don.geronimo.chadepanela.model.presente.Presente;
import don.geronimo.chadepanela.model.presente.PresenteConvidado;
import don.geronimo.chadepanela.model.presente.PresenteConvidadoDTO;
import don.geronimo.chadepanela.model.presente.PresenteDTO;
import don.geronimo.chadepanela.services.PessoaService;
import don.geronimo.chadepanela.services.PresenteService;
import don.geronimo.chadepanela.utils.MensagemErro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ConvidadoController {
    @Value("${jwtSecret}")
    private String jwtSecret;
    @Value("${claimFieldName}")
    private String claimFieldName;
    private PessoaService pessoaService;
    private PresenteService presenteService;
    @Autowired
    public ConvidadoController(PessoaService pessoaService, PresenteService presenteService){
        this.pessoaService = pessoaService;
        this.presenteService = presenteService;
    }

    @PostMapping("/secure/convidado/presente")
    public ResponseEntity<?> escolherPresente(@RequestBody PresenteConvidadoDTO presenteConvidadoDTO, final HttpServletRequest request) throws UserNotFoundException, IOException, PresenteJaEscolhidoException {
        //Pega o convidado
        PessoaAuthenticationData pessoaAuthenticationData = (PessoaAuthenticationData) request.getAttribute("pessoaAuthenticationData");
        Convidado convidado = (Convidado)pessoaService.getPessoaById(pessoaAuthenticationData.getId());
        //cria a relação convidado-presente
        presenteService.atribuirAConvidado(convidado.getId(), presenteConvidadoDTO.getIdPresente());
        //pega a lista de presentes atualizada
        List<Presente> presenteList = presenteService.findAllPresentes();
        return ResponseEntity.ok(presenteList);
    }


    @ExceptionHandler(PresenteJaEscolhidoException.class)
    public ResponseEntity<?> pessoaNaoEncontradaException(Exception ex){
        MensagemErro erro = new MensagemErro(HttpStatus.FORBIDDEN, "Presente Já escolhido", ex);
        return new ResponseEntity<MensagemErro>(erro, HttpStatus.NOT_ACCEPTABLE);
    }

    @DeleteMapping("/secure/convidado/presente")
    public ResponseEntity<?> desfazerEscolhaDePresente(@RequestBody PresenteConvidadoDTO presenteDTO, final HttpServletRequest request) throws UserNotFoundException {
        //Pega o convidado
        PessoaAuthenticationData pessoaAuthenticationData = (PessoaAuthenticationData) request.getAttribute("pessoaAuthenticationData");
        Convidado convidado = (Convidado)pessoaService.getPessoaById(pessoaAuthenticationData.getId());
        presenteService.desligarPresenteDeConvidado(convidado.getId(), presenteDTO.getIdPresente());
        List<Presente> presenteList = presenteService.findAllPresentes();
        return ResponseEntity.ok(presenteList);
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
    public ResponseEntity<?> sendMail(@PathVariable String id) throws UserNotFoundException, IOException, MessagingException {
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
