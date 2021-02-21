<template>
  <div class="user-avator-dropdown">
    <Dropdown @on-click="handleClick">
<!--      <Badge :dot="!!messageUnreadCount">-->
      <Badge >
        <Tag>{{userAvator}}<Icon :size="18" type="md-arrow-dropdown"></Icon></Tag>

        <!--<Avatar :src="userAvator"/>-->
      </Badge>

      <DropdownMenu slot="list">
        <DropdownItem name="message">
          消息中心<Badge style="margin-left: 10px" :count="messageUnreadCount"></Badge>
        </DropdownItem>
        <DropdownItem name="setMyPassword">修改密码</DropdownItem>
        <DropdownItem name="logout">退出登录</DropdownItem>
      </DropdownMenu>
    </Dropdown>

    <!--添加角色弹出框-->
    <Modal
      v-model="modalSetPassword"
      title="修改密码"
      :mask-closable="false"
    >
      <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80">
        <FormItem label="原始密码" prop="oldPassword">
          <Input v-model.trim="formValidate.oldPassword" placeholder="原始密码"></Input>
        </FormItem>
        <FormItem label="新密码" prop="newPassword">
          <Input v-model.trim="formValidate.newPassword" placeholder="新密码"></Input>
        </FormItem>
        <FormItem label="密码确认" prop="newSurePassword">
          <Input v-model.trim="formValidate.newSurePassword" placeholder="密码确认"></Input>
        </FormItem>
      </Form>
      <div slot="footer">
        <Button type="text" size="large" @click="modalSetPassword=false">取消</Button>
        <Button type="primary" size="large" @click="setPasswordClick" :loading="loadingModel">确定</Button>
      </div>
    </Modal>

  </div>
</template>

<script>
import './user.less'
import { mapActions } from 'vuex'
export default {
  name: 'User',
  data() {
    return {
      modalSetPassword:false,
      //表单
      formValidate: {
        id: '',
        oldPassword: '',
        newPassword: '',
        newSurePassword:''
      },
      //表单验证
      ruleValidate: {
        oldPassword: [
          {required: true, message: '原始密码', trigger: 'blur'},
          {type: 'string', max: 20, message: '菜单名最长为20个字', trigger: 'blur'},
        ],
        newPassword: [
          {required: true, message: '新密码', trigger: 'blur'},
          {type: 'string', max: 20, message: '菜单名最长为20个字', trigger: 'blur'},
        ],
        newSurePassword: [
          {required: true, message: '密码确认', trigger: 'blur'},
          {type: 'string', max: 20, message: '菜单名最长为20个字', trigger: 'blur'},
        ],
      },
      loadingModel:false,
    }
  },
  props: {
    userAvator: {
      type: String,
      default: ''
    },
    userAdminId:{
      type: Number,
      default: 0
    },
    messageUnreadCount: {
      type: Number,
      default: 0
    }
  },
  methods: {
    ...mapActions([
      'handleLogOut',
      'updatePasswordAdminUser'
    ]),
    logout () {
      console.log("退出系统logout");
      this.handleLogOut().then(() => {
        console.log("退出系统handleLogOut");
        this.$router.push({
          name: 'login'
        })
      })
    },
    setMyPassword(){
      console.log(this.userAdminId);
      this.modalSetPassword=true;
    },

    setPasswordClick(){
      this.formValidate.id=this.userAdminId;
      if(this.formValidate.newPassword!=this.formValidate.newSurePassword){
        this.$Message.error('两次输入密码不一致!');
        return;
      }else{
        this.handleSubmitEdit('formValidate');
      }
    },



    //表单验证提交
    handleSubmitEdit(name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          //表单提交
          //console.log(this.formValidate);
          let adminUser = this.formValidate;
          this.loadingModel = true;//启动提交按钮转圈
          this.updatePasswordAdminUser({adminUser}).then(res => {

              this.loadingModel = false;//关闭提交按钮转圈
              this.modalSetPassword = false;//关闭弹窗
              //情况表单数据
              this.formValidate = {
                id: '',
                oldPassword: '',
                newPassword: '',
                newSurePassword:''
              };
              //刷新菜单页面
              this.queryList();

          })
        } else {
          this.$Message.error('验证错误!');
        }
      })
    },



    message () {
      this.$router.push({
        name: 'message_page'
      })
    },
    handleClick (name) {
      switch (name) {
        case 'logout': this.logout()
          break
        case 'message': this.message()
          break
        case 'setMyPassword': this.setMyPassword()

      }
    }
  }
}
</script>
