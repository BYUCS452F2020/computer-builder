import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: null,
    builds: [],
    cpus: [],
    motherboards: [],
    gpus: [],
    rams: [],
    storages: [],
    psus: [],
    coolers: [],
    cases: [],
    cpuChosen: false,
    motherboardChosen: false,
    totalPSUReq: 0,
    maxPrice: 10000,
    performanceRating: 0,
    cpuFamily: null,
    currentBuild: {
      cpu: null,
      motherboard: null,
      gpu: null,
      ram: null,
      storage: null,
      psu: null,
      cooler: null,
      case: null ,
      name: null
    },
  },
  mutations: {
    setUser(state, user) {
        console.log("user is: " + user.username)
        state.user = user.username;
    },
    setBuilds(state, builds) {
        state.builds = builds;
    },
    setCPUs(state, cpus) {
        state.cpus = cpus;
    },
    setMotherboards(state, motherboards) {
        state.motherboards = motherboards;
    },
    setGPUs(state, gpus) {
        state.gpus = gpus;
    },
    setRams(state, rams) {
        state.rams = rams;
    },
    setStorages(state, storages) {
        state.storages = storages;
    },
    setPSUs(state, psus) {
        state.psus = psus;
    },
    setCoolers(state, coolers) {
        state.coolers = coolers;
    },
    setCases(state, cases) {
        state.cases = cases;
    },
    changeCPU(state, cpu) {
      console.log("changing cpu to: " + cpu.componentName)
        state.currentBuild.cpu = cpu;
        //set cpu family
        state.cpuFamily = cpu.cpuFamily;
        console.log("set cpu family to" + cpu.cpuFamily)
    },
    changeMotherboard(state, mobo) {
        state.currentBuild.motherboard = mobo;
        //set cpu family
        state.cpuFamily = mobo.cpuFamily;
        console.log("set cpu family to" + mobo.cpuFamily)
    },
    changeGPU(state, gpu) {
        state.currentBuild.gpu = gpu;
    },
    changeRAM(state, ram) {
        state.currentBuild.ram = ram;
    },
    changeStorage(state, storage) {
        state.currentBuild.storage = storage;
    },
    changePSU(state, psu) {
        state.currentBuild.psu = psu;
    },
    changeCooler(state, cooler) {
        state.currentBuild.cooler = cooler;
    },
    changeCase(state, casE) {
        state.currentBuild.case = casE;
    },
    changeName(state, name) {
      state.currentBuild.name = name
    },
    toggleCPU(state) {
        state.cpuChosen = true;
      console.log("CPU is now " + state.cpuChosen)
    },
    toggleMotherboard(state) {
        state.motherboardChosen = true;
    },
    setPSUReq(state, num) {
      if (num == 0) {
        state.totalPSUReq = 0;
      }
      else {
        state.totalPSUReq = state.totalPSUReq + num;
      }
    }
  },
  getters: {
    getUser(state) {
      return state.user
    },
    getBuilds (state) {
      return state.builds
    },
    getMaxPrice(state) {
      return state.maxPrice
    },
    getPerformanceRating(state) {
      return state.performanceRating
    },
    getCpuFamily(state) {
      return state.cpuFamily
    },
    getCpus(state) {
      return state.cpus
    },
    getMotherboards(state) {
      return state.motherboards
    },
    getGpus(state) {
      return state.gpus
    },
    getRams(state) {
      return state.rams
    },
    getStorages(state) {
      return state.storages
    },
    getPsus(state) {
      return state.psus
    },
    getCoolers(state) {
      return state.coolers
    },
    getCases(state) {
      return state.cases
    },
    getCurrentBuild(state) {
      console.log("getting current build")
      return state.currentBuild
    },
    cpuChosen(state) {
      if (state.currentBuild.cpu != null)
        return true
      else
        return false
    },
    motherboardChosen(state) {
      if (state.currentBuild.motherboard != null)
        return true
      else
        return false
    },
    gpuChosen(state) {
      if (state.currentBuild.gpu != null)
        return true
      else
        return false
    },
    ramChosen(state) {
      if (state.currentBuild.ram != null)
        return true
      else
        return false
    },
    storageChosen(state) {
      if (state.currentBuild.storage != null)
        return true
      else
        return false
    },
    psuChosen(state) {
      if (state.currentBuild.psu != null)
        return true
      else
        return false
    },
    coolerChosen(state) {
      if (state.currentBuild.cooler != null)
        return true
      else
        return false
    },
    caseChosen(state) {
      if (state.currentBuild.case != null)
        return true
      else
        return false
    }
  },
    actions: {
    async register(context, data) {
      try {
        console.log("registering!")
        let response = await axios.post("http://localhost:8081/user/register", data);
        context.commit('setUser', response.data);
        //console.log("user is " + context.state.user);
        return "";
      } catch (error) {
        //console.log(error.response.data.message);
        return error.response.data.message;
      }
    },
    async login(context, data) {
      try {
        console.log(data)
        let response = await axios.post("http://localhost:8081/user/login/" +
                                          data.username + "/" +
                                          data.password);
        context.commit('setUser', response.data);
        return response.data.username;
      } catch (error) {
        return error.response.data.message;
      }
    },
    async logout(context) {
      try {
        await axios.delete("/api/users");
        context.commit('setUser', null);
        return "";
      } catch (error) {
        return error.response.data.message;
      }
    },
    async getUser(context) {
      try {
        let response = await axios.get("/api/users");
        context.commit('setUser', response.data);
        return "";
      } catch (error) {
        return "";
      }
    },
    async getUserBuilds(context) {
      try {
        let response = await axios.get("http://localhost:8081/build/"
                                        + this.getters.getUser)
        context.commit('setBuilds', response.data);
        return "";
      } catch (error) {
        return "";
      }
    },
    async saveCurrentBuild() {
      try {
        let currentBuild = this.getters.getCurrentBuild
        console.log(this.getters.getUser)
        console.log(currentBuild.name)
        await axios.post('http://localhost:8081/builds/insert', {
          username: this.getters.getUser,
          cpu: currentBuild.cpu.componentID,
          motherboard: currentBuild.motherboard.componentID,
          gpu: currentBuild.gpu.componentID,
          ram: currentBuild.ram.componentID,
          storage: currentBuild.storage.componentID,
          psu: currentBuild.psu.componentID,
          cooler: currentBuild.cooler.componentID,
          pc_case: currentBuild.case.componentID,
          build_name: currentBuild.name
        });
        return "";
      } catch (error) {
        return error.response.data.message;
      }
    },
    async getSingleComponent(context, data) {
      try {
        let response = await axios.get("http://localhost:8081/one_component/" + data.componentID)
        return response.data
      } catch (error) {
        return "couldn't get cpus";
      }      
    },
    async getCPUs(context, data) {
      try {
        let response = await axios.get("http://localhost:8081/component/" +
                                        data.componentType + "/" +
                                        data.cpuFamily + "/" +
                                        data.performanceRating + "/" +
                                        data.maxPrice);
        context.commit('setCPUs', response.data.components);
        return "";
      } catch (error) {
        return "couldn't get cpus";
      }      
    },
    async getMotherboards(context, data) {
      try {
        let response = await axios.get("http://localhost:8081/component/" +
                                        data.componentType + "/" +
                                        data.cpuFamily + "/" +
                                        data.performanceRating + "/" +
                                        data.maxPrice);
        context.commit('setMotherboards', response.data.components);
        return "";
      } catch (error) {
        return "couldn't get motherboards";
      }  
    },
    async getGPUs(context, data) {
      try {
        let response = await axios.get("http://localhost:8081/component/" +
                                        data.componentType + "/" +
                                        data.cpuFamily + "/" +
                                        data.performanceRating + "/" +
                                        data.maxPrice);
        context.commit('setGPUs', response.data.components);
        return "";
      } catch (error) {
        return "couldn't get gpus";
      }  
    },
    async getRams(context, data) {
      try {
        let response = await axios.get("http://localhost:8081/component/" +
                                        data.componentType + "/" +
                                        data.cpuFamily + "/" +
                                        data.performanceRating + "/" +
                                        data.maxPrice);
        context.commit('setRams', response.data.components);
        return "";
      } catch (error) {
        return "couldn't get rams";
      } 
    },
    async getStorages(context, data) {
      try {
        let response = await axios.get("http://localhost:8081/component/" +
                                        data.componentType + "/" +
                                        data.cpuFamily + "/" +
                                        data.performanceRating + "/" +
                                        data.maxPrice);
        context.commit('setStorages', response.data.components);
        return "";
      } catch (error) {
        return "couldn't get storages";
      } 
    },
    async getPSUs(context, data) {
      try {
        let response = await axios.get("http://localhost:8081/component/" +
                                        data.componentType + "/" +
                                        data.cpuFamily + "/" +
                                        data.performanceRating + "/" +
                                        data.maxPrice);
        context.commit('setPSUs', response.data.components);
        return "";
      } catch (error) {
        return "couldn't get psus";
      } 
    },
    async getCoolers(context, data) {
      try {
        let response = await axios.get("http://localhost:8081/component/" +
                                        data.componentType + "/" +
                                        data.cpuFamily + "/" +
                                        data.performanceRating + "/" +
                                        data.maxPrice);
        context.commit('setCoolers', response.data.components);
        return "";
      } catch (error) {
        return "couldn't get coolers";
      }  
    },
    async getCases(context, data) {
      try {
        let response = await axios.get("http://localhost:8081/component/" +
                                        data.componentType + "/" +
                                        data.cpuFamily + "/" +
                                        data.performanceRating + "/" +
                                        data.maxPrice);
        context.commit('setCases', response.data.components);
        return "";
      } catch (error) {
        return "couldn't get cases";
      } 
    }
  }, 
})