<template>
  <!--回显-->
  <div class="page" @click="setMenuIsShowClick" style="   padding-top: 3.6rem;" id="article_top">
    <article>
      <div style="padding:20px;">
        <div style=" margin-bottom: 40px; ">
          <div style=" text-align: center;">{{article.title}}</div>
          <div style="float:left;">{{article.updateTime}}</div>
          <div style="float:right;" v-show="article!=''">访问次数:{{article.viewsNumber}}</div>
        </div>

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
    <div>
      <Row>
        <Col>
          <div class="menuButtonLeft">
            <Button type="warning" v-show="mapUrlDataList.topUrl.topShow">
              <a
                href="javascript:void(0);"
                @click="aClick(mapUrlDataList.topUrl.url)"
                style="margin-left:1.1rem;"
              >上一篇:{{mapUrlDataList.topUrl.title}}</a>
            </Button>
          </div>
        </Col>
        <Col>
          <div class="menuButtonRight">
            <Button type="warning" v-show="mapUrlDataList.bottomUrl.bottomShow">
              <a
                href="javascript:void(0);"
                @click="aClick(mapUrlDataList.bottomUrl.url)"
                style="margin-left:1.1rem;"
              >下一篇: {{mapUrlDataList.bottomUrl.title}}</a>
            </Button>
          </div>
        </Col>
      </Row>
    </div>
  </div>
</template>

<script>
import { mavonEditor } from "mavon-editor";
import "mavon-editor/dist/css/index.css";
import { mapActions } from "vuex";
import { localSave, localRead } from "@/libs/util";
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
      article: "",
      currentPage: 1,
      fetchNum: 10,
      totalPage: 0,
      loading: false, //表格加载转圈
      loadingModel: false, //按钮加载转圈
      articleUrl: "",
      articleMenuList: [],
      editorShow: false,
      mapUrlDataList: {
        topUrl: {
          title: "",
          url: "",
          topShow: false
        },
        bottomUrl: {
          title: "",
          url: "",
          bottomShow: false
        }
      }
    };
  },
  created() {
    this.getArticleContent();
    // this.findList();
  },
  computed: {
    isFollow() {
      // console.log(this.$store.state.user.articleUrl);
      return this.$store.state.user.articleUrl;
    }
  },
  watch: {
    //监听url变化
    isFollow(newVal, oldVal) {
      // console.log(newVal);
      // console.log(oldVal);
      // console.log(this.$store.state.user.articleUrl);
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
      "setArticleUrl",
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
      let urlId = this.$route.params.id;

      if (
        urlId != null &&
        urlId != "" &&
        urlId != undefined &&
        urlId.indexOf("html") != -1
      ) {
        url = urlId.replace("html", "md");
      }
      console.log(urlId);

      if (url != null && url != "" && url != undefined) {
        // console.log(document.getElementById("article_top"));
        if (document.getElementById("article_top") != null) {
          document.getElementById("article_top").scrollIntoView();
        }

        this.getMarkdownContent({ url }).then(res => {
          this.blogContent = res.data.markdownData;
          this.article = res.data.article;
        });
        var menuIsShow = false;
        this.setMenuIsShow({ menuIsShow }).then(res => {});

        this.articleMenuList = this.$store.state.user.articleMenuList;

        console.log(this.articleUrl);
        console.log(this.articleMenuList);
        this.mapUrlDataList = {
          topUrl: {
            title: "",
            url: "",
            topShow: false
          },
          bottomUrl: {
            title: "",
            url: "",
            bottomShow: false
          }
        };
        this.mapUrlDataList = this.getMapDataList(
          this.articleUrl,
          this.articleMenuList
        );
      }
    },
    getMapDataList(url, urlList) {
      var mapDataList = {
        topUrl: {
          title: "",
          url: "",
          topShow: false
        },
        bottomUrl: {
          title: "",
          url: "",
          bottomShow: false
        }
      };
      for (var i = 0; i < urlList.length; i++) {
        for (var j = 0; j < urlList[i].children.length; j++) {
          if (urlList[i].children[j].url == url) {
            if (j !== 0) {
              mapDataList.topUrl.title = urlList[i].children[j - 1].title;
              mapDataList.topUrl.url = urlList[i].children[j - 1].url;
              mapDataList.topUrl.topShow = true;
            } else if (i != 0) {
              mapDataList.topUrl.title =
                urlList[i - 1].children[
                  urlList[i - 1].children.length - 1
                ].title;
              mapDataList.topUrl.url =
                urlList[i - 1].children[urlList[i - 1].children.length - 1].url;
              mapDataList.topUrl.topShow = true;
            }
            if (j !== urlList[i].children.length - 1) {
              mapDataList.bottomUrl.title = urlList[i].children[j + 1].title;
              mapDataList.bottomUrl.url = urlList[i].children[j + 1].url;
              mapDataList.bottomUrl.bottomShow = true;
            } else if (i != urlList.length - 1) {
              mapDataList.bottomUrl.title = urlList[i + 1].children[0].title;
              mapDataList.bottomUrl.url = urlList[i + 1].children[0].url;
              mapDataList.bottomUrl.bottomShow = true;
            }
          }
        }
      }

      return mapDataList;
    },

    aClick(articleUrl) {
      localSave("localUrl", articleUrl);
      this.setArticleUrl({ articleUrl }).then(res => {});
      let urlId = articleUrl.replace("md", "html");
      console.log(urlId);
      this.$router.push({
        name: "md",
        params: { id: urlId }
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

.menuButtonLeft {
  a {
    color: #ffffff !important;
  }
  margin: auto;
  text-align: left;
  display: block;
  float: left;
  margin-top: 10px;
  margin-left: 20px;
}

.menuButtonRight {
  a {
    color: #ffffff !important;
  }
  margin: auto;
  text-align: right;
  display: block;
  float: right;
  margin-top: 10px;
  margin-left: 20px;
}
</style>
