import axios from "axios";
// Die URL für die API-Endpunkte der Authentifizierung
const API_URL = "http://localhost:8080/api/auth/";

/**
 * Eine Sammlung von Methoden für die Authentifizierung.
 */

/**
 * Registriert einen neuen Benutzer.
 * @param {string} username - Der Benutzername des Benutzers.
 * @param {string} email - Die E-Mail-Adresse des Benutzers.
 * @param {string} password - Das Passwort des Benutzers.
 * @returns {Promise} Ein Promise-Objekt, das die Antwort des Servers enthält.
 */
const register = (username, email, password) => {
  return axios.post(API_URL + "signup", {
    username,
    email,
    password,
  });
}

/**
 * Meldet einen Benutzer an.
 * @param {string} username - Der Benutzername des Benutzers.
 * @param {string} password - Das Passwort des Benutzers.
 * @returns {Promise} Ein Promise-Objekt, das die Antwort des Servers enthält.
 */
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

/**
 * Meldet den aktuellen Benutzer ab.
 */
const logout = () => {
  localStorage.removeItem("user");
}

/**
 * Gibt den aktuellen Benutzer zurück.
 * @returns {Object} Das Benutzerobjekt oder null, wenn kein Benutzer angemeldet ist.
 */
const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem('user'));
}
/**
 * Gibt den Zugriffstoken des aktuellen Benutzers zurück.
 * @returns {string} Der Zugriffstoken oder null, wenn kein Benutzer angemeldet ist.
 */
export const getCurrentUserToken = () => {
    return JSON.parse(localStorage.getItem('user')).accessToken;
}
/**
 * Gibt den Benutzernamen des aktuellen Benutzers zurück.
 * @returns {string} Der Benutzername oder null, wenn kein Benutzer angemeldet ist.
 */
const getCurrentUserUsername = () => {
    return JSON.parse(localStorage.getItem('user')).username;
}
/**
 * Gibt die E-Mail-Adresse des aktuellen Benutzers zurück.
 * @returns {string} Die E-Mail-Adresse oder null, wenn kein Benutzer angemeldet ist.
 */
const getCurrentUserEmail = () => {
    return JSON.parse(localStorage.getItem('user')).email;
}
/**
 * Gibt die Rollen des aktuellen Benutzers zurück.
 * @returns {Array} Ein Array von Rollen oder null, wenn kein Benutzer angemeldet ist.
 */
const getCurrentUserRoles = () => {
    return JSON.parse(localStorage.getItem('user')).roles;
}
/**
 * Gibt die Berechtigungen des aktuellen Benutzers zurück.
 * @returns {Array} Ein Array von Berechtigungen oder null, wenn kein Benutzer angemeldet ist.
 */
const getCurrentUserAuthorities = () => {
    return JSON.parse(localStorage.getItem('user')).authorities;
}
/**
 * Eine Sammlung von Methoden für die Authentifizierung.
 * @type {{logout: logout, getCurrentUser: (function(): any), login: (function(string, string): *), register: (function(string, string, string): *)}}
 */
const AuthService = {
    register,
    login,
    logout,
    getCurrentUser
};

export default AuthService;