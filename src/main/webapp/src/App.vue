<template>
  <MenuBar :key="menuBarKey"/>
  <div class="newlines">
    <ShootingStars/>
    <router-view @login-success="forceRerender" @logout-success="forceRerender"/>
  </div>
</template>

<script>
  import MenuBar from '@/components/MenuBar.vue';
  import ShootingStars from '@/components/ShootingStars.vue';
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
      ShootingStars,
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
  text-align: center;
  min-height 100rem;

  // You also need to change the body style in index.html if you want to change the background
  background: $theme-grey-ish;
</style>