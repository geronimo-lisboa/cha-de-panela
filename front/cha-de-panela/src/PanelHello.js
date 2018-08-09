import React, { Component } from 'react';

class PanelHello extends Component {
    render(){
        return(
            <div className="PanelHello">
                <span>Seja bem vindo, {this.props.nome}.</span>
            </div>
        )
    }
}
export default PanelHello;