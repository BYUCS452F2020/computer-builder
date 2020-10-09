<template>
    <div>
        <p>
            <button class="component_label" v-on:click="toggleVisible()">Storage</button>
            <span v-if="show">
                <ul>
                    <li v-for="storage in theStorages" :key="storage.name">
                        {{storage.name}}: ${{storage.price}}
                    </li>
                </ul>
            </span>
        </p>
    </div>
</template>

<script>
export default {
    name: "storages",
    data() {
        return {
            theStorages: [{
                name: "storage 1",
                price: 100
            },
            {
                name: "storage 2",
                price: 150
            }
            ],
            show: false
        }
    },
    methods: {
        toggleVisible() {
            if (this.show == true) {
                this.show = false
            }
            else {
                this.show = true
            }
        }
    }
}
</script>