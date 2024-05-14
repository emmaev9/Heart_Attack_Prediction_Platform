import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import PrimeVue from 'primevue/config';
import Button from 'primevue/button';
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import { FontAwesomeIcon } from './plugins/font-awesome'

import axios from 'axios'; // Add this line to import axios
import 'primevue/resources/themes/aura-light-green/theme.css'
import ToastService from 'primevue/toastservice';
//import Toast from 'primevue/toast';

axios.defaults.withCredentials = true;

createApp(App)
  .use(router)
  .use(store)
  .use(PrimeVue)
  .use(ToastService)
  .component("font-awesome-icon", FontAwesomeIcon)
  .component("Button", Button)
  .mount('#app')
