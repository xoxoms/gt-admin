<template>
  <v-row class="reservation-hour-header reservation-hour" v-if="header === true">
    <v-col class="header-first" v-if="perStaff === undefined">{{new Date().toLocaleDateString('ko-KR')}}</v-col>
    <v-col class="header-items" v-else>{{perStaff.staffNickname}}</v-col>
  </v-row>
  <v-row class="reservation-hour" v-else>
    <template v-if="perStaff === undefined">
      <v-col class="header-first">{{hour}}시</v-col>
    </template>
    <template v-else>
      <v-col class="quarter" v-if="perStaff.hourQuarterMap">
        <time-quarter :hour="hour" :quarter="1" title="123"></time-quarter>
        <time-quarter :hour="hour" :quarter="2"></time-quarter>
        <time-quarter :hour="hour" :quarter="3"></time-quarter>
        <time-quarter :hour="hour" :quarter="4"></time-quarter>
      </v-col>
      <template v-else>
          <template v-for="hourQuarter in perStaff.hourQuarterMap">
            <v-col class="quarter">
              <time-quarter :hour="hour" :quarter="1"></time-quarter>
              <time-quarter :hour="hour" :quarter="2"></time-quarter>
              <time-quarter :hour="hour" :quarter="3"></time-quarter>
              <time-quarter :hour="hour" :quarter="4"></time-quarter>
            </v-col>
          </template>
      </template>
    </template>
  </v-row>
</template>

<style scoped lang="sass">
  .reservation-hour-header
    background-color: #3c4858
  .reservation-hour
    border: 0.2px solid #eeeeee
    width: 100px
    margin: 0
    flex: none
    .header-first
      background-color: #3c4858
      color: #eeeeee
    .header-items
      color: yellow
  .quarter
    padding: 0
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
      staffNickname: String,
      hourQuarterMap: Array,
    }
  }
}
</script>
