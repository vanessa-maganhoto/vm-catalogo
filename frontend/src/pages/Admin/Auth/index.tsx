
import { Switch, Route } from 'react-router-dom';
import loginImagem from '../../../assets/images/login-image.png';

import './styles.css'

const Auth = () => {
    return(
       <div className="auth-container">
        <div className="auth-banner-container">
            <h1>Divulgue seus produtos no VM Catálogo</h1>
            <p>Faça parte do nosso catálogo de divulgação e aumente a venda de seus produtos</p>
            <img src={loginImagem} alt="Imagem de capa da tela de login"/>
        </div>
        <div className="auth-form-container">
            <Switch>
                <Route path="/admin/auth/login">
                    <h1>Card de login</h1>
                </Route>
                <Route path="/admin/auth/signup">
                    <h1>Card de signup</h1>
                </Route>
                <Route path="/admin/auth/recover">
                    <h1>Card de recover</h1>
                </Route>
            </Switch>
        </div>

       </div>
    );
}

export default Auth;