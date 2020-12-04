<template>
    <div>
        <p>
            <button class="component_label" v-on:click="toggleVisible()">GPU</button>
            <br>
            <br>
            Max Price
            <input v-model="max_price" type="number" @blur="getGpus()" placeholder="Enter your price limit here">

            <span v-if="show">
                <ul class="item_list" >
                    <li v-for="gpu in this.$store.getters.getGpus" :key="gpu.name">
                        {{gpu.componentName}}: ${{gpu.price}} <br> PR:{{gpu.performanceRating}}
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
    name: "Gpus",
    data() {
        return {
            theGpuS: [{
                name: "gpu 1",
                price: 100
            },
            {
                name: "gpu 2",
                price: 150
            }
            ],
            show: false,
            currentGpu: null,
            max_price: 0
        }
    },
    async created() {
        this.getGpus();
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
        addToBuild(gpu) {
            this.$store.commit('changeGPU', gpu);
        },
        async getGpus () {
            try {
                this.error = await this.$store.dispatch("getGPUs", {
                componentType: "GPU",
                cpuFamily: this.$store.getters.getCpuFamily,
                performanceRating: this.$store.getters.getPerformanceRating,
                maxPrice: this.max_price
                });
            if (this.error === "")
                console.log("success! got gpus")
            } catch (error) {
                console.log(error);
            }
        }
    }
}
</script>