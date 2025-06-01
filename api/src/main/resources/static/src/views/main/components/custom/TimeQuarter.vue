<template>
  <v-col class="time-quarter" :class="{
    empty: quarterItem === undefined,
    booked: quarterItem !== undefined,
    startQuarter: quarterItem !== undefined && quarterItem.startQuarter,
    endQuarter: quarterItem !== undefined && quarterItem.endQuarter
  }">
    <template
      v-if="quarterItem !== undefined && quarterItem.startQuarter"
    >
      <h5 class="customer-name">{{ quarterItem.customerName }}</h5>
      <h6 class="customer-name">{{ quarterItem.displayItemNames }}</h6>
    </template>
  </v-col>
</template>

<style scoped lang="sass">
  .time-quarter
    flex: none
    height: 30px
  .empty
    border: 0.2px solid #eeeeee
  .booked
    background-color: goldenrod
    border-right-style: inset
    border-left-style: outset
  .startQuarter
    padding: 1px
    border-top-style: outset
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
