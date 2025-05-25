<template>
  <div class="container form-container">
    <form class="blog-form">
      <div id="contribute-options">
          <router-link to="/newEntryForm"
                       class="fw-bold nav-link">
            <div class="contribute-option">
              New
            </div>
          </router-link>
          <router-link to="/drafts"
                       class="fw-bold nav-link">
            <div class="contribute-option">
              My Drafts
            </div>
          </router-link>
          <router-link to="/published"
                       class="fw-bold nav-link">
            <div class="contribute-option">
              Published
            </div>
          </router-link>
      </div>
    </form>

  </div>
</template>

<script>
export default {
  name: 'ContributeMenu'
};
</script>

<style scoped lang="stylus">
#contribute-options
  display flex
  flex-direction row
  align-items center

.contribute-option
  width 300px
  height 200px
  justify-self center
  align-content center
  margin 10px 10px 10px 10px
  border-color $theme-dark
  border-style solid
  border-radius 5px

</style>