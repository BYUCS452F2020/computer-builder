<template>
    <div>
        <p>
            <button class="component_label" v-on:click="toggleVisible()">PSUS</button>
            <span v-if="show">
                <ul>
                    <li v-for="psu in thePSUS" :key="psu.name">
                        {{psu.name}}: ${{psu.price}}
                    </li>
                </ul>
            </span>
        </p>
    </div>
</template>

<script>
export default {
    name: "PSUs",
    data() {
        return {
            thePSUS: [{
                name: "psu 1",
                price: 100
            },
            {
                name: "psu 2",
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