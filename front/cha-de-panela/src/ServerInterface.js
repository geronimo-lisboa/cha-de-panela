import axios from 'axios';

class ServerInterface{
    constructor(){
        this.login = this.login.bind(this);
        this.getConvidados = this.getConvidados.bind(this);
        this.createNewConvidado = this.createNewConvidado.bind(this);
        this.deleteConvidado = this.deleteConvidado.bind(this);
        this.enviarEmail = this.enviarEmail.bind(this);
        this.getPresentes = this.getPresentes.bind(this);
        this.deletePresente = this.deletePresente.bind(this);
        this.salvarPresente = this.salvarPresente.bind(this);

        this.escolherPresente = this.escolherPresente.bind(this);
        this.desfazerEscolhaDePresente = this.desfazerEscolhaDePresente.bind(this);

        this.loginPath = "http://localhost:8080/login/";
        this.convidadosPath="http://localhost:8080/secure/convidados";
        this.enviarEmailPath="http://localhost:8080/secure/convidados/mail/";
        this.presentesPath = "http://localhost:8080/secure/presentes/";
        this.salvarPresentePath = "http://localhost:8080/secure/presentes/";

        this.escolherPresentePath = "http://localhost:8080/secure/convidado/presente";
        this.desfazerEscolhaDePresentePath = "http://localhost:8080/secure/convidado/presente";

    }

    desfazerEscolhaDePresente(token, idPresente){
        var presenteData={idConvidado:"", idPresente:idPresente}
        return fetch(this.escolherPresentePath,{
            method:'DELETE',
            headers:{
                Authorization:this.assembleToken(token),
                Accept:'application/json',
                'Content-Type':'application/json'
            },
            body:JSON.stringify(presenteData)
        }).then(
            response=>response.json()
        );
    }

    escolherPresente(token, idPresente){
        var presenteData={idConvidado:"", idPresente:idPresente}
        return fetch(this.escolherPresentePath,{
            method:'POST',
            headers:{
                Authorization:this.assembleToken(token),
                Accept:'application/json',
                'Content-Type':'application/json'
            },
            body: JSON.stringify(presenteData)
            })
        //Se deu erro de http, grita
            .then(response=>{
                if(!response.ok){throw response}
                return response.json();
                }
            )
            //tratamento do erro
            .catch(error=>{
                if(error.status===406){
                    alert("presente já escolhido por outra pessoa");
                    return this.getPresentes(token);
                }
            })

    }

    salvarPresente(token, presenteData){
        var formData = new FormData();
        formData.append('myFile', presenteData.selectedFile, presenteData.selectedFile.name);
        formData.append('nomeDoPresente', presenteData.nomeDoPresente);

        return fetch(this.salvarPresentePath,{
           method:'POST',
           headers:{
               Authorization:this.assembleToken(token),
           },
            body:formData
        }).then(
            response=>response.json()
        );
    }

    deletePresente(token, idPresente){
        return fetch(this.presentesPath+idPresente, {
            method:'DELETE',
            headers:{
                Authorization:this.assembleToken(token),
                Accept:'application/json',
                'Content-Type':'application/json'
            },

        }).then(response=>response.json());
    }

    getPresentes(token){
        return fetch(this.presentesPath, {
            method:'GET',
            headers:{
                Authorization:this.assembleToken(token),
                Accept:'application/json',
                'Content-Type':'application/json'
            },
        }).then(response=>response.json());
    }


    enviarEmail(token, idDoConvidado){
        return fetch(this.enviarEmailPath+idDoConvidado, {
            method:'GET',
            headers:{
                Authorization:this.assembleToken(token),
                Accept:'application/json',
                'Content-Type':'application/json'
            },
        }).then(response=>response.json());
    }

    login(loginData){
        return fetch(this.loginPath,{
            method:'POST',
            headers:{
                Accept:'application/json',
                'Content-Type':'application/json'
            },
            body: JSON.stringify(loginData),
        }).then(response=>response.json());
    }

    getConvidados(token){
        return fetch(this.convidadosPath, {
           method:'GET',
           headers:{
               Authorization:this.assembleToken(token),
               Accept:'application/json',
               'Content-Type':'application/json'
           },
        }).then(response=>response.json());
    }

    createNewConvidado(token, newConvidadoData){
        return fetch(this.convidadosPath, {
            method:'POST',
            headers:{
                Authorization:this.assembleToken(token),
                Accept:'application/json',
                'Content-Type':'application/json'
            },
            body: JSON.stringify(newConvidadoData),
        }).then(response=>response.json());
    }

    deleteConvidado(token, idConvidado){
        return fetch(this.convidadosPath+"/"+idConvidado, {
            method:'DELETE',
            headers:{
                Authorization:this.assembleToken(token),
                Accept:'application/json',
                'Content-Type':'application/json'
            },
            body: {id:idConvidado},
        }).then(response=>response.json());
    }

    assembleToken(token){
        return 'Bearer '+token;
    }
}

export default ServerInterface;