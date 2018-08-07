import React, { Component } from 'react';
//TODO: Parar de usar labels e passar a usar aquele texto de hint dentro da caixa de texto
//TODO: Esconder o digitado na senha

class LoginForm extends Component {
    constructor(props){
        super(props);
        this.onLoginInputChange = this.onLoginInputChange.bind(this);
        this.onSenhaInputChange = this.onSenhaInputChange.bind(this);
        this.onButtonSubmitClick = this.onButtonSubmitClick.bind(this);
        this.state = {login:"", senha:""};
    }

    onButtonSubmitClick(){
        this.props.onLoginButtonSubmit(this.state);
        const newState = Object.assign({}, this.state, {login:"", senha:""});
        this.setState(newState);

    }

    onLoginInputChange(val){
        const newState = Object.assign({}, this.state, {login:val.currentTarget.value});
        this.setState(newState);
    }

    onSenhaInputChange(val){
        const newState = Object.assign({}, this.state, {senha:val.currentTarget.value});
        this.setState(newState);
    }

    render(){
        return(
            <div>
                <div>Login:<input onChange={this.onLoginInputChange} value={this.state.login}/></div>
                <div>Senha:<input onChange={this.onSenhaInputChange} value={this.state.senha}/></div>
                <button onClick={this.onButtonSubmitClick}>Entrar</button>
            </div>
        );
    }
}

export default LoginForm;