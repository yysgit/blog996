<template>
  <div>
    <div style="min-width:1000px">
    <Card shadow>

      <!--添加主菜单-->
      <Button v-if="buttonVerifAuthention('sys:menu:addMenu')" type="primary" icon="md-add" @click="addClick"
              style="margin-bottom: 10px;" >添加主菜单</Button>

      <!--菜单表格-->
      <tree-table expand-key="title" :expand-type="false"  :loading="loadingTable"  :selectable="false" :columns="columns" :data="data"
                  stripe border show-index>
        <template slot="likes" slot-scope="scope">
          <Button   v-if="buttonVerifAuthention('sys:menu:updateMenu')" type="warning" size="small" @click="editMenu(scope)" style="margin-right: 5px;">编辑</Button>
          <Button  v-if="buttonVerifAuthention('sys:menu:deleteMenu')" type="error" size="small" @click="deleteMenu(scope)" style="margin-right: 5px;">删除</Button>
          <Button v-if="scope.row.grade!=3 && buttonVerifAuthention('sys:menu:addMenu')" type="primary" size="small" @click="addMenuChild(scope)" style="margin-right: 5px;">添加子菜单</Button>

        </template>
      </tree-table>


      <!--添加主菜单弹出框-->
      <Modal
        v-model="modal6"
        title="添加主菜单"
        :mask-closable="false"
        >
        <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80">
          <FormItem label="菜单名" prop="title">
            <Input v-model.trim="formValidate.title" placeholder="请输入菜单名"></Input>
          </FormItem>
          <FormItem label="图标" prop="icon">
            <Input v-model="formValidate.icon" placeholder="请输入图标"></Input>
          </FormItem>
          <FormItem label="资源路径" prop="href">
            <Input v-model="formValidate.href" placeholder="请输入路径资源"></Input>
          </FormItem>
          <FormItem label="排序" prop="sorting">
            <Input v-model.trim="formValidate.sorting" placeholder="排序(整数)"></Input>
          </FormItem>
          <FormItem label="权限标识" prop="perms">
            <Input v-model="formValidate.perms" placeholder="权限标识"></Input>
          </FormItem>
        </Form>

        <div slot="footer">
          <Button type="text" size="large" @click="modal6=false">取消</Button>
          <Button type="primary" size="large" @click="asyncOK" :loading="loadingModel">确定</Button>
        </div>
      </Modal>
      <!--添加子菜单弹出框-->
      <Modal
        v-model="modal7"
        title="添加子菜单"
        :mask-closable="false">
        <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80">
          <FormItem label="子菜单名" prop="title">
            <Input v-model.trim="formValidate.title" placeholder="请输入子菜单名"></Input>
          </FormItem>
          <FormItem label="图标" prop="icon">
            <Input v-model="formValidate.icon" placeholder="请输入图标"></Input>
          </FormItem>
          <FormItem label="资源路径" prop="href">
            <Input v-model="formValidate.href" placeholder="请输入路径资源"></Input>
          </FormItem>
          <FormItem label="排序" prop="sorting">
            <Input v-model.trim="formValidate.sorting" placeholder="排序(整数)"></Input>
          </FormItem>
          <FormItem label="权限标识" prop="perms">
            <Input v-model="formValidate.perms" placeholder="权限标识"></Input>
          </FormItem>
        </Form>
        <div slot="footer">
          <Button type="text" size="large" @click="modal7=false">取消</Button>
          <Button type="primary" size="large" @click="asyncOKChild" :loading="loadingModel">确定</Button>
        </div>
      </Modal>
      <!--编辑菜单弹出框-->
      <Modal
        v-model="modal8"
        title="编辑菜单"
        :mask-closable="false"
       >
        <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80">
          <FormItem label="子菜单名" prop="title">
            <Input v-model.trim="formValidate.title" placeholder="请输入子菜单名"></Input>
          </FormItem>
          <FormItem label="图标" prop="icon">
            <Input v-model="formValidate.icon" placeholder="请输入图标"></Input>
          </FormItem>
          <FormItem label="资源路径" prop="href">
            <Input v-model="formValidate.href" placeholder="请输入路径资源"></Input>
          </FormItem>
          <FormItem label="排序" prop="sorting">
            <Input v-model.trim="formValidate.sorting" placeholder="排序(整数)"></Input>
          </FormItem>
          <FormItem label="权限标识" prop="perms">
            <Input v-model="formValidate.perms" placeholder="权限标识"></Input>
          </FormItem>
        </Form>

        <div slot="footer">
          <Button type="text" size="large" @click="modal8=false">取消</Button>
          <Button type="primary" size="large" @click="asyncOKEdit" :loading="loadingModel">确定</Button>
        </div>
      </Modal>

    </Card>
    </div>
  </div>
</template>

<script>
  import { mapActions } from 'vuex'
  import {permsVerifAuthention } from '@/libs/util'
  export default {
    name: 'tree_table_page',
    data() {

      const isInteger = (rule, value, callback) => {
        if(value==null||value==''){
          callback();
        }else{
          if (!Number(value)) {
            callback(new Error('请输入正整数'));
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
        //表单
        formValidate: {
          id:'',
          title: '',
          icon: '',
          href: '',
          sorting: '',
          perms: '',
          parentId:0
        },
        //表单验证
        ruleValidate: {
          title: [
            {required: true, message: '请输入菜单名', trigger: 'blur'},
            {type: 'string', max: 6, message: '菜单名最长为6个字', trigger: 'blur'},
          ],
          sorting: [
            {required: true, message: '请输入排序号', trigger: 'blur'},
            {validator: isInteger,  trigger: 'blur'}
          ],
        },
        //对话框
        modal6: false,
        modal7: false,
        modal8: false,
        loadingTable: true,//表格加载转圈
        loadingModel:false,//表单提交按钮转圈
        //表格列
        columns: [
          {
            title: '菜单名',
            key: 'title',
            width: '200'
          },
          {
            title: '图标',
            key: 'icon',
            minWidth: '150px'
          },

          {
            title: '链接',
            key: 'href',
            minWidth: '150px'
          },
          {
            title: '权限标识',
            key: 'perms',
            minWidth: '150px'
          },
          {
            title: '排序',
            key: 'sorting'
          },
          {
            title: '操作',
            key: 'likes',
            minWidth: '200px',
            type: 'template',
            template: 'likes'
          }
        ],
        //表格数据
        data: []
      }
    },
    created(){
      //初始化菜单列表
      this.getMenuManage();
    },
    methods: {
      ...mapActions([
        'addMenu',
        'getMenuManageList',
        'deleteMenuById',
        'editMenuById'
      ]),
      buttonVerifAuthention(perms){
        return permsVerifAuthention(perms,this.$store.state.user.authentionList)
      },

      handle(scope) {
        //console.log(scope)
      },
      //获取页面菜单列表
      getMenuManage(){
        this.getMenuManageList().then(res => {

          this.data=res;
          this.loadingTable=false;
        })
      },
      addClick(){
        this.formValidate = {
          id:'',
          title: '',
          icon: '',
          href: '',
          sorting: '',
          perms: '',
          parentId:0
        };
        this.modal6 = true;

      },

      //添加主菜单提交
      asyncOK() {

        this.handleSubmit('formValidate');
      },
      //表单验证提交
      handleSubmit(name) {

        this.$refs[name].validate((valid) => {
          if (valid) {
            //表单提交
            //console.log(this.formValidate);
            let menu=this.formValidate;
            this.loadingModel=true;//启动提交按钮转圈
            this.addMenu({menu}).then(res => {

              //console.log("addMenu请求:"+res);
              this.loadingModel=false;//关闭提交按钮转圈
              this.modal6 = false;//关闭弹窗
              this.modal7 = false;//关闭弹窗
              //情况表单数据
              this.formValidate = {
                id:'',
                title: '',
                icon: '',
                href: '',
                sorting: '',
                perms: '',
                parentId:0
              };
              //刷新菜单页面
              this.getMenuManage();
            })
          } else {
            this.$Message.error('Fail!');
          }
        })
      },
      //点击添加子菜单按钮
      addMenuChild(scope){
        this.formValidate = {
          id:'',
          title: '',
          icon: '',
          href: '',
          sorting: '',
          perms: '',
          parentId:0
        };
        this.formValidate.parentId=scope.row.id;
        this.modal7=true;
      },
      //添加主菜单提交
      asyncOKChild(){
        this.handleSubmit('formValidate');
      },
      //删除菜单
      deleteMenu(scope){
          this.$Modal.confirm({
            title: '删除',
            content: '<p>你确认要删除此条信息吗!</p>',
            onOk: () => {
              let menuId=scope.row.id;

              this.deleteMenuById({menuId}).then(res => {

                this.$Message.info('删除成功!');
                //刷新菜单页面
                this.getMenuManage();
              })

            },
            onCancel: () => {
              this.$Message.info('Clicked cancel');
            }
          });
      },
      //编辑菜单
      editMenu(scope){
        this.formValidate.id=scope.row.id;
        this.formValidate.title=scope.row.title;
        this.formValidate.icon=scope.row.icon;
        this.formValidate.href=scope.row.href;
        this.formValidate.sorting=scope.row.sorting+'';
        this.formValidate.perms=scope.row.perms;
        this.formValidate.parentId=scope.row.parentId;
        this.modal8=true;
      },

      //编辑菜单提交
      asyncOKEdit(){
        this.handleSubmitEdit('formValidate');
      },
      //表单验证提交
      handleSubmitEdit(name) {
        this.$refs[name].validate((valid) => {
          if (valid) {
            //表单提交
            //console.log(this.formValidate);
            let menu=this.formValidate;
            this.loadingModel=true;//启动提交按钮转圈
            this.editMenuById({menu}).then(res => {

              this.loadingModel=false;//关闭提交按钮转圈
              this.modal8 = false;//关闭弹窗
              //情况表单数据
              this.formValidate = {
                id:'',
                title: '',
                icon: '',
                href: '',
                sorting: '',
                perms: '',
                parentId:0
              };
              //刷新菜单页面
              this.getMenuManage();
            })
          } else {
            this.$Message.error('Fail!');
          }
        })
      },
    }
  }
</script>

<style>

</style>
