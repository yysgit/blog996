<template>
  <div :class="{sidebar_open:menuIsShow}">
    <div class="header">
      <div class="sidebar-button">
        <Icon type="md-menu" style=" font-size: 1.3rem; display: block;" @click="menuIsShowClick" />
      </div>
      <div style=" max-width: 200px;">
        <a href="/">
          <span class="site-name">Java 全栈知识体系</span>
        </a>
      </div>
      <div class="link" style="max-width: 1130px;">
        <div class="search-box">
          <!-- <Input search placeholder="Enter something..." /> -->
          <Select
            v-model="articleNameSearch"
            prefix="ios-search"
            filterable
            placeholder="Enter something..."
            :remote-method="getArticleList"
            :loading="articleSearchLoading"
            clearable
            @on-change="gotoUrl"
          >
            <Option
              v-for="(option, index) in articleList"
              :value="option.url"
              :key="index"
            >{{option.title}}</Option>
          </Select>
        </div>
        <div class="menu">
          <!--   <Dropdown>
            <a href="javascript:void(0)">
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
            <a href="javascript:void(0)">
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
          </Dropdown>-->

          <a href="javascript:void(0);" @click="aClick('about')" style="margin-left:1.1rem;">关于</a>
        </div>
      </div>
    </div>

    <router-view />
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "index_page",
  data() {
    return {
      menuIsShow: false,
      articleSearchLoading: false,
      articleNameSearch: "",
      articleList: []
    };
  },
  created() {},
  computed: {
    isFollow() {
      return this.$store.state.user.menuIsShow;
    }
  },
  watch: {
    isFollow(newVal, oldVal) {
      //do something
      this.menuIsShow = this.$store.state.user.menuIsShow;
    }
  },
  mounted() {},
  methods: {
    ...mapActions(["setMenuIsShow", "getArticleListByName"]),
    getArticleList(query) {
      console.log(query);
      if (query !== "") {
        this.articleSearchLoading = true;
        setTimeout(() => {
          let searchPream = {
            articleNameSearch: query
          };
          this.getArticleListByName({ searchPream }).then(res => {
            this.articleList = res.data;
            this.articleSearchLoading = false;
          });
        }, 200);
      } else {
        this.articleList = [];
      }
    },
    gotoUrl() {
      console.log();
      if (this.articleNameSearch) {
        if (true) {
          const { href } = this.$router.resolve({
            name: "md",
            params: { id: this.articleNameSearch.replace("md", "html") }
          });
          window.open(href, "_blank");
        }
      }
    },
    menuIsShowClick() {
      this.menuIsShow = !this.$store.state.user.menuIsShow;
      var menuIsShow = this.menuIsShow;
      this.setMenuIsShow({ menuIsShow }).then(res => {});
    },

    aClick(about) {
      this.$router.push({
        name: about
      });
    }
  }
};
</script>

<style lang="less">
.header {
  padding: 0.7rem 1.5rem;
  line-height: 2.2rem;
  background-color: #4c6069;
  color: #fec41b;
  position: fixed;
  top: 0;
  left: 0;
  z-index: 20000;
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
    padding: 0.6rem;
    top: 0.6rem;
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
    font-size: 0.9rem;
    position: absolute;
    right: 1.5rem;
    top: 0.7rem;
    display: flex;
  }

  .ivu-dropdown {
    margin-left: 1.1rem;
  }

  .menu {
    margin-left: 1.1rem;
  }
  .menu a {
    color: hsla(0, 0%, 100%, 0.7);
  }
  .menu a:hover {
    color: #fec41b;
    border-bottom: 2px solid #fec41b;
  }

  @media (max-width: 719px) {
    .menu {
      display: none;
    }
    .header {
      padding-left: 4rem;
    }
    .sidebar-button {
      display: block;
    }
  }
}

@media (max-width: 719px) {
  .header {
    padding-left: 4rem;
  }
  .sidebar_open .left_menu {
    transform: translateX(0);
  }
}

@media (max-width: 959px) {
  .search-box input {
    cursor: pointer;
    width: 7px!important;
    border-color: transparent;
    position: relative;
    padding: 0;
  }
  .search-box input:focus {
    cursor: text;
    left: 0;
    width: 10rem !important;
  }
}
</style>
