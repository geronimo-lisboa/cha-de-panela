import React, { Component } from 'react';
import PanelHello from './PanelHello'

class SelecaoDePresenteRow extends Component{
    render(){
        var img = "data:image/jpeg;base64,"+this.props.presente.imageAsBase64;
        return (
            <div>
                <span>{this.props.presente.nomeDoPresente}</span>
                <img src={img}/>
            </div>
        )
    }
}

class PainelDeSelecaoDePresentes extends Component{
    render(){
        const lstPresentes = this.props.presentes.map((currentPresente)=>{
           return <SelecaoDePresenteRow presente={currentPresente} key={currentPresente.id}/>
        });
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
    }

    storePresentesInState(jsonsDePresentes){
        const listPresentes = jsonsDePresentes.map((currentPresente)=>(currentPresente));
        let newState = Object.assign({}, this.state, {presentes:listPresentes});
        this.setState(newState);
    }

    componentDidMount(){
        this.props.getPresentes()
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
                />
            </div>
        )
    }
}

export default ConvidadoDashboard;