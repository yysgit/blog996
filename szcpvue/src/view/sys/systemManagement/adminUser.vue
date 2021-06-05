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
              <Button v-if="buttonVerifAuthention('sys:admin:addAdminUser')" type="primary" icon="md-add"
                      @click="addClick"
                      style="margin-bottom: 10px;margin-right: 10px;">添加用户
              </Button>

              <!--显示全部用户-->
              <Button v-if="buttonVerifAuthention('sys:role:allList') && level!=2" type="primary"
                      @click="queryAdminUserAllClick"
                      style="margin-bottom: 10px;margin-right: 10px;">显示全部用户
              </Button>
              <!--菜单表格-->
              <Table ref="tables" width="1200px" stripe border :loading="loading" :data="tableData" :columns="columns">
              </Table>
              <Page :total="totalPage" show-total :styles="stylePage" @on-change="changePage"/>

              <!--添加用户-->
              <Modal
                v-model="modalAddAdminUser"
                title="添加用户"
                :mask-closable="false"
              >

                <Form ref="formValidateAdd" :model="formValidateAdd" :rules="ruleValidateAdd" :label-width="80">
                  <FormItem label="登陆账户" prop="adminName">
                    <Input v-model="formValidateAdd.adminName" placeholder="请输入登陆账户"></Input>
                  </FormItem>
                  <FormItem label="姓名" prop="adminFullname">
                    <Input v-model="formValidateAdd.adminFullname" placeholder="请输入姓名"></Input>
                  </FormItem>
                  <FormItem label="手机号" prop="adminPhone">
                    <Input v-model="formValidateAdd.adminPhone" placeholder="请输入手机号"></Input>
                  </FormItem>
                  <FormItem label="机构" prop="organTitle">
                    <Input v-model="organTitle" disabled></Input>
                  </FormItem>
                  <FormItem label="角色" prop="roleId">
                    <Select v-model="formValidateAdd.roleId">
                      <Option v-for="(item, index) in roleAllList" :key="index" v-text="item.roleName"
                              :value="item.id+''">{{item.roleName}}
                      </Option>
                    </Select>
                  </FormItem>
                  <FormItem label="分成利率(%)" prop="interestRate">
                    <Input type="text" v-model="formValidateAdd.interestRate" placeholder="请输入分成利率(运营部请输入0)"></Input>
                  </FormItem>

                </Form>
                <div slot="footer">
                  <Button type="text" size="large" @click="modalAddAdminUser=false">取消</Button>
                  <Button type="primary" size="large" @click="addAdminUserClick" :loading="loadingModel">确定</Button>
                </div>
              </Modal>

              <!--编辑用户-->
              <Modal
                v-model="modalEditAdminUser"
                title="编辑用户"
                :mask-closable="false"
              >

                <Form ref="formValidateEdit" :model="formValidateEdit" :rules="ruleValidateEdit" :label-width="80">
                  <FormItem label="登陆账户" prop="adminName">
                    <Input v-model="formValidateEdit.adminName" placeholder="请输入登陆账户" disabled></Input>
                  </FormItem>
                  <FormItem label="姓名" prop="adminFullname">
                    <Input v-model="formValidateEdit.adminFullname" placeholder="请输入姓名"></Input>
                  </FormItem>
                  <FormItem label="手机号" prop="adminPhone">
                    <Input v-model="formValidateEdit.adminPhone" placeholder="请输入手机号"></Input>
                  </FormItem>
                  <FormItem label="角色" prop="roleId">
                    <Select v-model="formValidateEdit.roleId">
                      <Option v-for="(item, index) in roleAllList" :key="index" v-text="item.roleName"
                              :value="item.id+''">{{item.roleName}}
                      </Option>
                    </Select>
                  </FormItem>
                  <FormItem label="分成利率(%)" prop="interestRate">
                    <Input type="text" v-model="formValidateEdit.interestRate" placeholder="请输入分成利率(运营部请输入0)"></Input>
                  </FormItem>


                </Form>
                <div slot="footer">
                  <Button type="text" size="large" @click="modalEditAdminUser=false">取消</Button>
                  <Button type="primary" size="large" @click="editAdminUserClick" :loading="loadingModel">确定</Button>
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
  import SplitPane from '_c/split-pane'
  import {mapActions} from 'vuex'
  import {permsVerifAuthention} from '@/libs/util'

  export default {
    name: 'split_pane_page',
    components: {
      SplitPane
    },
    data() {
      let self = this;
      const isInteger2 = (rule, value, callback) => {
        if (value == null || value == '') {
          callback();
        } else {
          if (!Number(value)) {
            callback(new Error('请输入正整数'));
          } else if (value > 100) {
            callback(new Error('请输入小于100的正整数'));
          } else {
            const re = /^[0-9]*[1-9][0-9]*$/;
            const rsCheck = re.test(value);
            if (!rsCheck) {
              callback(new Error('请输入正整数'));
            } else {
              callback();
            }
          }
        }

      }
      return {
        offset: 0.2,
        offsetVertical: '250px',
        data1: [],
        loadingTable: true,

        stylePage: {
          marginTop: '20px'
        },
        currentPage: 1,
        fetchNum: 10,
        totalPage: 0,
        organId: 0,
        //0表示查询机构及其子孙机构的全部用户, 1表示查询单个机构的用户
        allOrgan: 0,

        organTitle: '默认机构',
        roleAllList: [],

        level: this.$store.state.user.userLevel,


        modalAddAdminUser: false,
        modalEditAdminUser: false,
        loadingModel: false,

        //添加表单
        formValidateAdd: {
          adminName: '',
          adminFullname: '',
          adminPhone: '',
          organId: this.organId,
          roleId: '',
          interestRate: '',
        },

        //编辑表单验证
        ruleValidateAdd: {
          adminName: [
            {required: true, message: '请输入登陆账号', trigger: 'blur'},
            {type: 'string', max: 20, message: '登陆账号最长为20个字', trigger: 'blur'},
          ],
          adminFullname: [
            {required: true, message: '请输入姓名', trigger: 'blur'},
            {type: 'string', max: 10, message: '姓名最长为10个字', trigger: 'blur'},
          ],
          adminPhone: [
            {required: true, message: '请输入登陆账号', trigger: 'blur'},
          ],
          roleId: [
            {required: true, message: '请选择角色', trigger: 'blur'},
          ],
          interestRate: [
            {required: true, message: '请输入利率', trigger: 'blur'},
            {validator: isInteger2, trigger: 'blur'}
          ],
        },

        //编辑表单验证
        formValidateEdit: {
          id: '',
          adminName: '',
          adminFullname: '',
          adminPhone: '',
          organId: this.organId,
          roleId: '',
          interestRate: '',
        },

        //编辑表单验证
        ruleValidateEdit: {
          adminName: [
            {required: true, message: '请输入登陆账号', trigger: 'blur'},
            {type: 'string', max: 20, message: '登陆账号最长为20个字', trigger: 'blur'},
          ],
          adminFullname: [
            {required: true, message: '请输入姓名', trigger: 'blur'},
            {type: 'string', max: 10, message: '姓名账号最长为10个字', trigger: 'blur'},
          ],
          adminPhone: [
            {required: true, message: '请输入登陆账号', trigger: 'blur'},
          ],
          roleId: [
            {required: true, message: '请选择角色', trigger: 'blur'},
          ],
          interestRate: [
            {required: true, message: '请输入利率', trigger: 'blur'},
            {validator: isInteger2, trigger: 'blur'}
          ],
        },

        columns: [
          {
            type: 'index2',
            width: 60,
            title: '序号',
            align: 'center',
            render: (h, params) => {
              return h('span', params.index + (this.currentPage - 1) * this.fetchNum + 1);
            }
          },
          {title: '用户名', width: 150, align: "center", key: 'adminName'},
          {title: '姓名', width: 200, align: "center", key: 'adminFullname'},
          {title: '手机', width: 120, align: "center", key: 'adminPhone'},
          {title: '机构', width: 150, align: "center", key: 'organName'},
          {title: '角色', width: 100, align: "center", key: 'roleName'},
          {
            title: '操作',
            key: 'handle',
            align: 'left',
            width: 220,
            render: (h, params) => {
              return h('div', [
                (() => {
                  if (this.buttonVerifAuthention('sys:admin:updateAdminUser')) {
                    return h('Button', {
                      props: {
                        type: 'primary',
                        size: 'small'
                      },
                      style: {
                        marginRight: '5px'
                      },
                      on: {
                        click: () => {
                          this.editClick(params)
                        }
                      }
                    }, '编辑')
                  }
                })(),
                (() => {
                  if (!this.showRoleMenu(params)) {
                    return h('Button', {
                      props: {
                        type: 'primary',
                        size: 'small'
                      },
                      style: {
                        marginRight: '5px'
                      },
                      on: {
                        click: () => {
                          this.editPasswordClick(params)
                        }
                      }
                    }, '密码初始化')
                  }
                })(),
                (() => {
                  if ((!this.showRoleMenu(params)) && this.buttonVerifAuthention('sys:admin:deleteAdminUser')) {
                    return h('Button', {
                      props: {
                        type: 'error',
                        size: 'small'
                      },
                      style: {
                        marginRight: '5px'
                      },
                      on: {
                        click: () => {
                          this.deleteClick(params)
                        }
                      }
                    }, '删除')
                  }
                })(),
              ])
            }
          }
        ],
        tableData: [],


      }
    },
    created() {
      //初始化菜单列表
      this.queryList();
      this.queryOrganList();
    },
    methods: {
      ...mapActions([
        'getOrganUserTree',
        'getAdminUserList',
        'editAdminUserById',
        'getRoleAllList',
        'addAdminUser',
        'deleteAdminUserById',
        'editPasswordAdminUserById',
      ]),
      buttonVerifAuthention(perms) {
        return permsVerifAuthention(perms, this.$store.state.user.authentionList)
      },


      handleMoving(e) {
        //console.log(e.atMin, e.atMax)
      },
      //获取页面菜单列表
      queryOrganList() {
        this.getOrganUserTree().then(res => {

          this.data1 = res.data;
          this.loadingTable = false;
        })
      },
      //分页改变
      changePage(page) {
        this.currentPage = page
        this.queryList()
      },
      //机构树被选中
      selectChange(selectedList) {
        if (selectedList.length > 0) {
          this.organId = selectedList[0].id;
          this.organTitle = selectedList[0].title;
          this.allOrgan = 1;
          this.queryList();
        }
      },
      //获取页面菜单列表
      queryList() {
        this.loading = true;

        if (this.organId == 0) {
          this.organId = this.$store.state.user.userOrganId;
        }
        let searchPream = {
          page: this.currentPage,
          limit: this.fetchNum,
          organId: this.organId,
          allOrgan: this.allOrgan,
        }
        //发送请求
        this.getAdminUserList({searchPream}).then(res => {

          this.tableData = res.data;
          this.totalPage = res.count;
          this.loading = false;
        })
      },
      //权限菜单按钮是否显示
      showRoleMenu(params) {
        return this.$store.state.user.userId == params.row.id;
      },
      //添加按钮点击
      addClick() {
        //发送请求
        this.getRoleAllList().then(res => {

          this.roleAllList = res.data;
          this.modalAddAdminUser = true;
        })
      },
      //添加用户表单提交
      addAdminUserClick() {
        this.formValidateAdd.organId = this.organId;
        this.handleSubmit('formValidateAdd');
      },
      //表单验证提交
      handleSubmit(name) {
        //console.log(this.$refs);
        this.$refs[name].validate((valid) => {
          // console.log(valid);
          if (valid) {
            //表单提交
            //console.log(this.formValidate);
            let adminUser = this.formValidateAdd;
            this.loadingModel = true;
            this.addAdminUser({adminUser}).then(res => {

                //console.log("请求:" + res);
                this.loadingModel = false;
                this.modalAddAdminUser = false;//关闭弹窗
                //情况表单数据
                //表单
                this.formValidateAdd = {
                  adminName: '',
                  adminFullname: '',

                  adminPhone: '',
                  organId: this.organId,
                  roleId: '',
                  interestRate: '',
                },
                  //刷新页面
                  this.currentPage = 1;
                this.queryList();

            })
          } else {
            this.$Message.error('请按要求填写表单!');
          }
        })
      },
      //显示当前机构全部用户
      queryAdminUserAllClick() {
        this.allOrgan = 0;
        this.queryList();
      },
      //删除
      editPasswordClick(params) {
        this.$Modal.confirm({
          title: '密码',
          content: '<p>你确认要初始化此条信息吗?</p>',
          onOk: () => {
            let adminUserId = params.row.id;
            this.editPasswordAdminUserById({adminUserId}).then(res => {

              this.$Message.info('密码初始化成功!');
              //刷新页面
              this.queryList();
            })
          },
          onCancel: () => {
            this.$Message.info('取消密码初始化!');
          }
        });
      },

      //删除
      deleteClick(params) {
        this.$Modal.confirm({
          title: '删除',
          content: '<p>你确认要删除此条信息吗!</p>',
          onOk: () => {
            let adminUserId = params.row.id;
            this.deleteAdminUserById({adminUserId}).then(res => {

              this.$Message.info('删除成功!');
              //刷新页面
              this.queryList();
            })
          },
          onCancel: () => {
            this.$Message.info('取消删除!');
          }
        });
      },


      //编辑点击
      editClick(params) {
        //发送请求
        this.getRoleAllList().then(res => {

          this.roleAllList = res.data;
          this.formValidateEdit.id = params.row.id;
          this.formValidateEdit.adminName = params.row.adminName;
          this.formValidateEdit.adminFullname = params.row.adminFullname;
          this.formValidateEdit.adminPhone = params.row.adminPhone;
          this.formValidateEdit.organId = params.row.organId;
          this.formValidateEdit.roleId = params.row.roleId + '';
          this.formValidateEdit.interestRate = params.row.interestRate + '';
          this.modalEditAdminUser = true;
        })


      },
      //编辑提交
      editAdminUserClick() {
        this.handleSubmitEdit('formValidateEdit');
      },
      //表单验证提交
      handleSubmitEdit(name) {
        this.$refs[name].validate((valid) => {
          if (valid) {
            //表单提交
            //console.log(this.formValidate);
            let adminUser = this.formValidateEdit;
            this.loadingModel = true;//启动提交按钮转圈
            this.editAdminUserById({adminUser}).then(res => {

                this.loadingModel = false;//关闭提交按钮转圈
                this.modalEditAdminUser = false;//关闭弹窗
                //情况表单数据
                this.formValidateEdit = {
                  id: '',
                  adminName: '',
                  adminFullname: '',
                  adminPhone: '',
                  organId: this.organId,
                  roleId: '',
                  interestRate: '',
                };
                //刷新菜单页面
                this.queryList();

            })
          } else {
            this.$Message.error('验证错误!');
          }
        })
      },


    }
  }
</script>

<style lang="less">

  .ivu-modal-body .ivu-form-item .ivu-form-item-label {
    width: 110px !important;
  }

  .ivu-modal-body .ivu-form-item .ivu-form-item-content {
    margin-left: 110px !important;
  }

  .ivu-tree-title-selected, .ivu-tree-title-selected:hover {
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
