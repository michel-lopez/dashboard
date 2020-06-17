<script>
import axios from "axios"

const dashboardUrl = "/api/dashboards"

export default {
    data () {
        return {
            dashboards : [],
            active : undefined,
            loading : true
        }
    },
    mounted () {
        axios.get(dashboardUrl)
                .then(({ data }) => {
                    this.dashboards = data._embedded.dashboards
                    this.loading = false
                })
                .catch(e => {})
    },
    methods : {
        edit(dashboard) {
            this.active = dashboard
        },
        remove(dashboard) {
            axios.delete(dashboard._links.self.href)
                .then(response => {
                    this.dashboards.splice(this.dashboards.indexOf(dashboard), 1)
                })
                .catch(e => {
                    console.log(e)
                })
        },
        updateActive() {
            const dashboard = this.active
            if (dashboard._links) {
                axios.put(dashboard._links.self.href, dashboard)
                    .then(response => {
                        this.active = undefined
                    })
                    .catch(e => {})
            } else {
                    axios.post(dashboardUrl, dashboard)
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
        <h1>Dashboard Page </h1>
        <table class="borders" v-if="!active">
            <caption>Dashboards</caption>
            <thead>
                <tr>
                    <th scope="col">Actions</th>
                    <th scope="col">Name</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(dashboard, i) in dashboards" :key="i">
                    <td>
                        <button @click="edit(dashboard)">edit</button>
                        <button @click="remove(dashboard)">delete</button>
                    </td>
                    <td>{{dashboard.name}}</td>
                </tr>
                <tr class="no-data" v-if="!dashboards.length">
                    <td colspan="2">{{loading ? 'Loading...' : 'No data'}}</td>
                </tr>
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="2"><button @click="addNew()">Add</button></td>
                </tr>
            </tfoot>
        </table>
        
        <form class="edit" v-if="active" @submit.prevent="updateActive()">
            <label>Name</label>
            <input v-model="active.name" />
            <input type="submit">
            <button @click="active = undefined">cancel</button>
        </form>
    </div>
</template>

<style lang="stylus" scoped>
    table
    &.borders
         border thin solid gray
    &.no-data
        text-align center

</style>