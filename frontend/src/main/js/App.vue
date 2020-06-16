<script>
import axios from "axios"

export default {
    data () {
        return {
            dashboards : [],
            active : undefined
        }
    },
    methods : {
        loadDashboards () {
            axios.get("/api/dashboards")
                .then(({ data }) => {
                    this.dashboards = data._embedded.dashboards
                })
                .catch(e => {})
        },
        edit(dashboard) {
            this.active = dashboard
        },
        remove(dashboard) {
            axios.delete(dashboard._links.self.href)
                .then(response => {
                })
                .catch(e => {
                    console.log(e)
                })
        },
        updateActive() {
            const dashboard = this.active
            if  (dashboard._links) {
                axios.put(dashboard._links.self.href, dashboard)
                    .then(response => {
                        this.active = undefined
                        this.dashboards.splice(this.dashboard.indexOf(dashboard), 1)
                    })
                    .catch(e => {})
            } else {
                    axios.post("/api/dashboards", dashboard)
                    .then(response => {
                        this.active = undefined
                        this.dashboards.push(dashboard)
                    })
                    .catch(e => {})
            }
        },
        addNew() {
            this.active = {}
        }
    }
}
</script>

<template>
    <div>
        <h1>Hello</h1>
        <button @click="loadDashboards()">Load</button>
        <table class="borders" v-if="dashboards.length">
            <caption>Dashboards</caption>
            <thead>
                <tr>
                    <th scope="col">Actions</th>
                    <th scope="col">Name</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="dashboard in dashboards" :key="dashboard">
                    <td>
                        <button @click="edit(dashboard)">edit</button>
                        <button @click="remove(dashboard)">delete</button>
                    </td>
                    <td>{{dashboard.name}}</td>
                </tr>
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="2"><button @click="addNew()">Add</button></td>
                </tr>
            </tfoot>
        </table>
        
        <form v-if="active" @submit.prevent="updateActive()">
            <label>Name</label>
            <input v-model="active.name" />
            <input type="submit">
            <button @click="active = undefined">cancel</button>
        </form>
    </div>
</template>

<style scoped>
    table.borders {
        border: thin solid gray;
    }
</style>