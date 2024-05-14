<template>
  <div class="medical-assisttant-background" >
    <img src="../assets/medical2.jpeg" class="background" alt="medical background"  />
  </div>
  <div class="medical-assistant-home">
    <div class="search-bar-container">
      <div class="search-bar">
        <InputText class="inputtext" v-model="patientSearch" @input="searchPatients"  placeholder="Search patient" />
      </div>
      <Button label="Add new patient" @click="showRegisterDialog" class="button-34" />
    </div>
    <RegisterPatientDialog :visible="visibleRegisterDialog" @update:visible="dialogVisibility" @registeredPatient="this.registerPatient" />
    <hr>
    <div class="patients-table">
      <PatientsTable ref="patientsTable" :patients="filteredPatients"  @delete-requested="this.handleDeletePatient" />
    </div>
  </div>

</template>


<script>
import InputText from 'primevue/inputtext';
import Button from 'primevue/button';
import RegisterPatientDialog from '../components/RegisterPatient.vue'; 
import PatientService from '@/services/patient.service'; 
import PatientsTable from '../components/PatientsTable.vue'; 

export default {
  name: "MedicalAssistantHome",
  components: {
    InputText,
    Button,
    RegisterPatientDialog,
    PatientsTable
  },
  data() {
    return {
      visibleRegisterDialog: false,
      patientSearch: '',
      patients: []
    };
  },
  methods: {
    showRegisterDialog() {
      this.visibleRegisterDialog = true;
    },
    dialogVisibility(newValue) {
      this.visibleRegisterDialog = newValue;
    },
    registerPatient(patient) {
      console.log("Ininate de register : " + patient);

      PatientService.registerPatient(patient)
        .then(response => {
          console.log(response);
          this.patients.push(response.data);
          this.$refs.patientsTable.refreshData();
        })
        .catch(error => {
          console.error(error);
        });
    },
    searchPatients(){
      this.$emit('search-patients', this.patientSearch);
    },
    getAllPatients(){
      PatientService.getAllPatients()
        .then(response => {
          this.patients = response.data;
        })
        .catch(error => {
          console.error(error);
        });
    },
    handleDeletePatient(patient) {
          this.patients = this.patients.filter(p => p.id !== patient.id);
    }
  },
  computed:{
    filteredPatients(){
      const searchQuery = this.patientSearch.toLowerCase();
      if (!searchQuery.trim()) {
        return this.patients;
      }

      return this.patients.filter(patient => 
        patient.firstName.toLowerCase().includes(searchQuery) ||
        patient.lastName.toLowerCase().includes(searchQuery) ||
        patient.phone.includes(searchQuery) ||
        patient.email.toLowerCase().includes(searchQuery) ||
        patient.cnp.toLowerCase().includes(searchQuery)
      );
    }
  },
  mounted(){
    this.getAllPatients();
  }
}
</script>

<style scoped>

.background {
  width: 100%;
  height: 60%;
  background-size: cover;
  z-index: -1;
}

.medical-assistant-home {
  margin-top: 10px;
  margin-left: 50px;
  margin-right: 50px;
  margin-bottom: 50px
}

.search-bar-container {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 2rem 0;
  width: 100%;
  gap: 10px; 

}
.button-34:hover {
  background-color: #7cc3e2;
}


.button-34 {
  background: #75acca;
  border-radius: 999px;
  box-shadow: #66666a 0 10px 20px -10px;
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
}

.patient-table {
  padding: 2rem 0;
  margin-left: 20px;
}

.search-bar input {
  background-color: aliceblue;
  border-radius: 20px; 
  border: none; 
  outline: none; 
  width: 250px; 
  padding: 10px; 
  align-items: center;
}

.inputtext {
  width: calc(100% - 150px);
  margin-right: 10px;
}

</style>
