import axios from "axios";
const API_URL = "http://localhost:8080/api/auth/";

const register = (username, email, password) => {
  return axios.post(API_URL + "signup", {
    username,
    email,
    password,
  });
}

const login = (username, password) => {
  return axios
    .post(API_URL + "signin", {
      username,
      password,
    })
    .then((response) => {
      if (response.data.username) {
        localStorage.setItem("user", JSON.stringify(response.data));
      }
      return response.data;
    });
}

const logout = () => {
  localStorage.removeItem("user");
}

const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem('user'));
}
export const getCurrentUserToken = () => {
    return JSON.parse(localStorage.getItem('user')).accessToken;
}
const getCurrentUserUsername = () => {
    return JSON.parse(localStorage.getItem('user')).username;
}
const getCurrentUserEmail = () => {
    return JSON.parse(localStorage.getItem('user')).email;
}
const getCurrentUserRoles = () => {
    return JSON.parse(localStorage.getItem('user')).roles;
}
const getCurrentUserAuthorities = () => {
    return JSON.parse(localStorage.getItem('user')).authorities;
}

const AuthService = {
    register,
    login,
    logout,
    getCurrentUser
};

export default AuthService;