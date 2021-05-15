<template>
  <div>
    <div style="min-width:1000px">
      <Card shadow>
        <!--添加基金类型-->
        <Button
          v-if="buttonVerifAuthention('sys:fundType:addFundType')"
          type="primary"
          icon="md-add"
          @click="addFundTypeButton"
          style="margin-bottom: 10px;"
        >添加基金类型</Button>
        <Row>
          <Col span="3" style="margin-right: 10px;">
            <Input v-model="titleSearch" placeholder="基金类型名称" clearable></Input>
          </Col>
          <Col span="3" style="margin-right: 10px;">
            <Select v-model="typeSearch" placeholder="类型" clearable>
              <Option value="1" key="1">基金类型</Option>
              <Option value="2" key="2">基金版块</Option>
              <Option value="3" key="3">基金公司</Option>
              <Option value="4" key="4">购买等级</Option>
            </Select>
          </Col>
          <Col span="2" style="margin-right: 10px;">
            <Button
              type="primary"
              icon="md-search"
              @click="searchQuery"
              style="margin-bottom: 10px;"
            >查询</Button>
          </Col>
        </Row>
        <!--表格-->
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

        <!--添加基金类型弹出框-->
        <Modal v-model="modalFundTypeAdd" title="添加基金类型" :mask-closable="false">
          <Form
            ref="formValidateFundTypeAdd"
            :model="formValidateFundTypeAdd"
            :rules="ruleValidateFundTypeAdd"
            :label-width="120"
          >
            <FormItem label="类型" prop="title">
              <Select v-model="formValidateFundTypeAdd.type" placeholder="请选择类型" clearable>
                <Option value="1" key="1">基金类型</Option>
                <Option value="2" key="2">基金版块</Option>
                <Option value="3" key="3">基金公司</Option>
                <Option value="4" key="4">购买等级</Option>
              </Select>
            </FormItem>
            <FormItem label="基金类型名称" prop="title">
              <Input v-model.trim="formValidateFundTypeAdd.title" placeholder="请输入基金类型名称"></Input>
            </FormItem>

            <FormItem label="基金类型备注" prop="remark">
              <Input
                type="textarea"
                :autosize="{minRows: 2,maxRows: 5}"
                v-model="formValidateFundTypeAdd.remark"
                placeholder="请输入基金类型备注"
              ></Input>
            </FormItem>
            <FormItem label="排序" prop="sorting">
              <Input v-model="formValidateFundTypeAdd.sorting" placeholder="排序(整数)"></Input>
            </FormItem>
          </Form>
          <div slot="footer">
            <Button type="text" size="large" @click="modalFundTypeAdd=false">取消</Button>
            <Button type="primary" size="large" @click="addFundTypeClick" :loading="loadingModel">确定</Button>
          </div>
        </Modal>

        <!--编辑菜单弹出框-->
        <Modal v-model="modalFundTypeEdit" title="编辑基金类型" :mask-closable="false">
          <Form
            ref="formValidateFundTypeEdit"
            :model="formValidateFundTypeEdit"
            :rules="ruleValidateFundTypeEdit"
            :label-width="120"
          >
            <FormItem label="类型" prop="title">
              <Select v-model="formValidateFundTypeEdit.type" placeholder="请选择类型" clearable>
                <Option value="1" key="1">基金类型</Option>
                <Option value="2" key="2">基金版块</Option>
                <Option value="3" key="3">基金公司</Option>
                <Option value="4" key="4">购买等级</Option>
              </Select>
            </FormItem>
            <FormItem label="基金类型名称" prop="title">
              <Input v-model.trim="formValidateFundTypeEdit.title" placeholder="请输入基金类型名称"></Input>
            </FormItem>
            <FormItem label="基金类型备注" prop="remark">
              <Input
                type="textarea"
                :autosize="{minRows: 2,maxRows: 5}"
                v-model="formValidateFundTypeEdit.remark"
                placeholder="请输入基金类型备注"
              ></Input>
            </FormItem>
            <FormItem label="排序" prop="sorting">
              <Input v-model="formValidateFundTypeEdit.sorting" placeholder="排序(整数)"></Input>
            </FormItem>
          </Form>

          <div slot="footer">
            <Button type="text" size="large" @click="modalFundTypeEdit=false">取消</Button>
            <Button
              type="primary"
              size="large"
              @click="editFundTypeClick"
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
      stylePage: {
        marginTop: "20px"
      },
      currentPage: 1,
      fetchNum: 10,
      totalPage: 0,

      titleSearch: "", //基金类型名称
      typeSearch: "", //类型
      //对话框

      loading: true, //表格加载转圈
      loadingModel: false, //表单提交按钮转圈

      modalFundTypeAdd: false,
      //添加表单
      formValidateFundTypeAdd: {
        id: "",
        title: "",
        type: "",
        remark: "",
        sorting: ""
      },
      //表单验证
      ruleValidateFundTypeAdd: {
        title: [
          { required: true, message: "请输入基金类型名称", trigger: "blur" },
          {
            type: "string",
            max: 100,
            message: "基金类型名称最长为100个字",
            trigger: "blur"
          }
        ],
        sorting: [{ validator: isInteger, trigger: "blur" }]
      },

      modalFundTypeEdit: false,
      //修改表单
      formValidateFundTypeEdit: {
        id: "",
        title: "",
        type: "",
        remark: "",
        sorting: ""
      },

      //表单验证
      ruleValidateFundTypeEdit: {
        title: [
          { required: true, message: "请输入基金类型名称", trigger: "blur" },
          {
            type: "string",
            max: 100,
            message: "基金类型名称最长为100个字",
            trigger: "blur"
          }
        ],
        sorting: [{ validator: isInteger, trigger: "blur" }]
      },
      //表格列
      columns: [
        {
          type: "index2",
          width: 70,
          title: "序号",
          align: "center",
          render: (h, params) => {
            return h(
              "span",
              params.index + (this.currentPage - 1) * this.fetchNum + 1
            );
          }
        },
        { title: "基金类型名称", align: "center", width: 180, key: "title" },
        {
          title: "类型",
          align: "center",
          width: 150,
          key: "type",
          render: (h, params) => {
            if (params.row.type == 1) {
              return h("div", { style: {} }, "基金类型");
            } else if (params.row.type == 2) {
              return h("div", { style: {} }, "基金版块");
            } else if (params.row.type == 3) {
              return h("div", { style: {} }, "基金公司");
            } else if (params.row.type == 4) {
              return h("div", { style: {} }, "购买等级");
            }
          }
        },

        { title: "备注", align: "center", width: 200, key: "remark" },
        { title: "排序", align: "center", width: 150, key: "sorting" },
        {
          title: "操作",
          key: "handle",
          align: "left",
          minWidth: 150,
          render: (h, params) => {
            return h("div", [
              (() => {
                if (this.buttonVerifAuthention("sys:fundType:updateFundType")) {
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
                          this.editFundTypeButton(params);
                        }
                      }
                    },
                    "编辑"
                  );
                }
              })(),
              (() => {
                if (this.buttonVerifAuthention("sys:fundType:deleteFundType")) {
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
                          this.deleteFundTypeButton(params);
                        }
                      }
                    },
                    "删除"
                  );
                }
              })()
            ]);
          }
        }
      ],

      //表格数据
      tableData: []
    };
  },
  created() {
    //初始化菜单列表
    this.queryList();
  },
  methods: {
    ...mapActions([
      "addFundType",
      "getFundTypeList",
      "deleteFundTypeById",
      "editFundTypeById"
    ]),
    buttonVerifAuthention(perms) {
      return permsVerifAuthention(perms, this.$store.state.user.authentionList);
    },
    //分页改变
    changePage(page) {
      this.currentPage = page;
      this.queryList();
    },
    //获取页面菜单列表
    queryList() {
      this.loading = true;
      let searchPream = {
        page: this.currentPage,
        limit: this.fetchNum,
        titleSearch: this.titleSearch, //基金类型名称
        typeSearch: this.typeSearch //类型
      };
      //发送请求
      this.getFundTypeList({ searchPream }).then(res => {
        this.tableData = res.data;
        this.totalPage = res.count;
        this.loading = false;
      });
    },

    //查询
    searchQuery() {
      this.currentPage = 1;
      this.queryList();
    },
    //点击添加子菜单按钮
    addFundTypeButton(scope) {
      this.formValidateFundTypeAdd = {
        id: "",
        title: "",
        type: "",
        remark: "",
        sorting: ""
      };
      this.modalFundTypeAdd = true;
    },
    //添加主菜单提交
    addFundTypeClick() {
      this.handleSubmitAdd("formValidateFundTypeAdd");
    },
    //表单验证提交
    handleSubmitAdd(name) {
      this.$refs[name].validate(valid => {
        if (valid) {
          //表单提交
          //console.log(this.formValidate);
          let fundType = this.formValidateFundTypeAdd;
          this.loadingModel = true; //启动提交按钮转圈
          this.addFundType({ fundType }).then(res => {
            this.loadingModel = false; //关闭提交按钮转圈
            this.modalFundTypeAdd = false; //关闭弹窗
            //情况表单数据
            this.formValidateFundTypeAdd = {
              id: "",
              title: "",
              type: "",
              remark: "",
              sorting: ""
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
    deleteFundTypeButton(scope) {
      this.$Modal.confirm({
        title: "删除",
        content: "<p>你确认要删除此条信息吗!</p>",
        onOk: () => {
          let fundTypeId = scope.row.id;
          this.deleteFundTypeById({ fundTypeId }).then(res => {
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
    editFundTypeButton(scope) {
      this.formValidateFundTypeEdit.id = scope.row.id;
      this.formValidateFundTypeEdit.title = scope.row.title;
      this.formValidateFundTypeEdit.type = scope.row.type  + "";
      this.formValidateFundTypeEdit.remark = scope.row.remark;
      this.formValidateFundTypeEdit.sorting = scope.row.sorting + "";
      this.modalFundTypeEdit = true;
    },

    //编辑菜单提交
    editFundTypeClick() {
      this.handleSubmitEdit("formValidateFundTypeEdit");
    },
    //表单验证提交
    handleSubmitEdit(name) {
      this.$refs[name].validate(valid => {
        if (valid) {
          //表单提交
          let fundType = this.formValidateFundTypeEdit;
          this.loadingModel = true; //启动提交按钮转圈
          this.editFundTypeById({ fundType }).then(res => {
            this.loadingModel = false; //关闭提交按钮转圈
            this.modalFundTypeEdit = false; //关闭弹窗
            //情况表单数据
            this.formValidateFundTypeEdit = {
              id: "",
              title: "",
              type: "",
              remark: "",
              sorting: ""
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
