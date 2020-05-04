import Vue from 'vue'
import Router from 'vue-router'
import Engines from '@/components/Engines'

Vue.use(Router);

const router = new Router({
    mode: 'history', // uris without hashes #, see https://router.vuejs.org/guide/essentials/history-mode.html#html5-history-mode
    routes: [
        { path: '/', component: Engines },

        // otherwise redirect to home
        { path: '*', redirect: '/' }
    ]
});

export default router;