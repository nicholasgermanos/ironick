<template>
<BlogEntries :drafts="true" section-header="Drafts"/>
</template>

<script>
import BlogEntries from '@/views/BlogEntries.vue';

export default {
  name: "DraftsPage",
  components: {
    BlogEntries
  }
}
</script>