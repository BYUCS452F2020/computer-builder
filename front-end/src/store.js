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
      case: null 
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
      console.log("changing cpu!")
        state.currentBuild.cpu = cpu;
    },
    changeMotherboard(state, mobo) {
        state.currentBuild.mobo = mobo;
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
    changeCoolers(state, coolers) {
        state.currentBuild.coolers = coolers;
    },
    changeCases(state, cases) {
        state.currentBuild.cases = cases;
    },
    toggleCPU(state) {
      if (state.cpuChosen == true)
        state.cpuChosen = false;
      else
        state.cpuChosen = true;
      console.log("CPU is now " + state.cpuChosen)
    },
    toggleMotherboard(state) {
      if (state.motherboardChosen == true)
        state.motherboardChosen = false;
      else
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
        let response = await axios.post("http://localhost:8081/user/login", data);
        context.commit('setUser', response.data);
        return "";
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
        let response = await axios.get("/api/builds");
        context.commit('setBuilds', response.data);
        return "";
      } catch (error) {
        return "";
      }
    },
    async addBuild(context, data) {
      try {
        await axios.post('/api/builds', data);
        return "";
      } catch (error) {
        return error.response.data.message;
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