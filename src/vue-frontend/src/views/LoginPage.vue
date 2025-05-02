<template>
  <form @submit.prevent="submit" class="container blog-form">
    <div class="m-4">
      <h1>Welcome back</h1>
      <h6>(login below)</h6>
      <input v-model="user.email" placeholder="Email"/>
      <input type="password" v-model="user.password" placeholder="Password"/>
      <table class="remember-me">
        <tr>
          <td><NickCheckBox v-model="rememberMe"/></td>
          <td><label>Remember me</label></td>
        </tr>
      </table>
      <button class="nick-button" type="submit" label="Login">Login</button>
    </div>
  </form>
</template>

<script>
import UserService from '@/services/userService';
import router from '@/router';
import NickCheckBox from '@/components/NickCheckBox.vue';

export default {
  name: "LoginPage",
  components: { NickCheckBox },
  data() {
    return {
      user: {
        email: null,
        password: null
      },
      rememberMe: false
    }
  },
  methods: {
    submit: function() {
      UserService.login(this.user)
      router.push("/")
    }
  }
};
</script>


<style lang="stylus" scoped>
button
  margin: 40px 0 20px 0;
  width: 90%;

h6
  font-size: 12px;
// Remember me checkbox
.remember-me
  width: 100%;
  margin-top: 30px;
  display: block;

  td
    height 100%;

  input
    margin-top: 0;

  label
    background: $theme-white
</style>