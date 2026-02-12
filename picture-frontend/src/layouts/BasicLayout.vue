<template>
  <div id="basicLayout">
    <a-layout style="min-height: 100vh">
      <a-layout-header class="header">
        <GlobalHeader />
      </a-layout-header>

      <a-layout-content class="content">
        <router-view />
      </a-layout-content>

      <a-layout-footer class="footer">
        <a href="https://www.codefather.cn" target="_blank">By back2_thebasic</a>
      </a-layout-footer>
    </a-layout>
  </div>
</template>

<script setup lang="ts">
import GlobalHeader from '@/components/GlobalHeader.vue';

</script>

<style>
#basicLayout .header {
  background-color: #fff;
  margin-bottom: 16px;
  padding-inline: 20px;
  color: unset;
}

#basicLayout .content {
  background: linear-gradient(to right, #efefef, #fff);
  padding: 20px;
}

#basicLayout .footer {
  background-color: #efefef;
  text-align: center;
}
</style>
