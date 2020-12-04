<template>
    <div>
        <p>
            <button class="component_label" v-on:click="toggleVisible()">Power Supply</button>
            <br>
            <br>
            Max Price
            <input v-model="max_price" type="number" @blur="getPsus()" placeholder="Enter your price limit here">

            <span v-if="show">
                <ul class="item_list" >
                    <li v-for="psu in this.$store.getters.getPsus" :key="psu.name">
                        {{psu.componentName}}: ${{psu.price}} <br> PR:{{psu.performanceRating}}
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
            currentPsu: null,
            max_price: 0
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
                maxPrice: this.max_price
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