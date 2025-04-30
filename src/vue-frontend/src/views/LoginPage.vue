<template>
  <form @submit="submit" class="container blog-form m-3">
    <div class="m-4">
      <h1>Welcome back</h1>
      <input v-model="user.email" placeholder="Email"/>
      <input type="password" v-model="user.password" placeholder="Password"/>
      <button class="nick-button" type="submit" label="Login">Login</button>
    </div>
  </form>
</template>

<script>
import UserService from '@/services/userService';
import router from '@/router';

export default {
  name: "LoginPage",
  data() {
    return {
      user: {
        email: null,
        password: null
      }
    }
  },
  methods: {
    submit: function() {
      UserService.login(this.user)
      this.$emit('login-success')
      router.push("/")
    }
  }
};
</script>


<style lang="stylus" scoped>

button
  margin-top: 20px;
  width: 90%;
</style>