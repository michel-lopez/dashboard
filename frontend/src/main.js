import Vue from "vue"
import App from "./App.vue"

//Add to index.html for better performance during development?
import "./css/tailwind.pcss"

const instance = new Vue({
    render : createElement => createElement(App)
})
instance.$mount("#app")