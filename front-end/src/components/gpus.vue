<template>
    <div>
        <p>
            <button class="component_label" v-on:click="toggleVisible()">GPUS</button>
            <span v-if="show">
                <ul>
                    <li v-for="gpu in theGPUS" :key="gpu.name">
                        {{gpu.name}}: ${{gpu.price}}
                    </li>
                </ul>
            </span>
        </p>
    </div>
</template>

<script>
export default {
    name: "GPUs",
    data() {
        return {
            theGPUS: [{
                name: "gpu 1",
                price: 100
            },
            {
                name: "gpu 2",
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