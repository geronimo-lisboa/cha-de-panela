import React, { Component } from 'react';
import LoginForm from './LoginForm';
import ServerInterface from './ServerInterface';
import DonoDashboard from './DonoDashboard';
import ConvidadoDashboard from './ConvidadoDashboard';
import './App.css';


class App extends Component {
    constructor(props){
        super(props);
        let initialState = {currentApplicationState:"login"}
        this.state = initialState;
        this.onLoginButtonSubmit = this.onLoginButtonSubmit.bind(this);
        this.serverInterface = new ServerInterface();
        this.getListaDeConvidados = this.getListaDeConvidados.bind(this);
        this.createNewConvidado = this.createNewConvidado.bind(this);
        // //PRA TESTE
        // let testState = {nome: "Erika", token: "eyJhbGciOiJIUzI1NiJ9.eyJcImF1dGhlbnRpY2F0aW9uRGF0Y…2Njd9.CwYuoRImXUUFn-7lHkc3TFobtGt8mmpEpsOPkAhXXYo", perfil: "DONO"};
        // this.state ={
        //     token : testState.token,
        //     tipoDeUsuario : testState.perfil,
        //     nome : testState.nome,
        //     currentApplicationState : "logado"
        // };
    }

    onLoginButtonSubmit(loginData){
        this.serverInterface.login(loginData)
            .then(serverData=>{
                console.log(serverData);
                if(serverData.status===403){
                    console.log("SAAAAAI");
                }else{
                    const newState = Object.assign({}, this.state, {
                        token : serverData.token,
                        tipoDeUsuario : serverData.perfil,
                        nome : serverData.nome,
                        currentApplicationState : "logado"
                    });
                    this.setState(newState);
                }
            });
    }

    createNewConvidado(newConvidadoData){
        return this.serverInterface.createNewConvidado(this.state.token, newConvidadoData);
    }

    getListaDeConvidados(){
        return this.serverInterface.getConvidados(this.state.token);
    }

    render() {
        if(this.state.currentApplicationState==="login"){
            return (
                <LoginForm onLoginButtonSubmit={this.onLoginButtonSubmit}/>
            );
        }
        else if (this.state.currentApplicationState==="logado"){
            if(this.state.tipoDeUsuario === "DONO"){
                return(
                <DonoDashboard
                    nome={this.state.nome}
                    getListaDeConvidados={this.getListaDeConvidados}
                    createNewConvidado={this.createNewConvidado}
                />
                );
            }else if (this.state.tipoDeUsuario ==="CONVIDADO"){
                return(
                <ConvidadoDashboard/>
                )
            }
        }
    }
}

export default App;
