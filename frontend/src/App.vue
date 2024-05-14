<template>
  <div>

  </div>
 <div id="app">
    <nav class="navbar navbar-expand shadow fixed-top" style="background-color:white;">     

      <div v-if="this.currentUser" class="navbar-nav ml-auto">
        <div v-if="this.isAdmin" class="nav-item">
          <router-link to="/AdminHome" class="nav-link">
             Home 
          </router-link>
        </div>
  
       <div v-if="this.isDoctor" class="nav-item">
          <router-link to="/DoctorHome" class="nav-link">
             Home
          </router-link>
        </div>

        <div v-if="this.isMedicalAssistant" class="nav-item">
          <router-link to="/MedicalAssistantHome" class="nav-link">
             Home
          </router-link>
        </div>

        <div class="navbar-nav ml-auto">
            <router-link @click="logOut()" to="/login" class="nav-link">
              Logout
            </router-link>
        </div>
      </div>

      <div v-if="!this.currentUser" class="nav-item">
        
          <router-link to="/login" class="nav-link">
             Login
          </router-link>
       
      </div>
       
      
    </nav>

      <div class="main-container">
        <router-view />
      </div>
  </div>
</template>

<script>


export default {

  computed: {
    currentUser() {
      return this.$store.state.auth.user;
      
    },
    isAdmin() {
      if (this.currentUser && this.currentUser['role']) {
        console.log(this.currentUser['role']);
        return this.currentUser['role'] == 'ADMIN';
      }
      return false;
    },
    isDoctor() {
      if (this.currentUser && this.currentUser['role']) {
        console.log(this.currentUser['role']);
        return this.currentUser['role'] == 'DOCTOR';
      }
      return false;
    },
    isPatient() {
      if (this.currentUser && this.currentUser['role']) {
        console.log(this.currentUser['role']);
        return this.currentUser['role'] == 'PATIENT';
      }
      return false;
    },
    isMedicalAssistant() {
      if (this.currentUser && this.currentUser['role']) {
        console.log(this.currentUser['role']);
        return this.currentUser['role'] == 'MEDICAL_ASSISTANT';
      }
      return false;
    }
  },
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    }
  }
};
</script>


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #ffffff;
  width: 100%;
}
.main-container{
  /* Center content horizontally and vertically */

  justify-content: center;
  align-items: center;
  height: 100%;
            width: 100%;
}

.material-symbols-outlined {
  font-variation-settings:
  'FILL' 0,
  'wght' 400,
  'GRAD' 0,
  'opsz' 24
}


.bg-img{
  width: 100vh;
  height: 100vw;
  object-fit: contain;
  filter: brightness(0.6);
  top:0;
  left:0;
  z-index:-1;
  position: absolute
}
.navbar{
  height: 40px;
  font-size: 15px;
  font-weight: bold;
  background-color:#bacbda !important;
  color:rgb(255, 254, 254);
  text-align: center;
  align-items: center;
  padding-left: 20px;
  padding-right: 20px;
  opacity: 0.8;
  box-shadow: 0 2px 20px rgba(0, 0, 0, 0.1);

  /*
  .button {
    background-color: #007BFF;
    color: #fff;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
    text-transform: uppercase;
  }
  .button:hover {
    background-color: #0069D9;
  }
  .button:active {
    background-color: #0069D9;
    transform: translateY(2px);
  }
  .button:focus {
    outline: none;
  }
  .button:disabled {
    background-color: #0069D9;
    opacity: 0.5;
    cursor: not-allowed;
  }
  .button:not(:disabled):active {
    transform: translateY(2px);
  }  
*/

}
</style>