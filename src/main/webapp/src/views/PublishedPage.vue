<script setup>
import BlogEntries from '@/views/BlogEntries.vue';
</script>

<template>
  <BlogEntries :fetch-user-blogs="true" section-header="My Posts"/>
</template>