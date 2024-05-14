import axios from 'axios';
//import authHeader from './auth-header';

const API_URL_DOCTOR = 'http://localhost:8081/doctor';

class DoctorService{
    
    updatePatientsDoctor(doctorId, patientId){
      return axios.put(`${API_URL_DOCTOR}/${patientId}/${doctorId}`);
    }
    getDoctorOfPatient(patientId){
      return axios.get(`${API_URL_DOCTOR}/getByPatient/${patientId}`);
    }
    getAllPatientsOfDoctor(id){
      return axios.get(`${API_URL_DOCTOR}/allPatientsOfDoctor/${id}`);
    }
}
export default new DoctorService();