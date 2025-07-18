<template>
  <ShootingStars id="stars"/>
  <MenuBar v-if="!isFullScreen" @fullscreen="openFullscreen" :key="menuBarKey"/>
  <div v-if="!isFullScreen" class="newlines">
    <router-view class="main-content" @login-success="forceRerender" @logout-success="forceRerender"/>
    <FooterBar/>
  </div>
</template>

<script>
import MenuBar from '@/components/MenuBar.vue';
import ShootingStars from '@/components/ShootingStars.vue';
import FooterBar from '@/components/FooterBar.vue';
import { ref } from 'vue';

export default {
  name: 'App',
  setup() {
    const menuBarKey = ref( 0 );
    const forceRerender = () => {
      menuBarKey.value++;
    };

    return {
      menuBarKey,
      forceRerender,
    };
  },
  data() {
    return {
      isFullScreen: false
    }
  },
  mounted() {
    document.addEventListener('fullscreenchange', this.handleFullScreen)
  },
  methods: {
    handleFullScreen() {
      this.isFullScreen = !!document.fullscreenElement
    },
    openFullscreen() {
      var fullscreenButton = document.getElementById( 'stars' );
      if ( fullscreenButton.requestFullscreen ) {
        fullscreenButton.requestFullscreen();
      } else if ( fullscreenButton.webkitRequestFullscreen ) { /* Safari */
        fullscreenButton.webkitRequestFullscreen();
      } else if ( fullscreenButton.msRequestFullscreen ) { /* IE11 */
        fullscreenButton.msRequestFullscreen();
      }
    }
  },
  components: {
    ShootingStars,
    MenuBar,
    FooterBar
  }
};
</script>

<style lang="stylus">
#app
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;

  margin-top: 56px;
  min-height 100vh
  max-width: 100%;

  display flex
  flex 1 1 100%

.main-content
  display flex
  flex-grow 1
  width 100%

</style>
