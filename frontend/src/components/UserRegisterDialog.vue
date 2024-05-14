<template>
  <Dialog header="Register new user" v-model:visible="this.internalVisible" :modal="true" :style="{width: '35vw', content: 'center' }">
    <form @submit.prevent="submitForm" :validation-schema="schema">
     
      <div class="p-fluid">
        <div class="p-field">
          <InputText id="firstName" v-model="user.firstName" placeholder="First Name" required />
          <ErrorMessage name="firstName" class="error-feedback" />
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
          <InputText id="username" v-model="user.username" placeholder="Username" required />
          <ErrorMessage name="username" class="error-feedback" />
        </div>
        <div class="p-field">
          <InputText id="phone" v-model="user.phone" placeholder="Phone" />
          <ErrorMessage name="phone" class="error-feedback" />
        </div>
        <div class="p-field">
          <InputText id="password" v-model="user.password" type="password" placeholder="Password" required />
          <ErrorMessage name="password" class="error-feedback" />
        </div>
        <div class="p-field">
          <InputText id="repeatPassword" v-model="repeatPassword" type="password" placeholder="Repeat Password" required />
          <ErrorMessage name="repeatPassword" class="error-feedback" />
        </div>
        <div class="p-field">
          <DropDown id="role" v-model="user.role" :options ="this.roles" optionLabel="label" placeholder="Select users role" required />
          <ErrorMessage name="role" class="error-feedback" />
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
import { ErrorMessage } from 'vee-validate';
import * as yup from 'yup';
import { useToast } from 'primevue/usetoast';
import adminService from '@/services/admin.service';

export default{
  components: {
    Dialog,
    InputText,
    Button,
    DropDown,
    ErrorMessage,
    Toast
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
        detail: 'User registered successfully!',
        life: 4000,
      });
    }
   
    return { showSuccessMessage };
  },
  emits: ['update:visible', 'registeredUser'], 

  data() {
    const schema = yup.object().shape({
      email: yup
        .string()
        .required("Email is required!")
        .email("Email is invalid!")
        .max(50, "Must be maximum 50 characters!"),
      password: yup
        .string()
        .required("Password is required!")
        .min(6, "Must be at least 6 characters!")
        .max(40, "Must be maximum 40 characters!"),
      repeatPassword: yup
        .string()
        .required("Repeat Password is required!")
        .oneOf([yup.ref('password'), null], 'Passwords must match'),
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
      role: yup 
        .string()
        .required("Role is required!")
    });
    return {
      internalVisible: this.visible,
      user: {
        firstName: '',
        lastName: '',
        email: '',
        username: '',
        password: '',
        phone: '',
        role: ''
      },
      roles: [
                 {label: 'Doctor', value: '1'},
                 {label: 'Medical Assistant', value: '2'}
             ],
      repeatPassword: '',
      schema,
      errorMessage: ''
    }
  },
  methods: {
    async submitForm() {
    this.errorMessage = '';

    if (this.user.password !== this.repeatPassword) {
      this.errorMessage = "Passwords do not match";
      return;
    }

    // Check if the username is unique
    const isUsernameUnique = await this.checkUsernameUnique(this.user.username);
    if (!isUsernameUnique) {
      this.errorMessage = "Username is already taken";
      return;
    }
    this.user.role = this.user.role.label === 'Doctor' ? "DOCTOR" : "MEDICAL_ASSISTANT";
    this.$emit('registeredUser', this.user);
    this.showSuccessMessage();
    this.internalVisible = false;
  },
    cancel1() {
      this.internalVisible = false;
    },
    async checkUsernameUnique(username) {
  try {
    const response = await adminService.isUsernameTaken(username);
    return response;
  } catch (error) {
    console.error("Error checking username uniqueness:", error);
    this.errorMessage = "Error checking username"; 
    return false; 
  }
}
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

.button-34,
.button-35 {
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

.button-35 {
  background-color: #e34f4f;
  color: #ffffff;
  border: none;
}

.button-34:hover {
  background-color: #62cffd;
}

.button-35:hover {
  background-color: #ff6161;
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