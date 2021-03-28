<template>
  <!--回显-->
  <div class="page" @click="setMenuIsShowClick">
    <article>
      <div style="padding:20px">
        <mavon-editor
          class="md"
          :value="blogContent"
          :subfield="false"
          :defaultOpen="'preview'"
          :toolbarsFlag="false"
          :editable="false"
          :scrollStyle="true"
          :ishljs="true"
        />
      </div>
    </article>
  </div>
</template>

<script>
import { mavonEditor } from "mavon-editor";
import "mavon-editor/dist/css/index.css";
import { mapActions } from "vuex";
export default {
  name: "markdown",
  // 注册
  components: {
    mavonEditor
  },
  data() {
    return {
      content: "", // 输入的markdown
      html: "", // 及时转的html
      blogContent: "",
      currentPage: 1,
      fetchNum: 10,
      totalPage: 0,
      loading: false, //表格加载转圈
      loadingModel: false, //按钮加载转圈
      articleUrl: ""
    };
  },
  created() {
    this.getArticleContent();
    // this.findList();
  },
  computed: {
    isFollow() {
      return this.$store.state.user.articleUrl;
    }
  },
  watch: {
    isFollow(newVal, oldVal) {
      console.log(newVal);
      console.log(oldVal);
      //do something
      this.articleUrl = this.$store.state.user.articleUrl;
      this.getArticleContent();
    }
  },
  methods: {
    ...mapActions([
      "markdownTest",
      "findMarkdownList",
      "setMenuIsShow",
      "getMarkdownContent"
    ]),

    setMenuIsShowClick() {
      var menuIsShow = false;
      this.setMenuIsShow({ menuIsShow }).then(res => {});
    },

    getArticleContent() {
      this.articleUrl = this.$store.state.user.articleUrl;
      console.log(this.articleUrl);
      let url = this.articleUrl;

      this.getMarkdownContent({ url }).then(res => {
        this.blogContent = res.data;
      });
    }
  }
};
</script>

<style lang="less">
.page {
  padding-bottom: 2rem;
  display: block;
  padding-right: 3.5rem;
  padding-left: 20rem;
}

// @media (max-width: 959px){
// .page {
//     padding-right: 3.5rem;
//     padding-left: 16.4rem;
// }
// }
@media (max-width: 719px) {
  .page {
    padding-right: 0;
    padding-left: 0;
  }
}
</style>
