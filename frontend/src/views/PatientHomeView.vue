<template>
  <div class="patient-container">
    <aside class="visits">
      <PatientVisits :id="this.$route.params.id" @visit-selected="handleVisitSelected" @register-selected="handleRegisterSelected" />
    </aside>
    
    <section class="patient-content">
      
      <div class="info">
        <PatientInfo :patient="patient" />
      </div>
      <div class="patient-operations">
        <div class="navigation-buttons">
          <router-link 
             :to="selectedVisitId ? { name: 'VisitDetails', params: { visitId: selectedVisitId, id: patient.id } } : { name: 'PatientRegisterVisit', params: { id: patient.id } }"
             class="button-34" 
            active-class="active-button">

  {{ selectedVisitId ? 'Visit Details' : 'Register New Visit' }}
</router-link>

          <router-link 
                :to="{ name: 'PatientFamilyHistory', params: { id: patient.id }}" 
                class="button-34" 
                active-class="active-button">
              Family History
          </router-link>

          <router-link 
              :to="{ name: 'PatientDetails', params: { id: patient.id }}" 
              class="button-34" 
              style="text-decoration:none" 
              active-class="active-button">
            Patient Details
          </router-link>
        </div>
        <div class="patient-page-content">
          <router-view></router-view>
        </div>
      </div>
    </section>
 
  </div>
</template>
<script>
import PatientService from '@/services/patient.service';
import PatientInfo from '@/components/patient/PatientInfo.vue';
import PatientVisits from '@/components/patient/PatientVisits.vue';
//import PatientRegisterVisit from '@/components/patient/PatientRegisterVisit.vue';


export default {
  name: 'PatientHomeView',
  components: {
    PatientInfo,
    PatientVisits,

   // PatientDetails
  },
  data() {
    return {
      patient: {},
      selectedVisitId: null
    };
  },
  methods: {
    fetchPatientDetails() {
      PatientService.getPatient(this.$route.params.id)
        .then(response => {
          this.patient = response.data;
          console.log("Pacinetul din")
          console.log(this.patient)
        })
        .catch(error => {
          console.error(error);
        });
    },
    handleVisitSelected(visit) {
      console.log('Selected visit id in handle visit selected:', visit.id);
      this.selectedVisitId = visit.id;
      this.$router.push({name: 'VisitDetails', params: {visitId: visit.id ,id: this.$route.params.id}});
    },
    handleRegisterSelected(){
      this.selectedVisitId = null;
      this.$router.push({name: 'PatientRegisterVisit', params: {id: this.$route.params.id}});
    }
  },
  mounted(){
    console.log('User ID:', this.$route.params.id);
    this.fetchPatientDetails();
  }
}
</script>
<style scoped>
.patient-container {
  margin-top: 20px;
  display: grid;
  grid-template-columns: 1fr 6fr; 
  gap: 1rem;
}

.visits {
  background-color: #f5f5f5;
  padding: 1rem; 
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.patient-content {
  display: flex;
  flex-direction: column;
  margin-right: 200px;
  
}
.info{
  background-color: #f6f6f6; 
}

.info, .registerVisit {
  background-color: #f6f6f6; 
  border-radius: 8px; 
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); 
  display: flex;
  flex-direction: column; 
  align-items: flex-start; 
}

.navigation-buttons {
  display: flex;
  margin-left: 30px;
  gap: 0.5rem;
  padding: 1rem;
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

.patient-operations {
  display: flex;
  flex-direction: column;
  width: 100%;
}

.patient-page-content {
  flex-grow: 1; 
  padding: 1rem; 
  background-color: #ffffff;
  border-radius: 8px; 
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  margin-bottom: 50px;
  margin-right: 190px;
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
</style>