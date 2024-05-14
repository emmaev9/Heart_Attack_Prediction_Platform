import axios from 'axios';

const API_URL_PATIENT_DETAILS = 'http://localhost:8081/patientDetails';

class PatientDetailsService{
    saveChanges(patientDetails){
      console.log("Se salveaza detaliile pacientului: " + patientDetails.patientId);
      console.log(patientDetails );
      return axios.post(API_URL_PATIENT_DETAILS, patientDetails);
    }
    getPatientDetails(id){
      console.log("Se cauta detaliile pacientului cu id: " + id);
      return axios.get(`${API_URL_PATIENT_DETAILS}/${id}`);
    }
}
export default new PatientDetailsService();