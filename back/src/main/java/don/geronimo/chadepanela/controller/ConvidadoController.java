package don.geronimo.chadepanela.controller;

import don.geronimo.chadepanela.model.convidado.Convidado;
import don.geronimo.chadepanela.model.convidado.ConvidadoDTO;
import don.geronimo.chadepanela.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
