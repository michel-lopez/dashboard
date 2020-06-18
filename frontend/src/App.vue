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
        <h1 class="font-semibold text-3xl">Dashboard Page</h1>
        <table class="border-collapse border border-gray-500" v-if="!active">
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
                        <button 
                            class="bg-blue-500 hover:bg-blue-700 rounded py-1 px-2 text-white" 
                            @click="edit(dashboard)">E</button>
                        <button 
                            class="bg-red-500 hover:bg-red-700 rounded py-1 px-2 text-white" 
                            @click="remove(dashboard)">D</button>
                    </td>
                    <td>{{dashboard.name}}</td>
                </tr>
                <tr class="no-data" v-if="!dashboards.length">
                    <td class="center" colspan="2">{{loading ? 'Loading...' : 'No data'}}</td>
                </tr>
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="2">
                        <button 
                            class="bg-blue-500 hover:bg-blue-700 rounded py-1 px-2 text-white" 
                            @click="addNew()">Add</button>
                    </td>
                </tr>
            </tfoot>
        </table>
        
        <form class="edit" v-if="active" @submit.prevent="updateActive()">
            <label>Name</label>
            <input v-model="active.name" />
            <button 
                class="bg-blue-500 hover:bg-blue-700 rounded py-1 px-2 text-white" 
                type="submit">Save</button>
            <!-- Tried to use a more neutral color, but it's being removed by purgecss -->
            <button
                class="bg-red-500 hover:bg-red-700 rounded py-1 px-2 text-white"
                @click="active = undefined">cancel</button>
        </form>
    </div>
</template>