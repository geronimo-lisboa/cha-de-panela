import React, { Component } from 'react';
import LoginForm from './LoginForm';
import ServerInterface from './ServerInterface';
import './App.css';


class App extends Component {
    constructor(props){
        super(props);
        let initialState = {"currentApplicationState":"login"}
        this.state = initialState;
        this.onLoginButtonSubmit = this.onLoginButtonSubmit.bind(this);
        this.serverInterface = new ServerInterface();
    }

    onLoginButtonSubmit(loginData){
        this.serverInterface.login(loginData)
            .then(whatIsHere=>{
                console.log(whatIsHere);
            });
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
