<template>
  <div>

    <div class="header">
      <div class="sidebar-button">
        <svg xmlns="http://www.w3.org/2000/svg" aria-hidden="true" role="img" viewBox="0 0 448 512" class="icon">
          <path fill="currentColor"
                d="M436 124H12c-6.627 0-12-5.373-12-12V80c0-6.627 5.373-12 12-12h424c6.627 0 12 5.373 12 12v32c0 6.627-5.373 12-12 12zm0 160H12c-6.627 0-12-5.373-12-12v-32c0-6.627 5.373-12 12-12h424c6.627 0 12 5.373 12 12v32c0 6.627-5.373 12-12 12zm0 160H12c-6.627 0-12-5.373-12-12v-32c0-6.627 5.373-12 12-12h424c6.627 0 12 5.373 12 12v32c0 6.627-5.373 12-12 12z"></path>
        </svg>
      </div>
      <div style=" max-width: 200px;">
        <a href="/">
          <span class="site-name">Java 全栈知识体系</span>
        </a>
      </div>
      <div class="link" style="max-width: 1130px;">
        <div class="search-box">

          <Input search placeholder="Enter something..." />
        </div>
        <div class="menu">

            <Dropdown>
              <a href="javascript:void(0)" >
                hover 触发
                <Icon type="md-arrow-dropdown" />
              </a>
              <DropdownMenu slot="list">
                <DropdownItem>驴打滚</DropdownItem>
                <DropdownItem>炸酱面</DropdownItem>
                <DropdownItem>豆汁儿</DropdownItem>
                <DropdownItem>冰糖葫芦</DropdownItem>
                <DropdownItem>北京烤鸭</DropdownItem>
              </DropdownMenu>
            </Dropdown>

            <Dropdown>
              <a href="javascript:void(0)" >
                hover 触发
                <Icon type="md-arrow-dropdown" />
              </a>
              <DropdownMenu slot="list">
                <DropdownItem>驴打滚</DropdownItem>
                <DropdownItem>炸酱面</DropdownItem>
                <DropdownItem>豆汁儿</DropdownItem>
                <DropdownItem>冰糖葫芦</DropdownItem>
                <DropdownItem>北京烤鸭</DropdownItem>
              </DropdownMenu>
            </Dropdown>

            <a href="/" style="margin-left:1.1rem;"> 关于</a>


        </div>
      </div>


    </div>

    <router-view/>
  </div>
</template>

<script>
  import {mapActions} from 'vuex'

  export default {
    name: 'index_page',
    data() {
      return {

      }
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
  .header {
    padding: .7rem 1.5rem;
    line-height: 2.2rem;
    background-color: #4c6069;
    color: #fec41b;
    position: fixed;
    top: 0;
    left: 0;
    z-index: 9999;
    right: 0;
    height: 3.6rem;
    display: block;
    box-sizing: border-box;

    .sidebar-button {
      cursor: pointer;
      display: none;
      width: 1.25rem;
      height: 1.25rem;
      position: absolute;
      padding: .6rem;
      top: .6rem;
      left: 1rem;
    }

    .site-name {
      display: inline-block;
      font-size: 1.3rem;
      font-weight: 600;
      color: #fec41b;
      position: relative;
    }

    .link {
      padding-left: 1.5rem;
      box-sizing: border-box;
      white-space: nowrap;
      font-size: .9rem;
      position: absolute;
      right: 1.5rem;
      top: .7rem;
      display: flex;
    }

    .ivu-dropdown {
      margin-left:1.1rem;
    }

    .menu{
      margin-left:1.1rem;
    }
    .menu a{
      color: hsla(0,0%,100%,.7);
    }
    .menu a:hover{
      color: #fec41b;
      border-bottom: 2px solid #fec41b;
    }


  }


</style>
