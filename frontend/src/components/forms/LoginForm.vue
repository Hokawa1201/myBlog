<template>
    <el-form ref="form" :model="form" label-width="80px" class="login-form" :rules="rules">
        <el-form-item prop="username">
        <el-input v-model="form.username" style="width: 200px;" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password">
        <el-input v-model="form.password" style="width: 200px;" placeholder="密码" show-password></el-input>
        </el-form-item>
        <el-button style="width: 200px;" type="primary" @click="login('form')">登录</el-button>  
    </el-form>
</template>


<script>
import request from '@/utils/request';
import { store } from '../../utils/store.js';

export default {
    data() {
      return {
        form: {
          username: '',
          password: '',
        },
        rules: {
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur', type: 'string' },
            { max: 10, message: '用户名过长' }
          ],
          password: [
            { required: true, message: '密码不符合要求,应为6-12位', trigger: 'blur', type: 'string', min: 6, max: 12 }
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
    methods: {
      login() {
        let This=this;
        request.post("/login",
        {
          username:this.form.username,
          password:this.form.password
        },function(data){
          if(data){
            store.state.user_info = data;
            /*
            store.state.user_info.id = data.id,
            store.state.user_info.nickname = data.nickname,
            store.state.user_info.username = data.username,
            store.state.user_info.email = data.email,
            store.state.user_info.register_date = data.registerDate,*/
            This.$router.push('/myhome');
          }
          else{
            alert("用户名或密码错误");
          }
        })
      }
    }
  }
</script>  

<style>
.login-form .el-form-item__content{
  margin-left: 5px !important;
}
</style>