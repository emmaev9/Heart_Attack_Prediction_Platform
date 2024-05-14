<template>
  <div class="content-of-page">
    <div>
      <h1 class="prediction-title">Heart Disease Risk Analyzer</h1>
      <p  v-if="!loading && !result" class="prediction-description">Click the button below to start the analysis</p>
    </div>
    <div v-if="loading" >
      <VueSpinnerBall size="160" color="red" />
    </div>
    <div v-else>
      <div v-if="analysisComplete">
        Prediction Result: {{ result }}
      </div>
    </div>
    <div>
      <button class="button-34" v-if="!analysisStarted" @click="startAnalysis">Start Analysis</button>
    </div>
  </div>
</template>
<script>
import {VueSpinnerBall} from 'vue3-spinners';
import PatientService from '@/services/patient.service';
import VisitService from '@/services/visit.service';
import PredictionService from '@/services/prediction.service';
export default {
  

  name: 'DocPatientPrediction',
  components: {
    VueSpinnerBall
  },

  data() {
    return {
      loading: false,
      analysisComplete: false,
      result: null,
      analysisStarted: false,
      visit: {},
      patient: {},
      predictionInput:{
        visitId: null,
        patientId: this.id,
        restecg: '',
        oldpeak: '',
        thalach: '',
        slope: '',
        exang: '',
        thal: '',
        fbs: '',
        chol: '',
        ca: '',
        age: '',
        gender: '',
        cp: '',
        trestbps: '',
      }
    };

  },
  props: {
    id: {
      type: String,
      required: true
    },
    visitId: {
      type: String,
      required: true
    }
  },
  watch: {
    visitId(newVal, oldVal) {
      if (newVal !== oldVal) {
        this.predictionInput.visitId = newVal;
        this.getSelectedVisit();
      }
    }
  },
  methods: {
    getPatient() {
      console.log('Getting patient with ID:', this.id)
      PatientService.getPatient(this.id)
        .then(response => {
          console.log("Patient data: ");
          console.log(response.data);
          this.predictionInput.age = this.calculateAge(response.data.birthday);
          console.log("Age: " + this.predictionInput.age);
          this.predictionInput.gender = response.data.gender;
        })
        .catch(error => {
          console.error(error);
        });
    },
    
    getSelectedVisit(){
      console.log("Se ia visita selectata cu id: " + this.visitId)
      VisitService.getVisit(this.visitId)
        .then(response => {
          console.log("Visit data: ");
          console.log(response.data);
          this.predictionInput.cp = response.data.chestPainType;
          this.predictionInput.trestbps = response.data.trestbps;
          console.log("Trestbps: " + this.predictionInput.trestbps);
          VisitService.getCardiacEvaluation(this.visitId)
          .then(response2 => {
            console.log("Cardiac evaluation data: ");
            console.log(response2.data);
            this.predictionInput.chol = response2.data.chol;
            this.predictionInput.fbs = response2.data.fbs;
            this.predictionInput.ca = response2.data.ca;
            this.predictionInput.thal = response2.data.thal;
            this.predictionInput.restecg = response2.data.restecg;
            this.predictionInput.oldpeak = response2.data.oldpeak;
            this.predictionInput.thalach = response2.data.thalach;
            this.predictionInput.slope = response2.data.slope;
            this.predictionInput.exang = response2.data.exang;

          })
        })
        .catch(error => {
          console.error(error);
        });     
    },
    startAnalysis() {
      this.loading = true;
      this.analysisStarted = true;

      PredictionService.getPrediction(this.predictionInput)
        .then(response => {
          console.log(response.data);
          setTimeout(() => {
            if(response.data == '0'){
              this.result = 'Low risk of developing a heart disease.';
            }
            else{
              this.result = 'High risk of developing a heart disease.';
            }
          
            this.analysisComplete = true;
            this.loading = false;
          }, 3000); // Simulate analysis time
          
        })
        .catch(error => {
          console.error(error);
          this.loading = false;
        });

     // this.analyzeData(); // simulate analysis
    },
    analyzeData() {
      setTimeout(() => {
        this.result = 'High Risk'; // Mock result
        this.analysisComplete = true;
        this.loading = false;
      }, 10000); // Simulate analysis time
    },
    calculateAge(birthday) {
      const birthDate = new Date(birthday);
      const now = new Date();
      let years = now.getFullYear() - birthDate.getFullYear();
      let months = now.getMonth() - birthDate.getMonth();
      let days = now.getDate() - birthDate.getDate();
      if (months < 0 || (months === 0 && days < 0)) {
        years--;
        months += 12;
      }
      if (days < 0) {
        months--;
        days += new Date(now.getFullYear(), now.getMonth(), 0).getDate();
      }
      return years;
    }
  },
  mounted(){
    this.getPatient();
    if (this.visitId) {
      this.predictionInput.visitId = this.visitId;
      this.getSelectedVisit();
  }
  }
}
</script>
<style scoped>
  .content-of-page {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin-top: 50px;
    margin-bottom: 50px;
    color:black;
  }
  .prediction-title {
    font-size: 30px;
    font-weight: bold;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    margin-bottom: 30px;
    color: #448ab0
  }
  .prediction-description {
    font-size: 15px;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    color:  #92aebd
  }
 /* button {
    padding: 10px 20px;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }
  button:hover {
    background-color: #45a049;
  }*/
</style>