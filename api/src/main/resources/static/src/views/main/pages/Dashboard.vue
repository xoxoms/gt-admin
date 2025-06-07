<template>
  <v-container
    id="reservations"
    fluid
    tag="section"
  >
    <div class="dashboard">
      <v-row style="width: max-content; overflow: scroll;">
        <reservation-hour :header="true"/>
        <template v-for="perStaff in data.perStaffs">
          <reservation-hour :header="true" v-bind:per-staff="perStaff"/>
        </template>
      </v-row>

      <template v-for="workingTime in data.workingTimes">
        <v-row style="width: max-content;">
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
    overscroll-behavior-x: none
    padding: 10px
    height: max-content
    overflow-x: scroll
    overflow-y: clip
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
    },

    async cancelReservation(reservationId) {
      const url = `${API_URL}/v1/reservations/${reservationId}/cancel`
      const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: {}
      }
      await fetch(url, requestOptions)
          .then(response =>  response.json())
          .then(response => {
            if (response.success) {
              this.fetchData()
            } else {
              alert(response.error.message)
            }
          })
    }
  }
}
</script>
