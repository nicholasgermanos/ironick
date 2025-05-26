<template>
  <div class="menu-bar">
    <nav class="navbar navbar-expand-sm fixed-top">
      <div class="container">
        <router-link to="/" role="button" class="fw-bold navbar-brand navbar-links">Ironick</router-link>
        <button class="hamburger navbar-links navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarToggle"
                aria-controls="navbarToggle" aria-expanded="false" aria-label="Toggle navigation">
          <span class="fa-solid fa-bars"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarToggle">
          <ul class="navbar-nav ms-auto">
            <li class="nav-item">
              <router-link to="/" class="fw-bold nav-link navbar-links">Home</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/blogs" class="fw-bold nav-link navbar-links">Articles</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/about" class="fw-bold nav-link navbar-links">About</router-link>
            </li>
            <li v-if="!isMobile()" id="fullscreen-button" class="nav-item fullscreen-icon">
              <img v-on:click="fullscreen" src="../assets/fullscreen.png" alt="Fullscreen">
            </li>
            /li>
          </ul>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>

import { isLoggedIn, isLoggedOut } from '@/utils/localStorageUtils';
import { isMobile } from '@/utils/utils';

export default {
  name: 'MenuBar',
  methods: {
    isMobile,
    isLoggedIn,
    isLoggedOut,
    fullscreen: function() {
      this.$emit('fullscreen')
    },
  }
};
</script>

<style lang="stylus" scoped>
.navbar
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

.fullscreen-icon
  margin-top 6px
  margin-left 6px
  a:hover
    transform none
    transition none
  img
    height 23px

    &:hover
      cursor pointer
      transform: scale(1.2)
    transition ease 0.2s

</style>