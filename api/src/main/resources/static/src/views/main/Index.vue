<template>
  <v-app>
    <main-core-app-bar />

    <main-core-drawer />

    <main-core-view />
  </v-app>
</template>

<script>
import {onMounted} from 'vue'

export default {
  name: 'MainIndex',

  setup() {
    onMounted(function() {
      if (!this.isAuthenticated()) { this.$router.push('/login') }
    })
  },

  methods: {
    async isAuthenticated() {
      await this.$store.dispatch('isAuthenticated')
    }
  },

  components: {
    MainCoreAppBar: () => import('./components/core/AppBar'),
    MainCoreDrawer: () => import('./components/core/Drawer'),
    MainCoreView: () => import('./components/core/View'),
  },

  data: () => ({
    expandOnHover: false,
  }),
}
</script>
