import React, { Component } from 'react';
import './App.css';


class LoginForm extends Component {
    constructor(props){
        super(props);
        this.onLoginInputChange = this.onLoginInputChange.bind(this);
        this.onSenhaInputChange = this.onSenhaInputChange.bind(this);
        this.onButtonSubmitClick = this.onButtonSubmitClick.bind(this);
    }

    onButtonSubmitClick(){

    }

    onLoginInputChange(val){

    }

    onSenhaInputChange(val){

    }

    render(){
        return(
          <div>
              <div>Login:<input onChange={this.onLoginInputChange}/></div>
              <div>Senha:<input onChange={this.onSenhaInputChange}/></div>
              <button onClick={this.onButtonSubmitClick}>Entrar</button>
          </div>
        );
    }
}

class App extends Component {
    constructor(props){
        super(props);
        let initialState = {"currentApplicationState":"login"}
        this.state = initialState;
        this.onLoginButtonSubmit = this.onLoginButtonSubmit.bind(this);
    }

    onLoginButtonSubmit(loginData){

    }

    render() {
        if(this.state.currentApplicationState==="login"){
            return (
                <LoginForm onLoginButtonSubmit={this.onLoginButtonSubmit}/>
            );
        }else{
            return(
                <div>TA LOGADAÇO!</div>
            );
        }
    }
}

export default App;
