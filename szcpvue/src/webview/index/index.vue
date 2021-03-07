<template>
  <!--编辑-->
  <!--<div>-->
  <!--<mavon-editor-->
  <!--v-model="content"-->
  <!--ref="md"-->
  <!--@change="change"-->
  <!--style="min-height: 600px"-->
  <!--/>-->

  <!--<button @click="submit">提交</button>-->
  <!--</div>-->

  <!--回显-->
  <div class="page" @click="setMenuIsShowClick">
    <article>
      <div style="padding:20px">
        <mavon-editor
          class="md"
          :value="Blog"
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
      Blog: "",
      currentPage: 1,
      fetchNum: 10,
      totalPage: 0,
      loading: false, //表格加载转圈
      loadingModel: false //按钮加载转圈
    };
  },
  methods: {
    ...mapActions(["markdownTest", "findMarkdownList", "setMenuIsShow"]),

    setMenuIsShowClick() {
      var menuIsShow = false;
      this.setMenuIsShow({ menuIsShow }).then(res => {});
    },

    // 所有操作都会被解析重新渲染
    change(value, render) {
      // render 为 markdown 解析后的结果[html]
      this.html = render;
    },
    // 提交
    submit() {
      console.log(this.content);
      console.log(this.html);
    },
    domarkdownTest() {
      this.markdownTest().then(res => {
        // console.log(res);
        this.Blog = res.msg;
      });
    },
    findList() {
      this.loading = true;
      let searchPream = {
        page: this.currentPage,
        limit: this.fetchNum
      };
      this.findMarkdownList({ searchPream }).then(res => {
        console.log(res);
        this.loading = true;
      });
    }
  },
  created() {
    this.domarkdownTest();
    // this.findList();
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
