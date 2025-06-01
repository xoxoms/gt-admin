<template>
  <v-row class="reservation-hour-header reservation-hour" :class="{
      'header-x-2': perStaff !== undefined && perStaff.maxBookedPerQuarter === 2,
      'header-x-3': perStaff !== undefined && perStaff.maxBookedPerQuarter === 3,
      'header-x-4': perStaff !== undefined && perStaff.maxBookedPerQuarter >= 4,
    }" v-if="header === true">
    <v-col class="header-first" v-if="perStaff === undefined">{{new Date().toLocaleDateString('ko-KR')}}</v-col>
    <v-col class="header-items" v-else>{{perStaff.staffNickname}}</v-col>
  </v-row>
  <v-row class="reservation-hour" :class="{
      'header-x-2': perStaff !== undefined && perStaff.maxBookedPerQuarter === 4,
      'header-x-3': perStaff !== undefined && perStaff.maxBookedPerQuarter === 6,
      'header-x-4': perStaff !== undefined && perStaff.maxBookedPerQuarter >= 7,
    }" v-else>
    <template v-if="perStaff === undefined">
      <v-col class="header-first">{{hour}}시</v-col>
    </template>
    <template v-else>
      <v-col class="quarter">
        <template v-for="quarter in (1, 2, 3, 4)">
          <template v-if="perStaff.hourQuarterMap[hour + '_' + quarter] !== undefined">
            <div :class="{
              'quarter-1': perStaff.hourQuarterMap[hour + '_' + quarter].totalQuarterTaken === 1,
              'quarter-2': perStaff.hourQuarterMap[hour + '_' + quarter].totalQuarterTaken === 2,
              'quarter-3': perStaff.hourQuarterMap[hour + '_' + quarter].totalQuarterTaken === 3,
              'quarter-4': perStaff.hourQuarterMap[hour + '_' + quarter].totalQuarterTaken === 4,
              'quarter-5': perStaff.hourQuarterMap[hour + '_' + quarter].totalQuarterTaken === 5,
              'quarter-6': perStaff.hourQuarterMap[hour + '_' + quarter].totalQuarterTaken === 6,
            }"
                  class="booking">
              <div class="booking-text">{{perStaff.hourQuarterMap[hour + '_' + quarter].customerName}}</div>
            </div>
          </template>
        </template>
        <template v-for="quarter in (1, 2, 3, 4)">
          <time-quarter :hour="hour" :quarter="quarter"></time-quarter>
        </template>
      </v-col>
    </template>
  </v-row>
</template>

<style scoped lang="sass">
  .reservation-hour
    border: 0.2px solid #eeeeee
    min-width: 120px
    height: 120px
    margin: 0
    flex: none
    .header-first
      background-color: #3c4858
      color: #eeeeee
    .header-items
      color: yellow
  .reservation-hour-header
    background-color: #3c4858
    min-width: 120px
    height: 50px
  .quarter
    width: 100px
    padding: 0
    position: relative
  .header-x-2
    width: 240px
  .header-x-3
    width: 360px
  .header-x-4
    width: 480px
  .booking
    width: 120px
    display: flex
    background-color: #a8c7fa
    float: left
    position: absolute
    border-right-style: inset
    border-left-style: outset
    border-top-style: outset
    border-bottom-style: inset
    border-radius: 10px
  .booking-text
    margin-top: 5px
    margin-left: 5px
    color: #eeeeee
  .quarter-1
    height: 30px
  .quarter-2
    height: 60px
  .quarter-3
    height: 90px
  .quarter-4
    height: 120px
  .quarter-5
    height: 150px
  .quarter-6
    height: 180px
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
      maxBookedPerQuarter: Number,
      staffNickname: String,
      hourMap: Array,
      hourQuarterMap: Array,
    }
  }
}
</script>
