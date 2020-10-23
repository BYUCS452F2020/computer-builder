<template>
    <div>
        <p>
            <button class="component_label" v-on:click="toggleVisible()">MOBOS</button>
            <span v-if="show">
                <ul>
                    <li v-for="motherboard in this.$store.motherboards" :key="motherboard.name">
                        {{motherboard.name}}: ${{motherboard.price}}
                    </li>
                </ul>
            </span>
        </p>
    </div>
</template>

<script>
export default {
    name: "motherboards",
    data() {
        return {
            theMOBOS: [{
                name: "mobo 1",
                price: 75
            },
            {
                name: "mobo 2",
                price: 125
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