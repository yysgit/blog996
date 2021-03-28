<template>
  <div>
    <div style="min-width:1000px">
      <Card shadow>
        <!--添加主文章菜单-->
        <Button
          v-if="buttonVerifAuthention('sys:articleMenu:addArticleMenu')"
          type="primary"
          icon="md-add"
          @click="addArticleMenuButton"
          style="margin-bottom: 10px;"
        >添加主菜单</Button>

        <!--文章菜单表格-->
        <tree-table
          expand-key="title"
          :expand-type="false"
          :loading="loadingTable"
          :selectable="false"
          :columns="columns"
          :data="data"
          stripe
          border
          show-index
        >
          <template slot="likes" slot-scope="scope">
            <Button
              v-if="buttonVerifAuthention('sys:articleMenu:updateArticleMenu')"
              type="warning"
              size="small"
              @click="editArticleMenu(scope)"
              style="margin-right: 5px;"
            >编辑</Button>
            <Button
              v-if="buttonVerifAuthention('sys:articleMenu:deleteArticleMenu')"
              type="error"
              size="small"
              @click="deleteArticleMenu(scope)"
              style="margin-right: 5px;"
            >删除</Button>
            <Button
              v-if="buttonVerifAuthention('sys:articleMenu:addArticleMenu')"
              type="primary"
              size="small"
              @click="addArticleMenuChildButton(scope)"
              style="margin-right: 5px;"
            >添加子文章菜单</Button>
          </template>
        </tree-table>

        <!--添加主文章菜单弹出框-->
        <Modal v-model="modalArticleMenuAdd" title="添加主文章菜单" :mask-closable="false">
          <Form
            ref="formValidateArticleMenuAdd"
            :model="formValidateArticleMenuAdd"
            :rules="ruleValidateArticleMenuAdd"
            :label-width="120"
          >
            <FormItem label="文章菜单名" prop="title">
              <Input v-model.trim="formValidateArticleMenuAdd.title" placeholder="请输入文章菜单名"></Input>
            </FormItem>
            <FormItem label="文章菜单备注" prop="remark">
              <Input
                type="textarea"
                :autosize="{minRows: 2,maxRows: 5}"
                v-model="formValidateArticleMenuAdd.remark"
                placeholder="请输入文章菜单备注"
              ></Input>
            </FormItem>
            <FormItem label="排序" prop="sorting">
              <Input v-model="formValidateArticleMenuAdd.sorting" placeholder="排序(整数)"></Input>
            </FormItem>
          </Form>
          <div slot="footer">
            <Button type="text" size="large" @click="modalArticleMenuAdd=false">取消</Button>
            <Button
              type="primary"
              size="large"
              @click="addArticleMenuClick"
              :loading="loadingModel"
            >确定</Button>
          </div>
        </Modal>
        <!--添加子文章菜单弹出框-->
        <Modal v-model="modalArticleMenuAddChild" title="添加子文章菜单" :mask-closable="false">
          <Form
            ref="formValidateArticleMenuAddChild"
            :model="formValidateArticleMenuAddChild"
            :rules="ruleValidateArticleMenuAddChild"
            :label-width="120"
          >
            <FormItem label="子文章菜单名" prop="title">
              <Input v-model.trim="formValidateArticleMenuAddChild.title" placeholder="请输入子文章菜单名"></Input>
            </FormItem>
            <FormItem label="子文章菜单备注" prop="remark">
              <Input
                type="textarea"
                :autosize="{minRows: 2,maxRows: 5}"
                v-model="formValidateArticleMenuAddChild.remark"
                placeholder="请输入子文章菜单备注"
              ></Input>
            </FormItem>
            <FormItem label="排序" prop="sorting">
              <Input v-model="formValidateArticleMenuAddChild.sorting" placeholder="排序(整数)"></Input>
            </FormItem>
          </Form>
          <div slot="footer">
            <Button type="text" size="large" @click="modalArticleMenuAddChild=false">取消</Button>
            <Button
              type="primary"
              size="large"
              @click="addArticleMenuChildClick"
              :loading="loadingModel"
            >确定</Button>
          </div>
        </Modal>
        <!--编辑菜单弹出框-->
        <Modal v-model="modalArticleMenuEdit" title="编辑菜单" :mask-closable="false">
          <Form
            ref="formValidateArticleMenuEdit"
            :model="formValidateArticleMenuEdit"
            :rules="ruleValidateArticleMenuEdit"
            :label-width="120"
          >
            <FormItem label="子文章菜单名" prop="title">
              <Input v-model.trim="formValidateArticleMenuEdit.title" placeholder="请输入子文章菜单名"></Input>
            </FormItem>
            <FormItem label="子文章菜单备注" prop="remark">
              <Input
                type="textarea"
                :autosize="{minRows: 2,maxRows: 5}"
                v-model="formValidateArticleMenuEdit.remark"
                placeholder="请输入子文章菜单备注"
              ></Input>
            </FormItem>
            <FormItem label="排序" prop="sorting">
              <Input v-model="formValidateArticleMenuEdit.sorting" placeholder="排序(整数)"></Input>
            </FormItem>
          </Form>

          <div slot="footer">
            <Button type="text" size="large" @click="modalArticleMenuEdit=false">取消</Button>
            <Button
              type="primary"
              size="large"
              @click="editArticleMenuClick"
              :loading="loadingModel"
            >确定</Button>
          </div>
        </Modal>
      </Card>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import { permsVerifAuthention } from "@/libs/util";
export default {
  name: "tree_table_page",
  data() {
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
      //是否展开
      tableExpandKey: true,

      //对话框

      loadingTable: true, //表格加载转圈
      loadingModel: false, //表单提交按钮转圈
      //表格列
      columns: [
        {
          title: "文章菜单名",
          key: "title",
          width: "200"
        },
        {
          title: "备注",
          key: "remark",
          minWidth: "150px"
        },
        {
          title: "排序",
          key: "sorting"
        },
        {
          title: "操作",
          key: "likes",
          minWidth: "200px",
          type: "template",
          template: "likes"
        }
      ],
      //表格数据
      data: [],

      modalArticleMenuAdd: false,
      //添加表单
      formValidateArticleMenuAdd: {
        id: "",
        title: "",
        remark: "",
        sorting: "",
        parentId: 0
      },
      //表单验证
      ruleValidateArticleMenuAdd: {
        title: [
          { required: true, message: "请输入文章菜单名", trigger: "blur" },
          {
            type: "string",
            max: 100,
            message: "菜单名最长为100个字",
            trigger: "blur"
          }
        ],
        sorting: [{ validator: isInteger, trigger: "blur" }]
      },

      modalArticleMenuAddChild: false,
      //添加表单
      formValidateArticleMenuAddChild: {
        id: "",
        title: "",
        remark: "",
        sorting: "",
        parentId: 0
      },
      //表单验证
      ruleValidateArticleMenuAddChild: {
        title: [
          { required: true, message: "请输入文章菜单名", trigger: "blur" },
          {
            type: "string",
            max: 100,
            message: "菜单名最长为100个字",
            trigger: "blur"
          }
        ],
        sorting: [{ validator: isInteger, trigger: "blur" }]
      },

      modalArticleMenuEdit: false,
      //修改表单
      formValidateArticleMenuEdit: {
        id: "",
        title: "",
        remark: "",
        sorting: "",
        parentId: 0
      },
      
      //表单验证
      ruleValidateArticleMenuEdit: {
        title: [
          { required: true, message: "请输入文章菜单名", trigger: "blur" },
          {
            type: "string",
            max: 100,
            message: "菜单名最长为100个字",
            trigger: "blur"
          }
        ],
        sorting: [{ validator: isInteger, trigger: "blur" }]
      }
    };
  },
  created() {
    //初始化菜单列表
    this.queryList();
  },
  methods: {
    ...mapActions([
      "addArticleMenu",
      "getArticleMenuList",
      "deleteArticleMenuById",
      "editArticleMenuById"
    ]),
    buttonVerifAuthention(perms) {
      return permsVerifAuthention(perms, this.$store.state.user.authentionList);
    },

    //获取页面菜单列表
    queryList() {
      this.getArticleMenuList().then(res => {
        this.data = res.data;
        this.loadingTable = false;
      });
    },
    //判断是否有删除权限
    showDeleteButton(params) {
      return this.buttonVerifAuthention("sys:articleMenu:deleteArticleMenu");
    },

//点击添加子菜单按钮
    addArticleMenuButton(scope) {
      this.formValidateArticleMenuAdd = {
        id: "",
        title: "",
        remark: "",
        sorting: "",
        parentId: 0
      };
      this.modalArticleMenuAdd = true;
    },
    //添加主菜单提交
    addArticleMenuClick() {
      this.handleSubmitAdd("formValidateArticleMenuAdd");
    },
    //表单验证提交
    handleSubmitAdd(name) {
      this.$refs[name].validate(valid => {
        if (valid) {
          //表单提交
          //console.log(this.formValidate);
          let articleMenu = this.formValidateArticleMenuAdd;
          this.loadingModel = true; //启动提交按钮转圈
          this.addArticleMenu({ articleMenu }).then(res => {
            this.loadingModel = false; //关闭提交按钮转圈
            this.modalArticleMenuAdd = false; //关闭弹窗
            //情况表单数据
            this.formValidateArticleMenuAdd = {
              id: "",
              title: "",
              remark: "",
              sorting: "",
              parentId: 0
            };
            //刷新菜单页面
            this.queryList();
          });
        } else {
          this.$Message.error("验证失败!");
        }
      });
    },

    //点击添加子菜单按钮
    addArticleMenuChildButton(scope) {
      this.formValidateArticleMenuAddChild = {
        id: "",
        title: "",
        remark: "",
        sorting: "",
        parentId: 0
      };
      this.formValidateArticleMenuAddChild.parentId = scope.row.id;
      this.modalArticleMenuAddChild = true;
    },
    //添加主菜单提交
    addArticleMenuChildClick() {
      this.handleSubmitAddChild("formValidateArticleMenuAddChild");
    },
    //表单验证提交
    handleSubmitAddChild(name) {
      this.$refs[name].validate(valid => {
        if (valid) {
          //表单提交
          //console.log(this.formValidate);
          let articleMenu = this.formValidateArticleMenuAddChild;
          this.loadingModel = true; //启动提交按钮转圈
          this.addArticleMenu({ articleMenu }).then(res => {
            this.loadingModel = false; //关闭提交按钮转圈
            this.modalArticleMenuAddChild = false; //关闭弹窗
            //情况表单数据
            this.formValidateArticleMenuAddChild = {
              id: "",
              title: "",
              remark: "",
              sorting: "",
              parentId: 0
            };
            //刷新菜单页面
            this.queryList();
          });
        } else {
          this.$Message.error("验证失败!");
        }
      });
    },

    //删除文章菜单
    deleteArticleMenu(scope) {
      this.$Modal.confirm({
        title: "删除",
        content: "<p>你确认要删除此条信息吗!</p>",
        onOk: () => {
          let articleMenuId = scope.row.id;
          this.deleteArticleMenuById({ articleMenuId }).then(res => {
            this.$Message.info(res.msg);
            //刷新菜单页面
            this.queryList();
          });
        },
        onCancel: () => {
          this.$Message.info("取消删除!");
        }
      });
    },

    //编辑
    editArticleMenu(scope) {
      this.formValidateArticleMenuEdit.id = scope.row.id;
      this.formValidateArticleMenuEdit.title = scope.row.title;
      this.formValidateArticleMenuEdit.remark = scope.row.remark;
      this.formValidateArticleMenuEdit.sorting = scope.row.sorting + "";
      this.formValidateArticleMenuEdit.parentId = scope.row.parentId;
      this.modalArticleMenuEdit = true;
    },

    //编辑菜单提交
    editArticleMenuClick() {
      this.handleSubmitEdit("formValidateArticleMenuEdit");
    },
    //表单验证提交
    handleSubmitEdit(name) {
      this.$refs[name].validate(valid => {
        if (valid) {
          //表单提交
          let articleMenu = this.formValidateArticleMenuEdit;
          this.loadingModel = true; //启动提交按钮转圈
          this.editArticleMenuById({ articleMenu }).then(res => {
            this.loadingModel = false; //关闭提交按钮转圈
            this.modalArticleMenuEdit = false; //关闭弹窗
            //情况表单数据
            this.formValidateArticleMenuEdit = {
              id: "",
              title: "",
              remark: "",
              sorting: "",
              parentId: 0
            };
            //刷新菜单页面
            this.queryList();
          });
        } else {
          this.$Message.error("Fail!");
        }
      });
    }
  }
};
</script>

<style>
</style>
