<template>
    <div>
        <p>
            <button class="component_label" v-on:click="toggleVisible()">Psus</button>
            <span v-if="show">
                <ul>
                    <li v-for="psu in this.$store.getters.getPsus" :key="psu.name">
                        {{psu.componentName}}: ${{psu.price}}
                        <button @click="addToBuild(psu)">Add to build</button>
                    </li>
                </ul>
            </span>
        </p>
    </div>
</template>

<script>
export default {
    name: "Psus",
    data() {
        return {
            thePsuS: [{
                name: "psu 1",
                price: 100
            },
            {
                name: "psu 2",
                price: 150
            }
            ],
            show: false,
            currentPsu: null
        }
    },
    async created() {
        this.getPsus();
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
        addToBuild(psu) {
            this.$store.commit('changePSU', psu);
        },
        async getPsus () {
            try {
                this.error = await this.$store.dispatch("getPSUs", {
                componentType: "Power-Supply",
                cpuFamily: this.$store.getters.getCpuFamily,
                performanceRating: this.$store.getters.getPerformanceRating,
                maxPrice: this.$store.getters.getMaxPrice
                });
            if (this.error === "")
                console.log("success! got psus")
            } catch (error) {
                console.log(error);
            }
        }
    }
}
</script>