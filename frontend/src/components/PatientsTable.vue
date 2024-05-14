<template>
  <div class="card">
    <div class="table-header">
      <h2 class="table-title">Patient Directory</h2>
      <p class="table-description">The list of all the patients currently registered in the system.</p>
    </div>
    <DataTable :value="patients" dataKey="id" scrollable scrollHeight="600px" expandableRows :filters="filters">

      <Column field="firstName" header="First Name" sortable>
        <template #body="slotProps">
          <div v-if="slotProps.data.isEditing">
            <input type="text" v-model="slotProps.data.editableFirstName" />
          </div>
          <div v-else>
            {{ slotProps.data.firstName }}
          </div>
        </template>
      </Column>

      <Column field="lastName" header="Last Name" sortable>
        <template #body="slotProps">
          <div v-if="slotProps.data.isEditing">
            <input type="text" v-model="slotProps.data.editableLastName" />
          </div>
          <div v-else>
            {{ slotProps.data.lastName }}
          </div>
        </template>
      </Column>

      <Column field="phone" header="Phone" sortable>
        <template #body="slotProps">
          <div v-if="slotProps.data.isEditing">
            <input type="text" v-model="slotProps.data.editablePhone" />
          </div>
          <div v-else>
            {{ slotProps.data.phone }}
          </div>
        </template>
      </Column>

      <Column field="birthday" header="Birth date" sortable>
        <template #body="slotProps">
          <div v-if="slotProps.data.isEditing">
            <input type="text" v-model="slotProps.data.birthday" />
          </div>
          <div v-else>
            {{ slotProps.data.birthday }}
          </div>
        </template>
      </Column>


      <Column field="email" header="Email" sortable>
        <template #body="slotProps">
          <div v-if="slotProps.data.isEditing">
            <input type="text" v-model="slotProps.data.editableEmail" />
          </div>
          <div v-else>
            {{ slotProps.data.email }}
          </div>
        </template>
      </Column>

      <Column field="CNP" header="CNP" sortable> 
        <template #body="slotProps">
          <div v-if="slotProps.data.isEditing">
            <input type="text" v-model="slotProps.data.cnp" />
          </div>
          <div v-else>
            {{ slotProps.data.cnp }}
          </div>
        </template>
      </Column>
      <Column fiels="gender" header="gender">
        <template #body="slotProps">
          <div v-if="slotProps.data.isEditing">
            <input type="text" v-model="slotProps.data.editableGender" />
          </div>
          <div v-else>
            {{ slotProps.data.gender }}
          </div>
        </template>
      </Column>
       
    
      <Column header="Actions" bodyStyle="text-align: center;" headerStyle="width: 8em">
        <template #body="slotProps">
          <div class="button-container">
          <Button v-if="slotProps.data.isEditing" icon="pi pi-check" @click="savePatient(slotProps.data)" class="p-button-success p-mr-2" />
          <Button v-if="slotProps.data.isEditing" icon="pi pi-times" @click="cancelEditPatient(slotProps.data)" class="p-button-danger" />
          <template v-else>
            <Button icon="pi pi-pencil" class="p-button-rounded p-button-success p-mr-2" @click="editPatient(slotProps.data)" />
            <Button icon="pi pi-trash" class="p-button-rounded p-button-danger" @click="deletePatient(slotProps.data)" />
            <Button icon="pi pi-eye" class="p-button-rounded p-button-info" @click="viewPatient(slotProps.data)" />
          </template>
          </div>
        </template>
      </Column>
     
    </DataTable>
  </div>
  <Toast />
</template>

<script>
import DataTable from 'primevue/datatable';
import Column from 'primevue/column';
import Button from 'primevue/button';
import patientService from '@/services/patient.service';
import Toast  from 'primevue/toast';
import { useToast } from 'primevue/usetoast';

export default {
components: {
  DataTable,
  Column,
  Button,
  Toast
},
data() {
  return {
    expandedRows: null,
    filters: {},
    patientSearch: '',
    allPatients: [],
  };
},

props:['patients'],

setup() {
  const toast = useToast();

  function showSuccessDeletePatientMessage() {
    toast.add({
      severity: 'success',
      summary: 'Delete Successful',
      detail: 'Patient deleted successfully!',
      life: 4000,
    });
  }
 
  function showUpdatePatientSuccessMessage() {
    toast.add({
      severity: 'success',
      summary: 'Update Successful',
      detail: 'Patient updated successfully!',
      life: 4000,
    });
  }
  return {showSuccessDeletePatientMessage, showUpdatePatientSuccessMessage};
},
methods: {
  refreshData() {
      this.getPatients();

  },

  editPatient(patient){
    patient.isEditing = true;
    patient.editableFirstName = patient.firstName;
    patient.editableLastName = patient.lastName;
    patient.editablePhone = patient.phone;
    patient.editableEmail = patient.email;
    patient.editableGender = patient.gender;
    patient.editableCNP = patient.cnp;
    patient.editableBirthday = patient.birthday;
   
  },
  savePatient(patient){
    patient.firstName = patient.editableFirstName;
    patient.lastName = patient.editableLastName;
    patient.phone = patient.editablePhone;
    patient.email = patient.editableEmail;
    patient.cnp = patient.editableCNP;
    patient.gender = patient.editableGender;
    patient.birthday = patient.editableBirthday;
    patient.isEditing = false;

    this.saveEditPatient(patient);
    this.showUpdatePatientSuccessMessage();
  },
  viewPatient(patient){
    console.log("View patient", patient);
    this.$router.push({name: 'PatientHome', params: {id: patient.id}});
  },
  
  cancelEditPatient(patient){  
    patient.isEditing = false;
  },
 
 
  saveEditPatient(patient){
    patientService.updatePatient(patient).then(response => {
      console.log(" editat",  response.data);
    }).catch(error => {
      console.log("Eroare la edit patient " + error);
    })
  },
  deletePatient(patient){
    patientService.deletePatient(patient.id).then(response => {
        console.log("Pacient sters" + response.data);
        this.$emit('delete-requested', patient);
        this.showSuccessDeletePatientMessage();
    }).catch(error => {
      console.log("Eroare la stergere pacient "+ error);
    })
  },

  getPatients(){
    patientService.getAllPatients()
      .then(response => {
        //this.newPatients = response.data;
        console.log(response.data)
       // console.log(this.patients);
      }).catch(error => {
        console.log("errrrrror")
        console.log(error);
      });
  },
},
mounted(){
  this.getPatients();
}

};
</script>
<style scoped>
.card {
  background: white;
  border-radius: 6px;
  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  margin: 20px 0;
}

.p-datatable .p-datatable-tbody > tr > td {
  padding: 0.25rem !important;;
  border-bottom: 1px solid #e0e0e0; 
  font-size: 0.6rem; 
}

.p-datatable .p-datatable-thead > tr > th {
  background-color: #8dc3fc; 
  color: rgb(0, 0, 0);
  padding: 0.5rem;
  text-align: left;
  font-size: 0.6rem !important;
}

.p-datatable .p-datatable-tbody > tr:nth-child(odd) {
  background-color: #f9f9f9;
}

.p-datatable .p-datatable-tbody > tr > td .p-datatable.p-component .p-datatable-tablewrapper {
  box-shadow: 0 2px 4px 0 rgba(0,0,0,0.1); 
  margin: 1rem 0;
  border: none; 
}

.nested-table {
  width: calc(100% - 2rem); 
  margin: 0 1rem; 
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.1);
}

.nested-table th,
.nested-table td {
  padding: 0.5rem; 
}

.nested-table thead th {
  background-color:  #f0f4f7; 
  color: rgb(0, 0, 0);
}

.p-datatable-header {
  background-color: #84c4ef; 
  color: white;
  padding: 0.5rem;
  margin: 0 1rem; 
  border-top-left-radius: 0.25rem; 
  border-top-right-radius: 0.25rem;
}

.p-button {
  border-radius: 0.25rem; 
}

.p-datatable .p-datatable-tbody > tr > td:last-child {
  border: 0;
}

.button-container {
  display: flex;
  align-items: center;
  justify-content: center;
}
.table-header {
  padding: 1rem;
  background-color: #f2f2f2; 
  border-radius: 5px; 
  margin-bottom: 1rem; 
}

.table-title {
  font-size: 1.5rem; 
  margin-top: 5px;
}

.table-description {
  color: #88a7c5; 
}

.search-container {
  align-items: center;
  display: flex;
}

.table-header {
  background-color: aliceblue;
  padding: 1rem; 
  border-bottom: 1px solid #dee2e6;
  text-align: center;
}

.table-title {
  margin: 0;
  color: #212529;
  font-size: 24px;
}

.table-description {
  margin-top: 0.25rem;
  font-size: 14px; 
  color: #495057; 
}

.p-datatable .p-datatable-thead > tr > th {
  background-color: #f8f9fa; 
  color: #495057; 
  font-size: 16px;
  font-weight: 600; 
}

.p-button {
  margin: 0 0.25rem; 
}

.button-container {
  justify-content: center; 
}

@media (max-width: 600px) {
  .p-datatable .p-datatable-tbody > tr > td {
    text-align: center; 
  }
  .p-datatable .p-datatable-thead > tr > th {
    text-align: center; 
  }
}

</style>