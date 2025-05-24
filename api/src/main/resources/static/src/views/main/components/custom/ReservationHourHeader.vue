<template>
  <v-container class="reservationHourHeader">
    <v-col cols="1">
      {{title}}
    </v-col>
  </v-container>
</template>

<style scoped lang="sass">
</style>
<script>

export default {
  components: {  },
  props: {
    title: String,
  }
}
</script>
