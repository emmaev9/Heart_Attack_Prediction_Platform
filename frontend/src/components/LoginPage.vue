<template>
  <div class="container vh-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-lg-4 col-sm-11">
          <div class="card text-black  shadow-lg p-3 mb-5 bg-white rounded">
            <div class="card-body">
              <div class="row justify-content-center">
                <div class="col-md-12">

                  <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Login</p>

                  <Form @submit="handleLogin" :validation-schema="schema" class="mx-1 mx-md-4">

                    <div class="d-flex flex-row align-items-center mb-4">
                      <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                      <div class="form-outline flex-fill mb-0">
                        <Field type="text" name="username" class="form-control" placeholder="Your username" />
                        <ErrorMessage name="username" class="error-feedback" />
                      </div>
                    </div>

                    <div class="d-flex flex-row align-items-center mb-4">
                      <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                      <div class="form-outline flex-fill mb-0">
                        <Field type="password" class="form-control" name="password" placeholder="Your password" />
                        <ErrorMessage name="password" class="error-feedback" />
                      </div>
                    </div>


                    <div class="button-container">
                      <button role="button" class="button-34">
                        Login
                      </button>
                    </div>
                  </form>

                </div>
                <div class="form-group">
                  <div v-if="message" class="alert alert-danger" role="alert">
                    {{ message }}
                  </div>
                </div>

              </div>
            </div>
          </div>
        </div>
      </div>
    

</div>
</template>

<script>
import { Form, Field, ErrorMessage } from "vee-validate";
import * as yup from "yup";
import "primevue/resources/themes/mira/theme.css";  
import "primevue/resources/primevue.min.css"; 
import "primeicons/primeicons.css"; 

export default {
  name: "LoginPage",
  components: {
    Form,
    Field,
    ErrorMessage,
  },

  data() {
    const schema = yup.object().shape({
      username: yup.string().required("Username is required"),
      password: yup.string().required("Password is required")
    });
    return {
      loading: false,
      message: "",
      schema,
    };
  },

  computed: {
    isLoggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
    currentUser() {
      return this.$store.state.auth.user;
    }
  },

  created() {
    if (this.loggedIn) {
      if (this.currentUser['role'] == 'DOCTOR') {
            this.$router.push("/DoctorHome");
          }
          if (this.currentUser['role'] == 'ADMIN') {
            this.$router.push("/AdminHome");
          }
          if (this.currentUser['role'] == 'MEDICAL_ASSISTANT') {
            this.$router.push("/MedicalAssistantHome");
          }
    }
  },
  methods: {
    handleLogin(user) {
      console.log(user);
      this.login = true;

      this.$store.dispatch("auth/login", user).then(
        () => {
          console.log("Dupa auth login: " + this.currentUser);
          if (this.currentUser['role'] == 'DOCTOR') {
            this.$router.push("/DoctorHome");
          }
          if (this.currentUser['role'] == 'ADMIN') {

            this.$router.push("/AdminHome");
          }
          if (this.currentUser['role'] == 'MEDICAL_ASSISTANT') {
            this.$router.push("/MedicalAssistantHome");
          }
        },
        (error) => {
          this.loading = false;
          this.message =
            (error.response && error.response.data && error.response.data.message) ||
            error.message ||
            error.toString();
        }
      );
    },
  }
}

</script>

<style>
@import url(../assets/styles/update_button.css); 


.error-feedback {
  color: red;
  font-size: 0.8rem;
}
.bg-img{
  width: 100vh;
  height: 100vw;
  object-fit: contain;
}
.form-control:focus {
  border-color: #1b6d6d;
  box-shadow: 0 0 0 0.2rem rgba(71, 230, 161, 0.25);
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
  justify-content: center;
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

</style>