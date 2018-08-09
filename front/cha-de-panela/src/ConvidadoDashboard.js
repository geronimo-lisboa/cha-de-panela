import React, { Component } from 'react';
import PanelHello from './PanelHello'

class SelecaoDePresenteRow extends Component{
    constructor(props){
        super(props);
        this.onEscolherClick = this.onEscolherClick.bind(this);
        this.onDesfazerEscolhaClick = this.onDesfazerEscolhaClick.bind(this);
        this.state = {escolhido:false}
    }

    componentDidMount(){
        //como saber se o presente já foi escolhido?
    }

    onEscolherClick(){
        this.props.escolherPresente(this.props.presente.id);
    }

    onDesfazerEscolhaClick(){
        this.props.desfazerEscolhaDePresente(this.props.presente.id);
    }

    render(){
        var btnChooseUnchoose = undefined;
        if(this.props.presente.idConvidado === null || this.props.idConvidado === this.props.presente.idConvidado){
            if(this.props.presente.escolhido===true){
                btnChooseUnchoose = <button onClick={this.onDesfazerEscolhaClick}>Cancelar</button>
            }else{
                btnChooseUnchoose = <button onClick={this.onEscolherClick}>Escolher</button>
            }
        }else{
            btnChooseUnchoose = <span>Já Escolhido</span>
        }
        var img = "data:image/jpeg;base64,"+this.props.presente.imageAsBase64;
        return (
            <div className="PresenteRow">
                <div>{this.props.presente.nomeDoPresente}</div>
                {btnChooseUnchoose}
                <img src={img}/>
            </div>
        )
    }
}

class PainelDeSelecaoDePresentes extends Component{
    render(){
        const lstPresentes = this.props.presentes.map(
            (currentPresente)=>{
                return <SelecaoDePresenteRow
                    presente={currentPresente} key={currentPresente.id}
                    escolherPresente={this.props.escolherPresente}
                    desfazerEscolhaDePresente={this.props.desfazerEscolhaDePresente}
                    idConvidado={this.props.idConvidado}
                />
            }
        );
        return(<div>
            {lstPresentes}
        </div>)
    }
}

class ConvidadoDashboard extends Component {
    constructor(props){
        super(props);
        this.state={presentes:[]};
        this.storePresentesInState = this.storePresentesInState.bind(this);
        this.desfazerEscolhaDoPresente = this.desfazerEscolhaDoPresente.bind(this);
        this.escolherPresente = this.escolherPresente.bind(this);
        this.autoRefresh = this.autoRefresh.bind(this)
    }

    autoRefresh(){
        this.props.getPresentes()
            .then(serverData=>{
                this.storePresentesInState(serverData);
            });
    }

    storePresentesInState(jsonsDePresentes){
        const listPresentes = jsonsDePresentes.map((currentPresente)=>(currentPresente));
        let newState = Object.assign({}, this.state, {presentes:listPresentes});
        this.setState(newState);
    }

    componentDidMount(){
        var intervalId = setInterval(this.autoRefresh, 1000);
        this.props.getPresentes()
            .then(serverData=>{
               this.storePresentesInState(serverData);
            });
    }

    desfazerEscolhaDoPresente(idPresente){
        this.props.desfazerEscolhaDePresente(idPresente)
            .then(serverData=>{
                this.storePresentesInState(serverData);
            })
    }

    escolherPresente(idPresente){
        this.props.escolherPresente(idPresente)
            .then(serverData=>{
               this.storePresentesInState(serverData);
            });
    }

    render(){
        return(
            <div>
                <PanelHello nome={this.props.nome}/>
                <PainelDeSelecaoDePresentes
                    presentes={this.state.presentes}
                    escolherPresente={this.escolherPresente}
                    desfazerEscolhaDePresente={this.desfazerEscolhaDoPresente}
                    idConvidado={this.props.idConvidado}
                />
            </div>
        )
    }
}

export default ConvidadoDashboard;