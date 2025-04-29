<template>
  <div class="container">
    <h1 class="text-center">Users List</h1>
    <table class="table table-striped">
      <thead>
      <tr>
        <th>User Id</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
      </tr>
      </thead>
      <tbody>
        <tr v-for="user in users" v-bind:key = "user.id">
          <td>{{ user.id }}</td>
          <td>{{ user.firstName }}</td>
          <td>{{ user.lastName }}</td>
          <td>{{ user.email }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import UserService from '@/services/userService';
  export default {
    name: 'BlogUsers',
    data() {
      return {
        users: []
      }
    },
    methods: {
      getUsers() {
        UserService.getUsers().then( (response) => {
          this.users = response.data;
        });
      }
    },
    created() {
      this.getUsers();
    }
  }
</script>

<style scoped>

</style>