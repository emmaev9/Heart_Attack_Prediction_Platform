<template>
  <div class="patient-container">
    <aside class="visits">
      <DocPatientVisits :id="this.$route.params.id" :visitDefault="this.visit" @visit-selected="handleVisitSelected" />
    </aside>
    
    <section class="patient-content">
      
      <div class="info">
        <PatientInfo :patient="patient" />
      </div>
      <div class="patient-operations">
        <div class="navigation-buttons">
          <router-link 
                :to="{ name: 'DocPatientECGs', params: {id: this.$route.params.id, visitId: selectedVisitId }}" 
                class="button-34" 
                style="text-decoration:none" 
                active-class="active-button">
              Medical Evaluation
          </router-link>
<!--
          <router-link 
              :to="{ name: 'DocPatientTests', params: {id: this.$route.params.id, visitId: selectedVisitId }}" 
              class="button-34" 
              style="text-decoration:none" 
              active-class="active-button">
            Other Medical Files
          </router-link>
        -->
          <router-link 
              :to="{ name: 'DocPatientDetails', params: { id: this.$route.params.id }}" 
              class="button-34" 
              style="text-decoration:none" 
              active-class="active-button">
            Patient Details
          </router-link>

          <router-link 
                :to="{ name: 'DocPatientFamilyHistory', params: { id: this.$route.params.id }}" 
                class="button-34" 
                active-class="active-button">
              Family History
          </router-link>

          
          <router-link 
                :to="{ name: 'DocPatientPrediction', params: { id: this.$route.params.id, visitId: selectedVisitId }}" 
                class="button-34" 
                active-class="active-button">
              HD Analyser
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
import VisitService from '@/services/visit.service';
import DocPatientVisits from '@/components/doctor/DocPatientVisits.vue';
//import PatientRegisterVisit from '@/components/patient/PatientRegisterVisit.vue';


export default {
  name: 'DocPatientHomeView',
  components: {
    PatientInfo,
    DocPatientVisits,

   // PatientDetails
  },
  data() {
    return {
      patient: {},
      selectedVisitId: null,
      visit: {}
     // latestVisit: {}
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
    getLatestVisit(){
      VisitService.getPatientLatestVisit(this.$route.params.id)
        .then(response => {
            this.visit = response.data;
            this.selectedVisitId = response.data.id;
        })
        .catch(error => {
          console.error(error);
        });
    },
    handleVisitSelected(visit) {
      console.log('Selected visit id in handle visit selected:', visit.id);

      this.selectedVisitId = visit.id;
      this.$router.push({name: 'DocPatientECGs', params: {id: this.$route.params.id, visitId: this.selectedVisitId}});
    },
  },
  mounted(){
    console.log('User ID:', this.$route.params.id);
    this.getLatestVisit();
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
  margin-right: 90px;
  
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
  margin-left: 5px;
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