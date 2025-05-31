<template>
  <v-col class="time-quarter booked" v-if="booked">
  </v-col>
  <v-col class="time-quarter" v-else>
    {{title}}
  </v-col>
</template>

<style scoped lang="sass">
  .time-quarter
    flex: none
    border: 0.2px solid #eeeeee
  .booked
    background-color: goldenrod
</style>
<script>
export default {
  props: {
    title: String,
    hour: Number,
    quarter: Number,
    booked: Boolean
  }
}
</script>
