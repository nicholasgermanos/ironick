<template>
  <div v-if="blogPage" class="container blog-page w-100 p-0 pb-3 mt-3">
    <div id="cover-image-container">
      <img :src="getCoverImage(blogPage)" class="card-img-top" alt="Cover Image">
    </div>
    <div v-if="blogPage.aboutPage !== true" class="article-header">
      <hr/>
      <div class="article-metadata">
        <p class="left">{{ getDate() }}</p>
        <p class="right">by {{ blogPage.author }}</p>
      </div>
      <hr/>
    </div>
    <div class="container p-5">
      <h1 class="text-uppercase">{{ blogPage.title }}</h1>
      <h4 class="fst-italic fw-light">{{ blogPage.subtitle }}</h4>
      <div class="ql-editor" v-html="blogPage.body"/>

      <div v-if="isAdmin()" class="text-center admin-section">
        <button v-if="blogPage.aboutPage!== true" class="nick-button" v-on:click="deleteBlogPage()"><i class="fa-solid fa-trash"></i>
          Delete
        </button>
        <router-link :to="{ name: 'entryFormEdit', params: { blogID: blogPage.id } }">
          <button class="nick-button"><i class="fa-solid fa-pencil"></i> Edit</button>
        </router-link>
        <button v-if="blogPage.published !== true && blogPage.aboutPage !== true" class="nick-button" v-on:click="publish()"><i
            class="fa-solid fa-trash"></i>
          Publish
        </button>
        <button v-if="blogPage.published === true && blogPage.aboutPage !== true" class="nick-button" v-on:click="unPublish()"><i
            class="fa-solid fa-trash"></i>
          UnPublish
        </button>
        <NickCheckBox v-if="blogPage.aboutPage!== true" v-model="blogPage.featured" checkbox-label="Featured"
                      :on-load-value="blogPage.featured"/>
      </div>
    </div>
  </div>
</template>

<script>
import { getCoverImage } from '@/utils/blogPageUtils';
import BlogPageService from '@/services/blogPageService';
import { isAdmin } from '@/utils/localStorageUtils';
import NickCheckBox from '@/components/NickCheckBox.vue';
import { getFormattedDate, isEmpty } from '@/utils/utils';

export default {
  name: 'BlogPage',
  components: { NickCheckBox },
  data() {
    return {
      blogPage: null
    };
  },
  props: {
    blogID: String,
    about: Boolean
  },
  beforeMount() {
    if ( this.about === true ) {
      BlogPageService.getAboutPage().then( response => this.blogPage = response.data );
    } else {
      if (isAdmin()) {
        BlogPageService.getBlogPageAuthorized( this.$props.blogID ).then( response => this.blogPage = response.data );
      } else {
        BlogPageService.getBlogPage( this.$props.blogID ).then( response => {
          if (isEmpty(response.data)) {
            this.$router.push('/')
          } else {
            this.blogPage = response.data
          }
        } );
      }
    }
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
        return getFormattedDate( this.blogPage.timestamp );
      }
    },
    publish() {
      BlogPageService.publishBlogPage( this.blogPage.id );
      this.$router.push( '/' );
    },
    unPublish() {
      BlogPageService.unPublishBlogPage( this.blogPage.id );
      this.$router.push( '/drafts' );
    }
  }
};
</script>

<style lang="stylus" scoped>
#cover-image-container
  overflow hidden
  height 50%

  img
    border-radius 5px 5px 0 0

p
  text-align: left

.blog-page
  display flex
  flex-direction column
  position: relative;
  background: $theme-white
  border-radius 10px 10px 5px 5px
  margin-top 0 !important
  margin-bottom 70px

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
  flex-wrap: wrap;
  margin-top: 30px;

:deep(.ql-editor)
  height unset

@media (max-width: $grid-breakpoint-sm )
  .blog-page
    margin-bottom 30px

	.admin-section {
		buttion {
		margin-bottom: 5px;
		}
		}

</style>
