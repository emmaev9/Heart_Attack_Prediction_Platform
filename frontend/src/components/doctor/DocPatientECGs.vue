<template>
  
  <div class="form-row-2">
    <div class="upload-form-label">Uploaded medical files</div>
   
    <table class="file-upload-table">
      
      <tr>
        <td>ECG Before Exercise</td>
        <td>
          <div v-if="visit.beforeExerciseECGUrl" class="file-link">
            <a :href="getFullPath(visit.beforeExerciseECGUrl)" target="_blank">View Document</a>
          </div>
          <div v-else>
            No file uploaded
          </div>
        </td>
      </tr>
      <tr>
        <td>ECG During Exercise on Stationary Bike</td>
        <td>
          <div v-if="visit.duringExerciseECGUrl" class="file-link">
            <a :href="getFullPath(visit.duringExerciseECGUrl)" target="_blank">View Document</a>
          </div>
          <div v-else>
            No file uploaded
          </div>
        </td>
      </tr>
      <tr>
        <td>Blood Test</td>
        <td>
          <div v-if="visit.bloodTestPdfUrl" class="file-link">
            <a :href="getFullPath(visit.bloodTestPdfUrl)" target="_blank">View Document</a>
          </div>
          <div v-else>
            No file uploaded
          </div>
        </td>
      </tr>
      <tr>
        <td>Coronary Angiography</td>
        <td>
          <div v-if="visit.coronaryAngiographyUrl" class="file-link">
            <a :href="getFullPath(visit.coronaryAngiographyUrl)" target="_blank">View Document</a>
          </div>
          <div v-else>
            No file uploaded
          </div>
        </td>
      </tr>
      <tr>
        <td>ECG After Exercise</td>
        <td>
          <div v-if="visit.afterExerciseECGUrl" class="file-link">
            <a :href="getFullPath(visit.afterExerciseECGUrl)" target="_blank">View Document</a>
          </div>
          <div v-else>
            No file uploaded
          </div>
        </td>
      </tr>
    </table>
    <div class="upload-form-label">ECGs Evaluation</div>
    <hr style="margin:0; margin-bottom:15px; width:100%;color: #5a869e;  "/>
    <div class="inputs">
      <div class="inputs-row">
        <label for="stDepression" style="margin-right:57px;" class="inputs-row-label">Exercise-Induced ST Depression</label>
        <Input id="stDepression" class="inputs-row-input" type="text" v-model="cardiacEvaluation.oldpeak"  style="height:35px; width: 114px;" v-bind:disabled="!isEditable" />
        <div class="row-measure">mm</div>
      </div>
      <div class="inputs-row">
        <label for="maxHeartRate" style="margin-right:79px;" class="inputs-row-label">Maximum Heart Rate Achieved
        </label>
        <Input id="maxHeartRate" class="inputs-row-input" type="text" v-model="cardiacEvaluation.thalach"  style="height:35px; width: 114px;" v-bind:disabled="!isEditable" />
        <div class="row-measure">bpm</div>
      </div>
      <div class="inputs-row">
        <label for="restingEcg" style="margin-right:198px;" class="inputs-row-label">Resting ECG</label>
        <Dropdown id="restingEcg" class="inputs-row-input" v-model="cardiacEvaluation.restecg" :options ="this.restingEcgOptions" optionLabel="label" placeholder="Select" required v-bind:disabled="!isEditable" style="height:40px;" />
      </div>
      <div class="inputs-row">
        <label for="stSegmentSlope" style="margin-right:157px;" class="inputs-row-label">ST Segment Slope</label>
        <Dropdown id="stSegmentSlope" class="inputs-row-input" v-model="cardiacEvaluation.slope" :options ="this.stSegmentSlopeOptions" optionLabel="label" placeholder="Select" required v-bind:disabled="!isEditable" style="height:40px;" />
      </div>
      <div class="inputs-row">
        <label for="exerciseInducedAngina" style="margin-right:114px;" class="inputs-row-label">Exercise-Induced Angina</label>
        <Dropdown id="exerciseInducedAngina" class="inputs-row-input" v-model="cardiacEvaluation.exang" :options ="this.exerciseInducedAnginaOptions" optionLabel="label" placeholder="Select" required v-bind:disabled="!isEditable" style="height:40px;" />
      </div>
    </div>
    <br>
    <br>

    <div class="upload-form-label">Blood Test Evaluation</div>
    <hr style="margin:0; margin-bottom:15px; width:100%;color: #5a869e;  "/>
    <div class="inputs">
      <div class="inputs-row">
        <label for="fastingBloodSugar" style="margin-right:152px;" class="inputs-row-label">Fasting Blood Sugar</label>
        <Input id="fastingBloodSugar" class="inputs-row-input" type="text" v-model="cardiacEvaluation.fbs"  style="height:35px; width: 114px;" v-bind:disabled="!isEditable" />
        <div class="row-measure">mg/dl</div>
      </div>
      <div class="inputs-row">
        <label for="serumCholesterol" style="margin-right:164px;" class="inputs-row-label">Serum Cholesterol 
        </label>
        <Input id="serumCholesterol" class="inputs-row-input" type="text" v-model="cardiacEvaluation.chol"  style="height:35px; width: 114px;" v-bind:disabled="!isEditable" />
        <div class="row-measure">mg/dl</div>
      </div>
      <div class="inputs-row">
        <label for="thalassemia" style="margin-right:203px;" class="inputs-row-label">Thalassemia</label>
        <Dropdown id="thalassemia" class="inputs-row-input" v-model="cardiacEvaluation.thal" :options ="this.thalassemiaOptions" optionLabel="label" placeholder="Select" required v-bind:disabled="!isEditable" style="height:40px;" />
      </div>
    </div>

    <br>
    <br>
    <div class="upload-form-label">Coronary Angiography Evaluation</div>
    <hr style="margin:0; margin-bottom:15px; width:100%;color: #5a869e;  "/>
    <div class="inputs">
        <div class="inputs-row">
          <label for="ca" style="margin-right:118px;" class="inputs-row-label">Number of Major Vassels</label>
          <Dropdown id="ca" class="inputs-row-input" v-model="cardiacEvaluation.ca" :options ="this.caOptions" optionLabel="label" placeholder="Select" required v-bind:disabled="!isEditable" style="height:40px;" />
        </div>
    </div>
    <div v-if="errors.hasErrors" class="error-message">
      {{ errors.message }}
    </div>
    <div class="button-container">
      <Button v-if="isEditable" label="Save"  icon="pi pi-check" type="submit" @click="this.saveEvaluation()" class="button-34" />
      <Button v-else-if="!isEditable" label="Modify" icon="pi pi-pencil" type="submit" @click="this.editEvaluation()" class="button-34" />
    </div> 
    
  </div>
</template>

<script>
import patientService from '@/services/patient.service';
import VisitService from '@/services/visit.service';
import Button from 'primevue/button';
import Input from 'primevue/inputtext';
import Dropdown from 'primevue/dropdown';
export default {
  name: 'DocPatientECGs',

  components: {
    Button,
    Input,
    Dropdown
  },

  data() {
    return {
      patient: {},
      isEditable: true,
      errors: {
        message: '',
        hasErrors: false
      },
      restingEcgOptions: [
        {value: '0', label: 'Normal'},
        {value: '1', label: 'Having ST-T wave abnormality'},
        {value: '2', label: 'Showing probable or definite left ventricular hypertrophy'}
      ],
      stSegmentSlopeOptions: [
        {value: '1', label: 'Up sloping'},
        {value: '2', label: 'Flat'},
        {value: '3', label: 'Down sloping'}
      ],
      exerciseInducedAnginaOptions: [
        {value: '0', label: 'No'},
        {value: '1', label: 'Yes'}
      ],
      thalassemiaOptions: [
        {value: '0', label: 'Normal'},
        {value: '1', label: 'Fixed defect (no blood flow in some part of the heart)'},
        {value: '2', label: 'Reversible defect (a blood flow is observed but it is not normal)'}
      ],
      caOptions: [
        {value: '0', label: '0'},
        {value: '1', label: '1'},
        {value: '2', label: '2'},
        {value: '3', label: '3'}
      ],
      cardiacEvaluation:{
        visitId: this.visitId || '',
        restecg: '',
        oldpeak: '',
        thalach: '',
        slope: '',
        exang: '',
        thal: '',
        fbs: '',
        chol: '',
        ca: ''
      },

      visit: {
        visitId: '',
        visitDate: '',
        doctorId: '',
        patientId: '',
        visitReason: '',
        chestPainType: '',
        bloodTestPdfUrl: null,
        coronaryAngiographyUrl: null,
        beforeExerciseECGUrl: null,
        duringExerciseECGUrl: null,
        afterExerciseECGUrl: null
      },
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
        this.cardiacEvaluation.visitId = newVal;
        this.getSelectedVisit();
        this.getCardiacEvaluation();
      }
    }
  },
  methods: {
    getPatient() {
      console.log('Getting patient with ID:', this.id)
      patientService.getPatient(this.id)
        .then(response => {
          this.patient = response.data;
        })
        .catch(error => {
          console.error(error);
        });
    },
    saveEvaluation(){
      console.log("Saving evaluation")
      this.isEditable = false;
      if(this.cardiacEvaluation.oldpeak == '' || this.cardiacEvaluation.thalach == '' || this.cardiacEvaluation.restecg == '' || this.cardiacEvaluation.slope == '' || this.cardiacEvaluation.exang == '' || this.cardiacEvaluation.thal == '' || this.cardiacEvaluation.fbs == '' || this.cardiacEvaluation.chol == '' || this.cardiacEvaluation.ca == ''){
        this.errors.message = 'Please complete all the fields!';
        this.errors.hasErrors = true;
        this.isEditable=true;
        return;
      }
      let evaluationToSave = {
        visitId: this.visitId,
        restecg: this.cardiacEvaluation.restecg.label,
        oldpeak: this.cardiacEvaluation.oldpeak,
        thalach: this.cardiacEvaluation.thalach,
        slope: this.cardiacEvaluation.slope.label,
        exang: this.cardiacEvaluation.exang.label,
        thal: this.cardiacEvaluation.thal.label,
        fbs: this.cardiacEvaluation.fbs,
        chol: this.cardiacEvaluation.chol,
        ca: this.cardiacEvaluation.ca.label
      };
      this.errors.message = '';
      this.errors.hasErrors = false;
      console.log("Cardial evaluation to be savedL ");
      console.log(evaluationToSave);


      VisitService.updateCardiacEvaluation(evaluationToSave)
        .then(response => {
          console.log(response.data);
        })
        .catch(error => {
          console.error(error);
        }); 
    },
    
    editEvaluation(){
      this.isEditable = true;
    },

    getSelectedVisit(){
      console.log("Se ia visita selectata cu id: " + this.visitId)
      VisitService.getVisit(this.visitId)
        .then(response => {
          console.log(response.data);
          this.visit = response.data;
        })
        .catch(error => {
          console.error(error);
        });     
    },
  
    getCardiacEvaluation(){
      VisitService.getCardiacEvaluation(this.visitId)
        .then(response => {
          console.log("Got cardiac evaluation")
          console.log(response.data);
          if(response.data.oldpeak == null || response.data.thalach == null || response.data.slope == null || response.data.exang == null || response.data.restecg == null || response.data.thal == null || response.data.fbs == null || response.data.chol == null || response.data.ca == null){
            this.isEditable=true;
            return;
          }
          else{
            this.isEditable=false;
          }
          this.cardiacEvaluation = response.data;
          this.cardiacEvaluation.restecg = this.restingEcgOptions.find(option => option.label === response.data.restecg);
          this.cardiacEvaluation.slope = this.stSegmentSlopeOptions.find(option => option.label === response.data.slope);
          this.cardiacEvaluation.exang = this.exerciseInducedAnginaOptions.find(option => option.label === response.data.exang);
          this.cardiacEvaluation.thal = this.thalassemiaOptions.find(option => option.label === response.data.thal);
          this.cardiacEvaluation.ca = this.caOptions.find(option => option.label   === response.data.ca);


        })
        .catch(error => {
          console.error(error);
        });
    },

    getFullPath(url){
      const baseUrl = 'http://localhost:8081/files';
      let path = url.split("\\");
      url = path[path.length - 1];
      return `${baseUrl}/${encodeURIComponent(url)}`;
    }
  },
  mounted() {
    this.getPatient();
    this.getSelectedVisit();
    this.getCardiacEvaluation();
  }
}

</script>
<style scoped>
.file-link a {
  color: #5a869e;
  font-weight: bold;
  text-decoration: none;
}
.file-link a:hover {
  text-decoration: underline;
}
.upload-form-label {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  color: #448ab0;
  font-weight: 500;
  margin-bottom: 5px;
  font-size: 18px;
  margin-left: 10px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}
.form-row-2{
  margin-top: 30px;
  margin-right: 70px;
  margin-left: 70px;
  display: flex;
  flex-direction: column;
  color:black;
}

.file-upload-table {
  width: 100%;
  border-collapse: collapse;
  box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.1);
  margin-top: 10px;
  margin-left:10px;
  margin-bottom: 25px;
  background-color: #f5f5f5
}

.file-upload-table td {
  padding: 8px;
  border: 1px solid #ddd;
}

.file-upload-table td:first-child {
  width: 50%; 
  text-align: left;
}

.file-upload-table td:last-child {
  width: 50%; 
  text-align: center;
}

.file-upload-row input[type="file"] {
  width: auto; 
  margin-top: 0; 
}

.form-label,
.upload-label {
  font-size: 0.9rem;
  color: #8b8b8b;
  margin-bottom: 0.5rem; 
}
.inputs{
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  margin-left: 15px;

}
.inputs-row{
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  margin-bottom: 10px;
}
.inputs-row-label{
  margin-top: 5px;
  margin-left: 20px;
}
.row-measure{
  margin-top: 5px;
  margin-left: 5px;
  color: #8b8b8b;
}

.button-container {
  display: flex;
  justify-content: center;

  margin-top: 40px;
  margin-bottom: 30px;
}
.button-34 {
  background: #25b27e;
  border-radius: 120px;
  box-shadow: #99a0a6 0 10px 20px -10px;
  box-sizing: border-box;
  color: #FFFFFF;
  cursor: pointer;
  font-size: 16px;
  font-weight: 500;
  line-height: 20px;
  opacity: 1;
  outline: 0 solid transparent;
  padding: 8px 18px;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  width: fit-content;
  word-break: break-word;
  border: 0;
  text-decoration: none;
  min-width: 180px
}

.button-34:hover {
  background-color: #5dd7a2;
}
.error-message {
  background-color: #feeeee;
  border: 1px solid #fa4646;
  border-radius: 5px;
  padding: 7px;
  color:  #fa4646;; /* Red color for errors */
  font-size: 1em;
  margin-top: 10px;
  margin-bottom: 10px;
  text-align: center;
  margin-left: 30px;
  margin-right: 30px;
}

</style>