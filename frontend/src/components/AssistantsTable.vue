<template>
  <div class="card">
    <div class="table-header">
      <h2 class="table-title">Medical Assistants</h2>
      <div class="search-container">
        <div class="search-bar">
          <IconField iconPosition="left">
            <InputIcon>
              <i class="pi pi-search" />
            </InputIcon>
            <InputText v-model="medicalAssistantSearch" placeholder="Search Medical Assistant"  />
          </IconField>
        </div>
      </div> 
    </div>
    <DataTable :value="filteredMedicalAssistants" dataKey="id" scrollable scrollHeight="1000px" expandableRows :filters="filters">

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

      <Column field="username" header="Username" sortable>
       
      </Column>
      <Column header="Actions" bodyStyle="text-align: center;" headerStyle="width: 8em">
        <template #body="slotProps">
          <div class="button-container">
          <Button v-if="slotProps.data.isEditing" icon="pi pi-check" @click="saveMedicalAssistant(slotProps.data)" class="p-button-success p-mr-2" />
          <Button v-if="slotProps.data.isEditing" icon="pi pi-times" @click="cancelEditMedicalAssistant(slotProps.data)" class="p-button-danger" />
          <template v-else>
            <Button icon="pi pi-pencil" class="p-button-rounded p-button-success p-mr-2" @click="editMedicalAssistant(slotProps.data)" />
            <Button icon="pi pi-trash" class="p-button-rounded p-button-danger" @click="deleteMedicalAssistant(slotProps.data)" />
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
import adminService from '@/services/admin.service';
import  InputText  from 'primevue/inputtext';
import  InputIcon  from 'primevue/inputicon';
import  IconField  from 'primevue/iconfield';
import Toast  from 'primevue/toast';
import { useToast } from 'primevue/usetoast';

//import AdminService from '@/services/admin.service';

export default {
components: {
  DataTable,
  Column,
  Button,
  InputText,
  InputIcon,
  IconField,
  Toast
},
data() {
  return {
    expandedRows: null,
    filters: {},
    medicalAssistantSearch: '',
    medicalAssistants: [],
  };
},
setup() {
  const toast = useToast();

  function showSuccessDeleteMedicalAssistantMessage() {
    toast.add({
      severity: 'success',
      summary: 'Delete Successful',
      detail: 'Medical assistant deleted successfully!',
      life: 4000,
    });
  }
 
  function showUpdateMedicalAssistantSuccessMessage() {
    toast.add({
      severity: 'success',
      summary: 'Update Successful',
      detail: 'Medical assistant updated successfully!',
      life: 4000,
    });
  }
  return {showSuccessDeleteMedicalAssistantMessage, showUpdateMedicalAssistantSuccessMessage};
},
methods: {
  refreshData() {
      this.getMedicalAssistants();

  },

  editMedicalAssistant(assistant){
    assistant.isEditing = true;
    assistant.editableFirstName = assistant.firstName;
    assistant.editableLastName = assistant.lastName;
    assistant.editablePhone = assistant.phone;
    assistant.editableEmail = assistant.email;
    assistant.editableUsername = assistant.username;
  },
  saveMedicalAssistant(assistant){
    assistant.firstName = assistant.editableFirstName;
    assistant.lastName = assistant.editableLastName;
    assistant.phone = assistant.editablePhone;
    assistant.email = assistant.editableEmail;
    assistant.username = assistant.editableUsername;
    assistant.isEditing = false;
    this.saveEditMedicalAssistant(assistant);
    this.showUpdateMedicalAssistantSuccessMessage();
  },
  
  cancelEditMedicalAssistant(assistant){
    assistant.isEditing = false;
  },
 
 
  saveEditMedicalAssistant(medicalAssistant){
    adminService.updateUser(medicalAssistant).then(response => {
      console.log("Asistent medical editat",  response.data);
    }).catch(error => {
      console.log("Eroare la edit asistent medical " + error);
    })
  },
  deleteMedicalAssistant(medicalAssistant){
    adminService.deleteUser(medicalAssistant.id).then(response => {
        console.log("Asistent medical sters" + response.data);
         this.refreshData();
          this.showSuccessDeleteMedicalAssistantMessage();
         this.getMedicalAssistants();
    }).catch(error => {
      console.log("Eroare la stergere asistent medical "+ error);
    })
  },

  getMedicalAssistants(){
     adminService.getAllMedicalAssistants()
      .then(response => {
        this.medicalAssistants = response.data;
        console.log(this.assistants);
      }).catch(error => {
        console.log(error);
      });
  },

},
computed: {
  filteredMedicalAssistants() {
    const searchQuery = this.medicalAssistantSearch.toLowerCase();
    return this.medicalAssistants.filter(assistant =>
      assistant.firstName.toLowerCase().includes(searchQuery) ||
      assistant.lastName.toLowerCase().includes(searchQuery) ||
      assistant.phone.toLowerCase().includes(searchQuery) ||
      assistant.email.toLowerCase().includes(searchQuery) ||
      assistant.username.toLowerCase().includes(searchQuery)
    );
  },
},
mounted() {
  // Fetch doctors or perform other initialization logic
  this.getMedicalAssistants();
},
};
</script>
<style scoped>
@import "../assets/styles/table_style.css"

</style>