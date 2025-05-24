<template>
  <v-row class="reservation-hour">
    <time-quarter :quarter="1"></time-quarter>
    <time-quarter :quarter="2"></time-quarter>
    <time-quarter :quarter="3"></time-quarter>
    <time-quarter :quarter="4"></time-quarter>
  </v-row>
</template>

<style scoped lang="sass">
  .reservation-hour
    border: 1px solid #c0c1c2
    width: 100px
    margin: 0
    flex: none
</style>
<script>
import TimeQuarter from "@/views/main/components/custom/TimeQuarter.vue";

export default {
  components: { TimeQuarter },
  props: {
    time: Number,
  }
}
</script>
