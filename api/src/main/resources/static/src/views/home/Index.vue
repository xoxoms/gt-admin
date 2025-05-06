<template>
  <v-app>
    <home-core-drawer />
    <home-core-view />
  </v-app>
</template>

<script>
  export default {
    name: 'HomeIndex',

    components: {
      HomeCoreAppBar: () => import('./components/core/AppBar'),
      HomeCoreDrawer: () => import('./components/core/Drawer'),
      HomeCoreView: () => import('./components/core/View'),
    },

    data: () => ({
      expandOnHover: false,
    }),
  }
</script>
