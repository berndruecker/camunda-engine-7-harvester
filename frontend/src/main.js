import Vue from 'vue'
import App from './App.vue'
import router from './router'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import moment from 'moment'

// import('./assets/css/styles.css')
// import('./assets/css/res/checkmark.svg')

Vue.config.productionTip = false;

// Bootstrap
Vue.use(BootstrapVue);

new Vue({
    router,
    render: h => h(App)
}).$mount('#app');



Vue.filter('formatDate', function(value) {
  if (value) {
    return moment(String(value)).format('MM/DD/YYYY HH:mm:ss')
  }
});
