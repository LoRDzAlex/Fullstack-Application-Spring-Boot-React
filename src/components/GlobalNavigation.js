import React from 'react';
import{Link}from'react-router-dom';
import AuthService from "../services/auth.service";

class GlobalNavigation extends React.Component {
    constructor(props) {
        super(props);
        this.logOut = this.logOut.bind(this);
        this.state = {
            showCompanyBoard: false,
            showAdminBoard: false,
            currentUser: undefined,
        };
    }


    componentDidMount() {
        const user = AuthService.getCurrentUser();
        if (user) {
            this.setState({
                currentUser: user,
                showModeratorBoard: user.roles.includes("ROLE_COMPANY"),
                showAdminBoard: user.roles.includes("ROLE_ADMIN"),
            });
        }
    }

    logOut() {
        AuthService.logout();
        this.setState(
            {
                showCompanyBoard: false,
                showAdminBoard: false,
                currentUser: undefined,
            });
    }

    render() {
        const {currentUser, showCompanyBoard, showAdminBoard} = this.state;
        return (
            <nav className="navbar navbar-expand navbar-dark bg-dark">
                <ul>
                    <li><Link to="/home">Home</Link></li>
                    <li><Link to="/quiz">Quiz</Link></li>
                    <li><Link to="/aboutus">AboutUs</Link></li>
                    {showCompanyBoard && (
                        <li><Link to="/questions">Questions</Link></li>
                    )}

                    {showAdminBoard && (<li><Link to={"/admin"}>AdminBoard</Link></li>
                    )}

                    {currentUser && (<li><Link to={"/user"}>User</Link></li>
                    )}

                    {currentUser ? (<div className="navbar-nav ml-auto">
                            <li>
                                <Link to={"/profile"}>{currentUser.username}</Link></li>
                            <li><a href="/login" onClick={this.logOut}>LogOut</a></li>
                        </div>
                    ) : (
                        <div className="navbar-nav ml-auto">
                            <li><Link to={"/login"}>Log in</Link></li>

                            <li><Link to={"/register"}>Sign Up</Link></li>
                        </div>
                    )}
                </ul>
            </nav>
        )

    }
}
 export default GlobalNavigation;