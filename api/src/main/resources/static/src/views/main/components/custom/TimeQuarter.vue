<template>
  <v-col class="time-quarter">
<!--    {{quarter}}-->
  </v-col>
</template>

<style scoped lang="sass">
  .time-quarter
    width: 50px
    height: 25px
    flex: none
</style>
<script>
export default {
  props: {
    title: String,
    quarter: Number
  }
}
</script>
