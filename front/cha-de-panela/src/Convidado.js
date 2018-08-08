import React, { Component } from 'react';

export class ConvidadoRow extends Component{
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


export class ConvidadosTable extends Component{
    render(){
        const listaDeConvidados = this.props.convidados.map((current)=>{
            return <ConvidadoRow
                convidado={current}
                deleteConvidado={this.props.deleteConvidado}
                enviarEmail={this.props.enviarEmail}
                key={current.id}
            />
        });
        return(
            <div>
                {listaDeConvidados}
            </div>
        )
    }
}

export class NewConvidadoForm extends Component {
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


export class ConvidadoPanel extends Component{
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
