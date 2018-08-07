class ServerInterface{
    constructor(){
        this.login = this.login.bind(this);
        this.getConvidados = this.getConvidados.bind(this);
        this.createNewConvidado = this.createNewConvidado.bind(this);
        this.deleteConvidado = this.deleteConvidado.bind(this);
        this.enviarEmail = this.enviarEmail.bind(this);
        this.loginPath = "http://localhost:8080/login/";
        this.convidadosPath="http://localhost:8080/secure/convidados";
        this.enviarEmailPath="http://localhost:8080/secure/convidados/mail/";
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