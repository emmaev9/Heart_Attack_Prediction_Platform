<template>
    <div class="card">
      <div class="table-header">
        <h2 class="table-title">Doctors</h2>
        <div class="search-container">
          <div class="search-bar">
            <IconField iconPosition="left">
              <InputIcon>
                <i class="pi pi-search" />
              </InputIcon>
              <InputText v-model="doctorSearch" placeholder="Search Doctors"  />
            </IconField>
          </div>
        </div> 
      </div>
      <DataTable :value="filteredDoctors" dataKey="id" scrollable scrollHeight="1000px" expandableRows :filters="filters">

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
            <Button v-if="slotProps.data.isEditing" icon="pi pi-check" @click="saveDoctor(slotProps.data)" class="p-button-success p-mr-2" />
            <Button v-if="slotProps.data.isEditing" icon="pi pi-times" @click="cancelEditDoctor(slotProps.data)" class="p-button-danger" />
            <template v-else>
              <Button icon="pi pi-pencil" class="p-button-rounded p-button-success p-mr-2" @click="editDoctor(slotProps.data)" />
              <Button icon="pi pi-trash" class="p-button-rounded p-button-danger" @click="deleteDoctor(slotProps.data)" />
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
      filters: {},
      doctors: [],
      doctorSearch: ''
    };
  },
  setup() {
    const toast = useToast();
    function showSuccessDeleteDoctorMessage() {
      toast.add({
        severity: 'success',
        summary: 'Delete Successful',
        detail: 'Doctor deleted successfully!',
        life: 4000,
      });
    }
    function showUpdateDoctorSuccessMessage() {
      toast.add({
        severity: 'success',
        summary: 'Update Successful',
        detail: 'Doctor updated successfully!',
        life: 4000,
      });
    }
    return {showSuccessDeleteDoctorMessage,showUpdateDoctorSuccessMessage};
  },
  methods: {
    refreshData() {
      this.getDoctors();
    },
    editDoctor(doctor) {
      doctor.isEditing = true;
      doctor.editableFirstName = doctor.firstName;
      doctor.editableLastName = doctor.lastName;
      doctor.editablePhone = doctor.phone;
      doctor.editableEmail = doctor.email;
    },
   
    saveDoctor(doctor) {
      
      doctor.firstName = doctor.editableFirstName;
      doctor.lastName = doctor.editableLastName;
      doctor.phone = doctor.editablePhone;
      doctor.email = doctor.editableEmail;
      doctor.isEditing = false;
      this.saveEditDoctor(doctor);
      this.showUpdateDoctorSuccessMessage();
    },
    cancelEditDoctor(doctor) {
      doctor.isEditing = false;
    },
    doctorHasMedicalAssistants() {
      return this.doctors.map(doctor => !!doctor.medicalAssistants && doctor.medicalAssistants.length > 0);
    },
    saveEditDoctor(doctor) {
      console.log("Doctor to be updated", doctor);
      adminService.updateUser(doctor).then(response => {
        console.log(response.data);
    }).catch(error =>{
      console.log("Eroare la edit doctor: " + error);
    })
    },
    deleteDoctor(doctor) {
       adminService.deleteUser(doctor.id).then(response => {
          console.log(response.data);
          this.showSuccessDeleteDoctorMessage();
          this.refreshData();

       }).catch(error => {
        console.log("Erroare la delete doctor " + error);
       })
    },
   
    getDoctors() {
        return adminService.getAllDoctors()
        .then(response => {
          this.doctors = response.data;
          console.log(this.doctors)
        }).catch(error => {
          console.log(error);
        });
    },

  },
  computed: {
    filteredDoctors() {
      const searchQuery = this.doctorSearch.toLowerCase();
      return this.doctors.filter(doctor =>
        doctor.firstName.toLowerCase().includes(searchQuery) ||
        doctor.lastName.toLowerCase().includes(searchQuery) ||
        doctor.phone.toLowerCase().includes(searchQuery) ||
        doctor.email.toLowerCase().includes(searchQuery) ||
        doctor.username.toLowerCase().includes(searchQuery)
      );
    },
  },
  mounted() {
    this.getDoctors();
  },
};
</script>
<style scoped>
@import "../assets/styles/table_style.css"
</style>