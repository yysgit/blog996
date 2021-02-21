<template>
  <div style=" margin-top: 3.6rem;">


    <div class="left_menu">
      左侧菜单
    </div>
    <router-view/>
  </div>
</template>

<script>
  import {mapActions} from 'vuex'

  export default {
    name: 'index_page',
    data() {
      return {}
    },
    created() {

    },
    mounted() {

    },
    methods: {
      ...mapActions([]),


    }
  }
</script>

<style lang="less">


</style>
