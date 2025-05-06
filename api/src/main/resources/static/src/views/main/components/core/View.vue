<template>
  <v-main>
    <router-view />

    <main-core-footer />
  </v-main>
</template>

<script>
  export default {
    name: 'MainCoreView',

    components: {
      MainCoreFooter: () => import('./Footer.vue'),
    },
  }
</script>
