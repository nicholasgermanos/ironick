<template>
  <form @submit.prevent="submit" class="container m-3">
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
        <QuillEditor :toolbar="toolbarOptions" theme="snow" v-model:content="blogPost.body" content-type="html" placeholder="Body"/>
      </div>
      <button type="submit" label="Publish" v-on:click="submit">Publish</button>
    </div>
  </form>
</template>

<script>
import BlogPageService from '@/services/blogPageService';
import { QuillEditor } from '@vueup/vue-quill';
import '@vueup/vue-quill/dist/vue-quill.snow.css';

export default {
  name: 'NewEntryForm',
  components: {
    QuillEditor
  },
  data() {
   const toolbarOptions = [
      ['bold', 'italic', 'underline', 'strike'],        // toggled buttons
      ['blockquote', 'code-block'],
      ['link', 'image', 'video', 'formula'],

      [{ 'header': 1 }, { 'header': 2 }],               // custom button values
      [{ 'list': 'ordered'}, { 'list': 'bullet' }, { 'list': 'check' }],
      [{ 'script': 'sub'}, { 'script': 'super' }],      // superscript/subscript
      [{ 'indent': '-1'}, { 'indent': '+1' }],          // outdent/indent

      [{ 'size': ['small', false, 'large', 'huge'] }],  // custom dropdown
      [{ 'header': [1, 2, 3, 4, 5, 6, false] }],

      [{ 'color': [] }, { 'background': [] }],          // dropdown with defaults from theme
      [{ 'font': [] }],
      [{ 'align': [] }],

      ['clean']                                         // remove formatting button
    ];

    return {
      blogPost: {
        title: '',
        subtitle: '',
        body: '',
        timestamp: null
      },
      coverImage: null,
      previewUrl: null,
      toolbarOptions
    };
  },
  methods: {
    submit: function(/*event*/) {
      this.$data.blogPost.timestamp = Date.now()
      BlogPageService.addBlogPage( this.$data.blogPost, this.coverImage );
      this.$router.push('/')
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
    }
  },
};
</script>

<style lang="stylus" scoped>
body,
form
  display: flex;
  justify-content: center;
  flex-direction: column;
  width: 90%;

  .rich-text-editor
    margin-top 30px;

  .ql-container
    min-height: 200px !important;

  input, textarea
    width: 90%;
    margin-top: 30px;
    border: 0;
    border-bottom: 1px solid #aaa;
    font-size: 13px;
    font-weight: 300;
    color: #797a9e;
    letter-spacing: 0.11em;

    &::placeholder
      color: #333;
      font-size: 14px;

    &:focus
      outline: 0;
      border-bottom: 2px solid rgba(129, 178, 154, 1);

  button
    margin-top: 20px;

.container
  width: 100%;
  background: white;
  position: relative;
  display: inline-flex;
  box-shadow: 2px 2px 10px 0 rgba(33, 33, 33, 0.2);

#image-preview
  width: 100%;
</style>