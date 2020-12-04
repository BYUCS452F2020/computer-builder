<template>
    <div>
        <p>
            <button class="component_label" v-on:click="toggleVisible()">RAM</button>
            <br>
            <br>
            Max Price
            <input v-model="max_price" type="number" @blur="getRAMs()" placeholder="Enter your price limit here">
            <span v-if="show">
                <ul class="item_list" >
                    <li v-for="ram in this.$store.getters.getRams" :key="ram.name">
                        {{ram.componentName}}: ${{ram.price}} <br> PR:{{ram.performanceRating}}
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
    name: "RAMs",
    data() {
        return {
            theRAMS: [{
                name: "ram 1",
                price: 100
            },
            {
                name: "ram 2",
                price: 150
            }
            ],
            show: false,
            currentRAM: null,
            max_price: 0
        }
    },
    async created() {
        this.getRAMs();
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
        addToBuild(ram) {
            this.$store.commit('changeRAM', ram);
        },
        async getRAMs () {
            try {
                this.error = await this.$store.dispatch("getRams", {
                componentType: "Memory",
                cpuFamily: this.$store.getters.getCpuFamily,
                performanceRating: this.$store.getters.getPerformanceRating,
                maxPrice: this.max_price
                });
            if (this.error === "")
                console.log("success! got rams")
            } catch (error) {
                console.log(error);
            }
        }
    }
}
</script>