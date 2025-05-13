<template>
  <div class="footer-bar">

  </div>
</template>

<script>

import { isLoggedIn, isLoggedOut } from '@/utils/localStorageUtils';

export default {
  name: 'MenuBar',
  methods: {
    isLoggedIn,
    isLoggedOut
  }
};
</script>

<style lang="stylus" scoped>
.footer-bar
  background $theme-dark-ish
  height 300px;
  margin-top 100px;
  z-index 1
</style>