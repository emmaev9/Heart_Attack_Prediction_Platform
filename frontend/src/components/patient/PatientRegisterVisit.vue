<template>
  <div class="visit-form">
  
    <div class="form-row-0">
      <label class="visit-date-label">Visit date</label>
      <Calendar id="visitDate" style="margin-left:48px; width:300px;" v-model="visit.visitDate" placeholder="Select visit date" showIcon :showOnFocus="false"  v-bind:disabled="!isEditableDate" class="visit-calendar" />
    </div>

    <div class="visit-reason-div">
      <label for="reason" class="visit-reason-label" style="margin-left:10px; margin-right: 40px; margin-top:6px;">Visit reason</label>
      <!--<Input id="raeson" class="visit-reason-input" type="text" v-model="visit.visitReason" v-bind:disabled="!isEditableReason" /> -->
      <Textarea style="border: 1px solid #ccc; width:600px; margin-bottom:3px;" rows="1" autoResize  v-model="visit.visitReason" v-bind:disabled="!isEditableReason" placeholder=""/>
    </div>

    
    <div class="form-row-0">
      <label class="visit-date-label">Doctor</label>
      <DropDown style="margin-left:138px;" id="doctor" v-model="selectedDoctorId.id" :options ="this.doctors" optionLabel="label"  option-value="id"  v-bind:disabled="!isEditable" placeholder="Select the doctor of the patient" required />

    </div>

 
    <div class="form-row-0">
      <label for="trestbps" style="margin-right:30px;" class="inputs-row-label">Resting blood preasure
      </label>
      <Input id="trestbps" class="inputs-row-input" type="text" v-model="visit.trestbps"  style="height:40px; width: 170px;" v-bind:disabled="!isEditableTrestbps" />
      <div class="row-measure">mm/hg</div>
    </div>

    <div class="form-row-1">
      <label class="radio-buttons-label">Type of chest pain:</label>
      <div class="radio-group">
        <div class="radio">
          <input type="radio" id="typical" name="chestPain" v-model="visit.chestPainType"  v-bind:disabled="!isEditable" value="Typical Angina"  />
          <label for="typical">Typical Angina</label>
        </div>
        <div class="radio">
          <input type="radio" id="atypical" name="chestPain" v-model="visit.chestPainType"  v-bind:disabled="!isEditable" value="Atypical Angina" />
          <label for="atypical">Atypical Angina</label>
        </div>
        <div class="radio">
          <input type="radio" id="non-anginal" name="chestPain" v-model="visit.chestPainType"  v-bind:disabled="!isEditable" value="Non-Anginal Pain" />
          <label for="non-anginal">Non-Anginal Pain</label>
        </div>
        <div class="radio">
          <input type="radio" id="asymptomatic" name="chestPain" v-model="visit.chestPainType"  v-bind:disabled="!isEditable" value="Asymptomatic" />
          <label for="asymptomatic">Asymptomatic</label>
        </div>
      </div>
    </div>



    <div class="form-row-2">
      <label class="upload-form-label">Upload Medical Documents</label>
      
      <table class="file-upload-table">
        <tr>
          <td>Blood Test Results</td>
          <td>
            <div v-if="!isEditable && visit.bloodTestPdfUrl" class="file-link">
              <a :href="getFullPath(visit.bloodTestPdfUrl)" target="_blank">View PDF</a>
            </div>
            <div v-else-if="!isEditable && !visit.bloodTestPdfUrl">
              No file uploaded
            </div>
            <div v-else>
              <FileUpload
                mode="basic"
                name="bloodTests"
                accept="image/*,application/pdf"
                :maxFileSize="1000000"
                v-bind:disabled="!isEditable"
                @select="handleFileUpload('bloodTestPdfUrl', $event)"
                class="custom-file-upload"
              />
            </div>
          </td>
        </tr>
        <tr>
          <td>Coronary Angiography</td>
          <td>
            <div v-if="!isEditable && visit.coronaryAngiographyUrl" class="file-link">
              <a :href="getFullPath(visit.coronaryAngiographyUrl)" target="_blank">View PDF</a>
            </div>
            <div v-else-if="!isEditable && !visit.coronaryAngiographyUrl">
              No file uploaded
            </div>
            <div v-else>
              <FileUpload
                mode="basic"
                name="angiography"
                accept="image/*, application/pdf"
                :maxFileSize="1000000"
                v-bind:disabled="!isEditable"
                @select="this.handleFileUpload('coronaryAngiographyUrl', $event)"
                class="custom-file-upload"
               />
            </div>
           
          </td>
        </tr>
        <tr>
          <td>ECG Before Exercise</td>
          <td>
            <div v-if="!isEditable && visit.beforeExerciseECGUrl" class="file-link">
              <a :href="getFullPath(visit.beforeExerciseECGUrl)" target="_blank">View PDF</a>
            </div>
            <div v-else-if="!isEditable && !visit.beforeExerciseECGUrl">
              No file uploaded
            </div>
            <div v-else>
            <FileUpload
              mode="basic"
              name="eckbefore"
              accept="image/*, application/pdf"
              :maxFileSize="1000000"
              v-bind:disabled="!isEditable"
              @select="handleFileUpload('beforeExerciseECGUrl', $event)"
              class="custom-file-upload"
            />
            </div>
          </td>
        </tr>
        <tr>
          <td>ECG During Exercise on Stationary Bike</td>
          <td>
            <div v-if="!isEditable && visit.duringExerciseECGUrl" class="file-link">
              <a :href="getFullPath(visit.duringExerciseECGUrl)" target="_blank">View PDF</a>
            </div>
            <div v-else-if="!isEditable && !visit.duringExerciseECGUrl">
              No file uploaded
            </div>
            <div v-else>
            <FileUpload
              mode="basic"
              name="eckduring"
              accept="image/*, application/pdf"
              :maxFileSize="1000000"
              v-bind:disabled="!isEditable"
              @select="handleFileUpload('duringExerciseECGUrl', $event)"
              class="custom-file-upload"
            />
            </div>
          </td>
        </tr>
        <tr>
          <td>ECG After Exercise</td>
          <td>
            <div v-if="!isEditable && visit.afterExerciseECGUrl" class="file-link">
              <a :href="getFullPath(visit.afterExerciseECGUrl)" target="_blank">View PDF</a>
            </div>
            <div v-else-if="!isEditable && !visit.afterExerciseECGUrl">
              No file uploaded
            </div>
            <div v-else>
            <FileUpload
              mode="basic"
              name="eckafter"
              accept="image/*, application/pdf"
              :maxFileSize="1000000"
              v-bind:disabled="!isEditable"
              @select="handleFileUpload('afterExerciseECGUrl', $event)"
              class="custom-file-upload"
            />
            </div>
          </td>
        </tr>
      </table>
    </div>
    <br>

   
  </div>
  <div class="button-container">
    <Button v-if="isEditable && !details" label="Save visit"  icon="pi pi-check" type="submit" @click="this.saveVisit()" class="button-34" />
    <Button v-else-if="!isEditable && !details" label="Modify" icon="pi pi-pencil" type="submit" @click="this.editVisit()" class="button-34" />
  </div> 
</template>

<script>
import patientService from '@/services/patient.service';
import VisitService from '@/services/visit.service';
import DoctorService from '@/services/doctor.service';
import FileUpload from 'primevue/fileupload';
import 'primevue/resources/primevue.min.css';
import 'primevue/resources/themes/saga-blue/theme.css';
import 'primeicons/primeicons.css';
import Calendar from 'primevue/calendar';
import Button from 'primevue/button';
import Input from 'primevue/inputtext';
import DropDown from 'primevue/dropdown';
import Textarea from 'primevue/textarea';
export default {
  name: 'PatientRegisterVisit',
  components: {
    FileUpload,
    Calendar,
    Button,
    Input,
    DropDown,
    Textarea
  },
  data() {
    return {
      patient: {},
      doctors: [],
      selectedDoctorId: {},
      visit: {
        visitDate: '',
        doctorId: '',
        patientId: '',
        visitReason: '',
        chestPainType: '',
        trestbps: '',
        bloodTestPdfUrl: null,
        coronaryAngiographyUrl: null,
        beforeExerciseECGUrl: null,
        duringExerciseECGUrl: null,
        afterExerciseECGUrl: null
      },
      isEditable: true,
      isEditableDate: true,
      isEditableReason: true,
      isEditableTrestbps: true,
      details: false
    };
  },
  props: {
    id: {
      type: String,
      required: true
    },
  },
  methods: {
    getDoctors(){
      VisitService.getDoctors()
        .then(response => {
          this.doctors = response.data.map(doctor => ({
        ...doctor,
        label: `${doctor.firstName} ${doctor.lastName}`}));
        })
        .catch(error => {
          console.error(error);
        });
      },
      getDoctorOfPatient() {
          DoctorService.getDoctorOfPatient(this.id)
            .then(response => {
              console.log("Doctor of patient")
              console.log(response.data);
              if (response && response.data) {
                this.selectedDoctorId.id = response.data.id; // Adjust according to actual expected response
              } else {
                console.error("No doctor data received");
                // Handle the case where no data is returned
              }
            })
            .catch(error => {
              console.error("Failed to fetch doctor data:", error);
              // Handle errors appropriately
            });
    },
    setDoctorForPatient(){
      console.log("Se seteaza doctorul cu id: "+ this.selectedDoctorId.id + " pentru pacientul cu id: "+ this.id);
      DoctorService.updatePatientsDoctor(this.selectedDoctorId.id, this.id)
        .then(response => {
  
          console.log(response.data);
        })
        .catch(error => {
          console.error(error);
        });
    },

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
    handleFileUpload(field, event) {
      if (!event.files || event.files.length === 0) return;
      console.log("In handle uplaod");
      console.log('Uploading file:', event.files[0]);
      const file = event.files[0];
      const formData = new FormData();
      formData.append('file', file);
    
      VisitService.handlePdf(formData)
        .then(response => {   
          console.log(response.data);
          if(field === 'bloodTestPdfUrl')
            this.visit.bloodTestPdfUrl = response.data;
          else if(field === 'coronaryAngiographyUrl')
            this.visit.coronaryAngiographyUrl = response.data;
          else if(field === 'beforeExerciseECGUrl')
            this.visit.beforeExerciseECGUrl = response.data;
          else if(field === 'duringExerciseECGUrl')
            this.visit.duringExerciseECGUrl = response.data;
          else if(field === 'afterExerciseECGUrl')
            this.visit.afterExerciseECGUrl = response.data;
        })
        .catch(error => {
           console.error(error);
        });
    },

    saveVisit(){
      
      const visitToSave = {
        visitDate: this.visit.visitDate,
        doctorId: "6bcf0508-c2d2-424e-8f26-df28ae5886aa",
        patientId: this.patient.id,
        visitReason: this.visit.visitReason,
        trestbps: this.visit.trestbps,
        chestPainType: this.visit.chestPainType,
        bloodTestPdfUrl: this.visit.bloodTestPdfUrl,
        coronaryAngiographyUrl: this.visit.coronaryAngiographyUrl,
        beforeExerciseECGUrl: this.visit.beforeExerciseECGUrl,
        duringExerciseECGUrl: this.visit.duringExerciseECGUrl,
       
        afterExerciseECGUrl: this.visit.afterExerciseECGUrl    
        };
        console.log("Saving data: ");
        console.log(visitToSave)
        this.setDoctorForPatient();
      VisitService.saveVisit(visitToSave)
        .then(response => {
          console.log(response.data);
          this.isEditable = false;
          this.isEditableDate = false;
          this.isEditableReason = false;
          this.isEditableTrestbps = false;
        })
        .catch(error => {
          console.error(error);
        //  this.$emit('visit-registered');
          this.isEditable = false;
          this.isEditableDate = false;
          this.isEditableReason = false;
          this.isEditableTrestbps = false;
        });
    },
    editVisit(){
      this.isEditable = true;
      this.isEditableDate = true;
      this.isEditableReason = true;
      this.isEditableTrestbps = true;
    },
    getLatestVisit(){
      VisitService.getPatientLatestVisit(this.id)
        .then(response => {
          console.log(response.data);
          if(response.data === ""){
            this.isEditable = true;
            return;
          }else{
            this.visit = response.data;
            this.visit.visitDate = "";
            this.visit.visitReason = "";
            this.visit.trestbps = "";
            this.isEditableDate=true;
            this.isEditableReason=true;
            this.isEditableTrestbps=true;
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
    console.log('Visit ID:', this.visitId);
    this.getPatient();
    this.getDoctors();
    this.getLatestVisit();
    this.getDoctorOfPatient();
    
  }
}
</script>
<style scoped>
.visit-form {
  
  margin-left: 70px;
  border-radius: 9px;
  color:black;
}
.button-container {
  display: flex;
  justify-content: center;

  margin-top: 20px;
  margin-bottom: 20px;
}

.visit-reason-input{
  margin-left: 13px;
  width: 600px;
  padding: 8px 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  box-sizing: border-box;
}

.visit-date-label{
  color: black;
  margin: 10px;
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

.form-row-0{
  margin-top: 10px;
  display: flex;
  flex-direction: row;
  align-items: flex-start; 
}

.form-row-2{
  margin-top: 30px;
  margin-left: 10px;
  margin-bottom: 20px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.visit-reason-div{
  margin-top: 10px;
  margin-bottom: 10px;
  display: flex;
  flex-direction: row;
  align-items: flex-start;
}
.visit-reason-label{
  color: black;
  display: flex;
  flex-direction: column;
  align-items: flex-start;

}

.form-row {
  margin-bottom: 15px;
  margin-top: 30px;
  display: flex;
  flex-direction: column;
  align-items: center; 
}

.label {
  display: block;
  margin-bottom: 5px;
  font-weight: 600;
}

.section-divider {
  border: 0;
  height: 2px;
  background-image: linear-gradient(to right, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.75), rgba(0, 0, 0, 0));
  margin:0;
}

.upload-form-label {
  display: block;
  color: #333;
  margin-left: 0; 
  margin-bottom: 5px;
}

.form-control {
  width: 100%;
  padding: 8px 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  box-sizing: border-box;
}

.custom-upload-button .p-button {
  padding: 0.25rem 0.5rem;
  font-size: 0.875rem; 
}

.radio-group {
  display: flex;
  flex-direction: column;
  align-items: flex-start; 
}

.radio {
  margin-bottom: 4px;
  margin-left:70px;
}

.radio input[type="radio"] {
  margin-right: 10px;
}

.radio label {
  margin: 0; 
}
.inputs-row{
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  margin-bottom: 10px;
}
.inputs-row-label{
  margin-top: 5px;
  margin-left: 10px;
}
.row-measure{
  margin-top: 5px;
  margin-left: 5px;
  margin-bottom: 7px;
  color: #8b8b8b;
}

.visit-calendar {
  color-scheme: light;
}

.file-upload-table {
  width: 100%;
  border-collapse: collapse;
  box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.1);
  margin-top: 10px;
}

.file-upload-table td {
  padding: 8px;
  border: 1px solid #ddd;
}

.file-upload-table td:first-child {
  width: 70%; 
  text-align: left;
}

.file-upload-table td:last-child {
  width: 30%; 
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

button-34, .router-link-exact-active.button-34 {
  text-decoration: none; 
}

.button-34:hover, .button-34:focus, .router-link-exact-active.button-34:hover, .router-link-exact-active.button-34:focus {
  text-decoration: none;
}
.file-upload-row {
  border: 1px solid #ddd;
  border-color: rgb(231, 231, 233);
  border-width: thick;
  padding: 10px;
  border-radius: 4px;
  height: 120px;
  text-align: center;
  background-color: #fafafa;
}

.router-link-exact-active.button-34 {
  text-decoration: none; 
}

.custom-file-upload .p-button {
  background-color: #333; 
  color: #fff; 
  border: none;
  border-radius: 4px;
  padding: 0.5rem 1rem;
  display: inline-flex;
  align-items: center;
  justify-content: center;
}

.button-34:hover {
  background-color: #7cc3e2; 
}

.button-34:active,
.router-link-exact-active.button-34 {
  background-color: #4fafe3;
  color: white; 
}

.active-button {
  background-color: #5890af; 
  color: white; 
  border-color: #2b7ba6;
}

.custom-file-upload .p-button .p-button-icon {
  margin-right: 0.5rem; 
}

.custom-file-upload input[type="file"] {
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  opacity: 0;
  cursor: pointer;
}
.button-34 {
  background: #75acca;
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
.file-link a {
  color: #5a869e;
  font-weight: bold;
  text-decoration: none;
}
.file-link a:hover {
  text-decoration: underline;
}

</style>