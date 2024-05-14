import axios from 'axios';
//import authHeader from './auth-header';

const API_URL_FAMILY_HISTORY = 'http://localhost:8081/familyHistory';

class FamilyHistoryService{
    saveEntry(entry){
      console.log("Se salveaza istoricul familiei pacientului: " + entry.patientId);
      console.log(entry);
      return axios.post(API_URL_FAMILY_HISTORY, entry);
    }
    getFamilyHistory(id){
      console.log("Se cauta istoricul familiei pacientului cu id: " + id);
      return axios.get(`${API_URL_FAMILY_HISTORY}/${id}`);
    }
    deleteFamilyHistory(id){
      return axios.delete(`${API_URL_FAMILY_HISTORY}/${id}`);
    }
    updateFamilyHistory(entry){
      return axios.put(API_URL_FAMILY_HISTORY, entry);
    }
}
export default new FamilyHistoryService();