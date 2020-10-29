<template>
    <div>
        <p>
            <button class="component_label" v-on:click="toggleVisible()">Coolers</button>
            <span v-if="show">
                <ul>
                    <li v-for="cooler in this.$store.getters.getCoolers" :key="cooler.name">
                        {{cooler.componentName}}: ${{cooler.price}}
                        <button @click="addToBuild(cooler)">Add to build</button>
                    </li>
                </ul>
            </span>
        </p>
    </div>
</template>

<script>
export default {
    name: "Coolers",
    data() {
        return {
            theCoolerS: [{
                name: "cooler 1",
                price: 100
            },
            {
                name: "cooler 2",
                price: 150
            }
            ],
            show: false,
            currentCooler: null
        }
    },
    async created() {
        this.getCoolers();
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
        addToBuild(cooler) {
            this.$store.commit('changeCooler', cooler);
        },
        async getCoolers () {
            try {
                this.error = await this.$store.dispatch("getCoolers", {
                componentType: "Cooler",
                cpuFamily: this.$store.getters.getCpuFamily,
                performanceRating: this.$store.getters.getPerformanceRating,
                maxPrice: this.$store.getters.getMaxPrice
                });
            if (this.error === "")
                console.log("success! got coolers")
            } catch (error) {
                console.log(error);
            }
        }
    }
}
</script>