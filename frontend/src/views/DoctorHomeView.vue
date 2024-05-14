<template>
  <div class="medical-assistant-home">
    <div class="medical-assistant-background">
      <img src="../assets/medical2.jpeg" class="background" alt="medical background" />
      <div class="search-bar-container">
        <InputText class="inputtext" v-model="patientSearch" @input="searchPatients" placeholder="Search patient" />
        <button role="button" @click="seeDashboard" class="button-34">
          Data Dashboard
        </button>
      </div>
    </div>

    <hr>
    <div class="patients-table">
      <DocPatientsTable ref="patientsTable" :patients="filteredPatients"/>
    </div>
  </div>
</template>


<script>
import InputText from 'primevue/inputtext';
//import Button from 'primevue/button';
import DoctorService from '@/services/doctor.service';
import VisitService from '@/services/visit.service';
import DocPatientsTable from '../components/doctor/DocPatientsTable.vue'; 

export default {
  name: "MedicalAssistantHome",
  components: {
    InputText,
  //  Button,
    DocPatientsTable
  },
  data() {
    return {
      patientSearch: '',
      patients: [],
    };
  },
  methods: {
    searchPatients(){
      this.$emit('search-patients', this.patientSearch);
    },
    seeDashboard() {
    this.$router.push({name: 'PatientsDataDashboard'});
  },
    getAllDoctorPatients(){
      DoctorService.getAllPatientsOfDoctor(this.doctorId)
      .then(response => {
        this.patients = response.data;
        for(let i = 0; i < this.patients.length; i++){
          VisitService.getPatientLatestVisit(this.patients[i].id)
          .then(response => {
            this.patients[i].latestVisit = response.data.visitDate.split('T')[0];
          })
          .catch(error => {
            console.log("Could't get patients latest visit. Got error: " + error);
          });
        }
       
      })
      .catch(error => {
        console.log(error);
      });
    },
  },
  computed:{
    doctorId() {
      return this.$store.state.auth.user ? this.$store.state.auth.user.id : null;
    },
    filteredPatients(){
      const searchQuery = this.patientSearch.toLowerCase();
      if (!searchQuery.trim()) {
        return this.patients;
      }

      return this.patients.filter(patient => 
        patient.firstName.toLowerCase().includes(searchQuery) ||
        patient.lastName.toLowerCase().includes(searchQuery) ||
        patient.cnp.toLowerCase().includes(searchQuery)
      );
    }
  },
  mounted(){
    if (!this.$store.state.user) {
      console.log('No user found, fetching user');
      this.$store.dispatch('fetchUser');
    } else {
      console.log('User already in store:', this.$store.state.user);
    }
    this.getAllDoctorPatients();
  },

}
</script>
<style scoped>
.background {
  position: relative; /* Add relative positioning here */
  width: 100%;
  height: 40vh; /* Adjust height as needed */
  background-size: cover;
  z-index: -1;
}



.search-bar-container {
  position: absolute; /* Position absolutely within .background */
 /* top: 50%; /* Center vertically, adjust as needed */
  /*left: 50%; /* Center horizontally */
  transform: translate(20%, -150%); /* Align center */
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  gap: 10px;
  z-index: 2; /* Ensure it's above the background image */
}

.button-34 {
  background: #75acca;
  border-radius: 999px;
  color: #FFFFFF;
  cursor: pointer;
  font-size: 16px;
  padding: 8px 18px;
  border: 0;
  transition: background-color 0.3s; /* Smooth transition for hover */
}

.button-34:hover {
  background-color: #7cc3e2;
}

.inputtext {
  width: 250px; /* Adjust width as needed */
  padding: 10px;
  border-radius: 20px;
  border: none;
  outline: none;
  background-color: aliceblue;
}
.inputtext:focus {
  box-shadow: inset 0 0 5px #5b7b9d; /* Add focus effect */
}
.patient-table {
  padding: 2rem 0;
  margin-left: 40px;
}

</style>

