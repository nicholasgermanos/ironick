<template>
  <div class="container p-5 flex-wrap">
    <div class="d-flex flex-wrap justify-content-center">
      <div v-for="blogEntry of blogEntries" v-bind:key="blogEntry.id">
        <div class="card zoom">
          <h1 v-if="isFirstCard(blogEntry.id)">{{ sectionHeader }}</h1>
          <div class="preview-cover-image">
            <img :src="getCoverImage(blogEntry)" class="card-img-top" alt="Cover Image">
          </div>
          <div class="card-body">
            <div class="card-headers">
              <h5 class="funky">{{ blogEntry.title }}</h5>
              <p>{{ blogEntry.subtitle }}</p>
            </div>
            <div class="card-read-button">
              <router-link v-if="blogEntry.id" :to="{name: 'blog', params: {blogID: blogEntry.id}}" class="w-full">
                <button class="nick-button">Read<span class="arrow fa-solid fa-arrow-right"/></button>
              </router-link>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getCoverImage } from '@/utils/blogPageUtils';
import BlogPageService from '@/services/blogPageService';
import { isEmpty } from '@/utils/utils';

export default {
  name: 'BlogEntries',
  components: {},
  data() {
    return {
      blogEntries: [],
      firstBlogEntry: 0
    };
  },
  props: {
    sectionHeader: {
      type: String,
      default: 'Articles'
    },
    featured: Boolean,
    unFeatured: Boolean
  },
  methods: {
    getCoverImage,
    getBlogPages() {
      BlogPageService.getBlogPages( this.featured, this.unFeatured ).then( ( response ) => {
        if ( isEmpty( response.data ) !== true ) {
          this.blogEntries = response.data;
          this.firstBlogEntry = this.blogEntries[ 0 ].id;
        }
      } );
    },
    isFirstCard( id ) {
      return id === this.firstBlogEntry;
    }
  },
  mounted() {
    this.getBlogPages();
  }
};
</script>

<style lang="stylus" scoped>
h1
  color: $theme-white;
  text-align: left;
  position: absolute;
  top: -55px;

// Main card
.card
  margin: 10px 10px 10px 10px;
  border: none;
  width: 18rem;
  height: 22rem;

.card-body
  background: $theme-white;
  border-radius: 0 0 10px 10px;

// Cover images
.preview-cover-image
  height 50%;
  min-height: 50%;
  overflow: hidden;

img
  height: 100%;

.zoom
  img
    transition: transform .8s;

.zoom:hover
  img
    transform: scale(1.1);

// Text on cards
.card-body
  height: 45%;
  text-align: left;

.card-headers
  height: 60%;

// Buttons
.card-read-button
  height: 40%;

.arrow
  margin-left: 3px;

</style>