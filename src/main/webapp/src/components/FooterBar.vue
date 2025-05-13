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
  position relative
  bottom 0
  background white
  width 100%
  height 50px;

.navbar
  .navbar-links
    color: $theme-white
    
  button, span, a
    &:hover
      transform: scale(1.05);
      color: $theme-white;
    &:focus
      color: $theme-white;
    transition ease 0.2s

  background: $theme-dark;

  .hamburger
    border-color: $theme-white;

  .hamburger:focus
    box-shadow: none;
</style>