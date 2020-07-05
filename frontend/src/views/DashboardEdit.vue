<template>
    <div class="sm:m-4 md:max-w-3xl">
        <h1 class="font-semibold text-3xl">Dashboard Edit</h1>
        <form class="bg-white pb-2 px-2 shadow border flex-row sm:max-w-full">
            <div class="flex-row sm:flex mt-2">
                <label for="name" class="block sm:inline-block sm:w-32 text-gray-800 font-bold">Name</label>
                <input class="field" id="name" v-model="dashboard.name" />
            </div>
            <div class="flex-row sm:flex mt-2">
                <label for="base-query"
                    class="text-gray-800 font-bold block sm:break-normal sm:w-32">Base query</label>
                <textarea class="area h-32 sm:flex-1" id="base-query" v-model="dashboard.baseQuery" />
            </div>
            <div class="flex-row sm:flex mt-2">
                <label id="listColumns"
                    class="text-gray-800 font-bold block sm:break-normal sm:w-32">Results list columns</label>
                <div class="flex-row sm:flex sm:flex-1 border rounded p-1">
                    <table class="table-fixed w-full" aria-describedby="listColumns">
                        <thead class="hidden sm:table-header-group">
                            <tr>
                                <th scope="col" class="w-24"></th>
                                <th scope="col" class="hidden sm:table-cell w-20">Type</th>
                                <th scope="col" class="">Name</th>
                                <th scope="col" class="hidden sm:table-cell">Sql</th>
                                <th scope="col" class="w-20 sm:w-10"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="(column, i) in dashboard.listColumns" :key="i">
                                <td class="w-24">
                                    <div class="inline-flex">
                                        <button class="bg-gray-500 rounded-l py-1 px-1">
                                            <svg class="w-4 h-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20">
                                                <path d="M10.707 7.05L10 6.343 4.343 12l1.414 1.414L10 9.172l4.243 4.242L15.657 12z"/>
                                            </svg>
                                        </button>
                                        <button class="bg-gray-500 rounded-r py-1 px-1">
                                            <svg class="w-4 h-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20">
                                                <path d="M9.293 12.95l.707.707L15.657 8l-1.414-1.414L10 10.828 5.757 6.586 4.343 8z"/>
                                            </svg>
                                        </button>
                                        <button class="bg-gray-500 rounded py-1 px-2 ml-1">
                                            <svg class="w-4 h-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20">
                                                <path d="M.2 10a11 11 0 0 1 19.6 0A11 11 0 0 1 .2 10zm9.8 4a4 4 0 1 0 0-8 4 4 0 0 0 0 8zm0-2a2 2 0 1 1 0-4 2 2 0 0 1 0 4z"/>
                                                <path d="M12.81 4.36l-1.77 1.78a4 4 0 0 0-4.9 4.9l-2.76 2.75C2.06 12.79.96 11.49.2 10a11 11 0 0 1 12.6-5.64zm3.8 1.85c1.33 1 2.43 2.3 3.2 3.79a11 11 0 0 1-12.62 5.64l1.77-1.78a4 4 0 0 0 4.9-4.9l2.76-2.75zm-.25-3.99l1.42 1.42L3.64 17.78l-1.42-1.42L16.36 2.22z"/>
                                            </svg>
                                        </button>
                                    </div>
                                </td>
                                <td class="hidden sm:table-cell"><select class="rounded w-full"></select></td>
                                <td class="">
                                    <span class="block rounded sm:hidden bg-gray-400 truncate w-full py-0 px-2">{{column.name}} - {{column.sql}}</span>
                                    <input class="field w-full hidden sm:block" v-model="column.name"/>
                                </td>
                                <td class="hidden sm:table-cell">
                                    <input class="field w-full" v-model="column.sql" />
                                </td>
                                <td class="w-20">
                                    <div class="">
                                        <button class="bg-gray-500 rounded py-1 px-2 ml-1 sm:hidden">
                                            <svg class="w-4 h-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20">
                                                <path d="M12.3 3.7l4 4L4 20H0v-4L12.3 3.7zm1.4-1.4L16 0l4 4-2.3 2.3-4-4z"/>
                                            </svg>
                                        </button>
                                        <button class="bg-red-600 text-white rounded py-1 px-2 ml-1">
                                            <svg class="fill-current w-4 h-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20">
                                                <path d="M6 2l2-2h4l2 2h4v2H2V2h4zM3 6h14l-1 14H4L3 6zm5 2v10h1V8H8zm3 0v10h1V8h-1z"/>
                                            </svg>
                                        </button>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td>
                                    <button class="bg-teal-300 hover:bg-teal-400 text-teal-900 font-bold py-1 px-4 rounded inline-flex items-center">
                                        <svg class="fill-current w-4 h-4 mr-2" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20">
                                            <path d="M6 2l2-2h4l2 2h4v2H2V2h4zM3 6h14l-1 14H4L3 6zm5 2v10h1V8H8zm3 0v10h1V8h-1z"/>
                                        </svg>
                                        <span>Add</span>
                                    </button>
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </form>
    </div>
</template>

<script>
export default {
    name : "DashboardEdit",
    data () {
        return {
            dashboard : {
                name : "Test dashboard",
                baseQuery : "SELECT *\nFROM test_order o",
                listColumns : [{
                    name : "id",
                    type : "identifier",
                    sql : "o.id"
                }, {
                    name : "name",
                    type : "text",
                    sql : "o.name"
                }]
            }
        }
    }
}
</script>