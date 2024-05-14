<template>
  <div class="home-admin">
    <br>
    <br> 
    <h1>Welcome to the Admin Home Page</h1>
    <div class="buttons-container">
      <Button label="Register User"  @click="showUserRegisterDialog" class="button-34"/>
    </div>
    <UserRegisterDialog :visible="visibleUserDialog" @update:visible="this.updateUserDialogVisibility" @registeredUser="this.registerUser"/>
    <hr>
    <div class="users-tables">
      <DoctorsTable ref="doctorsTable" /> 
      <hr>
      <AssistantsTable ref="assistantsTable" />
    </div>

  </div>
</template>

<script>
import AssistantsTable from '../components/AssistantsTable.vue'
import DoctorsTable from '../components/DoctorsTable.vue'
import Button from 'primevue/button'
import UserRegisterDialog from '../components/UserRegisterDialog.vue'
import AdminService from '@/services/admin.service'
//import { ref } from 'vue'

export default {
  name: 'AdminHomeView',
  
  components: {
    AssistantsTable,
    DoctorsTable,
    Button,
    UserRegisterDialog
  },
  data (){
    return{
      visibleUserDialog: false,
    }
  },
  methods:{
    showUserRegisterDialog(){
      this.visibleUserDialog = true;
    },
    
    updateUserDialogVisibility(newValue){
      this.visibleUserDialog = newValue;
    },
   
    registerUser(user){
      console.log(user);
      AdminService.registerUser(user)
      .then(response => {
        console.log(response);
        this.$store.dispatch('registerUser', user);
        this.$refs.doctorsTable.refreshData();
        this.$refs.assistantsTable.refreshData();
  
      }).catch(error => {
        console.log(error);
      });
    }
  }
}
</script>

<style scoped>
.buttons-container {
  display: flex;
  justify-content: center;
  gap: 10px; 
}
.button-34 {
  background: #4fafe3;
  border-radius: 999px;
  box-shadow: #66666a 0 10px 20px -10px;
  box-sizing: border-box;
  color: #FFFFFF;
  cursor: pointer;
  font-family: Inter, Helvetica, "Apple Color Emoji", "Segoe UI Emoji", NotoColorEmoji, "Noto Color Emoji", "Segoe UI Symbol", "Android Emoji", EmojiSymbols, -apple-system, system-ui, "Segoe UI", Roboto, "Helvetica Neue", "Noto Sans", sans-serif;
  font-size: 16px;
  font-weight: 700;
  line-height: 24px;
  opacity: 1;
  outline: 0 solid transparent;
  padding: 8px 18px;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  width: fit-content;
  word-break: break-word;
  border: 0;
}

.users-tables {
  margin-top: 20px;
  margin-left: 80px;
  margin-right: 80px;
}

</style>

