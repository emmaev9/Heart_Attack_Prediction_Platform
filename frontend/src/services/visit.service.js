import axios from 'axios';
//import authHeader from './auth-header';

const API_URL_VISIT = 'http://localhost:8081/visit';
const API_URL_EVALUATION = 'http://localhost:8081/evaluation';

class VisitService{
    saveVisit(visit){
      console.log(visit);
      return axios.post(API_URL_VISIT, visit);
    }
    
    handlePdf(formData){
      return axios.post(API_URL_VISIT+ "/upload", formData, {
        headers: { 'Content-Type': 'multipart/form-data' },
      });
    }

    getPatientVisits(id){
      return axios.get(`${API_URL_VISIT}/patient/${id}`);
    }

    getVisit(id){
      return axios.get(`${API_URL_VISIT}/visit/${id}`);
    }

    updateVisit(visit){
      return axios.put(API_URL_VISIT, visit);
    }

    getPatientLatestVisit(id){
      return axios.get(`${API_URL_VISIT}/latestVisit/${id}`);
    }

    getDoctors(){
      return axios.get(`${API_URL_VISIT}/doctors`);
    }

    updateCardiacEvaluation(cardiacEvaluation){
      return axios.put(`${API_URL_EVALUATION}/cardiacEvaluation`, cardiacEvaluation);
    }
    getCardiacEvaluation(id){
      return axios.get(`${API_URL_EVALUATION}/cardiacEvaluation/${id}`);
    }

}
export default new VisitService();