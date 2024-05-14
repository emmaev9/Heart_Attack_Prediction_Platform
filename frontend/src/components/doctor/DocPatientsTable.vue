<template>
  <div class="card">
    <div class="table-header">
      <h2 class="table-title">Patient Directory</h2>
      <p class="table-description">The list of all the patients currently registered in the system.</p>
    </div>
    <DataTable :value="patients" dataKey="id" scrollable scrollHeight="600px" expandableRows :filters="filters">
      <Column field="CNP" header="CNP" sortable> 
        <template #body="slotProps">
            {{ slotProps.data.cnp }}      
        </template>
      </Column>

      <Column field="firstName" header="First Name" sortable>
        <template #body="slotProps">
            {{ slotProps.data.firstName }}
        </template>
      </Column>

      <Column field="lastName" header="Last Name" sortable>
        <template #body="slotProps">
            {{ slotProps.data.lastName }}
        </template>
      </Column>

      <Column filed="lastVisitDate" header="Last Visit Date" sortable>
        <template #body="slotProps">
            {{ slotProps.data.latestVisit }}
        </template>
      </Column>
       
      <Column header="Actions" bodyStyle="text-align: center;" headerStyle="width: 8em">
        <template #body="slotProps">
          <div class="button-container">
            <Button icon="pi pi-eye" label="Consult" @click="consultPatient(slotProps.data.id)" class="p-button-success p-mr-2" />
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
import Toast  from 'primevue/toast';
import { useToast } from 'primevue/usetoast';

export default {
  name: 'DocPatientsTable',
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
    getPatientVisitDate(patient){
      const visit = patient.visit;
      return visit.visitDate;
    },

    consultPatient(patientId){
      console.log("Consult patient with ID: ", patientId);
      this.$router.push({name: 'DocPatientHome', params: {id: patientId}});
    },
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