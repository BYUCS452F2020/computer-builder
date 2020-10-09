<template>
    <div>
        <p>
            <button class="component_label" v-on:click="toggleVisible()">Coolers</button>
            <span v-if="show">
                <ul>
                    <li v-for="cooler in theCoolers" :key="cooler.name">
                        {{cooler.name}}: ${{cooler.price}}
                    </li>
                </ul>
            </span>
        </p>
    </div>
</template>

<script>
export default {
    name: "coolers",
    data() {
        return {
            theCoolers: [{
                name: "cooler 1",
                price: 20
            },
            {
                name: "cooler 2",
                price: 45
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

<style>

</style>