<template>
    <div>
        <p>
            <button class="component_label" v-on:click="toggleVisible()">Cases</button>
            <br>
            <br>
            Max Price
            <input v-model="max_price" type="number" @blur="getCases()" placeholder="Enter your price limit here">
            <span v-if="show">
                <ul>
                    <li v-for="casE in this.$store.getters.getCases" :key="casE.name">
                        {{casE.componentName}}: ${{casE.price}} PR:{{casE.performanceRating}}
                        <button @click="addToBuild(casE)">Add to build</button>
                    </li>
                </ul>
            </span>
        </p>
    </div>
</template>

<script>
export default {
    name: "Cases",
    data() {
        return {
            show: false,
            currentCase: null,
            max_price: 0
        }
    },
    async created() {
        this.getCases();
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
        addToBuild(casE) {
            this.$store.commit('changeCase', casE);
        },
        async getCases () {
            try {
                this.error = await this.$store.dispatch("getCases", {
                componentType: "Case",
                cpuFamily: this.$store.getters.getCpuFamily,
                performanceRating: this.$store.getters.getPerformanceRating,
                maxPrice: this.max_price
                });
            if (this.error === "")
                console.log("success! got casEs")
            } catch (error) {
                console.log(error);
            }
        }
    }
}
</script>