<template>
  <div>
    
      <h1>Computer Builder</h1>
      <span id="options_wrapper"> 
        <span class="options_item"> 
          <h3>What performance level are you looking for? (1-9)</h3>      
          <input v-model="performance_rating" type="number" @blur="updatePR()" placeholder="1-5">
        </span>
        <!-- <span>
          <h3>What is your total budget?</h3>      
          <input v-model="max_budget" type="number" @blur="updateBudget()" placeholder="">
        </span> -->
      </span>
      <br><br>
      <span id="wrapper">
      <span id="listOfComps">
      <CPUs/>
      <MOBOs/>
      <GPUs/>
      <RAM/>
      <Storage/>
      <PSUs/>
      <Coolers/>
      <Cases/>
      <br> <br>
      <input v-model="build_name" placeholder="Name your build"> <br> <br>
      <button class="component_label" v-on:click="saveBuild()">Save build</button>
      </span>
      <span id="currentBuild">
        <h3>Your Current Build</h3>
        <span v-if="this.$store.getters.cpuChosen">
        <p>CPU: {{this.$store.getters.getCurrentBuild.cpu.componentName}}</p>
        </span>
        <span v-if="this.$store.getters.motherboardChosen">
        <p>Motherboard: {{this.$store.getters.getCurrentBuild.motherboard.componentName}}</p>
        </span>
        <span v-if="this.$store.getters.gpuChosen">
        <p>GPU: {{this.$store.getters.getCurrentBuild.gpu.componentName}}</p>
        </span>
        <span v-if="this.$store.getters.ramChosen">
        <p>RAM: {{this.$store.getters.getCurrentBuild.ram.componentName}}</p>
        </span>
        <span v-if="this.$store.getters.storageChosen">
        <p>Storage: {{this.$store.getters.getCurrentBuild.storage.componentName}}</p>
        </span>
        <span v-if="this.$store.getters.psuChosen">
        <p>Power Supply: {{this.$store.getters.getCurrentBuild.psu.componentName}}</p>
        </span>
        <span v-if="this.$store.getters.coolerChosen">
        <p>CPU Cooler: {{this.$store.getters.getCurrentBuild.cooler.componentName}}</p>
        </span>
        <span v-if="this.$store.getters.caseChosen">
        <p>Case: {{this.$store.getters.getCurrentBuild.case.componentName}}</p>
        </span>
        <p>Name: {{build_name}}</p>
      </span>
      </span>
    </div>
    
</template>

<script>
import CPUs from "../components/cpus.vue"
import MOBOs from "../components/motherboards.vue"
import Coolers from "../components/coolers.vue"
import GPUs from "../components/gpus.vue"
import PSUs from "../components/psu.vue"
import Cases from "../components/case.vue"
import RAM from "../components/ram.vue"
import Storage from "../components/storage.vue"

export default {
  name: 'builder',
  components: {
    CPUs,
    MOBOs,
    Coolers,
    Cases,
    GPUs,
    PSUs,
    RAM,
    Storage
  },
  props: {
  },
  data () {
    return {
      build_name: "",
      performance_rating: 0,
      max_budget: 0.0,
      remaining_budget: 0.0
    }
  },
  computed: {
    remainingBudget() {
      return 7
    }
  },
  methods: {
    async saveBuild() {
      try {
        //TODO dispatch adding build
        if (this.$store.getters.getUser == null) {
          console.log("GOTTA BE LOGGED IN!!!!!!")
          return
        }
        this.$store.commit('changeName', this.build_name);
        this.error = await this.$store.dispatch("saveCurrentBuild")
        if (!this.error) {
          this.title = '';
          this.description = '';
          this.file = null;
          this.$emit('uploadFinished');
        }
      } catch (error) {
        console.log(error);
      }
    },
      async getAllComponents() {
      try {
        this.error = await this.$store.dispatch("getAllComponents")
        const formData = new FormData();
        formData.append('')
      } catch (error) {
        console.log(error);
      }
    },
    updatePR() {
      this.$store.commit("updatePerformanceRating", this.performance_rating)
    },
    updateBudget() {
      this.$store.commit("updateMaxBudget", this.max_budget)
    }
  }
}
</script>

<style>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
.component_label {
  background-color: #4c63af; /* Green */
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
}

#listOfComps {
  max-width: 30%;
  min-width: 30%;
}

#currentBuild {
  max-width: 30%;
  min-width: 30%;
}

.on_right {
  display: flex;
  justify-content: flex-end;
}

#options_wrapper {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
}

.options_item {
  display: flex;
  flex-direction: column;
  justify-content: center;
} 

#wrapper {
  display: flex;
  justify-content: center;
}

.item_list {
  display: flex;
  flex-direction: column;
}

.list_item {

}
</style>
