<template>
  <div class="menu-bar">
    <nav class="navbar navbar-expand-sm fixed-top">
      <div class="container">
        <router-link to="/" role="button" class="fw-bold navbar-brand antique-white">NickBlog</router-link>
        <button class="hamburger antique-white navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarToggle"
                aria-controls="navbarToggle" aria-expanded="false" aria-label="Toggle navigation">
          <span class="fa-solid fa-bars"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarToggle">
          <ul class="navbar-nav ms-auto">
            <li class="nav-item">
              <router-link to="/" class="fw-bold nav-link antique-white">Home</router-link>
            </li>
            <li class="nav-item">
              <router-link  to="/blogs" class="fw-bold nav-link antique-white">Articles</router-link>
            </li>
            <li v-if="isLoggedIn()" class="nav-item">
              <router-link  to="/newEntryForm" class="fw-bold nav-link antique-white">New Article</router-link>
            </li>
            <li v-if="isLoggedOut()" class="nav-item">
              <router-link  to="/login" class="fw-bold nav-link antique-white">Login</router-link>
            </li>
            <li v-if="isLoggedOut()" class="nav-item">
              <router-link  to="/register" class="fw-bold nav-link antique-white">Register</router-link>
            </li>
            <li v-if="isLoggedIn()" class="nav-item">
            <router-link  to="/logout" class="fw-bold nav-link antique-white">Logout</router-link>
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
  button, span, a
    &:hover
      transform: scale(1.05);
      color: $antique-white;
    &:focus
      color: $antique-white;
    transition ease 0.2s

  background: $terracotta;

  .hamburger
    border-color: $antique-white;

  .hamburger:focus
    box-shadow: none;
</style>