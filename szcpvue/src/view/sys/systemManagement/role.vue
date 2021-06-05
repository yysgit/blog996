<template>
  <div>
    <div style="min-width:1000px">
    <Card shadow>

      <!--添加角色-->
      <Button v-if="buttonVerifAuthention('sys:role:addRole')" type="primary" icon="md-add" @click="addClick"
              style="margin-bottom: 10px;">添加角色
      </Button>

      <!--菜单表格-->
      <Table ref="tables" width="1200px" stripe border :loading="loading" :data="tableData" :columns="columns">
      </Table>
      <Page :total="totalPage" show-total :styles="stylePage" @on-change="changePage"/>


      <!--添加角色弹出框-->
      <Modal
        v-model="modalAddRole"
        title="添加角色"
        :mask-closable="false"
      >
        <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80">
          <FormItem label="角色名" prop="roleName">
            <Input v-model.trim="formValidate.roleName" placeholder="请输入菜单名"/>
          </FormItem>
          <FormItem label="备注" prop="roleRemark">
            <Input type="textarea" :autosize="{minRows: 2,maxRows: 5}" v-model="formValidate.roleRemark"
                   placeholder="备注"/>
          </FormItem>
        </Form>
        <div slot="footer">
          <Button type="text" size="large" @click="modalAddRole=false">取消</Button>
          <Button type="primary" size="large" @click="addRoleClick" :loading="loadingModel">确定</Button>
        </div>
      </Modal>

      <!--编辑角色弹出框-->
      <Modal
        v-model="modalEditRole"
        title="编辑角色"
        :mask-closable="false"
      >
        <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80">
          <FormItem label="角色名" prop="roleName">
            <Input v-model.trim="formValidate.roleName" placeholder="请输入菜单名"/>
          </FormItem>
          <FormItem label="备注" prop="roleRemark">
            <Input type="textarea" :autosize="{minRows: 2,maxRows: 5}" v-model="formValidate.roleRemark"
                   placeholder="备注"/>
          </FormItem>
        </Form>
        <div slot="footer">
          <Button type="text" size="large" @click="modalEditRole=false">取消</Button>
          <Button type="primary" size="large" @click="editRoleClick" :loading="loadingModel">确定</Button>
        </div>
      </Modal>

      <!--角色菜单弹出框-->
      <Modal
        v-model="modalRoleMenu"
        title="角色菜单"
        :mask-closable="false"
      >
        <Tree :data="roleMenuData" show-checkbox ref="tree"></Tree>
        <div slot="footer">
          <Button type="text" size="large" @click="modalRoleMenu=false">取消</Button>
          <Button type="primary" size="large" @click="roleMenuClick" :loading="loadingModel">确定</Button>
        </div>
      </Modal>

    </Card>
    </div>
  </div>
</template>

<script>
  import {mapActions} from 'vuex'
  import {permsVerifAuthention } from '@/libs/util'

  export default {
    name: 'role_page',
    data() {
      return {
        stylePage: {
          marginTop: '20px'
        },
        currentPage: 1,
        fetchNum: 10,
        totalPage: 0,

        //表单
        formValidate: {
          id: '',
          roleName: '',
          roleRemark: ''
        },
        //表单验证
        ruleValidate: {
          roleName: [
            {required: true, message: '请输入角色名', trigger: 'blur'},
            {type: 'string', max: 10, message: '菜单名最长为10个字', trigger: 'blur'},
          ],
        },
        //对话框
        modalAddRole: false, //显示添加对话框
        modalEditRole: false,//显示编辑对话框
        modalRoleMenu: false,//显示角色菜单对话框

        loading: false,//表格加载转圈
        loadingModel: false,//按钮加载转圈


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
          {title: '角色名', width: 200, key: 'roleName'},
          {title: '备注', key: 'remark'},
          {
            title: '操作',
            key: 'handle',
            align: 'left',
            width: 200,
            render: (h, params) => {
              return h('div', [
                (() => {
                  if (this.buttonVerifAuthention("sys:role:updateRole")) {
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
                  if (this.buttonVerifAuthention("sys:role:deleteRole")) {
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
                (() => {

                  if (!this.showRoleMenu(params) && this.buttonVerifAuthention("sys:menu:roleMenuList")) {
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
                          this.roleMenu(params)
                        }
                      }
                    }, '权限菜单')
                  }

                })(),
              ])
            }
          }
        ],
        tableData: [],
        roleMenuData: [],
        myRoleId:'',
      }
    },
    created() {
      //初始化角色列表
      this.queryList();
    },
    mounted() {

    },
    methods: {
      ...mapActions([
        'addRole',
        'getRoleList',
        'deleteRoleById',
        'editRoleById',
        'getRoleMenuList',
        'addOrEditRoleMenu',
      ]),


      buttonVerifAuthention(perms){
        return permsVerifAuthention(perms,this.$store.state.user.authentionList)
      },

      //分页改变
      changePage(page) {
        this.currentPage = page
        this.queryList()
      },
      //获取页面菜单列表
      queryList() {
        this.loading = true;
        let searchPream = {
          page: this.currentPage,
          limit: this.fetchNum,
        }
        //发送请求
        this.getRoleList({searchPream}).then(res => {

          this.tableData = res.data;
          this.totalPage = res.count;
          this.loading = false;
        })
      },
      //添加按钮点击
      addClick() {
        this.formValidate = {
          id: '',
          roleName: '',
          roleRemark: ''
        };
        this.modalAddRole = true;
      },
      //添加主菜单提交
      addRoleClick() {
        this.handleSubmit('formValidate');
      },
      //表单验证提交
      handleSubmit(name) {
        this.$refs[name].validate((valid) => {
          if (valid) {
            //表单提交
            let role = this.formValidate;
            this.loadingModel = true;
            this.addRole({role}).then(res => {

                this.loadingModel = false;
              this.modalAddRole = false;//关闭弹窗
              //情况表单数据
              this.formValidate = {
                id: '',
                roleName: '',
                roleRemark: ''
              };
              //刷新页面
              this.currentPage = 1;
              this.queryList();
            })
          } else {
            this.$Message.error('请按要求填写表单!');
          }
        })
      },
      //编辑点击
      editClick(params) {
        this.formValidate.id = params.row.id;
        this.formValidate.roleName = params.row.roleName;
        this.formValidate.roleRemark = params.row.roleRemark;
        this.modalEditRole = true;
      },
      //编辑提交
      editRoleClick() {
        this.handleSubmitEdit('formValidate');
      },
      //表单验证提交
      handleSubmitEdit(name) {
        this.$refs[name].validate((valid) => {
          if (valid) {
            //表单提交
            //console.log(this.formValidate);
            let role = this.formValidate;
            this.loadingModel = true;//启动提交按钮转圈
            this.editRoleById({role}).then(res => {

                this.loadingModel = false;//关闭提交按钮转圈
                this.modalEditRole = false;//关闭弹窗
                //情况表单数据
                this.formValidate = {
                  id: '',
                  roleName: '',
                  roleRemark: ''
                };
                //刷新菜单页面
                this.queryList();

            })
          } else {
            this.$Message.error('验证错误!');
          }
        })
      },
      //删除
      deleteClick(params) {
        this.$Modal.confirm({
          title: '删除',
          content: '<p>你确认要删除此条信息吗!</p>',
          onOk: () => {
            let roleId = params.row.id;
            this.deleteRoleById({roleId}).then(res => {

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
      //修改角色菜单
      roleMenu(params) {
        this.myRoleId=params.row.id;
        let roleId = params.row.id;
        this.getRoleMenuList({roleId}).then(res => {
            this.roleMenuData = res.data;
            this.modalRoleMenu = true;

        })
      },
      //权限菜单按钮是否显示
      showRoleMenu(params) {
        return this.$store.state.user.userRoleId == params.row.id;
      },

      roleMenuClick() {
        let list = this.$refs.tree.getCheckedAndIndeterminateNodes();
        //console.log(list);

        //封装id
        var idArray ='';
        for (var i = 0; i < list.length; i++) {
          if(i==0){
            idArray=list[i].id;
          }else{
            idArray=idArray+","+list[i].id;
          };
        }
        //console.log(idArray)
        let myRoleId=this.myRoleId;
        //修改或者添加角色菜单
        this.addOrEditRoleMenu({idArray,myRoleId}).then(res => {
            this.modalRoleMenu = false;
            this.$Message.success(res.msg);
        })
      }

    }
  }
</script>

<style>

</style>
