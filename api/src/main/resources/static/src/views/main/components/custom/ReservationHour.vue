<template>
  <v-row v-if="header === true" class="reservation-hour-header reservation-hour" :class="{
      'header-x-2': perStaff !== undefined && perStaff.maxReservationCountPerQuarter === 2,
      'header-x-3': perStaff !== undefined && perStaff.maxReservationCountPerQuarter === 3,
      'header-x-4': perStaff !== undefined && perStaff.maxReservationCountPerQuarter === 4,
      'header-x-5': perStaff !== undefined && perStaff.maxReservationCountPerQuarter === 5,
      'header-x-6': perStaff !== undefined && perStaff.maxReservationCountPerQuarter === 6,
      'header-x-7': perStaff !== undefined && perStaff.maxReservationCountPerQuarter >= 7,
    }">
    <v-col class="header-first" v-if="perStaff === undefined">{{new Date().toLocaleDateString('ko-KR')}}</v-col>
    <v-col class="header-items" v-else>{{perStaff.staffNickname}}</v-col>
  </v-row>
  <v-row v-else class="reservation-hour" :class="{
      'header-x-2': perStaff !== undefined && perStaff.maxReservationCountPerQuarter === 2,
      'header-x-3': perStaff !== undefined && perStaff.maxReservationCountPerQuarter === 3,
      'header-x-4': perStaff !== undefined && perStaff.maxReservationCountPerQuarter === 4,
      'header-x-5': perStaff !== undefined && perStaff.maxReservationCountPerQuarter === 5,
      'header-x-6': perStaff !== undefined && perStaff.maxReservationCountPerQuarter === 6,
      'header-x-7': perStaff !== undefined && perStaff.maxReservationCountPerQuarter >= 7,
    }">
    <template v-if="perStaff === undefined">
      <v-col class="header-first">{{hour}}시</v-col>
    </template>
    <template v-else>
      <v-col class="quarter">
        <template v-for="quarter in (1, 2, 3, 4)">
          <template v-if="perStaff.hourQuarterMap[hour + '_' + quarter] !== undefined">
            <div v-for="(item, idx) in perStaff.hourQuarterMap[hour + '_' + quarter]" :class="{
              'quarter-1': item.totalQuarterTaken === 1,
              'quarter-2': item.totalQuarterTaken === 2,
              'quarter-3': item.totalQuarterTaken === 3,
              'quarter-4': item.totalQuarterTaken === 4,
              'quarter-5': item.totalQuarterTaken === 5,
              'quarter-6': item.totalQuarterTaken === 6,
              'quarter-7': item.totalQuarterTaken === 7,
              'quarter-8': item.totalQuarterTaken === 8,
              'quarter-9': item.totalQuarterTaken === 9,
              'quarter-10': item.totalQuarterTaken === 10,

              'left-0': idx === 0,
              'left-1': idx === 1,
              'left-2': idx === 2,
              'left-3': idx === 3,
              'left-4': idx === 4,
              'left-5': idx === 5,
              'left-6': idx === 6,

              'canceled': isCanceled(item)
            }" class="booking" @mouseover="hovered(item)" @mouseleave="hovered(null)">
              <div class="booking-text">{{item.customerName}} {{quarter}}</div>
              <span class="remove-reservation clickable" @click="removeReservation(item)">
                <v-icon v-if="isHovered(item) && !isCanceled(item)" style="color: palevioletred;" aria-label="Close">mdi-close</v-icon>
              </span>
            </div>
          </template>
          <v-col class="empty clickable" :class="{
              'even-row-color': quarter % 2 === 0,
            }">
          </v-col>
        </template>
      </v-col>
    </template>
  </v-row>
</template>

<style scoped lang="sass">
  .reservation-hour
    border: 0.2px solid #eeeeee
    min-width: 100px
    height: 100px
    margin: 0
    flex: none
    .header-first
      background-color: #3c4858
      color: #eeeeee
    .header-items
      color: yellow
  .reservation-hour-header
    background-color: #3c4858
    min-width: 100px
    height: 50px
  .canceled
    text-decoration: line-through #eeeeee
  .quarter
    width: 100px
    padding: 0
    position: relative
  .header-x-1
    width: 125px
  .header-x-2
    width: 225px
  .header-x-3
    width: 325px
  .header-x-4
    width: 425px
  .header-x-5
    width: 525px
  .header-x-6
    width: 625px
  .header-x-7
    width: 725px
  .booking:hover
    background-color: #B1c7fa
  .booking
    z-index: 1
    width: 100px
    display: flex
    background-color: #a8c7fa
    float: left
    position: absolute
    border-right-style: inset
    border-left-style: outset
    border-top-style: outset
    border-bottom-style: inset
    border-radius: 10px
    cursor: pointer
  .booking-text
    margin-top: 2px
    margin-left: 5px
    color: #eeeeee
    max-width: 70px
    overflow: hidden
    white-space: nowrap
  .quarter-1
    height: 25px
  .quarter-2
    height: 50px
  .quarter-3
    height: 75px
  .quarter-4
    height: 100px
  .quarter-5
    height: 125px
  .quarter-6
    height: 150px
  .quarter-7
    height: 175px
  .quarter-8
    height: 200px
  .quarter-9
    height: 225px
  .quarter-10
    height: 250px
  .left-1
    left: 100px
  .left-2
    left: 200px
  .left-3
    left: 300px
  .left-4
    left: 400px
  .left-5
    left: 500px
  .left-6
    left: 600px
  .remove-reservation
    position: absolute
    float: right
    right: 0
  .empty:hover
    background-color: #FAFAFF
  .empty
    border: 0.2px solid #eeeeee
    position: static
    flex: none
    height: 25px
  .even-row-color
    background-color: #FDFDFD
  .clickable
    cursor: pointer
</style>
<script>
import TimeQuarter from "@/views/main/components/custom/TimeQuarter.vue";

export default {
  components: { TimeQuarter },
  props: {
    header: Boolean,
    headerDate: String,
    hour: Number,
    perStaff: {
      maxReservationCountPerQuarter: Number,
      staffNickname: String,
      hourQuarterMap: Array,
    },
  },
  data () {
    return {
      position: 1,
      hoveredColumn: null
    }
  },

  methods: {
    hovered(reservation) {
      if (reservation === null) {
        this.hoveredColumn = null
      } else {
        this.hoveredColumn = reservation.hour + "_" + reservation.quarter + "_" + reservation.reservationId
      }
    },

    isHovered(reservation) {
      return this.hoveredColumn === reservation.hour + "_" + reservation.quarter + "_" + reservation.reservationId
    },

    isCanceled(reservation) {
      return reservation.reservationStatus === 'CANCELED'
    },

    removeReservation(reservation) {
      if (confirm('예약을 취소할까요?')) {
        this.$parent.cancelReservation(reservation.reservationId)
      }
    }
  }
}
</script>
