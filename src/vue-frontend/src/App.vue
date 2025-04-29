<template>
  <MenuBar :key="menuBarKey"/>
  <div class="newlines">
    <router-view @login-success="forceRerender" @logout-success="forceRerender"/>
  </div>
</template>

<script>
  import MenuBar from '@/components/MenuBar.vue';
  import { ref } from 'vue';

  export default {
    name: 'App',
    setup() {

      const menuBarKey = ref(0)
      const forceRerender = () => {
        menuBarKey.value++;
      }

      return {
        menuBarKey,
        forceRerender
      }
    },
    components: {
      MenuBar
    }
  };
</script>

<style lang="stylus">
#app
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  margin-top: 40px;
  background: #F4F1DE;
  text-align: center;
</style>