import axios from "axios";
const API_URL="http://localhost:8080/api/auth/";
/*** The service uses Axios for HTTP requests * and Local Storage for user information & JWT.*/
const register=(username,email,password)=>{
    return axios.
    post(API_URL+"signup",{
        username,email,password
        ,});
};
const login=(username,password)=>{
    return axios.
    post(API_URL+"sign in",{ username,password, })
        .then((response)=>{
            if(response.data.username){
                localStorage.setItem("user",JSON.stringify(response.data));
            }
            return response.data;
        });
};
const logout=()=>{
    localStorage.removeItem("user");
};
const getCurrentUser=()=>{
    return JSON.parse(localStorage.getItem("user"));
};

const AuthService={
    register,
    login,
    logout,
    getCurrentUser,
}

export default AuthService;