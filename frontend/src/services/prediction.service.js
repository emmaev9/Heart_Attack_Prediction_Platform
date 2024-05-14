import axios from 'axios';

const API_URL_PREDICTION= 'http://localhost:8084/prediction';

class PredictionService{
   getPrediction(predictionInput){
      console.log(predictionInput);
      return axios.post(API_URL_PREDICTION + "/predict", predictionInput);
    }
}
export default new PredictionService();