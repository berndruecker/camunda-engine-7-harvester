import axios from 'axios'

const AXIOS = axios.create({
  baseURL: `/api`,
  timeout: 1000
});


export default {
    getEngine(id) {
        return AXIOS.get(`/engine/` + id);
    },
    getEngines() {
        return AXIOS.get(`/engine`);
    }
}


