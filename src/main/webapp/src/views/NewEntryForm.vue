<template>
  <div class="container form-container">
    <div class="container blog-form">
      <div class="m-4">
        <h1>New Blog Post</h1>
        <p>Please remember to back up your posts as we are still running in beta mode</p>
        <input required v-model="blogPost.title" placeholder="Title"/>
        <input required v-model="blogPost.subtitle" placeholder="Subtitle"/>
        <input required type="file" @change="uploadImage($event)"/>
        <output>
          <img id="image-preview" :src="previewUrl" v-if="previewUrl" alt="Cover Image Preview">
        </output>
        <div class="rich-text-editor">
          <QuillEditor :toolbar="toolbarOptions" theme="snow" v-model:content="blogPost.body" content-type="html"
                       @textChange="setupAutoSave"
                       placeholder="Body"/>
        </div>
        <div class="buttons">
          <button class="nick-button" type="submit" label="Save Draft" v-on:click="submit(false, true)">Save Draft</button>
          <button class="nick-button" type="submit" label="Publish" v-on:click="submit(true, true)">Publish</button>
        </div>
        <div id="last-saved" v-if="blogPost.lastSaved">
          <p>Last saved {{ getFormattedDate( blogPost.lastSaved ) }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import BlogPageService from '@/services/blogPageService';
import { QuillEditor } from '@vueup/vue-quill';
import '@vueup/vue-quill/dist/vue-quill.snow.css';
import { getLoggedInID } from '@/utils/localStorageUtils';
import { getFormattedDate } from '@/utils/utils';

export default {
  name: 'NewEntryForm',
  components: {
    QuillEditor
  },
  data() {
    const toolbarOptions = [
      [ 'bold', 'italic', 'underline', 'strike' ],        // toggled buttons
      [ 'blockquote', 'code-block' ],
      [ 'link', 'image', 'video', 'formula' ],

      [ { 'header': 1 }, { 'header': 2 } ],               // custom button values
      [ { 'list': 'ordered' }, { 'list': 'bullet' }, { 'list': 'check' } ],
      [ { 'script': 'sub' }, { 'script': 'super' } ],      // superscript/subscript
      [ { 'indent': '-1' }, { 'indent': '+1' } ],          // outdent/indent

      [ { 'size': [ 'small', false, 'large', 'huge' ] } ],  // custom dropdown
      [ { 'header': [ 1, 2, 3, 4, 5, 6, false ] } ],

      [ { 'color': [] }, { 'background': [] } ],          // dropdown with defaults from theme
      [ { 'font': [] } ],
      [ { 'align': [] } ],

      [ 'clean' ]                                         // remove formatting button
    ];

    return {
      blogPost: {
        id: null,
        title: '',
        subtitle: '',
        body: '',
        timestamp: null,
        featured: false,
        published: false,
        lastSaved: null
      },
      userID: getLoggedInID(),
      coverImage: null,
      previewUrl: null,
      toolbarOptions
    };
  },
  props: {
    blogID: String
  },
  beforeMount() {
    BlogPageService.getBlogPage( this.$props.blogID ).then( response => this.blogPost = response.data );
  },
  methods: {
    getFormattedDate,
    submit: function( publish, redirect ) {
      if ( publish === true ) {
        confirm( 'Are you ready to publish?' );
      }

      window.onbeforeunload = undefined;

      this.blogPost.published = publish;
      if ( this.blogPost.timestamp === undefined ) {
        this.blogPost.timestamp = Date.now();
      }

      this.blogPost.lastSaved = Date.now();
      BlogPageService.addBlogPage( this.blogPost, this.userID, this.coverImage ).then( ( response ) => {
        this.blogPost = response.data;
      } );

      if ( redirect === true && publish === true ) {
        this.$router.push( '/' );
      } else if( redirect === true) {
        this.$router.push( '/newEntryForm/' + this.blogPost.id );
      }
    },
    uploadImage( event ) {
      this.coverImage = event.target.files[ 0 ];
      if ( !this.coverImage ) {
        return false;
      }
      if ( !this.coverImage.type.match( 'image.*' ) ) {
        return false;
      }
      const reader = new FileReader();
      const that = this;
      reader.onload = function( e ) {
        that.previewUrl = e.target.result;
      };
      reader.readAsDataURL( this.coverImage );
    },
    setupAutoSave() {
      window.onbeforeunload = function() {
        return 'Unsaved changes detected, are you sure you want to leave?';
      };
    }
  },
  unmounted() {
    window.onbeforeunload = undefined;
    this.submit( false, false );
  }
};
</script>

<style lang="stylus" scoped>
.blog-form
  padding-top 10px

  button
    width 45%

.buttons
  display flex
  justify-content space-around

.rich-text-editor
  margin-top 30px;

button
  margin-top: 20px;

:deep(.ql-editor)
  resize: vertical;
  height: 200px;

#image-preview
  width: 100%;

#last-saved
  font-size 14px
</style>