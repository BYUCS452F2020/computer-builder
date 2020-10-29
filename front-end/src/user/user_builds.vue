<template>
    <div>
        <div id="builds">
            <h3>Your Builds</h3>
            <li v-for="build in this.$store.getters.getBuilds.buildList" :key="build.name">
                <h4>{{build.buildName}}</h4>
                <p>CPU: {{build.processor.component.componentName}}</p>
                <p>Motherboard: {{build.motherboard.component.componentName}}</p>
                <p>GPU: {{build.graphicsCard.component.componentName}}</p>
                <p>RAM: {{build.memory.component.componentName}}</p>
                <p>Storage: {{build.storage.component.componentName}}</p>
                <p>Power Supply: {{build.powerSupply.component.componentName}}</p>
                <p>CPU Cooler: {{build.cpuCooler.component.componentName}}</p>
                <p>Case: {{build.pcCase.component.componentName}}</p>
                <span/>
            </li>
        </div>
    </div>
</template>

<script>

export default {
    name: 'user',
    components: {
    },
    computed: {
    },
    created() {
        //TODO get all users builds
        this.getUserBuilds();
    },
    data() {
        return {
            builds: [],
        }
    },
    methods: {
        async getUserBuilds() {
            //Get user builds here
            console.log("gettin builds")
            try {
                this.error = await this.$store.dispatch("getUserBuilds", {
                username: this.username
            });
            this.builds = this.$store.getters.getBuilds
            await this.fillUserBuilds()
            } catch (error) {
                console.log(error);
            }
        },
        async fillUserBuilds() {
            var i;
            console.log("filling builds")
            for(i = 0; i < this.builds.buildList.length; i++) {
                this.builds.buildList[i].processor = await this.$store.dispatch("getSingleComponent", {
                componentID: this.builds.buildList[i].processor
                });
                this.builds.buildList[i].motherboard = await this.$store.dispatch("getSingleComponent", {
                componentID: this.builds.buildList[i].motherboard
                });
                this.builds.buildList[i].graphicsCard = await this.$store.dispatch("getSingleComponent", {
                componentID: this.builds.buildList[i].graphicsCard
                });
                this.builds.buildList[i].memory = await this.$store.dispatch("getSingleComponent", {
                componentID: this.builds.buildList[i].memory
                });
                this.builds.buildList[i].storage = await this.$store.dispatch("getSingleComponent", {
                componentID: this.builds.buildList[i].storage
                });
                this.builds.buildList[i].powerSupply = await this.$store.dispatch("getSingleComponent", {
                componentID: this.builds.buildList[i].powerSupply
                });
                this.builds.buildList[i].cpuCooler = await this.$store.dispatch("getSingleComponent", {
                componentID: this.builds.buildList[i].cpuCooler
                });
                this.builds.buildList[i].pcCase = await this.$store.dispatch("getSingleComponent", {
                componentID: this.builds.buildList[i].pcCase
                });
            }
        }
    },
    props: {
    }
}
</script>

<style>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
.component_label {
  background-color: #4c63af; /* Green */
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
}

#sign-in_form {
    display: flex;
    flex-direction: column;
}

#sign-in_fields {

}

#sign-in_buttons {

}
</style>
