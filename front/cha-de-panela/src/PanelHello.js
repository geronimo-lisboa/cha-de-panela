import React, { Component } from 'react';

class PanelHello extends Component {
    render(){
        return(
            <div>
                Seja bem vindo, {this.props.nome}.
            </div>
        )
    }
}
export default PanelHello;