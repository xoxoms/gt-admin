<template>
  <v-col class="time-quarter empty">
  </v-col>
</template>

<style scoped lang="sass">
  .time-quarter
    flex: none
    height: 30px
  .empty
    border: 0.2px solid #eeeeee
    position: static
  .booked
    background-color: goldenrod
    border-right-style: inset
    border-left-style: outset
  .startQuarter
    padding: 1px
    border-top-style: outset
    border-bottom-style: inset
    border-right-style: inset
    border-left-style: outset
  .endQuarter
    border-bottom-style: inset
    border-right-style: inset
    border-left-style: outset
  .customer-name
    flex: none
</style>
<script>
export default {
  props: {
    hour: Number,
    quarter: Number,
    quarterItem: {
      bookingItemId: Number,
      startQuarter: Boolean,
      endQuarter: Boolean,
      customerName: String,
      source: String,
      displayItemNames: String,
    }
  }
}
</script>
