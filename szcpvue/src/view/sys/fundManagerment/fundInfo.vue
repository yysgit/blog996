<template>
  <div>
    <div style="min-width:1000px">
      <Card shadow>
        <!--添加基金类型-->
        <Button
          v-if="buttonVerifAuthention('sys:fundInfo:addFundInfo')"
          type="primary"
          icon="md-add"
          @click="addFundInfoButton"
          style="margin-bottom: 10px;"
        >添加基金</Button>

        <!-- 查询 -->
        <Row>
          <Col span="2" style="margin-right: 10px;">
            <Select v-model="fundTypeSectionIdSearch" placeholder="基金版块" clearable>
              <Option
                v-for="item in fundTypeSectionData"
                :value="item.id"
                :key="item.id"
              >{{ item.title }}</Option>
            </Select>
          </Col>

          <Col span="2" style="margin-right: 10px;">
            <Select v-model="fundTypeTypeIdSearch" placeholder="基金类型" clearable>
              <Option
                v-for="item in fundTypeTypeData"
                :value="item.id"
                :key="item.id"
              >{{ item.title }}</Option>
            </Select>
          </Col>
          <Col span="3" style="margin-right: 10px;">
            <Select v-model="fundTypeCompanyIdSearch" placeholder="基金公司" clearable>
              <Option
                v-for="item in fundTypeCompanyData"
                :value="item.id"
                :key="item.id"
              >{{ item.title }}</Option>
            </Select>
          </Col>
          <Col span="2" style="margin-right: 10px;">
            <Select v-model="fundTypePurchaseIdSearch" placeholder="购买等级" clearable>
              <Option
                v-for="item in fundTypePurchaseData"
                :value="item.id"
                :key="item.id"
              >{{ item.title }}</Option>
            </Select>
          </Col>

          <Col span="3" style="margin-right: 10px;">
            <Input v-model="fundNameSearch" placeholder="基金名称" clearable></Input>
          </Col>
          <Col span="3" style="margin-right: 10px;">
            <Input v-model="fundCodeSearch" placeholder="基金代码" clearable></Input>
          </Col>

          <Col span="2" style="margin-right: 10px;">
            <Select v-model="sortFieldSearch" placeholder="排序字段" clearable>
              <Option
                v-for="item in sortFieldData"
                :value="item.value"
                :key="item.value"
              >{{ item.label }}</Option>
            </Select>
          </Col>
          <Col span="2" style="margin-right: 10px;">
            <Select v-model="sortSearch" placeholder="正序倒序" clearable>
              <Option
                v-for="item in sortData"
                :value="item.value"
                :key="item.value"
              >{{ item.label }}</Option>
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
        <Modal v-model="modalFundInfoAdd" title="添加基金" :mask-closable="false">
          <Form
            ref="formValidateFundInfoAdd"
            :model="formValidateFundInfoAdd"
            :rules="ruleValidateFundInfoAdd"
            :label-width="120"
          >
            <FormItem label="基金版块" prop="fundTypeSectionId">
              <Select
                v-model="formValidateFundInfoAdd.fundTypeSectionId"
                placeholder="请选择基金版块"
                clearable
              >
                <Option
                  v-for="item in fundTypeSectionData"
                  :value="item.id"
                  :key="item.id"
                >{{ item.title }}</Option>
              </Select>
            </FormItem>

            <FormItem label="基金类型" prop="fundTypeTypeId">
              <Select
                v-model="formValidateFundInfoAdd.fundTypeTypeId"
                placeholder="请选择基金类型"
                clearable
              >
                <Option
                  v-for="item in fundTypeTypeData"
                  :value="item.id"
                  :key="item.id"
                >{{ item.title }}</Option>
              </Select>
            </FormItem>

            <FormItem label="基金公司" prop="fundTypeCompanyId">
              <Select
                v-model="formValidateFundInfoAdd.fundTypeCompanyId"
                placeholder="请选择基金公司"
                clearable
              >
                <Option
                  v-for="item in fundTypeCompanyData"
                  :value="item.id"
                  :key="item.id"
                >{{ item.title }}</Option>
              </Select>
            </FormItem>

            <FormItem label="购买等级" prop="fundTypePurchaseId">
              <Select
                v-model="formValidateFundInfoAdd.fundTypePurchaseId"
                placeholder="请选择购买等级"
                clearable
              >
                <Option
                  v-for="item in fundTypePurchaseData"
                  :value="item.id"
                  :key="item.id"
                >{{ item.title }}</Option>
              </Select>
            </FormItem>

            <FormItem label="基金名称" prop="fundName">
              <Input v-model.trim="formValidateFundInfoAdd.fundName" placeholder="请输入基金名称"></Input>
            </FormItem>
            <FormItem label="基金编号" prop="fundCode">
              <Input v-model.trim="formValidateFundInfoAdd.fundCode" placeholder="请输入基金编号"></Input>
            </FormItem>

            <FormItem label="基金规模(亿)" prop="fundScale">
              <Input v-model.trim="formValidateFundInfoAdd.fundScale" placeholder="请输入基金规模"></Input>
            </FormItem>
            <FormItem label="本金" prop="principal">
              <Input v-model.trim="formValidateFundInfoAdd.principal" placeholder="请输入本金"></Input>
            </FormItem>
            <FormItem label="盈亏" prop="profitLoss">
              <Input v-model.trim="formValidateFundInfoAdd.profitLoss" placeholder="请输入本金"></Input>
            </FormItem>
            <FormItem label="持有收益率" prop="holdingYield">
              <Input v-model.trim="formValidateFundInfoAdd.holdingYield" placeholder="请输入持有收益率"></Input>
            </FormItem>

            <FormItem label="推荐等级" prop="recommendationLevel">
              <Input v-model="formValidateFundInfoAdd.recommendationLevel" placeholder="推荐等级(整数)"></Input>
            </FormItem>
            <FormItem label="基金备注" prop="remark">
              <Input
                type="textarea"
                :autosize="{minRows: 2,maxRows: 5}"
                v-model="formValidateFundInfoAdd.remark"
                placeholder="请输入基金备注"
              ></Input>
            </FormItem>
          </Form>
          <div slot="footer">
            <Button type="text" size="large" @click="modalFundInfoAdd=false">取消</Button>
            <Button type="primary" size="large" @click="addFundInfoClick" :loading="loadingModel">确定</Button>
          </div>
        </Modal>

        <!--编辑菜单弹出框-->
        <Modal v-model="modalFundInfoEdit" title="编辑基金类型" :mask-closable="false">
          <Form
            ref="formValidateFundInfoEdit"
            :model="formValidateFundInfoEdit"
            :rules="ruleValidateFundInfoEdit"
            :label-width="120"
          >
            <FormItem label="基金版块" prop="fundTypeSectionId">
              <Select
                v-model="formValidateFundInfoEdit.fundTypeSectionId"
                placeholder="请选择基金版块"
                clearable
              >
                <Option
                  v-for="item in fundTypeSectionData"
                  :value="item.id"
                  :key="item.id"
                >{{ item.title }}</Option>
              </Select>
            </FormItem>

            <FormItem label="基金类型" prop="fundTypeTypeId">
              <Select
                v-model="formValidateFundInfoEdit.fundTypeTypeId"
                placeholder="请选择基金类型"
                clearable
              >
                <Option
                  v-for="item in fundTypeTypeData"
                  :value="item.id"
                  :key="item.id"
                >{{ item.title }}</Option>
              </Select>
            </FormItem>

            <FormItem label="基金公司" prop="fundTypeCompanyId">
              <Select
                v-model="formValidateFundInfoEdit.fundTypeCompanyId"
                placeholder="请选择基金公司"
                clearable
              >
                <Option
                  v-for="item in fundTypeCompanyData"
                  :value="item.id"
                  :key="item.id"
                >{{ item.title }}</Option>
              </Select>
            </FormItem>

            <FormItem label="购买等级" prop="fundTypePurchaseId">
              <Select
                v-model="formValidateFundInfoEdit.fundTypePurchaseId"
                placeholder="请选择购买等级"
                clearable
              >
                <Option
                  v-for="item in fundTypePurchaseData"
                  :value="item.id"
                  :key="item.id"
                >{{ item.title }}</Option>
              </Select>
            </FormItem>

            <FormItem label="基金名称" prop="fundName">
              <Input v-model.trim="formValidateFundInfoEdit.fundName" placeholder="请输入基金名称"></Input>
            </FormItem>
            <FormItem label="基金编号" prop="fundCode">
              <Input v-model.trim="formValidateFundInfoEdit.fundCode" placeholder="请输入基金编号"></Input>
            </FormItem>

            <FormItem label="基金规模(亿)" prop="fundScale">
              <Input v-model.trim="formValidateFundInfoEdit.fundScale" placeholder="请输入基金规模"></Input>
            </FormItem>
            <FormItem label="本金" prop="principal">
              <Input v-model.trim="formValidateFundInfoEdit.principal" placeholder="请输入本金"></Input>
            </FormItem>
            <FormItem label="盈亏" prop="profitLoss">
              <Input v-model.trim="formValidateFundInfoEdit.profitLoss" placeholder="请输入本金"></Input>
            </FormItem>
            <FormItem label="持有收益率" prop="holdingYield">
              <Input v-model.trim="formValidateFundInfoEdit.holdingYield" placeholder="请输入持有收益率"></Input>
            </FormItem>

            <FormItem label="推荐等级" prop="recommendationLevel">
              <Input v-model="formValidateFundInfoEdit.recommendationLevel" placeholder="推荐等级(整数)"></Input>
            </FormItem>
            <FormItem label="基金备注" prop="remark">
              <Input
                type="textarea"
                :autosize="{minRows: 2,maxRows: 5}"
                v-model="formValidateFundInfoEdit.remark"
                placeholder="请输入基金备注"
              ></Input>
            </FormItem>
          </Form>

          <div slot="footer">
            <Button type="text" size="large" @click="modalFundInfoEdit=false">取消</Button>
            <Button
              type="primary"
              size="large"
              @click="editFundInfoClick"
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

      fundTypeCompanyData: [], //查询公司
      fundTypeSectionData: [], //查询版块
      fundTypeTypeData: [], //查询类型
      fundTypePurchaseData: [], //查询购买等级

      sortData: [
        {
          value: "ASC",
          label: "正序"
        },
        {
          value: "DESC",
          label: "倒序"
        }
      ],

      sortFieldData: [
        {
          value: "principal",
          label: "本金"
        },
        {
          value: "holding_yield",
          label: "收益率"
        },
        {
          value: "profit_loss",
          label: "盈亏"
        },
        {
          value: "recommendation_level",
          label: "推荐等级"
        }
      ],
      sortFieldSearch: "", //字段排序
      sortSearch: "", //排序
      fundTypeCompanyIdSearch: "", //查询公司
      fundTypeSectionIdSearch: "", //查询版块
      fundTypeTypeIdSearch: "", //查询类型
      fundTypePurchaseIdSearch: "", //查询购买等级
      fundNameSearch: "", //基金名称
      fundCodeSearch: "", //基金代码
      principalOrderBySearch: "", //基金本金
      profitLossOrderBySearch: "", //基金盈亏排序
      recommendationLevelOrderBySearch: "", //基金购买等级排序
      holdingYieldOrderBySearch: "", //基金持有收益率

      //对话框

      loading: true, //表格加载转圈
      loadingModel: false, //表单提交按钮转圈

      modalFundInfoAdd: false,

      //添加表单
      formValidateFundInfoAdd: {
        id: "",
        fundTypeCompanyId: "",
        fundTypeSectionId: "",
        fundTypeTypeId: "",
        fundTypePurchaseId: "",
        fundName: "",
        fundCode: "",
        fundScale: "",
        holdingYield: "",
        principal: "",
        profitLoss: "",
        recommendationLevel: "",
        remark: ""
      },
      //表单验证
      ruleValidateFundInfoAdd: {
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

      modalFundInfoEdit: false,
      //修改表单
      formValidateFundInfoEdit: {
        id: "",
        fundTypeCompanyId: "",
        fundTypeSectionId: "",
        fundTypeTypeId: "",
        fundTypePurchaseId: "",
        fundName: "",
        fundCode: "",
        fundScale: "",
        holdingYield: "",
        principal: "",
        profitLoss: "",
        recommendationLevel: "",
        remark: ""
      },

      //表单验证
      ruleValidateFundInfoEdit: {
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
        {
          title: "基金版块",
          align: "center",
          width: 100,
          key: "fundTypeSectionId",
          render: (h, params) => {
            for (let i = 0; i < this.fundTypeSectionData.length; i++) {
              if (
                params.row.fundTypeSectionId == this.fundTypeSectionData[i].id
              ) {
                return h(
                  "div",
                  { style: {} },
                  this.fundTypeSectionData[i].title
                );
              }
            }
          }
        },
        { title: "名称", align: "center", width: 180, key: "fundName" },
        { title: "代码", align: "center", width: 100, key: "fundCode" },

        {
          title: "基金规模(亿)",
          align: "center",
          width: 120,
          key: "fundScale"
        },
        { title: "本金", align: "center", width: 110, key: "principal" },
        {
          title: "盈亏",
          align: "center",
          width: 110,
          key: "profitLoss",
          render: (h, params) => {
            if (params.row.profitLoss > 0) {
              return h(
                "div",
                { style: { color: "red" } },
                params.row.profitLoss
              );
            } else {
              return h(
                "div",
                { style: { color: "green" } },
                params.row.profitLoss
              );
            }
          }
        },
        {
          title: "持有收益率",
          align: "center",
          width: 110,
          key: "holdingYield",
          render: (h, params) => {
            if (params.row.holdingYield > 0) {
              return h(
                "div",
                { style: { color: "red" } },
                params.row.holdingYield * 100 + "%"
              );
            } else if (params.row.holdingYield < 0) {
              return h(
                "div",
                { style: { color: "green" } },
                params.row.holdingYield * 100 + "%"
              );
            }
          }
        },
        {
          title: "推荐等级",
          align: "center",
          width: 100,
          key: "recommendationLevel"
        },

        {
          title: "购买等级",
          align: "center",
          width: 110,
          key: "fundTypePurchaseId",
          render: (h, params) => {
            for (let i = 0; i < this.fundTypePurchaseData.length; i++) {
              if (
                params.row.fundTypePurchaseId == this.fundTypePurchaseData[i].id
              ) {
                return h(
                  "div",
                  { style: {} },
                  this.fundTypePurchaseData[i].title
                );
              }
            }
          }
        },

        {
          title: "基金类型",
          align: "center",
          width: 100,
          key: "fundTypeTypeId",
          render: (h, params) => {
            for (let i = 0; i < this.fundTypeTypeData.length; i++) {
              if (params.row.fundTypeTypeId == this.fundTypeTypeData[i].id) {
                return h("div", { style: {} }, this.fundTypeTypeData[i].title);
              }
            }
          }
        },
        {
          title: "基金公司",
          align: "center",
          width: 100,
          key: "fundTypeCompanyId",
          render: (h, params) => {
            for (let i = 0; i < this.fundTypeCompanyData.length; i++) {
              if (
                params.row.fundTypeCompanyId == this.fundTypeCompanyData[i].id
              ) {
                return h(
                  "div",
                  { style: {} },
                  this.fundTypeCompanyData[i].title
                );
              }
            }
          }
        },
        { title: "基金备注", align: "center", width: 150, key: "remark" },

        {
          title: "操作",
          key: "handle",
          align: "left",
          minWidth: 150,
          render: (h, params) => {
            return h("div", [
              (() => {
                if (this.buttonVerifAuthention("sys:fundInfo:updateFundInfo")) {
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
                          this.editFundInfoButton(params);
                        }
                      }
                    },
                    "编辑"
                  );
                }
              })(),
              (() => {
                if (this.buttonVerifAuthention("sys:fundInfo:deleteFundInfo")) {
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
                          this.deleteFundInfoButton(params);
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
    //1基金类型
    this.queryTypeList(1);
    //2基金版块
    this.queryTypeList(2);
    //3 基金公司
    this.queryTypeList(3);
    //4推荐等级
    this.queryTypeList(4);
    //初始化菜单列表
    this.queryList();
  },
  methods: {
    ...mapActions([
      "addFundInfo",
      "getFundInfoList",
      "getFundTypeListByType",
      "deleteFundInfoById",
      "editFundInfoById"
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
      var sortSearchData = "ASC";
      if (this.sortSearch == "DESC") {
        sortSearchData = "DESC";
      }

      if (this.sortFieldSearch == "principal") {
        this.principalOrderBySearch = "";
        this.holdingYieldOrderBySearch = "";
        this.profitLossOrderBySearch = "";
        this.recommendationLevelOrderBySearch = "";
        this.principalOrderBySearch =
          this.sortFieldSearch + " " + sortSearchData;
      }
      if (this.sortFieldSearch == "holding_yield") {
        this.principalOrderBySearch = "";
        this.holdingYieldOrderBySearch = "";
        this.profitLossOrderBySearch = "";
        this.recommendationLevelOrderBySearch = "";
        this.holdingYieldOrderBySearch =
          this.sortFieldSearch + " " + sortSearchData;
      }
      if (this.sortFieldSearch == "profit_loss") {
        this.principalOrderBySearch = "";
        this.holdingYieldOrderBySearch = "";
        this.profitLossOrderBySearch = "";
        this.recommendationLevelOrderBySearch = "";
        this.profitLossOrderBySearch =
          this.sortFieldSearch + " " + sortSearchData;
      }
      if (this.sortFieldSearch == "recommendation_level") {
        this.principalOrderBySearch = "";
        this.holdingYieldOrderBySearch = "";
        this.profitLossOrderBySearch = "";
        this.recommendationLevelOrderBySearch = "";
        this.recommendationLevelOrderBySearch =
          this.sortFieldSearch + " " + sortSearchData;
      }

      this.loading = true;
      let searchPream = {
        page: this.currentPage,
        limit: this.fetchNum,
        fundTypeCompanyIdSearch: this.fundTypeCompanyIdSearch,
        fundTypeSectionIdSearch: this.fundTypeSectionIdSearch,
        fundTypeTypeIdSearch: this.fundTypeTypeIdSearch,
        fundTypePurchaseIdSearch: this.fundTypePurchaseIdSearch,
        fundNameSearch: this.fundNameSearch,
        fundCodeSearch: this.fundCodeSearch,
        principalOrderBySearch: this.principalOrderBySearch,
        profitLossOrderBySearch: this.profitLossOrderBySearch,
        recommendationLevelOrderBySearch: this.recommendationLevelOrderBySearch,
        holdingYieldOrderBySearch: this.holdingYieldOrderBySearch
      };
      //发送请求
      this.getFundInfoList({ searchPream }).then(res => {
        this.tableData = res.data;
        this.totalPage = res.count;
        this.loading = false;
      });
    },

    //获取基金类型
    queryTypeList(type) {
      let searchPream = {
        typeSearch: type //类型
      };

      //发送请求
      this.getFundTypeListByType({ searchPream }).then(res => {
        if (type == 1) {
          this.fundTypeTypeData = res.data;
        }
        if (type == 2) {
          this.fundTypeSectionData = res.data;
        }
        if (type == 3) {
          this.fundTypeCompanyData = res.data;
        }
        if (type == 4) {
          this.fundTypePurchaseData = res.data;
        }
      });
    },

    //查询
    searchQuery() {
      this.currentPage = 1;
      this.queryList();
    },
    //点击添加子菜单按钮
    addFundInfoButton(scope) {
      this.formValidateFundInfoAdd = {
        id: "",
        fundTypeCompanyId: "",
        fundTypeSectionId: "",
        fundTypeTypeId: "",
        fundTypePurchaseId: "",
        fundName: "",
        fundCode: "",
        fundScale: "",
        holdingYield: "",
        principal: "",
        profitLoss: "",
        recommendationLevel: "",
        remark: ""
      };
      this.modalFundInfoAdd = true;
    },
    //添加主菜单提交
    addFundInfoClick() {
      this.handleSubmitAdd("formValidateFundInfoAdd");
    },
    //表单验证提交
    handleSubmitAdd(name) {
      this.$refs[name].validate(valid => {
        if (valid) {
          //表单提交
          //console.log(this.formValidate);
          let fundInfo = this.formValidateFundInfoAdd;
          this.loadingModel = true; //启动提交按钮转圈
          this.addFundInfo({ fundInfo }).then(res => {
            this.loadingModel = false; //关闭提交按钮转圈
            this.modalFundInfoAdd = false; //关闭弹窗
            //情况表单数据
            this.formValidateFundInfoAdd = {
              id: "",
              fundTypeCompanyId: "",
              fundTypeSectionId: "",
              fundTypeTypeId: "",
              fundTypePurchaseId: "",
              fundName: "",
              fundCode: "",
              fundScale: "",
              holdingYield: "",
              principal: "",
              profitLoss: "",
              recommendationLevel: "",
              remark: ""
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
    deleteFundInfoButton(scope) {
      this.$Modal.confirm({
        title: "删除",
        content: "<p>你确认要删除此条信息吗!</p>",
        onOk: () => {
          let fundInfoId = scope.row.id;
          this.deleteFundInfoById({ fundInfoId }).then(res => {
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
    editFundInfoButton(scope) {
      this.formValidateFundInfoEdit.id = scope.row.id;
      this.formValidateFundInfoEdit.fundTypeCompanyId =
        scope.row.fundTypeCompanyId;
      this.formValidateFundInfoEdit.fundTypeSectionId =
        scope.row.fundTypeSectionId;
      this.formValidateFundInfoEdit.fundTypeTypeId = scope.row.fundTypeTypeId;
      this.formValidateFundInfoEdit.fundTypePurchaseId =
        scope.row.fundTypePurchaseId;
      this.formValidateFundInfoEdit.fundName = scope.row.fundName;
      this.formValidateFundInfoEdit.fundCode = scope.row.fundCode;
      this.formValidateFundInfoEdit.fundScale = scope.row.fundScale;
      this.formValidateFundInfoEdit.holdingYield = scope.row.holdingYield;
      this.formValidateFundInfoEdit.principal = scope.row.principal;
      this.formValidateFundInfoEdit.profitLoss = scope.row.profitLoss;
      this.formValidateFundInfoEdit.recommendationLevel =
        scope.row.recommendationLevel;
      this.formValidateFundInfoEdit.remark = scope.row.remark;
      this.modalFundInfoEdit = true;
    },

    //编辑菜单提交
    editFundInfoClick() {
      this.handleSubmitEdit("formValidateFundInfoEdit");
    },
    //表单验证提交
    handleSubmitEdit(name) {
      this.$refs[name].validate(valid => {
        if (valid) {
          //表单提交
          let fundInfo = this.formValidateFundInfoEdit;
          this.loadingModel = true; //启动提交按钮转圈
          this.editFundInfoById({ fundInfo }).then(res => {
            this.loadingModel = false; //关闭提交按钮转圈
            this.modalFundInfoEdit = false; //关闭弹窗
            //情况表单数据
            this.formValidateFundInfoEdit = {
              id: "",
              fundTypeCompanyId: "",
              fundTypeSectionId: "",
              fundTypeTypeId: "",
              fundTypePurchaseId: "",
              fundName: "",
              fundCode: "",
              fundScale: "",
              holdingYield: "",
              principal: "",
              profitLoss: "",
              recommendationLevel: "",
              remark: ""
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
