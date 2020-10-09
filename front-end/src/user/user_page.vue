<template>
    <div>
        <div id="cpu">
            <h1>User Page</h1>
            <span v-if="loggedOut">
                <h3>Looks like you're not logged in</h3>
                <span id="sign-in_form">
                    <span id="sign-in_fields">
                        <input v-model="username" placeholder="Username">
                        <input v-model="password" placeholder="Password">
                    </span>
                    <span id="sign-in_buttons">
                        <button class="sign-in_button" v-on:click="signIn()">Sign In</button>
                        <button class="register_button" v-on:click="register()">Register</button>
                    </span>
                </span>
            </span>
            <Builds/>
        </div>
    </div>
</template>

<script>
import Builds from "../user/user_builds.vue"

export default {
    name: 'user',
    components: {
        Builds
    },
    data() {
        return {
            username: "",
            password: "",
            loggedOut: true
        }
    },
    methods: {
        signIn() {
            
        },
        register() {

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
