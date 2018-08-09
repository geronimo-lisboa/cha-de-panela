import React, { Component } from 'react';
import LoginForm from './LoginForm';
import ServerInterface from './ServerInterface';
import DonoDashboard from './DonoDashboard';
import ConvidadoDashboard from './ConvidadoDashboard';
import './App.css';

class Titulo extends Component{
    render(){
        return(
            <div className="Titulo">
                Chá de Barnela Erika&Luciano
            </div>
        );
    }
}

class App extends Component {
    constructor(props){
        super(props);
        let initialState = {currentApplicationState:"login"}
        this.state = initialState;
        this.onLoginButtonSubmit = this.onLoginButtonSubmit.bind(this);
        this.serverInterface = new ServerInterface();
        this.getListaDeConvidados = this.getListaDeConvidados.bind(this);
        this.createNewConvidado = this.createNewConvidado.bind(this);
        this.deleteConvidado = this.deleteConvidado.bind(this);
        this.enviarEmail = this.enviarEmail.bind(this);
        this.getPresentes = this.getPresentes.bind(this);
        this.addPresente = this.addPresente.bind(this);
        this.deletePresente = this.deletePresente.bind(this);
        this.salvarPresente = this.salvarPresente.bind(this);

        this.escolherPresente = this.escolherPresente.bind(this);
        this.desfazerEscolhaDePresente = this.desfazerEscolhaDePresente.bind(this);
    }

    getPresentes(){
        return this.serverInterface.getPresentes(this.state.token);
    }

    addPresente(newPresenteData){
        return this.serverInterface.addPresente(this.state.token, newPresenteData);
    }

    deletePresente(idPresente){
        return this.serverInterface.deletePresente(this.state.token, idPresente);
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
                        currentApplicationState : "logado",
                        idPessoa:serverData.id,
                    });
                    this.setState(newState);
                }
            });
    }

    enviarEmail(idDoConvidado){
        return this.serverInterface.enviarEmail(this.state.token, idDoConvidado);
    }

    deleteConvidado(idConvidado){
        return this.serverInterface.deleteConvidado(this.state.token, idConvidado);
    }

    createNewConvidado(newConvidadoData){
        return this.serverInterface.createNewConvidado(this.state.token, newConvidadoData);
    }

    getListaDeConvidados(){
        return this.serverInterface.getConvidados(this.state.token);
    }

    salvarPresente(presenteData){
        return this.serverInterface.salvarPresente(this.state.token, presenteData);
    }

    escolherPresente(idPresente){
        return this.serverInterface.escolherPresente(this.state.token, idPresente);
    }

    desfazerEscolhaDePresente(idPresente){
        return this.serverInterface.desfazerEscolhaDePresente(this.state.token, idPresente);
    }

    render() {
        if(this.state.currentApplicationState==="login"){
            return (
                <div className="App">
                    <Titulo/>
                    <LoginForm onLoginButtonSubmit={this.onLoginButtonSubmit}/>

                </div>
            );
        }
        else if (this.state.currentApplicationState==="logado"){
            if(this.state.tipoDeUsuario === "DONO"){
                return(
                    <div className="App">
                        <Titulo/>
                        <DonoDashboard
                            nome={this.state.nome}
                            getListaDeConvidados={this.getListaDeConvidados}
                            createNewConvidado={this.createNewConvidado}
                            deleteConvidado={this.deleteConvidado}
                            enviarEmail={this.enviarEmail}
                            getPresentes={this.getPresentes}
                            addPresente={this.addPresente}
                            deletePresente={this.deletePresente}
                            salvarPresente={this.salvarPresente}
                        />
                    </div>
                );
            }else if (this.state.tipoDeUsuario ==="CONVIDADO"){
                return(
                    <div className="App">
                        <Titulo/>
                        <ConvidadoDashboard
                            nome={this.state.nome}
                            getPresentes={this.getPresentes}
                            escolherPresente={this.escolherPresente}
                            desfazerEscolhaDePresente={this.desfazerEscolhaDePresente}
                            idConvidado={this.state.idPessoa}
                        />
                    </div>
                )
            }
        }
    }
}

export default App;
