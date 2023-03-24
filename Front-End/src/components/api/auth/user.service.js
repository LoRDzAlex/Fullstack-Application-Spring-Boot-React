import axios from "axios";
const API_URL = "http://localhost:8080/api/auth/";

const getPublicContent = () => {
  return axios.get(API_URL + "all");
}

const getUserBoard = () => {
  return axios.get(API_URL + "user", { headers: authHeader() });
}

const getCompanyBoard = () => {
  return axios.get(API_URL + "company", { headers: authHeader() });
}

const getAdminBoard = () => {
  return axios.get(API_URL + "admin", { headers: authHeader() });
}

const authHeader = () => {
  const user = JSON.parse(localStorage.getItem('user'));

  if (user && user.accessToken) {
    return { Authorization: 'Bearer ' + user.accessToken };
  } else {
    return {};
  }
}

const UserService = {
    getPublicContent,
    getUserBoard,
    getCompanyBoard,
    getAdminBoard
};

export default UserService;
