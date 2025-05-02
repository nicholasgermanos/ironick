<template>
  <BlogEntries/>
</template>

<script>
import BlogEntries from '@/views/BlogEntries.vue';
export default {
  components: {
    BlogEntries
  },
  emits: ['open-Blog-Page']
}
</script>

<style lang="stylus" scoped>

</style>