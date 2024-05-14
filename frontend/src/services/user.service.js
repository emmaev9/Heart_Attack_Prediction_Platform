import axios from 'axios';
import authHeader from './auth-header';

const API_URL_ADMIN = 'http://localhost:8080/admin/';
const API_URL_MEDICAL_ASSISTANT = 'http://localhost:8080/medicalAssistant/';
const API_URL_DOCTOR = 'http://localhost:8080/doctor/';

class UserService {
  getAdminHome() {
    return axios.get(API_URL_ADMIN + 'adminHome', { headers: authHeader() });
  }
  getDoctorHome() {
    return axios.get(API_URL_DOCTOR + 'doctorHome', { headers: authHeader() });
  }
  getMedicalAssistantHome() {
    return axios.get(API_URL_MEDICAL_ASSISTANT + 'medicalAssistantHome', { headers: authHeader() });
  }
}

export default new UserService();