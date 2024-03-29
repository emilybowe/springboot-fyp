import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import router from './router'
import axios from 'axios'

Vue.config.productionTip = false
axios.defaults.baseURL = 'http://localhost:8081/'
new Vue({
  vuetify,
  render: h => h(App),
  router
}).$mount('#app')
