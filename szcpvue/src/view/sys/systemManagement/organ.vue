<template>
  <div>
    <div style="min-width:1000px">
    <Card shadow>



      <!--机构表格-->
      <tree-table expand-key="organName" :expand-type="false"  :loading="loadingTable"  :selectable="false" :columns="columns" :data="data"
                  stripe border show-index>
        <template slot="likes" slot-scope="scope">
          <Button v-if="buttonVerifAuthention('sys:organ:updateOrgan')" type="warning" size="small" @click="editOrgan(scope)" style="margin-right: 5px;">编辑</Button>
          <Button v-if="showDeleteButton(scope)" type="error" size="small" @click="deleteOrgan(scope)" style="margin-right: 5px;">删除</Button>
          <Button  v-if="buttonVerifAuthention('sys:organ:addOrgan')" type="primary" size="small" @click="addOrganChildButton(scope)" style="margin-right: 5px;">添加子机构</Button>

        </template>
      </tree-table>

      <!--添加子机构弹出框-->
      <Modal
        v-model="modal7"
        title="添加子机构"
        :mask-closable="false">
        <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80">
          <FormItem label="子机构名" prop="organName">
            <Input v-model.trim="formValidate.organName" placeholder="请输入子机构名"></Input>
          </FormItem>
          <FormItem label="子机构备注" prop="organRemake">
            <Input type="textarea" :autosize="{minRows: 2,maxRows: 5}" v-model="formValidate.organRemake" placeholder="请输入子机构备注"></Input>
          </FormItem>
          <FormItem label="排序" prop="sorting">
            <Input v-model="formValidate.sorting" placeholder="排序(整数)"></Input>
          </FormItem>
        </Form>
        <div slot="footer">
          <Button type="text" size="large" @click="modal7=false">取消</Button>
          <Button type="primary" size="large" @click="addOrganChildClick" :loading="loadingModel">确定</Button>
        </div>
      </Modal>
      <!--编辑菜单弹出框-->
      <Modal
        v-model="modal8"
        title="编辑菜单"
        :mask-closable="false"
       >
        <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80">
          <FormItem label="子机构名" prop="organName">
            <Input v-model.trim="formValidate.organName" placeholder="请输入子机构名"></Input>
          </FormItem>
          <FormItem label="子机构备注" prop="organRemake">
            <Input type="textarea" :autosize="{minRows: 2,maxRows: 5}" v-model="formValidate.organRemake" placeholder="请输入子机构备注"></Input>
          </FormItem>
          <FormItem label="排序" prop="sorting">
            <Input v-model="formValidate.sorting" placeholder="排序(整数)"></Input>
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
        //console.log(value);
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
          organName: '',
          organRemake: '',
          sorting: '',
          parentId:0
        },
        //表单验证
        ruleValidate: {
          organName: [
            {required: true, message: '请输入机构名', trigger: 'blur'},
            {type: 'string', max: 10, message: '菜单名最长为10个字', trigger: 'blur'},
          ],
          sorting: [
            {validator: isInteger,  trigger: 'blur'}
          ],
        },
        //对话框
        modal7: false,
        modal8: false,
        loadingTable: true,//表格加载转圈
        loadingModel:false,//表单提交按钮转圈
        //表格列
        columns: [
          {
            title: '机构名',
            key: 'organName',
            width: '200'
          },
          {
            title: '备注',
            key: 'organRemake',
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
      this.queryList();
    },
    methods: {
      ...mapActions([
        'addOrganChild',
        'getOrganList',
        'getArticleMenuList',
        'deleteOrganById',
        'editOrganById'
      ]),
      buttonVerifAuthention(perms){
        return permsVerifAuthention(perms,this.$store.state.user.authentionList)
      },

      //获取页面菜单列表
      queryList(){
        this.getArticleMenuList().then(res => {

          this.data=res.data;
          this.loadingTable=false;
        })
      },
      //判断是否有删除权限
      showDeleteButton(params){
        return this.$store.state.user.userOrganId != params.row.id && this.buttonVerifAuthention("sys:organ:deleteOrgan");
      },

      //点击添加子菜单按钮
      addOrganChildButton(scope){
        this.formValidate = {
          id:'',
          organName: '',
          organRemake: '',
          sorting: '',
          parentId:0
        };
        this.formValidate.parentId=scope.row.id;
        this.modal7=true;
      },
      //添加主菜单提交
      addOrganChildClick(){
        this.handleSubmit('formValidate');
      },
      //表单验证提交
      handleSubmit(name) {
        this.$refs[name].validate((valid) => {
          if (valid) {
            //表单提交
            //console.log(this.formValidate);
            let organ=this.formValidate;
            this.loadingModel=true;//启动提交按钮转圈
            this.addOrganChild({organ}).then(res => {

                this.loadingModel=false;//关闭提交按钮转圈
                this.modal7 = false;//关闭弹窗
                //情况表单数据
                this.formValidate = {
                  id:'',
                  organName: '',
                  organRemake: '',
                  sorting: '',
                  parentId:0
                };
                //刷新菜单页面
                this.queryList();

            })
          } else {
            this.$Message.error('验证失败!');
          }
        })
      },


      //删除机构
      deleteOrgan(scope){
          this.$Modal.confirm({
            title: '删除',
            content: '<p>你确认要删除此条信息吗!</p>',
            onOk: () => {
              let organId=scope.row.id;
              this.deleteOrganById({organId}).then(res => {

                  this.$Message.info(res.msg);
                  //刷新菜单页面
                  this.queryList();

              })
            },
            onCancel: () => {
              this.$Message.info('取消删除!');
            }
          });
      },

      //编辑
      editOrgan(scope){
        this.formValidate.id=scope.row.id;
        this.formValidate.organName=scope.row.organName;
        this.formValidate.organRemake=scope.row.organRemake;
        this.formValidate.sorting=scope.row.sorting;
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
            let organ=this.formValidate;
            this.loadingModel=true;//启动提交按钮转圈
            this.editOrganById({organ}).then(res => {

                this.loadingModel=false;//关闭提交按钮转圈
                this.modal8 = false;//关闭弹窗
                //情况表单数据
                this.formValidate = {
                  id:'',
                  organName: '',
                  organRemake: '',
                  sorting: '',
                  parentId:0
                };
                //刷新菜单页面
                this.queryList();

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
