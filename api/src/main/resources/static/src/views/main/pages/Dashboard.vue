<template>
  <v-container
    id="reservations"
    fluid
    tag="section"
  >
    <div class="dashboard">
      <v-row style="padding-left: 10px; padding-top: 10px">
        <reservation-hour :header="true"/>
        <template v-for="perStaff in data.perStaffs">
          <reservation-hour :header="true" v-bind:per-staff="perStaff"/>
        </template>
      </v-row>

      <template v-for="workingTime in data.workingTimes">
        <v-row style="padding-left: 10px;">
          <reservation-hour v-bind:hour="workingTime"/>
          <template v-for="perStaff in data.perStaffs">
            <reservation-hour v-bind:hour="workingTime" v-bind:per-staff="perStaff"/>
          </template>
        </v-row>
      </template>
    </div>
  </v-container>
</template>


<style scoped lang="sass">
  .dashboard
    width: 1000px
    height: 1000px
    overflow: scroll
</style>
<script>
import ReservationHour from "@/views/main/components/custom/ReservationHour.vue";

const API_URL = 'http://localhost:3000/api'

export default {
  name: 'StaffListView',
  components: {ReservationHour},
  props: {
    id: {
      type: Number
    },
    name: {
      type: String
    },
    regTs: {
      type: String
    }
  },

  data() {
    return {
      date: new Date().toLocaleDateString('ko-KR'),
      data: {
        workingTimes: [],
        perStaffs: []
      }
    }
  },

  created() {
    this.fetchData()
  },

  methods: {


    async fetchData() {
      const url = `${API_URL}/v1/reservation-dashboard`
      await fetch(url)
          .then(response =>  response.json())
          .then(response => {
            this.data = response.data
          })
    }
  }
}
</script>
