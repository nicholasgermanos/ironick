<template>
  <div class="container form-container">
    <form class="blog-form">
      <div id="contribute-options">
          <router-link to="/newEntryForm"
                       class="fw-bold nav-link">
            <div class="contribute-option">
              <img src="../assets/contribute-new.png"  alt="New"/>
              New
            </div>
          </router-link>
          <router-link to="/drafts"
                       class="fw-bold nav-link">
            <div class="contribute-option">
              <img src="../assets/contribute-drafts.png"  alt="Drafts"/>
              My Drafts
            </div>
          </router-link>
          <router-link to="/published"
                       class="fw-bold nav-link">
            <div class="contribute-option">
              <img src="../assets/contribute-published.png"  alt="Published"/>
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
  flex-wrap wrap
  justify-content center
  align-items center

.contribute-option
  width 250px
  height 200px
  justify-self center
  align-content center
  flex-wrap wrap
  margin 10px 10px 10px 10px
  border-color $theme-dark
  border-style solid
  border-radius 5px

  img
    width 50px;
</style>