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
                        this.dashboards.push(response.data)
                    })
                    .catch(e => {})
            }
        },
        addNew() {
            this.active = {
                configuration : {
                    columns : []
                }
            }
        },
        addColumn() {
            this.active.configuration.columns.push({})
        },
        deleteColumn(column) {
            this.active.configuration.columns.splice(this.active.configuration.columns.indexOf(column), 1)
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
        
        <form v-if="active" @submit.prevent="updateActive()">
            <div>
                <label>Name</label>
                <input v-model="active.name" class="shadow border rounded px-3 py-1" required />
            </div>
            <div>
                <label>Base query</label>
                <textarea v-model="active.configuration.baseSqlQuery" class="shadow border rounded px-3 py-1" required></textarea>
            </div>
            <h2>Columns</h2>
            <table>
                <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Sql</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(column, i) in active.configuration.columns" :key="i">
                        <td><input v-model="column.name" class="shadow border rounded w-full px-3 py-1" required /></td>
                        <td><input v-model="column.sql" class="shadow border rounded w-full px-3 py-1" required /></td>
                        <td><button 
                                class="bg-red-500 hover:bg-red-700 rounded py-1 px-2 text-white" 
                                @click="deleteColumn(column)" type="button">Delete</button></td>
                    </tr>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="3">
                            <button 
                                class="bg-blue-500 hover:bg-blue-700 rounded py-1 px-2 text-white" 
                                @click="addColumn()" type="button">Add</button>
                        </td>
                    </tr>
                </tfoot>
            </table>


            <button 
                class="bg-blue-500 hover:bg-blue-700 rounded py-1 px-2 text-white" 
                type="submit">Save</button>
            <!-- Tried to use a more neutral color, but it's being removed by purgecss -->
            <button
                class="bg-gray-500 hover:bg-gray-700 rounded py-1 px-2 text-white"
                @click="active = undefined">cancel</button>
        </form>
    </div>
</template>