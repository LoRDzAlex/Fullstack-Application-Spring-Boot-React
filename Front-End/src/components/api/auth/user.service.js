import axios from "axios";

const API_URL = "http://localhost:8080/api/auth/";

/**
 * Ruft öffentliche Daten ab, die für alle Benutzer verfügbar sind.
 * @returns {Promise} Ein Promise-Objekt, das die Ergebnisse des API-Aufrufs enthält.
 */
const getPublicContent = () => {
  return axios.get(API_URL + "all");
}
/**
 * Ruft Daten für den Benutzer ab, wenn dieser eingeloggt ist.
 * @returns {Promise} Ein Promise-Objekt, das die Ergebnisse des API-Aufrufs enthält.
 */
const getUserBoard = () => {
  return axios.get(API_URL + "user", { headers: authHeader() });
}
/**
 * Ruft Daten für das Dashboard der Firma ab, wenn der Benutzer die Rolle "Firma" hat.
 * @returns {Promise} Ein Promise-Objekt, das die Ergebnisse des API-Aufrufs enthält.
 */
const getCompanyBoard = () => {
  return axios.get(API_URL + "company", { headers: authHeader() });
}
/**
 * Ruft Daten für das Admin-Dashboard ab, wenn der Benutzer die Rolle "Admin" hat.
 * @returns {Promise} Ein Promise-Objekt, das die Ergebnisse des API-Aufrufs enthält.
 */
const getAdminBoard = () => {
  return axios.get(API_URL + "admin", { headers: authHeader() });
}
/**
 * Liefert die erforderlichen HTTP-Header für die Authentifizierung.
 * @returns {Object} Ein Objekt mit dem Autorisierungs-Header oder einem leeren Objekt, falls der Benutzer nicht authentifiziert ist.
 */
const authHeader = () => {
  const user = JSON.parse(localStorage.getItem('user'));

  if (user && user.accessToken) {
    return { Authorization: 'Bearer ' + user.accessToken };
  } else {
    return {};
  }
}
/**
 * Eine Sammlung von Methoden für die Authentifizierung.
 * @type {{getAdminBoard: (function(): *), getPublicContent: (function(): *), getUserBoard: (function(): *), getCompanyBoard: (function(): *)}}
 */
const UserService = {
    getPublicContent,
    getUserBoard,
    getCompanyBoard,
    getAdminBoard
};

export default UserService;
