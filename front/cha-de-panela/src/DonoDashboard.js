import React, { Component } from 'react';

import {ConvidadoPanel} from './Convidado'
import PanelHello from './PanelHello'


class PresentesRow extends Component{
    constructor(props){
        super(props);
        this.deletePresenteClick = this.deletePresenteClick.bind(this);
    }

    deletePresenteClick(event){
        this.props.deletePresente(this.props.presente.id);
    }

    render(){
        var img = "data:image/jpeg;base64,"+this.props.presente.imageAsBase64;
        return (
            <div className="rowPresenteTelaDono">
                <span>{this.props.presente.nomeDoPresente}</span>
                <img src={img}/>
                <button onClick={this.deletePresenteClick}>Delete</button>
            </div>
        )
    }

}

class PresentesTable extends Component{
    render(){
        const lstPresentes = this.props.presentes.map((currentPresente)=>{
            return <PresentesRow presente={currentPresente} key={currentPresente.id} deletePresente={this.props.deletePresente}/>
        })
        return (
            <div className="tablePresenteTelaDono">
                {lstPresentes}
            </div>
        )
    }
}

class NewPresentePanel extends Component{
    constructor(props){
        super(props);
        this.fileChangedHandler = this.fileChangedHandler.bind(this);
        this.onSalvarPresenteClick = this.onSalvarPresenteClick.bind(this);
        this.onNomeChange = this.onNomeChange.bind(this);
        this.state = {selectedFile:null, nomeDoPresente:""}
    }

    onNomeChange(event){
        const newState = Object.assign({},
            this.state, {
                nomeDoPresente:event.currentTarget.value
        });
        this.setState(newState);
    }

    fileChangedHandler(event){
        const newState = Object.assign({},
            this.state,{
                selectedFile : event.target.files[0],
            });
        this.setState(newState);
    }

    onSalvarPresenteClick(){
        this.props.salvarPresente(this.state);
        //this.props.salvarPresente(this.state.selectedFile);
    }

    render(){
        return (
            <div className="newPresentePanel">
                <div>Nome:<input onChange={this.onNomeChange}/></div>
                <div>Imagem:<input type="file" onChange={this.fileChangedHandler}/></div>
                <div><button onClick={this.onSalvarPresenteClick}>Salvar</button></div>
            </div>
        )
    }
}

class PresentePanel extends Component{
    render(){
        return(
            <div className="presentePanelGlobal">
                <div className="donoPresentePanelHeader">Presentes</div>
                <NewPresentePanel
                    salvarPresente={this.props.salvarPresente}
                />
                <PresentesTable
                    presentes={this.props.presentes}
                    deletePresente={this.props.deletePresente}
                />
            </div>
        )
    }
}

class DonoDashboard extends Component {
    constructor(props){
        super(props);
        this.state={convidados:[], presentes:[]};
        this.createNewConvidado = this.createNewConvidado.bind(this);
        this.storeConvidadosInState = this.storeConvidadosInState.bind(this);
        this.storePresentesInState = this.storePresentesInState.bind(this);
        this.deleteConvidado = this.deleteConvidado.bind(this);
        this.enviarEmail = this.enviarEmail.bind(this);
        this.salvarPresente = this.salvarPresente.bind(this);
        this.deletePresente = this.deletePresente.bind(this);
    }

    deletePresente(idPresenteToDie){
        this.props.deletePresente(idPresenteToDie).then(serverData=>{this.storePresentesInState(serverData)});
    }
    salvarPresente(presenteData){
        this.props.salvarPresente(presenteData)
            .then(serverData=>{
               this.storePresentesInState(serverData);
            });
    }

    enviarEmail(idConvidado){
        this.props.enviarEmail(idConvidado)
            .then(serverData=>{
               this.storeConvidadosInState(serverData);
            });
    }

    storeConvidadosInState(jsonsDosConvidados){
        const listConvidados = jsonsDosConvidados.map((currentConvidado)=>(currentConvidado));
        let newState = Object.assign({}, this.state, {convidados:listConvidados});
        this.setState(newState);
    }

    storePresentesInState(jsonsDePresentes){
        const listPresentes = jsonsDePresentes.map((currentPresente)=>(currentPresente));
        let newState = Object.assign({}, this.state, {presentes:listPresentes});
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
        this.props.getPresentes()
            .then(serverData=>{
                this.storePresentesInState(serverData);
            })
    }

    render(){
        return(
            <div className="areaDoDono">
                <PanelHello nome={this.props.nome}/>
                <ConvidadoPanel
                    convidados={this.state.convidados}
                    deleteConvidado={this.deleteConvidado}
                    createNewConvidado={this.createNewConvidado}
                    enviarEmail={this.enviarEmail}
                />
                <PresentePanel
                    presentes={this.state.presentes}
                    uploadImage={this.props.salvarPresente}
                    salvarPresente={this.salvarPresente}
                    deletePresente={this.deletePresente}
                />
            </div>
        )
    }
}

export default DonoDashboard;