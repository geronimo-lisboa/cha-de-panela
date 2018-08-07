class ServerInterface{
    constructor(){
        this.login = this.login.bind(this);
        this.loginPath = "http://localhost:8080/login/"
    }

    login(loginData){
        console.log("tentando logar ,"+loginData);
        return fetch(this.loginPath,{
            method:'POST',
            headers:{
                Accept:'application/json',
                'Content-Type':'application/json'
            },
            body: JSON.stringify(loginData),
        }).then(response=>response.json());
    }
}

export default ServerInterface;