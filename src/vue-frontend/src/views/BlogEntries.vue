<script setup>
  import BlogPageService from '@/services/blogPageService'
  BlogPageService.getBlogPages()
</script>

<template>
  <div class="container p-5 flex-wrap">
    <div class="d-flex flex-wrap justify-content-center">
      <div class="card" style="margin: 10px 10px 10px 10px; width: 18rem; height: 20rem;"
           v-for="blogEntry of blogEntries" v-bind:key="blogEntry.id">
        <img :src="getCoverImage(blogEntry)" class="preview-cover-image card-img-top" alt="...">
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
.card-body
  text-align: left;

.arrow
  margin-left: 3px;

.preview-cover-image
  height 60%;
</style>