import axios from 'axios';
//import authHeader from './auth-header';

const API_URL_PATIENT = 'http://localhost:8081/patient';

class PatientService{
    registerPatient(patient){
      console.log(patient);
      return axios.post(API_URL_PATIENT, patient);
    }

    updatePatient(patient){
      return axios.put(API_URL_PATIENT+"/update" , patient);
    }

    deletePatient(id){
      console.log("Se sterge pacientul cu id: " + id);
      return axios.delete(`${API_URL_PATIENT}/${id}`);
    }

    getPatient(id){
      console.log("Se cauta pacientul cu id: " + id);
      return axios.get(`${API_URL_PATIENT}/${id}`);
    }

    async asyncGetPatient(id){
      try{
        const response = await axios.get(`${API_URL_PATIENT}/${id}`);
        return response;
      }
      catch(error){
        console.log(error);
      }
    }

    getAllPatients(){
      return axios.get(API_URL_PATIENT + "/patients");
    }
}
export default new PatientService();