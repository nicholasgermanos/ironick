<template>
  <form @submit.prevent="submit" class="container m-3">
    <div class="m-4">
      <h1>Register</h1>
      <input v-model="user.firstName" placeholder="First Name"/>
      <input v-model="user.lastName" placeholder="Surname"/>
      <input v-model="user.email" placeholder="Email"/>
      <input type="password" v-model="user.password" placeholder="Password"/>
      <button class="muted-sage-green" type="submit" label="Register">Publish</button>
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
input, textarea
  width: 90%;
  margin-top: 30px;
  border: 0;
  border-bottom: 1px solid #aaa;
  font-size: 13px;
  font-weight: 300;
  color: #797a9e;
  letter-spacing: 0.11em;

.container
  width: fit-content;
  background: white;
  position: relative;
  display: inline-flex;
  box-shadow: 2px 2px 10px 0 rgba(33, 33, 33, 0.2);

button
  width: 100px;
  height: 30px;
  border: 0;
  outline: 0;
  color: white;
  font-size: 15px;
  font-weight: 400;
  position: relative;
  z-index: 3;
  font-family: $font;
  cursor: pointer;
  align-self: center;
  margin 20px;


</style>