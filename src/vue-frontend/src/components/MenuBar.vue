<template>
  <div class="menu-bar">
    <nav class="navbar navbar-expand-sm fixed-top">
      <div class="container">
        <router-link to="/" role="button" class="fw-bold navbar-brand navbar-links">Ironick</router-link>
        <button class="hamburger navbar-links navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarToggle"
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
            <li v-if="isLoggedIn()" class="nav-item">
              <router-link to="/newEntryForm" class="fw-bold nav-link navbar-links">New Article</router-link>
            </li>
            <li v-if="isLoggedOut()" class="nav-item">
              <router-link to="/login" class="fw-bold nav-link navbar-links">Login</router-link>
            </li>
            <li v-if="isLoggedOut()" class="nav-item">
              <router-link to="/register" class="fw-bold nav-link navbar-links">Register</router-link>
            </li>
            <li v-if="isLoggedIn()" class="nav-item">
            <router-link to="/logout" class="fw-bold nav-link navbar-links">Logout</router-link>
          </li>
          </ul>
        </div>
      </div>
    </nav>
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