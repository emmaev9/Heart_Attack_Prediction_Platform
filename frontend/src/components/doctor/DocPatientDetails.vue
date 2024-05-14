<template>
  <div class="lifestyle-medical-form">
    <div v-if="this.isEditable">
      <div class="prediction-description">Lifestyle factors and relevand medical history about the patient were not completed yet.</div>
    </div>
    <form v-else @submit.prevent="isEditable ? saveChanges() : modify()">
      <div class="label-hr">Lifestyle factors</div>
      <hr class="section-divider" />
      <!-- Smoking Status -->
      <div class="form-row-1">
        <label class="radio-buttons-label">Smoking status:</label>
        <div class="radio-group">
          <div class="radio">
            <input type="radio" id="smoker" name="smokingStatus" value="Smoker" v-model="formData.smokingStatus"  v-bind:disabled="!isEditable" />
            <label for="smoker">Smoker</label>
          </div>
          <div class="radio">
            <input type="radio" id="non-smoker" name="smokingStatus" value="Non smoker" v-model="formData.smokingStatus" v-bind:disabled="!isEditable" />
            <label for="non-smoker">Non-smoker</label>
          </div>
          <div class="radio">
            <input type="radio" id="ex-smoker" name="smokingStatus" value="Ex smoker" v-model="formData.smokingStatus" v-bind:disabled="!isEditable" />
            <label for="ex-smoker">Ex-smoker</label>
          </div>
        </div>
      </div>

      <div class="form-row-1">
        <label class="radio-buttons-label">Alcohol consumption: </label>
        <div class="radio-group">
          <div class="radio">
            <input type="radio" id="non-drinker" name="drinkingStatus" value="Non Drinker" v-model="formData.alcoholConsumption" v-bind:disabled="!isEditable" />
            <label for="non-drinker">Non-drinker</label>
          </div>
          <div class="radio">
            <input type="radio" id="social-drinker" name="drinkingStatus" value="Social Drinker" v-model="formData.alcoholConsumption" v-bind:disabled="!isEditable" />
            <label for="social-drinker">Social drinker</label>
          </div>
          <div class="radio">
            <input type="radio" id="regular-driker" name="drinkingStatus" value="Regular Drinker" v-model="formData.alcoholConsumption" v-bind:disabled="!isEditable"/>
            <label for="regular-drinker">Regular drinker</label>
          </div>
          <div class="radio">
            <input type="radio" id="heavy-drinker" name="drinkingStatus" value="Heavy Drinker" v-model="formData.alcoholConsumption" v-bind:disabled="!isEditable" />
            <label for="heavy-drinker">Heavy drinker</label>
          </div>
        </div>
      </div>

      <div class="form-row-1">
        <label class="radio-buttons-label">Physical activity level: </label>
        <div class="radio-group">
          <div class="radio">
            <input type="radio" id="sedentary" name="activityLevel" value="Sedentary" v-model="formData.physicalActivityLevel" v-bind:disabled="!isEditable" />
            <label for="sedentary">Sedentary</label>
          </div>
          <div class="radio">
            <input type="radio" id="lightly-active" name="activityLevel" value="Lightly Active"  v-model="formData.physicalActivityLevel" v-bind:disabled="!isEditable" />
            <label for="lightly-active">Lightly active</label>
          </div>
          <div class="radio">
            <input type="radio" id="moderately-active" name="activityLevel" value="Moderately Active"  v-model="formData.physicalActivityLevel" v-bind:disabled="!isEditable" />
            <label for="moderately-active">Moderately active</label>
          </div>
          <div class="radio">
            <input type="radio" id="very-active" name="activityLevel" value="Very Active"  v-model="formData.physicalActivityLevel" v-bind:disabled="!isEditable" />
            <label for="very-active">Very active</label>
          </div>
        </div>
      </div>

      <div class="form-row-1">
        <label class="radio-buttons-label">Stress levels: </label>
        <div class="radio-group">
          <div class="radio">
            <input type="radio" id="minimal" name="stressLevel" value="Minimal" v-model="formData.stressLevel" v-bind:disabled="!isEditable" />
            <label for="minimal">Minimal</label>
          </div>
          <div class="radio">
            <input type="radio" id="manageable" name="stressLevel" value="Manageable" v-model="formData.stressLevel" v-bind:disabled="!isEditable" />
            <label for="manageable">Manageable</label>
          </div>
          <div class="radio">
            <input type="radio" id="high" name="stressLevel" value="High" v-model="formData.stressLevel" v-bind:disabled="!isEditable" />
            <label for="high">High</label>
          </div>
          <div class="radio">
            <input type="radio" id="very-high" name="stressLevel" value="Very High" v-model="formData.stressLevel" v-bind:disabled="!isEditable" />
            <label for="very-high">Very high</label>
          </div>
        </div>
      </div>

      <div class="form-row-1">
        <label class="radio-buttons-label">Body Mass Index: </label>
        <div class="radio-group">
          <div class="radio">
            <input type="radio" id="Underweight" name="bodyMass" value="Underweight" v-model="formData.bodyMassIndex"  v-bind:disabled="!isEditable" />
            <label for="underweigh">Underweigh</label>
          </div>
          <div class="radio">
            <input type="radio" id="normal-weight" name="bodyMass" value="Normal Weight" v-model="formData.bodyMassIndex"  v-bind:disabled="!isEditable" />
            <label for="normal-weight">Normal weight</label>
          </div>
          <div class="radio">
            <input type="radio" id="overweight" name="bodyMass" value="Overweight" v-model="formData.bodyMassIndex"  v-bind:disabled="!isEditable" />
            <label for="overweight">Overweight</label>
          </div>
          <div class="radio">
            <input type="radio" id="obese" name="bodyMass" value="Obese" v-model="formData.bodyMassIndex"  v-bind:disabled="!isEditable" />
            <label for="obese">Obese</label>
          </div>
        </div>
      </div>

      <div class="form-row-1">
        <label class="radio-buttons-label">Diabetes: </label>
        <div class="radio-group">
          <div class="radio">
            <input type="radio" id="no-diabetes" name="diabetes" value="No-Diabetes" v-model="formData.diabetes" v-bind:disabled="!isEditable" />
            <label for="no-diabetes">No diabetes</label>
          </div>
          <div class="radio">
            <input type="radio" id="prediabetes" name="diabetes" value="prediabetes" v-model="formData.diabetes" v-bind:disabled="!isEditable"/>
            <label for="prediabetes">Prediabetes</label>
          </div>
          <div class="radio">
            <input type="radio" id="type-1" name="diabetes" value="Type 1 diabetes" v-model="formData.diabetes" v-bind:disabled="!isEditable"/>
            <label for="type-1">Type 1 diabetes</label>
          </div>
          <div class="radio">
            <input type="radio" id="type-2" name="diabetes" value="Type 2 diabetes" v-model="formData.diabetes" v-bind:disabled="!isEditable"/>
            <label for="type-2">Type 2 diabetes</label>
          </div>
        </div>
      </div>
      <br>
      <br>
      <div class="label-hr">Relevant medical history</div>
      <hr class="section-divider" />

      <div class="form-row-1">
        <label class="radio-buttons-label">History of heart disease: </label>
        <div class="radio-group">
          <div class="radio">
            <input type="radio" id="no" name="heartDisease" value="No history of heart disease" v-model="formData.historyOfHD" v-bind:disabled="!isEditable" />
            <label for="no">No history of heart disease</label>
          </div>
          <div class="radio">
            <input type="radio" id="atrialFibrillation" name="heartDisease" value="Atrial fibrillation" v-model="formData.historyOfHD" v-bind:disabled="!isEditable" />
            <label for="atrialFibrillation">Atrial fibrillation</label>
          </div>
          <div class="radio">
            <input type="radio" id="hf" name="heartDisease" value="Heart Failure" v-model="formData.historyOfHD" v-bind:disabled="!isEditable"/>
            <label for="hf">Heart failure</label>
          </div>
          <div class="radio">
            <input type="radio" id="other" name="heartDisease" value="Other heart disease" v-model="formData.historyOfHD" v-bind:disabled="!isEditable" />
            <label for="other">Other heart disease</label>
            <input  style="margin-left:10px" type="text" placeholder="Please specify" v-model="otherHeartDiseaseDetails" v-bind:disabled="!isEditable" />
          </div>
        </div>
      </div>

      <div class="form-row-1">
        <label class="radio-buttons-label">History of heart attack or stroke: </label>
        <div class="radio-group">
          <div class="radio">
            <input type="radio" id="no" name="heartAttack" value="No previous heart attack or stroke" v-model="formData.historyOfHA" v-bind:disabled="!isEditable"/>
            <label for="no">No previous heart attack or stroke</label>
          </div>
          <div class="radio">
            <input type="radio" id="hadHA" name="heartAttack" value="Had heart attack" v-model="formData.historyOfHA" v-bind:disabled="!isEditable"/>
            <label for="hadHA">Had a heart attack</label>
          </div>
          <div class="radio">
            <input type="radio" id="hadS" name="heartAttack" value="Had a stroke" v-model="formData.historyOfHA" v-bind:disabled="!isEditable"/>
            <label for="hadS">Had a stroke</label>
          </div>
          <div class="radio">
            <input type="radio" id="both" name="heartAttack" value="Had both" v-model="formData.historyOfHA" v-bind:disabled="!isEditable"/>
            <label for="both">Had both</label>
          </div>
        </div>
      </div>

      <div class="form-row-1">
        <label class="radio-buttons-label">Other conditions: </label>
        <div class="radio-group">
          <div class="radio">
            <input type="radio" id="no" name="otherConditions" value="No other conditions" v-model="formData.otherCondition" v-bind:disabled="!isEditable"/>
            <label for="no">No other conditions</label>
          </div>
          <div class="radio">
            <input type="radio" id="kidneyDisease" name="otherConditions" value="Chronic kidney disease"  v-model="formData.otherCondition"  v-bind:disabled="!isEditable"/>
            <label for="kidneyDisease">Chronic kidney disease</label>
          </div>
          <div class="radio">
            <input type="radio" id="autoim" name="otherConditions" value="Autoimmune disease"  v-model="formData.otherCondition" v-bind:disabled="!isEditable"/>
            <label for="autoim">Autoimmune disease</label>
          </div>
          <div class="radio">
            <input type="radio" id="otherD" name="otherConditions" value="Other"  v-model="formData.otherCondition" v-bind:disabled="!isEditable"/>
            <label for="otherD">Other </label>
            <input style="margin-left:129px" type="text" placeholder="Please specify"  v-model="otherConditionOption"  v-bind:disabled="!isEditable"/>
          </div>
        </div>
      </div>
      <br>
      <br>
    </form>
  </div>
</template>

<script>

import PatientService from '@/services/patient.service';
import PatientDetailsService from '@/services/patient.details.service';
export default {
  name: 'PatientDetails',

  data() {
    return {
      patient: {},
      isEditable: true,
      otherHeartDiseaseDetails: '',
      otherConditionOption: '',
      formData: {
        id: null,
        patientId: null,
        smokingStatus: null,
        alcoholConsumption: null,
        physicalActivityLevel: null,
        stressLevel: null,
        bodyMassIndex: null,
        diabetes: null,
        historyOfHD: null,
        historyOfHA: null,
        otherCondition: null,
      },
    };
  },
  props: {
    id: {
      type: String,
      required: true
    },
  },
  methods: {
    fetchPatient() {
      return PatientService.getPatient(this.id)
        .then(response => {
          console.log(response.data);
          this.patient = response.data;
        })
        .catch(error => {
          console.error(error);
        });
    },
    isFirstEntry(){
      return this.formData==null || this.formData.patientId == null;
    },
    fetchPatientDetails() {
      console.log('Fetching patient details with id:' + this.id);
      return PatientDetailsService.getPatientDetails(this.id)
        .then(response => {
          console.log(response.data);
          if(response.data.message == "No data"){
            this.isEditable = true;
          }else{
            this.isEditable = false;
            this.formData = response.data;
            if(response.data.otherCondition !== "No other conditions" && response.data.otherCondition !== "Autoimmune disease" && response.data.otherCondition !== "Chronic kidney disease"){
              this.otherConditionOption = response.data.otherCondition;
              this.formData.otherCondition = "Other";
            }
           
            if(response.data.historyOfHD !== "No history of heart disease" && response.data.historyOfHD !== "Atrial fibrillation" && response.data.historyOfHD !== "Heart Failure"){
              this.otherHeartDiseaseDetails = response.data.historyOfHD;
              this.formData.historyOfHD = "Other heart disease";
            }
           
            
          }

        })
        .catch(error => {
          console.error(error);
        });
    },
  },
  mounted(){
    console.log('User ID:', this.id);
    this.fetchPatient().then(() => {
      this.fetchPatientDetails();
    });
    if(this.isFirstEntry()){
      this.isEditable = true;
    }
  }
}
</script>

<style scoped>
.label-hr {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  color: #448ab0;
  font-weight: 500;
  margin-bottom: 5px;
  font-size: 18px;
  margin-left: 30px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.section-divider {
  border: 0;
  height: 1px;
  background-image: linear-gradient(to right, rgba(0, 0, 0, 0.75), rgba(0, 0, 0, 0.75), rgba(0, 0, 0, 0));
  margin:0;
}

.lifestyle-medical-form {
  margin: 2rem auto;
  color:black;
  display:flex;
  flex-direction: column;
  align-items: flex-start;
  margin-left:70px;
}
.prediction-description {
  font-size: 15px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  color:  #92aebd
}

.button-34:hover {
  background-color: #5dd7a2; 
}

.radio-buttons-label{
  color: black;
  margin-left: 10px;
}

.form-row-1{
  margin-top: 30px;
  display: flex;
  flex-direction: row;
  align-items: flex-start; 
}
.label {
  display: block;
  margin-bottom: 5px;
  font-weight: 600;
}

.radio-group {
  display: flex;
  flex-direction: column;
  align-items: flex-start; 
}

.radio {
  margin-bottom: 3px;
  margin-left:20px;
}

.radio input[type="radio"] {
  margin-right: 10px;
}

.radio label {
  margin: 0; 
}

.button-34 {
  background: #25b27e;
  border-radius: 15px;
  box-shadow: #787d81 0 10px 20px -10px;
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
</style>