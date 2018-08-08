package don.geronimo.chadepanela.controller;

import don.geronimo.chadepanela.model.presente.Presente;
import don.geronimo.chadepanela.model.presente.PresenteDTO;
import don.geronimo.chadepanela.services.PresenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@RestController
public class PresenteController {
    private PresenteService presenteService;
    @Autowired
    public PresenteController(PresenteService presenteService){
        this.presenteService = presenteService;
    }

    @GetMapping("/secure/presentes/")
    public ResponseEntity<?> getAllPresentes(){
        List<Presente> presenteList = presenteService.findAllPresentes();
        return ResponseEntity.ok(presenteList);
    }

    @DeleteMapping("/secure/presentes/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        List<Presente> presenteList = presenteService.deletePresenteAndGetUpdatedList(id);
        return ResponseEntity.ok(presenteList);
    }
    @PostMapping("/secure/presentes/")
    public ResponseEntity<?> salvarPresente(@RequestParam("myFile") MultipartFile file,
                                          @RequestParam("nomeDoPresente")String nomeDoPresente,
                                          RedirectAttributes redirectAttributes) throws IOException {
        //Upload e cópia pra uma array de Bytes pra por no objeto
        InputStream uploadInputStream = file.getInputStream();
        byte[] _buffer = new byte[uploadInputStream.available()];
        uploadInputStream.read(_buffer);
        Byte[] buffer = new Byte[_buffer.length];
        int i=0;
        for(byte b:_buffer){
            buffer[i++] = b;
        }
        //Eu tenho o nome do presente e sua imagem : gravar tudo no banco
        Presente newPresente = new Presente();
        newPresente.setNomeDoPresente(nomeDoPresente);
        newPresente.setImageData(buffer);
        presenteService.addPresente(newPresente);
        //pega a lista atualizada de presentes
        List<Presente> presenteList = presenteService.findAllPresentes();

        return ResponseEntity.ok(presenteList);
    }

    @PostMapping("/secure/testeUpload/")
    public ResponseEntity<?> testeUpload(@RequestParam("myFile")MultipartFile file,
                                         RedirectAttributes redirectAttributes){
        //pega o arquivo e salva no disco pra teste
        //guarda o arquivo em um lugar temporário
        System.out.println(file);
        return ResponseEntity.ok("ainda está testando");
    }
}
