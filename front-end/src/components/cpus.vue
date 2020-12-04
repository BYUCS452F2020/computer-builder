<template>
    <div>
        <p>
            <button class="component_label" v-on:click="toggleVisible()">CPU</button>
            <br>
            <br>
            Max Price
            <input v-model="max_price" type="number" @blur="getCPUs()" placeholder="Enter your price limit here">
            <span v-if="show">
                <ul class="item_list" >
                    <li class="list_item" v-for="cpu in this.$store.getters.getCpus" :key="cpu.name">
                        {{cpu.componentName}}: ${{cpu.price}} <br> PR:{{cpu.performanceRating}}
                        <br>
                        <button @click="addToBuild(cpu)">Add to build</button>
                        <br><br>
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
                let pr = this.$store.getters.getPerformanceRating
                console.log("pr is" + pr)
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