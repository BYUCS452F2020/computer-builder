<template>
    <div>
        <p>
            <button class="component_label" v-on:click="toggleVisible()">Gpus</button>
            <span v-if="show">
                <ul>
                    <li v-for="gpu in this.$store.getters.getGpus" :key="gpu.name">
                        {{gpu.componentName}}: ${{gpu.price}}
                        <button @click="addToBuild(gpu)">Add to build</button>
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
            currentGpu: null
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
                maxPrice: this.$store.getters.getMaxPrice
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