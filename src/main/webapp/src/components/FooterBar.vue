<template>
  <div class="footer-bar">
    <div class="footer-content">
      <div class="footer-links">
        <router-link to="/login" class="fw-bold nav-link navbar-links">About</router-link>
        <router-link to="/login" class="fw-bold nav-link navbar-links">Contact</router-link>
        <router-link v-if="isLoggedIn()" to="/logout" class="fw-bold nav-link navbar-links">Logout</router-link>
        <router-link v-if="isLoggedOut()" to="/login" class="fw-bold nav-link navbar-links">Login</router-link>
        <router-link v-if="isLoggedOut()" to="/register" class="fw-bold nav-link navbar-links">Register</router-link>
        <router-link to="/newEntryForm" class="fw-bold nav-link navbar-links">Contribute</router-link>
        <a href="https://github.com/nicholasgermanos/nickblog" target="_blank" class="fw-bold nav-link navbar-links">See my code</a>
      </div>
      <div class="newsletter-signup-container">
        <h4>Get notified about new posts</h4>
        <div class="newsletter-form">
          <input placeholder="Email Address">
          <button class="nick-button">Notify me</button>
        </div>
      </div>
    </div>
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
  display flex
  background $theme-dark-ish
  margin-top 100px
  z-index 1
  padding 80px 10% 50px 10%
  justify-content space-evenly

  a
    padding 3px

  a:focus, a:hover
    color $theme-white

.footer-content
  max-width 100%
  min-width 100%
  display flex
  flex-wrap wrap-reverse
  justify-content space-around


.footer-links
  display flex
  flex-direction column
  align-items flex-start
  justify-content center

.newsletter-signup-container
  display flex
  flex-direction column
  align-items flex-start
  justify-content start
  width 50%

  h4
    color $theme-white
    padding 5px

  .newsletter-form
    display flex
    flex-wrap nowrap
    width 100%
    justify-content start
    margin-bottom 30px

    input
      height 40px
      width 80%
      max-width 400px
      border-radius 5px
      padding-left 10px
      margin-right 7px
      margin-bottom 10px
      box-shadow: 1px 1px 0 0, 2px 2px 0 0, 3px 3px 0 0, 4px 4px 0 0, 5px 5px 0 0;
      color $theme-grey-ish

@media (max-width: $grid-breakpoint-md)
  .footer-bar
    padding 10px
  .footer-links
    align-items center
    padding 0

  .footer-content
    flex-direction column-reverse
    max-width 100%
    min-width 100%
    margin 0
    margin-top 50px

  .newsletter-signup-container
    align-items center
    width 100%

    .newsletter-form
      justify-content center
      flex-wrap wrap

@media (min-width: $grid-breakpoint-3xl)
  .footer-content
    max-width 70%
    min-width 70%
</style>