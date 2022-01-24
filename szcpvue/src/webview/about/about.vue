<template>
  <!--回显-->
  <div class="page" @click="setMenuIsShowClick" style="   padding-top: 3.6rem;" id="article_top">
    <article>
      <div style="padding:20px;">
        <div>
          <strong>关于作者</strong>
          <br />
          <br />
          <!-- <List header="Header" footer="Footer" border> -->
          <List border>
            <ListItem>网名: 简单点</ListItem>
            <ListItem>爱好: 捣鼓新技术,专研新知识!</ListItem>
            <ListItem>邮箱: 1095737364@qq.com</ListItem>
            <ListItem>QQ群交流: 647474923(满),1051169670</ListItem>
            <ListItem>QQ群交流(收费): 123300273</ListItem>
          </List>
          <br />
          <strong>关于本网站</strong>
          <br />
          <br />
          <List border size="small">
            <ListItem>GitHub(Star): https://github.com/yysgit/blog996/ </ListItem>
            <ListItem>说明: 本站是为了搭建国际一流博客网站,给中高级编程人员提供相关知识要点!</ListItem>
          </List>
          <br />
          <strong>更新日志:</strong>
          <br />
          <br />
          <List border size="large" style="padding:50px">
            <Timeline>
               <TimelineItem>
                <p class="time">2022年1月11号</p>
                <p class="content" style="padding-top:20px">
                  <Card :bordered="true">
                    <p slot="title">文章功能优化</p>
                    <p>1.文章搜索跳转功能</p>
                  </Card>
                  
                </p>
              </TimelineItem>
              <TimelineItem>
                <p class="time">2021年12月19号</p>
                <p class="content" style="padding-top:20px">
                  <Card :bordered="true">
                    <p slot="title">文章功能优化</p>
                    <p>1.文章加入了标题</p>
                    <p>2.文章加入了修改时间</p>
                    <p>3.文章加入了访问次数</p>
                  </Card>
                  
                </p>
              </TimelineItem>
              <TimelineItem>
                <p class="time">2021年6月26号</p>
                <p class="content" style="padding-top:20px">
                  <Card :bordered="true">
                    <p slot="title">文章功能优化</p>
                    <p>1.路径访问优化,可以指定路径访问指定页面</p>
                  </Card>
                  <Card :bordered="true" style="margin-top:20px">
                    <p slot="title">系统优化</p>
                    <p>1.更新百度统计方式</p>
                    <p>2.加入百度SEO</p>
                    <p>3.nginx支持输入blog996.com访问网站</p>
                  </Card>
                </p>
              </TimelineItem>
              <TimelineItem>
                <p class="time">2021年6月5号</p>
                <p class="content" style="padding-top:20px">
                  <Card :bordered="true">
                    <p slot="title">文章功能优化</p>
                    <p>1.文章图片显示层遮挡修复</p>
                    <p>2.本地化记录上次访问的页面</p>
                    <p>3.文章底部页面浏览出现上一页下一页</p>
                  </Card>
                  <Card :bordered="true" style="margin-top:20px">
                    <p slot="title">系统优化</p>
                    <p>1.加入百度统计</p>
                    <p>2.添加关于信息和版本更新页面</p>
                    <p>3.文章列表排序整理,方便开发</p>
                  </Card>
                </p>
              </TimelineItem>
            </Timeline>
          </List>
        </div>
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
      articleUrl: "",
      editorShow: false
    };
  },
  created() {
    // this.getArticleContent();
    // this.findList();
  },
  computed: {
    isFollow() {
      return this.$store.state.user.articleUrl;
    }
  },
  watch: {
    //监听url变化
    isFollow(newVal, oldVal) {
      // console.log(newVal);
      // console.log(oldVal);
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
      // console.log(this.articleUrl);
      let url = this.articleUrl;
      // console.log(document.getElementById("article_top"));
      if (document.getElementById("article_top") != null) {
        document.getElementById("article_top").scrollIntoView();
      }

      this.getMarkdownContent({ url }).then(res => {
        this.blogContent = res.data.markdownData;
      });
      var menuIsShow = false;
      this.setMenuIsShow({ menuIsShow }).then(res => {});
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


.ivu-card-head {
    border-bottom: 1px solid #fec41b!important;
    background-color: #fec41b!important;
}
.ivu-card-bordered {
    border: 1px solid #fec41b!important;
    border-color: #fec41b!important;
}

</style>
