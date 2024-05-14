<template>
  <div class="tests-content">
    <div class="upload-form-label">Evaluation</div>
    <hr style="margin:0; margin-bottom:15px; width:100%;color: #5a869e;  "/>
    <div class="inputs">
      <div class="inputs-row">
        <label for="fastingBloodSugar" style="margin-right:57px;" class="inputs-row-label">Fasting Blood Sugar</label>
        <Input id="fastingBloodSugar" class="inputs-row-input" type="text" v-model="fastingBloodSugar"  style="height:35px; width: 114px;" v-bind:disabled="!isEditable" />
        <div class="row-measure">mg/dl</div>
      </div>

      <div class="inputs-row">
        <label for="serumCholosterolLevel" style="margin-right:57px;" class="inputs-row-label">Serum Cholesterol Level</label>
        <Input id="serulCholesterolLevel" class="inputs-row-input" type="text" v-model="fastingBloodSugar"  style="height:35px; width: 114px;" v-bind:disabled="!isEditable" />
        <div class="row-measure">mg/dl</div>
      </div>
    
      <div class="inputs-row">
        <label for="thallasemia" style="margin-right:114px;" class="inputs-row-label">Thalassemia</label>
        <Dropdown id="thalassemia" class="inputs-row-input" v-model="thalassemia" :options ="this.thalassemiaOptions" optionLabel="label" placeholder="Select" required v-bind:disabled="!isEditable" style="height:40px;" />
      </div>
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
  
  name: 'DocVisitDetails',

  data() {
    return {
      patient: {},
      isEditable: true,
      visit: {},
      thalassemia: '',
      fastingBloodSugar: '',
      serumCholesterolLevel: '',
      thalassemiaOptions: [
        { label: 'Normal', value: 'normal' },
        { label: 'Fixed Defect(No blood flow in some part of the heart)', value: 'fixedDefect' },
        { label: 'Reversable Defect (A blood flow is observed but it is not normal)', value: 'reversableDefect' }
      ],
      medicalEvaluation:{
          visitId: '',
          trestbps: '',
          chol: '',
          fbs: '',
          ca: ''
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
        this.getSelectedVisit();
        this.getCardiacEvaluation();
      }
    },
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
    getMedicalEvaluation(){
      VisitService.getCardiacEvaluation(this.visitId)
        .then(response => {
          console.log(response.data);
          if(response.data.oldpeak == null || response.data.thalach == null || response.data.slope == null || response.data.exang == null || response.data.restecg == null){
            this.isEditable=true;
          }
          else{
            this.isEditable=false;
          }
        
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
    this.getMedicalEvaluation();
  }

  
}
</script>

<style scoped>
.tests-contet{
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  margin-top: 20px;
  margin-left: 20px;
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
</style>