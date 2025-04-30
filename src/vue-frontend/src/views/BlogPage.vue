<template>
  <div class="container bg-white w-100 p-0 pb-3 mt-3">
    <div class="w-100">
      <img :src="getCoverImage(blogPage)" class="card-img-top" alt="Cover Image">
    </div>
    <div class="article-header">
      <hr/>
      <div class="article-metadata">
        <p class="left">{{ getDate( blogPage ) }}</p>
        <p class="right">by Nicholas Germanos</p>
      </div>
      <hr/>
    </div>
    <div class="container p-5" v-if="blogPage !== null">
      <h1 class="text-uppercase">{{ blogPage.title }}</h1>
      <h4 class="fst-italic fw-light">{{ blogPage.subtitle }}</h4>
      <div class="ql-editor" v-html="blogPage.body"/>

      <div v-if="isAdmin()" class="text-center">
        <button v-on:click="deleteBlogPage(blogPage)"><i class="fa-solid fa-trash"></i> Delete</button>
        <button v-on:click="deleteBlogPage(blogPage)"><i class="fa-solid fa-pencil"></i> Edit</button>
      </div>
    </div>
  </div>
</template>

<script>
import { getCoverImage } from '@/utils/blogPageUtils';
import BlogPageService from '@/services/blogPageService';
import { isAdmin } from '@/utils/localStorageUtils';

export default {
  name: 'BlogPage',
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
  methods: {
    isAdmin,
    getCoverImage,
    deleteBlogPage( blogPage ) {
      if ( blogPage !== null && confirm( 'Are you sure?' ) ) {
        BlogPageService.deleteBlogPage( blogPage.id );
        this.$router.push( '/' );
      }
    },
    getDate( blogPage ) {
      if ( blogPage !== null ) {
        const timestamp = blogPage.timestamp;
        const date = new Date( timestamp );

        const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
        return date.toLocaleDateString( 'en-AU', options );
      }
    }
  }
};
</script>

<style lang="stylus" scoped>
p
  text-align: left

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

</style>