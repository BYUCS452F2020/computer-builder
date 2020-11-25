<template>
    <div>
        <p>
            <button class="component_label" v-on:click="toggleVisible()">Motherboards</button>
            <input v-model="max_price" type="number" @blur="getMotherboards()" placeholder="Enter your price limit here">
            <span v-if="show">
                <ul>
                    <li v-for="motherboard in this.$store.getters.getMotherboards" :key="motherboard.name">
                        {{motherboard.componentName}}: ${{motherboard.price}}
                        <button @click="addToBuild(motherboard)">Add to build</button>
                    </li>
                </ul>
            </span>
        </p>
    </div>
</template>

<script>
export default {
    name: "Motherboards",
    data() {
        return {
            theMotherboardS: [{
                name: "motherboard 1",
                price: 100
            },
            {
                name: "motherboard 2",
                price: 150
            }
            ],
            show: false,
            currentMotherboard: null,
            max_price: 0
        }
    },
    async created() {
        this.getMotherboards();
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
        async addToBuild(motherboard) {
            this.$store.commit('changeMotherboard', motherboard);
            try {
                this.error = await this.$store.dispatch("getCPUs", {
                componentType: "CPU",
                cpuFamily: this.$store.getters.getCpuFamily,
                performanceRating: this.$store.getters.getPerformanceRating,
                maxPrice: this.$store.getters.getMaxPrice
                });
            if (this.error === "")
                console.log("success! got cpus")
            } catch (error) {
                console.log(error);
            }
        },
        async getMotherboards () {
            try {
                this.error = await this.$store.dispatch("getMotherboards", {
                componentType: "Motherboard",
                cpuFamily: this.$store.getters.getCpuFamily,
                performanceRating: this.$store.getters.getPerformanceRating,
                maxPrice: this.max_price
                });
            if (this.error === "")
                console.log("success! got motherboards")
            } catch (error) {
                console.log(error);
            }
        }
    }
}
</script>