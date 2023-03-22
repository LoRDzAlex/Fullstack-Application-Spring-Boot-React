import axios from 'axios';
import authHeader from'./auth-header';
const API_URL='http://localhost:8080/api/';
const getPublicContent=()=>{
    return axios.get(API_URL+"all");
};

const getUserBoard=()=>{
    return axios.get(API_URL+"user");
};

const getModeratorBoard=()=>{
    return axios.get(API_URL+"mod");
};

const getAdminBoard=()=>{
    return axios.get(API_URL+"admin");
};

const UserService={
    getPublicContent,
    getUserBoard,
    getModeratorBoard,
    getAdminBoard,
}

export default UserService;