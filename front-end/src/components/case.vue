<template>
    <div>
        <p>
            <button class="component_label" v-on:click="toggleVisible()">Cases</button>
            <span v-if="show">
                <ul>
                    <li v-for="casE in theCases" :key="casE.name">
                        {{casE.name}}: ${{casE.price}}
                    </li>
                </ul>
            </span>
        </p>
    </div>
</template>

<script>
export default {
    name: "cases",
    data() {
        return {
            theCases: [{
                name: "case 1",
                price: 100
            },
            {
                name: "case 2",
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