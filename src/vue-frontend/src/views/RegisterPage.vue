<template>
  <form @submit.prevent="submit" class="container blog-form">
    <div class="m-4">
      <h1>Hi, nice to meet you</h1>
      <input v-model="user.firstName" placeholder="First Name"/>
      <input v-model="user.lastName" placeholder="Surname"/>
      <input v-model="user.email" placeholder="Email"/>
      <input type="password" v-model="user.password" placeholder="Password"/>
      <button class="nick-button" type="submit" label="Register">Register</button>
    </div>
  </form>
</template>

<script>

import UserService from '@/services/userService';

export default {
  name: "RegisterPage",
  data() {
    return {
      user: {
        firstName: null,
        lastName: null,
        email: null,
        password: null
      }
    }
  },
  methods: {
    submit() {
      UserService.register(this.user)
    }
  }
};
</script>

<style lang="stylus" scoped>
button
  margin-top: 20px;
  width: 90%;
</style>