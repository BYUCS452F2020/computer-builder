<template>
    <div>
        <p>
            <button class="component_label" v-on:click="toggleVisible()">Cooler</button>
            <br>
            <br>
            Max Price
            <input v-model="max_price" type="number" @blur="getCoolers()" placeholder="Enter your price limit here">
            <span v-if="show">
                <ul class="item_list" >
                    <li v-for="cooler in this.$store.getters.getCoolers" :key="cooler.name">
                        {{cooler.componentName}}: ${{cooler.price}} <br> PR:{{cooler.performanceRating}}
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
            currentCooler: null,
            max_price: 0
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
                maxPrice: this.max_price
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