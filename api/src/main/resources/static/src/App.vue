<template>
  <router-view />
</template>

<script>
  export default {
    name: 'App',

    data: () => ({
      authenticated: false,
    }),
  }
</script>
