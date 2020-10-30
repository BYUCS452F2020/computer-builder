<template>
    <div>
        <p>
            <button class="component_label" v-on:click="toggleVisible()">Storages</button>
            <span v-if="show">
                <ul>
                    <li v-for="storage in this.$store.getters.getStorages" :key="storage.name">
                        {{storage.componentName}}: ${{storage.price}}
                        <button @click="addToBuild(storage)">Add to build</button>
                    </li>
                </ul>
            </span>
        </p>
    </div>
</template>

<script>
export default {
    name: "Storages",
    data() {
        return {
            theStorageS: [{
                name: "storage 1",
                price: 100
            },
            {
                name: "storage 2",
                price: 150
            }
            ],
            show: false,
            currentStorage: null
        }
    },
    async created() {
        this.getStorages();
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
        addToBuild(storage) {
            this.$store.commit('changeStorage', storage);
        },
        async getStorages () {
            try {
                this.error = await this.$store.dispatch("getStorages", {
                componentType: "Storage",
                cpuFamily: this.$store.getters.getCpuFamily,
                performanceRating: this.$store.getters.getPerformanceRating,
                maxPrice: this.$store.getters.getMaxPrice
                });
            if (this.error === "")
                console.log("success! got storages")
            } catch (error) {
                console.log(error);
            }
        }
    }
}
</script>