import Vue from "vue"
import App from "./App.vue"

const instance = new Vue({
    render : createElement => createElement(App)
})
instance.$mount("#app")