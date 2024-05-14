<template>
  <div v-if="familyHistories.length > 0">
    <DataTable :value="familyHistories" class="table-of-family">
      <Column field="relationship" header="Relationship">
        <template #body="slotProps">
          <div v-if="slotProps.data.isEditing">
            <InputText v-model="slotProps.data.editableRelationship" />
          </div>
          <div v-else>
            {{ slotProps.data.relationship }}
          </div>
        </template>
      </Column>
      <Column field="name" header="Name">
        <template #body="slotProps">
          <div v-if="slotProps.data.isEditing">
            <InputText v-model="slotProps.data.editableName" />
          </div>
          <div v-else>
            {{ slotProps.data.name }}
          </div>
        </template>
      </Column>
      <Column field="gender" header="Gender">
        <template #body="slotProps">
          <div v-if="slotProps.data.isEditing">
            <InputText v-model="slotProps.data.editableGender" />
          </div>
          <div v-else>
            {{ slotProps.data.gender }}
          </div>
        </template>
      </Column>
      <Column field="currentAge" header="Current Age">
        <template #body="slotProps">
          <div v-if="slotProps.data.isEditing">
            <InputText v-model="slotProps.data.editableCurrentAge" />
          </div>
          <div v-else>
            {{ slotProps.data.currentAge }}
          </div>
        </template>
      </Column>
      <Column field="ageOfDeath" header="Age at death">
        <template #body="slotProps">
          <div v-if="slotProps.data.isEditing">
            <InputText v-model="slotProps.data.editableAgeOfDeath" />
          </div>
          <div v-else>
            {{ slotProps.data.ageOfDeath }}
          </div>
        </template>
      </Column>
      <Column field="causeOfDeath" header="Cause of death">
        <template #body="slotProps">
          <div v-if="slotProps.data.isEditing">
            <InputText v-model="slotProps.data.editableCauseOfDeath" />
          </div>
          <div v-else>
            {{ slotProps.data.causeOfDeath }}
          </div>
        </template>
      </Column>
      <Column field="heartDisease" header="Heart Disease (Y/N)">
        <template #body="slotProps">
          <div v-if="slotProps.data.isEditing">
            <InputText v-model="slotProps.data.editableHeartDisease" />
          </div>
          <div v-else>
            {{ slotProps.data.heartDisease }}
          </div>
        </template></Column>
      <Column field="typeOfHeartDisease" header="Type of HD">
        <template #body="slotProps">
          <div v-if="slotProps.data.isEditing">
            <InputText v-model="slotProps.data.editableTypeOfHeartDisease" />
          </div>
          <div v-else>
            {{ slotProps.data.typeOfHeartDisease }}
          </div>
        </template>
      </Column>
      <Column header="Actions">
        <template #body="slotProps">
          <div class="button-container">
            <Button v-if="!slotProps.data.isEditing" icon="pi pi-pencil" class="p-button-rounded p-button-success p-mr-2" @click="editEntry(slotProps.data)" />
            <Button v-if="slotProps.data.isEditing" icon="pi pi-check" class="p-button-rounded p-button-success p-mr-2" @click="saveEntry(slotProps.data)" />
            <Button icon="pi pi-trash" class="p-button-rounded p-button-danger" @click="deleteEntry(slotProps.data)" />
          </div>
        </template>
      </Column>
    </DataTable>
  </div>
 
  <div class="patient-family-history">
    <div class="new-entry-form">
      <h4 style="margin-bottom:20px;">Add a new entry</h4>
      <div class="elements-row">
        <div class="single-element">
          <FloatLabel>
            <InputText id="relationship" v-model="newEntry.relationship" />
            <label for="relationship">Relationship</label>
        </FloatLabel>
      </div>
      <div class="single-element">
        <FloatLabel>
          <InputText id="name" v-model="newEntry.name" />
          <label for="name">Name</label>
       </FloatLabel>
      </div>
   
      </div>
      <div class="elements-row">
        <div class="single-element">
          <FloatLabel>
            <label for="currentAge">Current Age (If living)</label>
            <InputText id="currentAge" v-model="newEntry.currentAge" />
          </FloatLabel>
        </div>
        <div class="single-element">
          <FloatLabel>
            <label for="gender">Gender</label>
            <InputText id="gender" v-model="newEntry.gender" />
          </FloatLabel>
        </div>
      </div>

      <div class="elements-row">
        <div class="single-element">
          <FloatLabel>
            <label for="ageOfDeath">Age at Death</label>
            <InputText id="ageOfDeath" v-model="newEntry.ageOfDeath" />
          </FloatLabel>
        </div>
        <div class="single-element">
          <FloatLabel>
            <label for="causeOfDeath">Cause of Death</label>
            <InputText id="causeOfDeath" v-model="newEntry.causeOfDeath" />
          </FloatLabel>
        </div>
      </div>
      <div class="elements-row">
        <div class="single-element">
          <FloatLabel>
            <label for="heartDisease">Heart Disease(Y/N)</label>
            <InputText id="heartDisease" v-model="newEntry.heartDisease" />
          </FloatLabel>
        </div>
        <div class="single-element">
          <FloatLabel>
            <label for="typeOfHeartDisease">Type of HD</label>
            <InputText id="typeOfHeartDisease" v-model="newEntry.typeOfHeartDisease" />
          </FloatLabel>
        </div>
      </div>



      <Button style="margin-bottom:20px;" label="Add new entry" @click="addEntry" class="button-34" />
    </div>
  </div>
  <Toast />
</template>

<script>
import PatientService from '@/services/patient.service';
import FamilyHistoryService from '@/services/family.history.service';
import DataTable from 'primevue/datatable';
import Column from 'primevue/column';
import InputText from 'primevue/inputtext';
import Button from 'primevue/button';
import Toast from 'primevue/toast';
import FloatLabel from 'primevue/floatlabel';
import { useToast } from 'primevue/usetoast';

export default {
  name: 'PatientFamilyHistory',
  components: {
    DataTable,
    Column,
    InputText,
    Button,
    FloatLabel,
    Toast
  },
  props: {
    id: {
      type: String,
      required: true
    },
  },
  setup() {
  const toast = useToast();

  function showSuccessDeleteEntryMessage() {
    toast.add({
      severity: 'success',
      summary: 'Delete Successful',
      detail: 'Entry deleted successfully!',
      life: 4000,
    });
  }
 
  function showUpdateEntrySuccessMessage() {
    toast.add({
      severity: 'success',
      summary: 'Update Successful',
      detail: 'Entry updated successfully!',
      life: 4000,
    });
  }
  return {showSuccessDeleteEntryMessage, showUpdateEntrySuccessMessage};
},
  data() {
    return {
      patient: null,
      newEntry: {
        patientId: this.id,
        relationship: '',
        name: '',
        gender: '',
        currentAge: '',
        ageOfDeath: '',
        causeOfDeath: '',
        heartDisease: '',
        typeOfHeartDisease: '',
      },
      familyHistories: [],
    }
  },
  methods: {
    fetchPatientDetails() {
      PatientService.getPatient(this.id)
        .then(response => {
          console.log(response.data);
          this.patient = response.data;
        })
        .catch(error => {
          console.error(error);
        });
    },
    addEntry() {
      console.log(this.newEntry);
      FamilyHistoryService.saveEntry(this.newEntry)
        .then(response => {
          console.log(response.data);
          this.familyHistories.push(this.newEntry);
        })
        .catch(error => {
          console.error(error);
          this.getFamilyHistories();
        });
      },
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
    editEntry(entry) {
      entry.isEditing = true;
      entry.editableRelationship = entry.relationship;
      entry.editableName = entry.name;
      entry.editableGender = entry.gender;
      entry.editableCurrentAge = entry.currentAge;
      entry.editableAgeOfDeath = entry.ageOfDeath;
      entry.editableCauseOfDeath = entry.causeOfDeath;
      entry.editableHeartDisease = entry.heartDisease;
      entry.editableTypeOfHeartDisease = entry.typeOfHeartDisease;
    },
    saveEntry(entry) {
      entry.relationship = entry.editableRelationship;
      entry.name = entry.editableName
      entry.gender = entry.editableGender;
      entry.currentAge = entry.editableCurrentAge;
      entry.ageOfDeath = entry.editableAgeOfDeath;
      entry.causeOfDeath = entry.editableCauseOfDeath;
      entry.heartDisease = entry.editableHeartDisease;
      entry.typeOfHeartDisease = entry.editableTypeOfHeartDisease;

      FamilyHistoryService.updateFamilyHistory(entry)
        .then(() => {
          entry.isEditing = false;
          this.showUpdateEntrySuccessMessage();
        })
        .catch(error => {
          console.error(error);
        });
    },

    deleteEntry(entry) {
      FamilyHistoryService.deleteFamilyHistory(entry.id)
        .then(() => {
          this.familyHistories = this.familyHistories.filter(fh => fh.id !== entry.id);
          this.showSuccessDeleteEntryMessage();
        })
        .catch(error => {
          console.error(error);
        });
    },  
  },
  mounted(){
    console.log('User ID:', this.id);
    this.fetchPatientDetails();
    this.getFamilyHistories();
  }
}
</script>

<style scoped>
@import "../../assets/styles/table_style.css";
.table-of-family {
  margin-top: 30px;
  margin-left: 20px;
  margin-right: 52px;
  border-radius: 10px;
  box-shadow: #c0c5ca 0 10px 20px -15px;
}

.patient-family-history {
  max-width: 50rem;
  margin: auto;
  margin-bottom: 30px;
  border-radius: 10px;
  color: #495057;
}

.elements-row {
  display: flex;
  margin-top: 17px;
  margin-left: 70px;
  margin-bottom: 15px;
  gap: 5rem;
}
.single-element {
  display: flex;
  flex-direction: column;
  margin-bottom: 10px;
}

.new-entry-form {
  margin-top: 2rem;
  background-color: #f8f9fa;
  padding: 1rem;
  border-radius: 8px;
  margin-left: 50px;
  margin-right: 50px;
  box-shadow: #c0c5ca 0 10px 20px -15px;
}

.p-field label {
  display: inline-block;
  margin-right: 1rem;
}

.p-field .p-inputtext {
  width: auto;
  display: inline-block;
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
.h4{
  margin-bottom: 20px;
  font-size: 10px;
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

.button-container {
  display: flex;
  align-items: center;
  justify-content: center;
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