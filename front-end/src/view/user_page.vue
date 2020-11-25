<template>
    <div>
        <div id="cpu">
            <h1>User Page</h1>
            <span v-if="loggedOut & !registering">
                <h3>Looks like you're not logged in</h3>
                <span id="sign-in_form">
                    <span id="sign-in_fields">
                        <input v-model="username" placeholder="Username">
                        <input v-model="password" placeholder="Password">
                    </span>
                    <span id="sign-in_buttons">
                        <button class="sign-in_button" v-on:click="loginDatabase()">Sign In</button>
                        <button class="register_button" v-on:click="register()">Register</button>
                    </span>
                </span>
            </span>
            <span v-if="registering">
                <input v-model="username" placeholder="Username">
                <input v-model="password" placeholder="Password">
                <input v-model="firstName" placeholder="First Name">
                <input v-model="lastName" placeholder="Last Name">
                <input v-model="email" placeholder="Email">
                <button class="register_button" v-on:click="registerDatabase()">Register</button>

            </span>
            <span v-if="!loggedOut & !registering">
                <UserBuilds/>
                
                <button class="log-out_button" v-on:click="logOut()">Log Out</button>
            </span>
            
        </div>
    </div>
</template>

<script>

import UserBuilds from "../user/user_builds.vue"

export default {
    name: 'user',
    components: {
        UserBuilds
    },
    computed: {
    },
    created() {
        //TODO get all users builds
        if (this.$store.getters.getUser == null)
            this.logOut
    },
    data() {
        return {
            username: "",
            password: "",
            firstName: "",
            lastName: "",
            email: "",
            loggedOut: false,
            registering: false,
            builds: []
        }
    },
    methods: {
        signIn() {
            this.loggedOut = false
        },
        register() {
            this.registering = true
        },
        logOut() {
            this.loggedOut = true
        },
        async registerDatabase() {
            try {
                this.error = await this.$store.dispatch("register", {
                    username: this.username,
                    password: this.password,
                    firstName: this.firstName,
                    lastName: this.lastName,
                    email: this.email
                });
                if (this.error === "") {
                    this.registering = false
                    this.loggedOut = false
                }
            } catch (error) {
                console.log(error);
            }
        },
        async loginDatabase() {
            try {
                this.error = await this.$store.dispatch("login", {
                username: this.username,
                password: this.password
            });
            if (this.error != "Badlogincredentials") {
                this.registering = false
                this.loggedOut = false
            }
            } catch (error) {
                console.log(error);
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
