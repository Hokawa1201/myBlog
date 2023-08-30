<template>
    <el-form ref="form" :model="form" label-width="80px" class="login-form" :rules="rules">
      <el-form-item prop="nickname">
        <el-input v-model="form.nickname" style="width: 200px;" placeholder="昵称"></el-input>
      </el-form-item>
      <el-form-item prop="username">
        <el-input v-model="form.username" style="width: 200px;" placeholder="用户名"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input v-model="form.password" style="width: 200px;" placeholder="密码" show-password></el-input>
      </el-form-item>
      <el-form-item prop="confirmPassword">
          <el-input v-model="form.confirmPassword"  placeholder="确认密码" type="password" show-password></el-input>
      </el-form-item>
      <el-form-item prop="mailbox">
        <el-input v-model="form.mailbox" style="width: 200px;" placeholder="邮箱"></el-input>
      </el-form-item>
      <el-button style="width: 200px;" type="primary" @click="register('form')">注册</el-button>  
    </el-form>
</template>


<script>

import request from '../../utils/request.js'

export default {
    data() {
      return {
        form: {
          nickname: '',
          username: '',
          password: '',
        },
        rules: {
          nickname: [
            { required: true, message: '昵称不符合要求,应为6-12位', trigger: 'blur', type: 'string', min: 6, max: 12 }
          ],
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur', type: 'string' },
            { max: 10, message: '用户名过长' }
          ],
          password: [
            { required: true, message: '密码不符合要求,应为6-12位', trigger: 'blur', type: 'string', min: 6, max: 12 }
          ],
          confirmPassword: [
            { required: true, message: '请再次输入密码', trigger: 'blur', type: 'string', min: 6, max: 12 }
          ],
          mailbox: [
            { required: true, message: '邮箱不符合要求,应为6-12位', trigger: 'blur', type: 'string', min: 6, max: 12 }
          ]
        }
      }
    },
    props:{
      parentRef:{
        type:Object,
        required:true
      }
    },
    methods:{
      register() {
        if (this.form.password !== this.form.confirmPassword){
          this.$message({
            type: "error",
            message: "两次输入密码不一致"
          })
          return
        }

        let This = this;
        request.post("/register",
        {
          username:this.form.username,
          password:this.form.password,
          nickname:this.form.nickname,
          email:this.form.mailbox
        },function(data){
          if(data){
            alert('注册成功');
            console.log('hhhh')
            This.parentRef.changeState('login');
          }
          else{
            alert('用户名重复');
          }
        },function(){
          alert('请求超时')
        })
      }
    },
  }
</script>  

<style>
.login-form .el-form-item__content{
  margin-left: 5px !important;
}
</style>