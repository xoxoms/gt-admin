<template>
  <v-row class="reservation-hour-header reservation-hour" :class="{
      'header-x-2': perStaff !== undefined && perStaff.maxBookedPerQuarter === 4,
      'header-x-3': perStaff !== undefined && perStaff.maxBookedPerQuarter === 6,
      'header-x-4': perStaff !== undefined && perStaff.maxBookedPerQuarter >= 7,
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
          <time-quarter :hour="hour" :quarter="quarter" :title="'empty'"></time-quarter>
        </template>
      </v-col>
    </template>
<!--    <template v-else>-->
<!--      <v-col class="quarter" v-if="perStaff.hourMap[hour] === undefined">-->
<!--        <template v-for="quarter in (1, 2, 3, 4)">-->
<!--          <time-quarter :hour="hour" :quarter="quarter" :title="'empty'"></time-quarter>-->
<!--        </template>-->
<!--      </v-col>-->
<!--      <v-col class="quarter" v-for="hourItem in perStaff.hourMap[hour]" v-else>-->
<!--        <template v-for="quarter in (1, 2, 3, 4)">-->
<!--          <time-quarter :hour="hour" :quarter="quarter" :quarter-item="perStaff.hourQuarterMap[hour + '_' + quarter + '_' + hourItem.bookingItemId]"></time-quarter>-->
<!--        </template>-->
<!--      </v-col>-->
<!--    </template>-->
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
  .header-x-2
    width: 240px
  .header-x-3
    width: 360px
  .header-x-4
    width: 480px
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
