<template>
    <div>
        <p>
            <button class="component_label" v-on:click="toggleVisible()">CPUS</button>
            <input v-model="max_price" type="number" @blur="getCPUs()" placeholder="Enter your price limit here">
            <span v-if="show">
                <ul>
                    <li v-for="cpu in this.$store.getters.getCpus" :key="cpu.name">
                        {{cpu.componentName}}: ${{cpu.price}}
                        <button @click="addToBuild(cpu)">Add to build</button>
                    </li>
                </ul>
            </span>
        </p>
    </div>
</template>

<script>
export default {
    name: "CPUs",
    data() {
        return {
            theCPUS: [{
                name: "cpu 1",
                price: 100
            },
            {
                name: "cpu 2",
                price: 150
            }
            ],
            show: false,
            currentCPU: null,
            max_price: 0
        }
    },
    async created() {
        this.getCPUs();
    },
    methods: {
        toggleVisible() {
            if (this.show == true) {
                this.show = false
            }
            else {
                this.show = true
            }
        },
        async addToBuild(cpu) {
            this.$store.commit('changeCPU', cpu);
            try {
                this.error = await this.$store.dispatch("getMotherboards", {
                componentType: "Motherboard",
                cpuFamily: this.$store.getters.getCpuFamily,
                performanceRating: this.$store.getters.getPerformanceRating,
                maxPrice: this.$store.getters.getMaxPrice
                });
            if (this.error === "")
                console.log("success! got mobos")
            } catch (error) {
                console.log(error);
            }
            this.$store.commit("toggleCPU")
        },
        async getCPUs () {
            try {
                console.log("getting cpus")
                this.error = await this.$store.dispatch("getCPUs", {
                componentType: "CPU",
                cpuFamily: this.$store.getters.getCpuFamily,
                performanceRating: this.$store.getters.getPerformanceRating,
                maxPrice: this.max_price
                });
            if (this.error === "")
                console.log("success! got cpus")
            } catch (error) {
                console.log(error);
            }
        }
    }
}
</script>