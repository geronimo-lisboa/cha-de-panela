import React, { Component } from 'react';
import LoginForm from './LoginForm';
import ServerInterface from './ServerInterface';
import './App.css';


class App extends Component {
    constructor(props){
        super(props);
        let initialState = {currentApplicationState:"login"}
        this.state = initialState;
        this.onLoginButtonSubmit = this.onLoginButtonSubmit.bind(this);
        this.serverInterface = new ServerInterface();
    }

    onLoginButtonSubmit(loginData){
        this.serverInterface.login(loginData)
            .then(serverData=>{
                console.log(serverData);
                if(serverData.status==403){
                    console.log("SAAAAAI");
                }else{
                    const newState = Object.assign({}, this.state, {
                        token : serverData.token,
                        currentApplicationState : "logado"
                    });
                    this.setState(newState);
                }
            });
    }

    render() {
        if(this.state.currentApplicationState==="login"){
            return (
                <LoginForm onLoginButtonSubmit={this.onLoginButtonSubmit}/>
            );
        }
        else if (this.state.currentApplicationState==="logado"){
            return (
                <div>
                    BLABLABLABLA...
                </div>
            )
        }
    }
}

export default App;
