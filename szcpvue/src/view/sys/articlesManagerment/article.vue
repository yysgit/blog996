<template>
  <div>
    <div style="min-width:1000px">
      <Card shadow>
        <div class="split-pane-page-wrapper">
          <split-pane v-model="offset" @on-moving="handleMoving">
            <div slot="left" class="pane left-pane">
              <Tree :loading="loadingTable" :data="data1" @on-select-change="selectChange"></Tree>
            </div>
            <div slot="right" class="pane right-pane">
              <!--添加用户-->
              <Button
                v-if="buttonVerifAuthention('sys:article:addArticle')"
                type="primary"
                icon="md-add"
                @click="addArticleClick"
                style="margin-bottom: 10px;margin-right: 10px;"
              >添加文章</Button>

              <!--菜单表格-->
              <Table
                ref="tables"
                width="1200px"
                stripe
                border
                :loading="loading"
                :data="tableData"
                :columns="columns"
              ></Table>
              <Page :total="totalPage" show-total :styles="stylePage" @on-change="changePage" />

              <!--添加用户-->
              <Modal v-model="modalArticleAdd" title="添加用户" :mask-closable="false">
                <Form
                  ref="formValidateArticleAdd"
                  :model="formValidateArticleAdd"
                  :rules="ruleValidateArticleAdd"
                  :label-width="80"
                >
                  <FormItem label="标题" prop="title">
                    <Input v-model="formValidateArticleAdd.title" placeholder="标题" />
                  </FormItem>
                  <FormItem label="排序" prop="sorting">
                    <Input v-model="formValidateArticleAdd.sorting" placeholder="排序" />
                  </FormItem>
                  <FormItem label="备" prop="remark">
                    <Input v-model="formValidateArticleAdd.remark" placeholder="备注" />
                  </FormItem>
                </Form>
                <div slot="footer">
                  <Button type="text" size="large" @click="modalArticleAdd=false">取消</Button>
                  <Button
                    type="primary"
                    size="large"
                    @click="addArticleButton"
                    :loading="loadingModel"
                  >确定</Button>
                </div>
              </Modal>

              <!--编辑用户-->
              <Modal v-model="modalArticleEdit" title="编辑用户" :mask-closable="false">
                <Form
                  ref="formValidateArticleEdit"
                  :model="formValidateArticleEdit"
                  :rules="ruleValidateArticleEdit"
                  :label-width="80"
                >
                  <FormItem label="标题" prop="title">
                    <Input v-model="formValidateArticleEdit.title" placeholder="标题" />
                  </FormItem>
                  <FormItem label="排序" prop="sorting">
                    <Input v-model="formValidateArticleEdit.sorting" placeholder="排序" />
                  </FormItem>
                  <FormItem label="备" prop="remark">
                    <Input v-model="formValidateArticleEdit.remark" placeholder="备注" />
                  </FormItem>
                </Form>
                <div slot="footer">
                  <Button type="text" size="large" @click="modalArticleEdit=false">取消</Button>
                  <Button
                    type="primary"
                    size="large"
                    @click="editArticleButton"
                    :loading="loadingModel"
                  >确定</Button>
                </div>
              </Modal>

              <!--编辑文章弹出框-->
              <Modal
                v-model="modalArticleContentEdit"
                width="100%"
                height="100%"
                title="编辑文章"
                :mask-closable="false"
              >
                <Form
                  ref="formValidateArticleContentEdit"
                  :model="formValidateArticleContentEdit"
                  :rules="ruleValidateArticleContent"
                  :label-width="80"
                >
                  <div>
                    <mavon-editor
                      v-model="formValidateArticleContentEdit.articleContent"
                      ref="md"
                      @change="articleChange"
                      style="min-height: 600px"
                      @imgAdd="handleEditorImgAdd"
                      @imgDel="handleEditorImgDel"
                    />
                  </div>
                </Form>
                <div slot="footer">
                  <Button type="text" size="large" @click="modalArticleContentEdit=false">取消</Button>
                  <Button
                    type="primary"
                    size="large"
                    @click="editArticleContentButton"
                    :loading="loadingModel"
                  >确定</Button>
                </div>
              </Modal>
            </div>
          </split-pane>
        </div>
      </Card>
    </div>
  </div>
</template>

<script>
import SplitPane from "_c/split-pane";
import { mapActions } from "vuex";
import { permsVerifAuthention } from "@/libs/util";
import { mavonEditor } from "mavon-editor";
import { setToken, getToken } from "@/libs/util";
import "mavon-editor/dist/css/index.css";
import config from "@/config";

const baseUrl =
  process.env.NODE_ENV === "development"
    ? config.baseUrl.dev
    : config.baseUrl.pro;

export default {
  name: "split_pane_page",
  components: {
    SplitPane,
    mavonEditor
  },
  data() {
    let self = this;

    const isInteger = (rule, value, callback) => {
      //console.log(value);
      if (value == null || value == "") {
        callback();
      } else {
        if (!Number(value)) {
          callback(new Error("请输入正整数"));
        } else {
          const re = /^[0-9]*[1-9][0-9]*$/;
          const rsCheck = re.test(value);
          if (!rsCheck) {
            callback(new Error("请输入正整数"));
          } else {
            callback();
          }
        }
      }
    };

    return {
      offset: 0.2,
      offsetVertical: "250px",
      data1: [],
      loadingTable: true,

      stylePage: {
        marginTop: "20px"
      },
      currentPage: 1,
      fetchNum: 10,
      totalPage: 0,
      articleMenuId: "",

      //文章内容
      token: getToken(),
      imgFile: [],
      modalArticleContentEdit: false,

      formValidateArticleContentEdit: {
        articleContent: "",
        url: ""
      },
      ruleValidateArticleContent: {
        articleContent: [
          { required: true, message: "请输入标题", trigger: "blur" },
          {
            type: "string",
            max: 100000,
            message: "标题最长为100000个字",
            trigger: "blur"
          }
        ]
      },

      modalArticleAdd: false,
      modalArticleEdit: false,
      loadingModel: false,
      loading: false,

      //添加表单
      formValidateArticleAdd: {
        title: "",
        sorting: "",
        url: "",
        remark: "",
        articleMenuId: ""
      },

      //编辑表单验证
      ruleValidateArticleAdd: {
        title: [
          { required: true, message: "请输入标题", trigger: "blur" },
          {
            type: "string",
            max: 100,
            message: "标题最长为100个字",
            trigger: "blur"
          }
        ],
        sorting: [
          { validator: isInteger, trigger: "blur" },
          { required: true, message: "请输入标题", trigger: "blur" }
        ]
      },
      //编辑表单验证
      formValidateArticleEdit: {
        id: "",
        title: "",
        sorting: "",
        url: "",
        remark: "",
        articleMenuId: ""
      },

      //编辑表单验证
      ruleValidateArticleEdit: {
        title: [
          { required: true, message: "请输入标题", trigger: "blur" },
          {
            type: "string",
            max: 100,
            message: "标题最长为100个字",
            trigger: "blur"
          }
        ],
        sorting: [
          { validator: isInteger, trigger: "blur" },
          { required: true, message: "请输入标题", trigger: "blur" }
        ]
      },

      columns: [
        {
          type: "index2",
          width: 60,
          title: "序号",
          align: "center",
          render: (h, params) => {
            return h(
              "span",
              params.index + (this.currentPage - 1) * this.fetchNum + 1
            );
          }
        },
        { title: "标题", width: 150, align: "center", key: "title" },
        { title: "路径", width: 200, align: "center", key: "url" },
        { title: "备注", width: 120, align: "center", key: "remark" },
        { title: "排序", width: 150, align: "center", key: "sorting" },
        {
          title: "操作",
          key: "handle",
          align: "left",
          width: 220,
          render: (h, params) => {
            return h("div", [
              (() => {
                if (this.buttonVerifAuthention("sys:article:updateArticle")) {
                  return h(
                    "Button",
                    {
                      props: {
                        type: "primary",
                        size: "small"
                      },
                      style: {
                        marginRight: "5px"
                      },
                      on: {
                        click: () => {
                          this.editArticleClick(params);
                        }
                      }
                    },
                    "编辑"
                  );
                }
              })(),

              (() => {
                if (this.buttonVerifAuthention("sys:article:deleteArticle")) {
                  return h(
                    "Button",
                    {
                      props: {
                        type: "error",
                        size: "small"
                      },
                      style: {
                        marginRight: "5px"
                      },
                      on: {
                        click: () => {
                          this.deleteArticleClick(params);
                        }
                      }
                    },
                    "删除"
                  );
                }
              })(),
              (() => {
                if (
                  this.buttonVerifAuthention("sys:article:updateArticleContent")
                ) {
                  return h(
                    "Button",
                    {
                      props: {
                        type: "primary",
                        size: "small"
                      },
                      style: {
                        marginRight: "5px"
                      },
                      on: {
                        click: () => {
                          this.editArticleContentClick(params);
                        }
                      }
                    },
                    "编辑内容"
                  );
                }
              })()
            ]);
          }
        }
      ],
      tableData: []
    };
  },
  created() {
    //文章树列表
    this.queryArticleMenuList();
  },
  methods: {
    ...mapActions([
      "getArticleMenuToArticleList",
      "getMarkdownContent",
      "getArticleList",
      "editArticleById",
      "editArticleContent",
      "addArticle",
      "deleteArticleById"
    ]),
    buttonVerifAuthention(perms) {
      return permsVerifAuthention(perms, this.$store.state.user.authentionList);
    },

    handleMoving(e) {
      //console.log(e.atMin, e.atMax)
    },

    articleChange(value, render) {
      // render 为 markdown 解析后的结果[html]
      this.html = render;
    },

    //获取页面菜单列表
    queryArticleMenuList() {
      this.getArticleMenuToArticleList().then(res => {
        this.data1 = res.data;
        this.loadingTable = false;
      });
    },
    //分页改变
    changePage(page) {
      this.currentPage = page;
      this.queryList();
    },
    //机构树被选中
    selectChange(selectedList) {
      if (selectedList.length > 0) {
        this.articleMenuId = selectedList[0].id;
        this.queryList();
      }
    },
    //获取页面菜单列表
    queryList() {
      this.loading = true;
      let searchPream = {
        page: this.currentPage,
        limit: this.fetchNum,
        articleMenuId: this.articleMenuId
      };
      //发送请求
      this.getArticleList({ searchPream }).then(res => {
        this.tableData = res.data;
        this.totalPage = res.count;
        this.loading = false;
      });
    },

    editArticleContentClick(params) {
      let url = params.row.url;
      this.getMarkdownContent({ url }).then(res => {
        this.formValidateArticleContentEdit.articleContent = res.data;
      });
      this.formValidateArticleContentEdit.url = url;
      this.modalArticleContentEdit = true;
    },

    editArticleContentButton() {
      console.log(this.formValidateArticleContentEdit.articleContent.length);

      this.formValidateArticleContentEdit.articleContent = this.formValidateArticleContentEdit.articleContent
        .split("[")
        .join("#1#")
        .split("]")
        .join("#2#");

      console.log(this.formValidateArticleContentEdit.articleContent.length);
      let articleContentALL = this.formValidateArticleContentEdit
        .articleContent;
      console.log(articleContentALL.length);

      var contentList = [];
      var n = 5000;
      for (var i = 0, l = articleContentALL.length; i < l / n; i++) {
        var a = articleContentALL.slice(n * i, n * (i + 1));
        contentList.push(a);
      }

      console.log(contentList);
      this.loadingModel = true;
      this.addContent(contentList, 0, this.formValidateArticleContentEdit.url);
      this.loadingModel = false;
      this.modalArticleContentEdit = false; //关闭弹窗

      // if (articleContentALL.length > 1000) {
      //   var contentList = [];
      //   var n = 1000;
      //   for (var i = 0, l = articleContentALL.length; i < l / n; i++) {
      //     var a = articleContentALL.slice(n * i, n * (i + 1));
      //     contentList.push(a);
      //   }

      //   console.log(contentList);
      //   this.loadingModel = true;
      //   this.addContent(
      //     contentList,
      //     0,
      //     this.formValidateArticleContentEdit.url
      //   );
      //   this.loadingModel = false;
      //   this.modalArticleContentEdit = false; //关闭弹窗
      // } else {
      //   this.loadingModel = true;

      //   let articleContent = {
      //     articleContent: articleContentALL,
      //     num: 0,
      //     url: this.formValidateArticleContentEdit.url
      //   };

      //   this.editArticleContent({ articleContent }).then(res => {
      //     //console.log("请求:" + res);
      //     this.loadingModel = false;
      //     this.modalArticleContentEdit = false; //关闭弹窗
      //     //情况表单数据
      //     //表单
      //     this.formValidateArticleContentEdit = {
      //       articleContent: "",
      //       url: ""
      //     };
      //     //刷新页面
      //     this.currentPage = 1;
      //     this.queryList();
      //   });
      //   //console.log("请求:" + res);
      //   this.loadingModel = false;
      // }
    },

    addContent(contentList, i, url) {
      console.log("第" + i + "次请求----");
      var sss = window.setTimeout(() => {
        let instance = this.$axios.create({
          withCredentials: true,
          headers: {
            Authorization: this.token,
            "Content-Type": "multipart/form-data"
          }
        });

        let formdata = new FormData();
        formdata.append(
          "articleContent",
          this.formValidateArticleContentEdit.articleContent
        );
        formdata.append("num", 0);
        formdata.append("url", this.formValidateArticleContentEdit.url);

        let articleContent = {
          articleContent: this.formValidateArticleContentEdit.articleContent,
          num: 0,
          url: this.formValidateArticleContentEdit.url
        };
        instance
          .post(baseUrl + "/sys/article/updateArticleContent", formdata)
          .then(res => {
            if (i < contentList.length - 1) {
              //执行任务
              this.addContent(contentList, i + 1, url);
            }
          });
      }, 500);

      // console.log("第" + i + "次请求----");
      // var sss = window.setTimeout(() => {
      //   this.editArticleContent({ articleContent }).then(res => {
      //     if (i < contentList.length - 1) {
      //       //执行任务
      //       this.addContent(contentList, i + 1, url);
      //     }
      //   });
      // }, 500);
      // clearInterval(sss); //取消执行
    },

    //添加按钮点击
    addArticleClick() {
      if (this.articleMenuId == null || this.articleMenuId == "") {
        this.$Message.info("请选择文章菜单!");
        return;
      }
      this.modalArticleAdd = true;
    },
    //添加用户表单提交
    addArticleButton() {
      this.formValidateArticleAdd.articleMenuId = this.articleMenuId;
      this.handleSubmit("formValidateArticleAdd");
    },
    //表单验证提交
    handleSubmit(name) {
      //console.log(this.$refs);
      this.$refs[name].validate(valid => {
        console.log(valid);
        if (valid) {
          //表单提交
          //console.log(this.formValidate);
          let article = this.formValidateArticleAdd;
          this.loadingModel = true;
          this.addArticle({ article }).then(res => {
            //console.log("请求:" + res);
            this.loadingModel = false;
            this.modalArticleAdd = false; //关闭弹窗
            //情况表单数据
            //表单
            this.formValidateArticleAdd = {
              title: "",
              sorting: "",
              url: "",
              remark: "",
              articleMenuId: ""
            };
            //刷新页面
            this.currentPage = 1;
            this.queryList();
          });
        } else {
          this.$Message.error("请按要求填写表单!");
        }
      });
    },

    //删除
    deleteArticleClick(params) {
      this.$Modal.confirm({
        title: "删除",
        content: "<p>你确认要删除此条信息吗!</p>",
        onOk: () => {
          let articleId = params.row.id;
          this.deleteArticleById({ articleId }).then(res => {
            this.$Message.info("删除成功!");
            //刷新页面
            this.queryList();
          });
        },
        onCancel: () => {
          this.$Message.info("取消删除!");
        }
      });
    },

    //编辑点击
    editArticleClick(params) {
      this.formValidateArticleEdit.id = params.row.id;
      this.formValidateArticleEdit.title = params.row.title;
      this.formValidateArticleEdit.sorting = params.row.sorting + "";
      this.formValidateArticleEdit.url = params.row.url;
      this.formValidateArticleEdit.remark = params.row.remark;
      this.formValidateArticleEdit.articleMenuId =
        params.row.articleMenuId + "";
      this.modalArticleEdit = true;
    },
    //编辑提交
    editArticleButton() {
      this.handleSubmitEdit("formValidateArticleEdit");
    },
    //表单验证提交
    handleSubmitEdit(name) {
      this.$refs[name].validate(valid => {
        if (valid) {
          //表单提交
          //console.log(this.formValidate);
          let article = this.formValidateArticleEdit;
          this.loadingModel = true; //启动提交按钮转圈
          this.editArticleById({ article }).then(res => {
            this.loadingModel = false; //关闭提交按钮转圈
            this.modalArticleEdit = false; //关闭弹窗
            //情况表单数据
            this.formValidateArticleEdit = {
              id: "",
              title: "",
              sorting: "",
              url: "",
              remark: "",
              articleMenuId: ""
            };
            //刷新菜单页面
            this.queryList();
          });
        } else {
          this.$Message.error("验证错误!");
        }
      });
    },

    //添加图片
    handleEditorImgAdd(pos, $file) {
      let formdata = new FormData();
      formdata.append("file", $file);
      this.imgFile[pos] = $file;
      let instance = this.$axios.create({
        withCredentials: true,
        headers: {
          Authorization: this.token,
          "Content-Type": "multipart/form-data"
        }
      });
      instance.post(baseUrl + "/sys/image/uploadImage", formdata).then(res => {
        if (res.data.code === 200) {
          this.$Message.success("上传成功");
          let url = baseUrl + res.data.msg;
          let name = $file.name;
          if (name.includes("-")) {
            name = name.replace(/-/g, "");
          }
          let content = this.formValidateArticleContentEdit.articleContent;
          // 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)  这里是必须要有的
          if (content.includes(name)) {
            let oStr = `(${pos})`;
            let nStr = `(${url})`;
            let index = content.indexOf(oStr);
            let str = content.replace(oStr, "");
            let insertStr = (soure, start, newStr) => {
              return soure.slice(0, start) + newStr + soure.slice(start);
            };
            this.formValidateArticleContentEdit.articleContent = insertStr(
              str,
              index,
              nStr
            );
          }
        } else {
          this.$Message.error(res.data.msg);
        }
      });
    },
    handleEditorImgDel(pos) {
      delete this.imgFile[pos];
    }
  }
};
</script>

<style lang="less">
.ivu-modal-body .ivu-form-item .ivu-form-item-label {
  width: 110px !important;
}

.ivu-modal-body .ivu-form-item .ivu-form-item-content {
  margin-left: 110px !important;
}

.ivu-tree-title-selected,
.ivu-tree-title-selected:hover {
  background-color: #699fd8;
  color: white;
}

.center-middle {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}

.split-pane-page-wrapper {
  height: 600px;

  .pane {
    width: 100%;
    height: 100%;

    &.left-pane {
      background: white !important;
    }

    &.right-pane {
      background: white !important;
      margin-left: 22px;
    }

    &.top-pane {
      background: white !important;
    }

    &.bottom-pane {
      background: white !important;
    }
  }

  .custom-trigger {
    width: 20px;
    height: 20px;
    border-radius: 50%;
    background: #000000;
    position: absolute;
    .center-middle;
    box-shadow: 0 0 6px 0 rgba(28, 36, 56, 0.4);

    i.trigger-icon {
      .center-middle;
    }
  }
}
</style>
