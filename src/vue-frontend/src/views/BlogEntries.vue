<script setup>
  import BlogPageService from '@/services/blogPageService'
  BlogPageService.getBlogPages()
</script>

<template>
  <div class="container p-5 flex-wrap">
    <div class="d-flex flex-wrap justify-content-center">
      <div class="card zoom" v-for="blogEntry of blogEntries" v-bind:key="blogEntry.id">
        <div class="preview-cover-image">
          <img :src="getCoverImage(blogEntry)" class="card-img-top" alt="Cover Image">
        </div>
        <div class="card-body">
          <h5 class="funky">{{ blogEntry.title }}</h5>
          <p class="card-text">{{ blogEntry.subtitle }}</p>
          <router-link v-if="blogEntry.id" :to="{name: 'blog', params: {blogID: blogEntry.id}}" class="w-full deep-slate-blue"><button>Read<span class="arrow fa-solid fa-arrow-right"/></button></router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getCoverImage } from '@/utils/blogPageUtils';

export default {
  name: 'BlogEntries',
  components: {},
  data() {
    return {
      blogEntries: [],
    };
  },
  methods: {
    getCoverImage,
    getBlogPages() {
      BlogPageService.getBlogPages().then( ( response ) => {
        this.blogEntries = response.data;
      } );
    },
  },
  created() {
    this.getBlogPages();
  },
};
</script>

<style lang="stylus" scoped>
.card
  margin: 10px 10px 10px 10px;
  width: 18rem;
  height: 20rem;
  overflow: hidden

.card-body
  text-align: left;

.arrow
  margin-left: 3px;

.preview-cover-image
  height 55%;
  overflow: hidden

.zoom
  img
    transition: transform .8s; /* Animation */

.zoom:hover
  img
    transform: scale(1.2); /* (150% zoom - Note: if the zoom is too large, it will go outside of the viewport) */
</style>