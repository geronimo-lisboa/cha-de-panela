package don.geronimo.chadepanela.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import don.geronimo.chadepanela.exceptions.UserNotFoundException;
import don.geronimo.chadepanela.model.convidado.Convidado;
import don.geronimo.chadepanela.model.convidado.ConvidadoDTO;
import don.geronimo.chadepanela.model.pessoa.Pessoa;
import don.geronimo.chadepanela.model.pessoa.PessoaAuthenticationData;
import don.geronimo.chadepanela.repository.ConvidadoRepository;
import don.geronimo.chadepanela.repository.PessoaRepository;
import don.geronimo.chadepanela.utils.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.swing.text.html.Option;
import java.io.*;
import java.util.List;
import java.util.Optional;


//TODO: Separar em DonoService, ConvidadoService e PessoaService;
@Service
public class PessoaService {
    @Value("${urlDaApp}")
    private String urlDaApp;

    private PessoaRepository pessoaRepository;
    private ConvidadoRepository convidadoRepository;
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository, ConvidadoRepository convidadoRepository){

        this.pessoaRepository = pessoaRepository;
        this.convidadoRepository = convidadoRepository;
    }


    public void enviarConvite(Convidado convidado) throws MailException, IOException, MessagingException {
        //Le o arquivo
        ClassLoader classLoader = getClass().getClassLoader();
        //File file = new File(classLoader.getResource("Convite.html").getFile());
	File file = new File("/home/ubuntu/cha-de-panela/back/src/main/resources/Convite.html");//Hack horrorso pra por o programa funcionando na produção.
        FileReader reader = new FileReader(file);
        BufferedReader in = new BufferedReader(reader);
        String st;
        StringBuffer stringBuffer = new StringBuffer();
        while ((st = in.readLine()) != null){
            stringBuffer.append(st);
        }
        in.close();
        reader.close();
        //Faz as substituições no texto
        String mailText = stringBuffer.toString();
        mailText = mailText.replaceAll("_NOME_", convidado.getNome())
                .replaceAll("_URL_", urlDaApp)
                .replaceAll("_LOGIN_", convidado.getLogin())
                .replaceAll("_SENHA_", convidado.getSenha());
        //Envia o email
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
        mimeMessage.setContent(mailText, "text/html");
        helper.setTo(convidado.getEmail());
        helper.setSubject("Convite de Erika Thaissa e Luciano Gerônimo");
        helper.setFrom("erikaeluciano2016@gmail.com");
        mailSender.send(mimeMessage);

        convidado.setConviteEnviado(true);
        convidadoRepository.save(convidado);
    }

    public Pessoa getPessoaByLoginAndSenha(String login, String senha) throws UserNotFoundException {
        Pessoa p = pessoaRepository.findByLoginAndSenha(login, senha);
        if(p==null){
            throw new UserNotFoundException();
        }
        return p;
    }

    public String convertPessoaToAuthentiticationData(Pessoa p) throws JsonProcessingException {
        PessoaAuthenticationData pd = new PessoaAuthenticationData(p);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(pd);
    }

    public PessoaAuthenticationData createAuthenticationDataFromJson(String jsonAuthenticationData) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonAuthenticationData, PessoaAuthenticationData.class);
    }

    public Pessoa getPessoaById(String id) throws UserNotFoundException {
        Pessoa pop = pessoaRepository.findById(id);
        if(pop!=null){
            return pop;
        }else{
            throw new UserNotFoundException();
        }

    }

    public List<Convidado> getAllConvidados() {
        List<Convidado> convidados = convidadoRepository.findAll();
        return convidados;
    }

    public Convidado addConvidado(Convidado c) {
        RandomString gen = new RandomString(8);
        c.setSenha(gen.nextString());
        return convidadoRepository.save(c);
    }

    public Convidado createConvidadoFromDTO(ConvidadoDTO convidadoDTO) {
        Convidado convidado = new Convidado();
        convidado.setNome(convidadoDTO.getNome());
        convidado.setEmail(convidadoDTO.getEmail());
        convidado.setLogin(convidadoDTO.getLogin());
        convidado.setId(convidadoDTO.getId());
        return convidado;
    }

    public void deleteConvidado(String id) {
        Convidado c = convidadoRepository.findById(id).get();
        convidadoRepository.delete(c);
    }
}
