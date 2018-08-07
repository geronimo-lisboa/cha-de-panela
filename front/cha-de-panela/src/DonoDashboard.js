import React, { Component } from 'react';
import PanelHello from './PanelHello'

class NewConvidadoForm extends Component {
    constructor(props){
        super(props);
        this.state = {nome:"", email:"", login:""};
        this.onNomeChange = this.onNomeChange.bind(this);
        this.onEmailChange = this.onEmailChange.bind(this);
        this.onLoginChange = this.onLoginChange.bind(this);
        this.onEnviarClick = this.onEnviarClick.bind(this);
    }

    onNomeChange(src){
        const newState = Object.assign({}, this.state,{nome:src.currentTarget.value});
        this.setState(newState);
    }

    onEmailChange(src){
        const newState = Object.assign({}, this.state,{email:src.currentTarget.value});
        this.setState(newState);
    }

    onLoginChange(src){
        const newState = Object.assign({}, this.state,{login:src.currentTarget.value});
        this.setState(newState);
    }

    onEnviarClick(){
        const newConvidado = {nome:this.state.nome,
            email:this.state.email,
            login:this.state.login,};
        this.props.createNewConvidado(newConvidado);
    }

    render(){
        return(
            <div>
                <div>Novo Convidado</div>
                <div> Nome: <input onChange={this.onNomeChange}/></div>
                <div> Email: <input onChange={this.onEmailChange}/></div>
                <div> Login: <input onChange={this.onLoginChange}/></div>
                <div> <button onClick={this.onEnviarClick}>Criar</button> </div>
            </div>
        );
    }
}

class ConvidadoRow extends Component{
    constructor(props){
        super(props);
        this.onDeleteConvidadoClick = this.onDeleteConvidadoClick.bind(this);
        this.onEnviarEmailClick = this.onEnviarEmailClick.bind(this);
    }

    onDeleteConvidadoClick(){
        this.props.deleteConvidado(this.props.convidado.id);
    }

    onEnviarEmailClick(){
        this.props.enviarEmail(this.props.convidado.id);
    }

    render(){
        var infoConviteEnviado;
        if(this.props.convidado.conviteEnviado===true){
            infoConviteEnviado = <span>Convite Enviado</span>
        }else{
            infoConviteEnviado = <span>Convite por enviar</span>
        }
        return(
        <div>
            <span>
                {this.props.convidado.nome}
                <button onClick={this.onEnviarEmailClick}>Enviar Email</button>
                <button onClick={this.onDeleteConvidadoClick}>Excluir Convidado</button>
                {infoConviteEnviado}
            </span>
        </div>
        );
    }
}

class ConvidadosTable extends Component{
    render(){
        const listaDeConvidados = this.props.convidados.map((current)=>{
           return <ConvidadoRow
               convidado={current}
               deleteConvidado={this.props.deleteConvidado}
               enviarEmail={this.props.enviarEmail}
           />
        });
        return(
            <div>
                {listaDeConvidados}
            </div>
        )
    }
}

class ConvidadoPanel extends Component{
    render(){
        return(
            <div>
                <NewConvidadoForm createNewConvidado={this.props.createNewConvidado}/>
                <ConvidadosTable
                    convidados={this.props.convidados}
                    deleteConvidado={this.props.deleteConvidado}
                    enviarEmail={this.props.enviarEmail}
                />
            </div>
        )
    }
}

class PresentePanel extends Component{
    render(){
        return(
            <div>
                Area de gerenciamento dos presentes
            </div>
        )
    }
}

class DonoDashboard extends Component {
    constructor(props){
        super(props);
        this.state={convidados:[]};
        this.createNewConvidado = this.createNewConvidado.bind(this);
        this.storeConvidadosInState = this.storeConvidadosInState.bind(this);
        this.deleteConvidado = this.deleteConvidado.bind(this);
        this.enviarEmail = this.enviarEmail.bind(this);
    }

    enviarEmail(idConvidado){
        this.props.enviarEmail(idConvidado)
            .then(serverData=>{
               this.storeConvidadosInState(serverData);
            });
    }

    storeConvidadosInState(jsonsDosConvidados){
        const listConvidados = jsonsDosConvidados.map((currentConvidado)=>(currentConvidado));
        let newState = {convidados:listConvidados};
        this.setState(newState);
    }

    deleteConvidado(idConvidado){
        this.props.deleteConvidado(idConvidado)
            .then(serverData=>{
                this.storeConvidadosInState(serverData);
            });
    }
    //Como a lista de convidados vive aqui eu preciso atualizar esse componente qdo um novo convidado for criado, com os
    //dados vindos do servidor
    createNewConvidado(newConvidadoData){
        this.props.createNewConvidado(newConvidadoData)
            .then(serverData=>{
                this.storeConvidadosInState(serverData);
            })
    }

    componentDidMount(){
        //pega a lista de convidados pra passar pro panel de convidados
        this.props.getListaDeConvidados()
            .then(serverData=>{
                //mapeia a lista de jsons de convidado pra dentro do convidado panel
                this.storeConvidadosInState(serverData);
            })
    }

    render(){
        return(
            <div>
                <PanelHello nome={this.props.nome}/>
                <ConvidadoPanel
                    convidados={this.state.convidados}
                    deleteConvidado={this.deleteConvidado}
                    createNewConvidado={this.createNewConvidado}
                    enviarEmail={this.enviarEmail}
                />
                <PresentePanel/>
            </div>
        )
    }
}

export default DonoDashboard;