<template>
  <div style>
    <div class="left_menu">
      <Menu
        active-name="1-2"
        :open-names="['1']"
        style="width: 100%;"
        @on-select="getMarkdownContent"
      >
        <Submenu v-for="item in data1" :name="item.id" :key="item.id">
          <template slot="title">{{item.title}}</template>
          <MenuItem
            v-for="itemChild in item.children"
            :name="itemChild.url"
            :key="itemChild.id"
          >{{itemChild.title}}</MenuItem>
        </Submenu>
      </Menu>
    </div>
    <router-view />
    <div style="text-align: center;">
      ©2021 blog996.com 版权所有
      <a href="https://beian.miit.gov.cn">豫ICP备2021005024号-1</a>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import { localSave, localRead } from "@/libs/util";
export default {
  name: "index_page",
  data() {
    return {
      data1: []
    };
  },
  created() {
    //查询菜单
    this.getArticleMenuList();
  },
  mounted() {},
  methods: {
    ...mapActions([
      "getArticleMenuToArticleListForHome",
      "setArticleUrl",
      "setArticleMenuList"
    ]),
    getArticleMenuList() {
      this.getArticleMenuToArticleListForHome().then(res => {
        this.data1 = res.data;
        var articleMenuList=res.data;
        var url = this.data1[0].children[0].url;
        // console.log(url);
        this.loadingTable = false;
        this.setArticleMenuListByQuery(articleMenuList); //设置全局的文章菜单
        var localUrl = localRead("localUrl");
        if (localUrl != null && "" != localUrl && localUrl != undefined) {
          this.getMarkdownContentInit(localUrl);
        } else {
          this.getMarkdownContentInit(url);
        }
      });
    },

    setArticleMenuListByQuery(articleMenuList) {
      this.setArticleMenuList({ articleMenuList }).then(res => {});
    },
    getMarkdownContentInit(articleUrl) {
      this.setArticleUrl({ articleUrl }).then(res => {});
    },

    getMarkdownContent(articleUrl) {
      // console.log(articleUrl);
      // console.log(this.$route); //可以获取路由后面参数
      // console.log(this.$router); //可以获取路由后面参数
      // console.log(this.$route.name); //可以获取路由后面参数
      localSave("localUrl", articleUrl);
      this.setArticleUrl({ articleUrl }).then(res => {});
      // if (this.$route.name != "md") {
        let urlId=articleUrl.replace("md","html");
      if (true) {
        this.$router.push({
          name: "md",
          params:{id:urlId}

        });
      }
    }
  }
};
</script>

<style lang="less">
.left_menu {
  font-size: 16px;
  background-color: #fff;

  width: 20rem;
  position: fixed;
  z-index: 10;
  margin: 0;
  top: 3.6rem;
  left: 0;
  bottom: 0;
  box-sizing: border-box;
  border-right: 1px solid #eaecef;
  overflow-y: auto;

  .ivu-menu-light.ivu-menu-vertical
    .ivu-menu-item-active:not(.ivu-menu-submenu) {
    background: #ffffff;
  }
  .ivu-menu-light.ivu-menu-vertical
    .ivu-menu-item-active:not(.ivu-menu-submenu):after {
    width: 1px;
    background: #dcdee2;
  }
}

@media (max-width: 719px) {
  .left_menu {
    top: 0;
    z-index: 11000;
    padding-top: 3.6rem;
    transform: translateX(-100%);
    transition: transform 0.2s ease;
  }
}
@media (max-width: 959px) {
  .left_menu {
    z-index: 11000;
    font-size: 15px;
    padding-top: 3.6rem;
    width: 16.4rem;
  }
}
</style>
