<template>
  <v-col class="time-quarter empty" :class="{
    'even-row-color': quarter % 2 === 0,
  }" @mouseover="hovered()">

  <span class="add-reservation"
    :class="{
      'hover': isHovered(),
    }">
    <v-icon aria-label="Close" @click="closeCreateModal">mdi-close</v-icon>
  </span>
  </v-col>
</template>

<style scoped lang="sass">

</style>
<script>
export default {
  props: {
    hour: Number,
    quarter: Number,
  },

  data() {
    return {
      hoveredColumn: null
    }
  },

  methods: {
    hovered() {
      this.hoveredColumn = this.hour + "_" + this.quarter
    },

    isHovered() {
      return this.hoveredColumn === this.hour + "_" + this.quarter
    }
  }
}
</script>
