<template>
  <h1>Logout</h1>
</template>

<script>
  import UserService from '@/services/userService';
  import router from '@/router';

  export default {
    name: "LogoutPage",
    created() {
      UserService.logout()
      this.$emit("logout-success")
      router.push("/")
    }
  }
</script>

<style scoped lang="stylus">

</style>