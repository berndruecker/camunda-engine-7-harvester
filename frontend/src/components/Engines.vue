<template>
  <div class="container">
    <h1>Camunda Engine Harvester</h1>

    <button @click="getEngines()" type="button" class="btn btn-link">Refresh</button>

    <!--<p>Backend response: {{ engines }}</p> -->
    <table class="table">
      <thead>
        <tr>
          <th scope="col">Engine Deployment</th>
          <th scope="col">Version</th>
          <th scope="col" style="text-align:center;">FNI Count</th>
          <th scope="col" style="text-align:center;">Connection Status</th>
        </tr>
      </thead>
      <tbody>
          <tr v-for="engine in engines" :key="engine.name" >
            <td>
              <h2><a :href="engine.cockpit">{{engine.name}}</a></h2>
              <p>{{engine.endpoint}}</p>
            </td>
            <td>
              {{engine.version}}
            </td>
            <td style="text-align:center;">
              <div v-bind:class="[{'text-success': engine.isInFniLimit}, {'text-error': !engine.isInFniLimit}]">
                {{engine.fniCounter}}<br>
                ({{engine.fniLimit}})
              </div>
            </td>
            <td style="text-align:center;">
              <div v-if="engine.lastUpdateSuccesful" class="text-success">&check;<br>{{engine.latestUpdate | formatDate}}</div>
              <div v-if="!engine.lastUpdateSuccesful" class="text-warning">	&cross;<br>last successful connect at<br>{{engine.latestUpdate | formatDate}}</div>
            </td>
          </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
  import api from "./backend-api";

  export default {
    name: 'engines',

    data () {
      return {
        engines: [],
        errors: []
      }
    },
    methods: {
      getEngines () {
        api.getEngines().then(response => {
            this.engines = response.data;
            console.log(response.data)
        })
        .catch(error => {
          this.errors.push(error)
        })
      }
    }
  }

</script>

