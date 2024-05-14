<template>
  <div class="history-contents">
   
    <hr class="section-divider" />
    <DataTable v-if="familyHistories.length > 0" :value="familyHistories" responsiveLayout="scroll">
      <Column field="relationship" header="Relationship"></Column>
      <Column field="name" header="Name"></Column>
      <Column field="currentAge" header="Current age (if living)"></Column>
      <Column field="ageOfDeath" header="Age at death"></Column>
      <Column field="causeOfDeath" header="Cause of death"></Column>
      <Column field="heartDisease" header="Heart disease (Y/N)"></Column>
      <Column field="typeOfHeartDisease" header="Type of HD"></Column>
    </DataTable>
    <div v-else>
      <div class="prediction-description">This patient does not have family history of cardiac disease.</div>
    </div>
    <hr class="section-divider" />
    <div v-if="familyHistories.length > 0" class="basic-label">Additional notes</div>
    <Textarea v-if="familyHistories.length > 0" style="width:100%; border: 1px solid #ccc;" rows="3" autoResize v-model="additionalNotes" placeholder="" class="notes-input"/>
  </div>
 
  
</template>
<script>
import FamilyHistoryService from '@/services/family.history.service';
import DataTable from 'primevue/datatable';
import Column from 'primevue/column';
import Textarea from 'primevue/textarea';
//import Button from 'primevue/button';
export default {
  
  name: 'DocPatientFamilyHistory',
  components: {
    DataTable,
    Column,
    Textarea,
  //  Button
  },

  data() {
    return {
      familyHistories: [],
      additionalNotes: '',
    };
  },
  props: {
    id: {
      type: String,
      required: true
    },
  },
  methods:{
    getFamilyHistories() {
      FamilyHistoryService.getFamilyHistory(this.id)
        .then(response => {
          console.log(response.data);
          this.familyHistories = response.data;
        })
        .catch(error => {
          console.error(error);
        });
    },
  },
  mounted() {
    this.getFamilyHistories();
  }
}
</script>
<style>
@import "../../assets/styles/table_style.css";

.history-contents {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-left: 60px;
  margin-right: 60px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}
.basic-label {
  font-size: 17px;
  font-weight: 500;
  margin-bottom: 10px;
  margin-top: 20px;
  margin-left: 25px;
  align-items: left;
  color:#0a2239;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}
@media screen and (max-width: 960px) {
  .p-field label,
  .p-field .p-inputtext {
    display: block;
    width: 100%;
  }

  .p-field label {
    margin-bottom: 0.5rem;
  }
}
.button-34:hover {
  background-color: #5dd7a2;
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
.prediction-description {
  font-size: 15px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  color:  #92aebd
}

@media (max-width: 575.98px) {
  .new-entry-form .p-grid {
    flex-direction: column;
  }

  .new-entry-form .p-col-6 {
    flex: 0 0 100%;
    max-width: 100%;
  }

  .elements-row {
    flex-direction: column;
    margin-left: 0;
    gap: 1rem;
  }
}
</style>