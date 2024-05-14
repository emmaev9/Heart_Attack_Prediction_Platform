<template>
  <Dialog header="Register new patient" v-model:visible="this.internalVisible" :modal="true" :style="{width: '35vw', content: 'center' }">
    <form @submit.prevent="submitForm" :validation-schema="schema">
     
      <div class="p-fluid">
        <div class="p-field"> 
       <!-- <FloatLabel> -->
            <InputText id="firstName" v-model="user.firstName" placeholder="First Name" required />
      <!--      <label for="firstName">First Name</label> -->
            <ErrorMessage name="firstName" class="error-feedback" />
        <!--  </FloatLabel> -->
        </div>
        <div class="p-field">
          <InputText id="lastName" v-model="user.lastName" placeholder="Last Name" required />
          <ErrorMessage name="lastName" class="error-feedback" />
        </div>
        <div class="p-field">
          <InputText id="email" v-model="user.email" type="email" placeholder="Email" required />
          <ErrorMessage name="email" class="error-feedback" />
        </div>
        <div class="p-field">
          <InputText id="CNP" v-model="user.cnp" placeholder="CNP" required />
          <ErrorMessage name="CNP" class="error-feedback" />
        </div>
        <div class="p-field">
          <InputText id="phone" v-model="user.phone" placeholder="Phone" />
          <ErrorMessage name="phone" class="error-feedback" />
        </div>
        <div class="p-field">
          <Calendar id="birthday" v-model="user.birthday" placeholder="Select birthday" showIcon :showOnFocus="false" />
          <ErrorMessage name="birthday" class="error-feedback" />
        </div>
   
        <div class="p-field">
          <DropDown id="gender" v-model="user.gender" :options ="this.genders" optionLabel="label" placeholder="Select gender" required />
          <ErrorMessage name="gender" class="error-feedback" />
        </div>
      </div>
      <div class="button-container">
        <Button label="Save" type="submit" class="button-34" />
      </div>
      <div v-if="errorMessage" class="error-feedback">{{ errorMessage }}</div>
    </form> 
  </Dialog>
  <Toast />
</template>

<script>
import Dialog from 'primevue/dialog';
import InputText from 'primevue/inputtext';
import Button from 'primevue/button';
import DropDown from 'primevue/dropdown';
import Toast from 'primevue/toast';
import Calendar from 'primevue/calendar';
import { ErrorMessage } from 'vee-validate';
import * as yup from 'yup';
import { useToast } from 'primevue/usetoast';
//import FloatLabel from 'primevue/floatlabel';

export default{
  components: {
    Dialog,
    InputText,
    Button,
    DropDown,
    ErrorMessage,
    Toast,
    Calendar
  },
  props:{
    visible: Boolean
  },
  setup() {
    const toast = useToast();
    function showSuccessMessage() {
      toast.add({
        severity: 'success',
        summary: 'Registration Successful',
        detail: 'Patient registered successfully!',
        life: 4000,
      });
    }
   
    return { showSuccessMessage };
  },
  emits: ['update:visible', 'registeredPatient'], 

  data() {
    const schema = yup.object().shape({
      email: yup
        .string()
        .required("Email is required!")
        .email("Email is invalid!")
        .max(50, "Must be maximum 50 characters!"),
      firstName: yup
        .string()
        .required("First Name is required!"),
      lastName: yup
        .string()
        .required("Last Name is required!"),
      username: yup
        .string()
        .required("Username is required!"),
      phone: yup
        .string()
        .max(10, "Must be maximum 10 characters!"),
      cnp: yup
        .string()
        .required("CNP is required!")
        .max(13, "Must be maximum 13 characters!"),
      birthday: yup
        .string()
        .required("Birthday is required!"),
    });
    return {
      internalVisible: this.visible,
      user: {
        firstName: '',
        lastName: '',
        email: '',
        phone: '',
        cnp: '',
        birthday: '',
        gender: null
    
      },
      genders: [
                 {label: 'Female', value: '1'},
                 {label: 'Male', value: '2'}
             ],
      schema,
      errorMessage: ''
    }
  },
  methods: {
    async submitForm() {
    this.errorMessage = '';
    this.user.gender = this.user.gender.label === 'Male' ? "Male" : "Female";
    console.log("Inainte de emit: " + this.user);
    this.$emit('registeredPatient', this.user);
    this.showSuccessMessage();

    this.internalVisible = false;
  },
    cancel1() {
      this.internalVisible = false;
    },
  },
  watch: {
    visible(newValue) {
      this.internalVisible = newValue;
    },
    internalVisible(newValue) {
      this.$emit('update:visible', newValue);
    }
  },
}

</script>
<style scoped>
.dialog-content {
  padding: 20px;
}

.button-container {
  display: flex;
  justify-content: center;
}

.button-34 {
  width: 150px;
  height: 40px; 
  border-radius: 10px;
  padding: 10px 20px;
  font-weight: bold;
  margin: 5px;
  transition: background-color 0.3s;
  background: #e34f4f;
  border-radius: 999px;
  box-shadow: #66666a 0 10px 20px -10px;
  box-sizing: border-box;
  color: #FFFFFF;
  cursor: pointer;
}

.button-34 {
  background-color: #4fafe3;
  color: #ffffff;
  border: none;
}

.button-34:hover {
  background-color: #62cffd; 
}

.p-field {
  margin-bottom: 10px; 
  padding: auto;
  margin-left: 7px;
  margin-right: 7px;
}
.error-feedback {
  color: #ff6161;
  font-size: 0.8rem;
}
</style>