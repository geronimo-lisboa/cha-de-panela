package don.geronimo.chadepanela.controller;

import don.geronimo.chadepanela.model.presente.Presente;
import don.geronimo.chadepanela.model.presente.PresenteDTO;
import don.geronimo.chadepanela.services.PresenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @PostMapping("/secure/presentes/")
    public ResponseEntity<?> add(@RequestBody PresenteDTO presenteDTO){
        Presente presente = new Presente(presenteDTO.getNome(), null);
        presenteService.addPresente(presente);
        List<Presente> presenteList = presenteService.findAllPresentes();
        return ResponseEntity.ok(presenteList);
    }
    @DeleteMapping("/secure/presentes/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        List<Presente> presenteList = presenteService.deletePresenteAndGetUpdatedList(id);
        return ResponseEntity.ok(presenteList);
    }

    @PostMapping("/secure/testeUpload/")
    public ResponseEntity<?> testeUpload(@RequestParam("myFile")MultipartFile file,
                                         RedirectAttributes redirectAttributes){
        System.out.println(file);
        return ResponseEntity.ok("ainda está testando");
    }
}
