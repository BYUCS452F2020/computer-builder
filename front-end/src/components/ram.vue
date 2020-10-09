<template>
    <div>
        <p>
            <button class="component_label" v-on:click="toggleVisible()">RAMS</button>
            <span v-if="show">
                <ul>
                    <li v-for="ram in theRAMS" :key="ram.name">
                        {{ram.name}}: ${{ram.price}}
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