import axios from 'axios';
import authHeader from './auth-header';

const API_URL_ADMIN = 'http://localhost:8080/admin/';

class AdminService{
    registerUser(user){
      return axios.post(API_URL_ADMIN +"insert", user, { headers: authHeader() });
    }

    updateUser(user){
      console.log("UPDATING: " + user.id + " " + user.username + " " + user.password + " " + user.email + " " + user.role);
      return axios.put(API_URL_ADMIN + "edit", user, { headers: authHeader() });
    }
    isUsernameTaken(username){
      console.log("Checking if username is taken: " + username);
      return axios.get(`${API_URL_ADMIN}isUsernameTaken/${username}`, { headers: authHeader() });
    }

    deleteUser(id){
      console.log("Se sterge docrorul cu id: " + id);
      return axios.delete(`${API_URL_ADMIN}${id}`, { headers: authHeader() });
    }

    getDoctor(id){
      return axios.get(API_URL_ADMIN, id, { headers: authHeader() });
    }

    getMedicalAssistant(id){
      return axios.get(API_URL_ADMIN, id, { headers: authHeader() });
    }

    getAllDoctors(){
      return axios.get(API_URL_ADMIN + "doctors", { headers: authHeader() });
    }

    getAllMedicalAssistants(){
      return axios.get(API_URL_ADMIN + "medicalAssistants", { headers: authHeader() });
    }
}

export default new AdminService();