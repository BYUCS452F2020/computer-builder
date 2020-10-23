<template>
  <div>
    
      <h1>Builder Page</h1>
      <CPUs/>
      <MOBOs/>
      <GPUs/>
      <RAM/>
      <Storage/>
      <PSUs/>
      <Coolers/>
      <Cases/>
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

.on_right {
  display: flex;
  justify-content: flex-end;
}

</style>
