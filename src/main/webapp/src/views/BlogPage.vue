<template>
  <div v-if="blogPage" class="container blog-page w-100 p-0 pb-3 mt-3">
    <div id="cover-image-container">
      <img :src="getCoverImage(blogPage)" class="card-img-top" alt="Cover Image">
    </div>
    <div class="article-header">
      <hr/>
      <div class="article-metadata">
        <p class="left">{{ getDate() }}</p>
        <p class="right">by {{ blogPage.user.firstName }} {{ blogPage.user.lastName }}</p>
      </div>
      <hr/>
    </div>
    <div class="container p-5" v-if="blogPage !== null">
      <h1 class="text-uppercase">{{ blogPage.title }}</h1>
      <h4 class="fst-italic fw-light">{{ blogPage.subtitle }}</h4>
      <div class="ql-editor" v-html="blogPage.body"/>

      <div v-if="isAdmin()" class="text-center admin-section">
        <button class="nick-button" v-on:click="deleteBlogPage()"><i class="fa-solid fa-trash"></i> Delete</button>
        <router-link :to="{name: 'entryFormEdit', params: {blogID: blogPage.id}}">
          <button class="nick-button"><i class="fa-solid fa-pencil"></i> Edit</button>
        </router-link>
        <NickCheckBox v-model="blogPage.featured" checkbox-label="Featured" :on-load-value="blogPage.featured"/>
      </div>
    </div>
  </div>
</template>

<script>
import { getCoverImage } from '@/utils/blogPageUtils';
import BlogPageService from '@/services/blogPageService';
import { isAdmin } from '@/utils/localStorageUtils';
import NickCheckBox from '@/components/NickCheckBox.vue';

export default {
  name: 'BlogPage',
  components: { NickCheckBox },
  data() {
    return {
      blogPage: null
    };
  },
  props: {
    blogID: String
  },
  beforeMount() {
    BlogPageService.getBlogPage( this.$props.blogID ).then( response => {
                                                              this.blogPage = response.data;
                                                            }
    );
  },
  watch: {
    'blogPage.featured': function() {
      if ( this.blogPage.featured === true ) {
        BlogPageService.featureBlogPage( this.blogPage.id );
      } else {
        BlogPageService.unFeatureBlogPage( this.blogPage.id );
      }
    }
  },
  methods: {
    isAdmin,
    getCoverImage,
    deleteBlogPage() {
      if ( this.blogPage !== null && confirm( 'Are you sure?' ) ) {
        BlogPageService.deleteBlogPage( this.blogPage.id );
        this.$router.push( '/' );
      }
    },
    getDate() {
      if ( this.blogPage !== null ) {
        const timestamp = this.blogPage.timestamp;
        const date = new Date( timestamp );

        const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
        return date.toLocaleDateString( 'en-AU', options );
      }
    }
  }
};
</script>

<style lang="stylus" scoped>
#cover-image-container
  height 50%

p
  text-align: left

.blog-page
  position: relative;
  background: $theme-white

.article-header
  width: 90%
  margin-left: 5%

  .article-metadata
    width 100%
    display inline-flex

    .left, .right
      width 50%
      margin-bottom 0

    .right
      text-align right

.nick-button
  margin: 0 10px 0 10px;

.admin-section
  display: inline-flex;
  margin-top: 30px;
</style>