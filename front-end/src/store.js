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
        let response = await axios.post("/api/users/login", data);
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
    async getCPUs(context) {
      if (!context.state.motherboardChosen){
        try {
          let response = await axios.get("/api/cpus");
          context.commit('setCPUs', response.data);
          return "";
        } catch (error) {
          return "";
        }
      }
      if (context.state.motherboardChosen){
        //get it but with mobo constraints
      }
    },
    async getMotherboards(context) {
      if (!context.state.cpuChosen){
        try {
          let response = await axios.get("/api/motherboards");
          context.commit('setMotherboards', response.data);
          return "";
        } catch (error) {
          return "";
        }
      }
      if (context.state.cpuChosen){
        //get it but with mobo constraints
      }
    },
    async getGPUs(context) {
      try {
        let response = await axios.get("/api/gpus");
        context.commit('setGPUs', response.data);
        return "";
      } catch (error) {
        return "";
      }
    },
    async getRams(context) {
      try {
        let response = await axios.get("/api/rams");
        context.commit('setRams', response.data);
        return "";
      } catch (error) {
        return "";
      }
    },
    async getStorages(context) {
      try {
        let response = await axios.get("/api/storages");
        context.commit('setStorages', response.data);
        return "";
      } catch (error) {
        return "";
      }
    },
    async getPSUs(context) {
      try {
        let response = await axios.get("/api/psus");
        context.commit('setPSUs', response.data);
        return "";
      } catch (error) {
        return "";
      }
    },
    async getCoolers(context) {
      try {
        let response = await axios.get("/api/coolers");
        context.commit('setCoolers', response.data);
        return "";
      } catch (error) {
        return "";
      }
    },
    async getCases(context) {
      try {
        let response = await axios.get("/api/cases");
        context.commit('setCases', response.data);
        return "";
      } catch (error) {
        return "";
      }
    }
  }, 
})