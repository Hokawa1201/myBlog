<template>
    <el-form ref="form" :model="form" label-width="80px" class="login-form" :rules="rules" status-icon>
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
        <el-input v-model="form.confirmPassword" placeholder="确认密码" type="password" show-password :status-icon="confirmPasswordIcon"></el-input>
      </el-form-item>
      <el-button style="width: 200px;" type="primary" :disabled="isRegisterDisabled" @click="register('form')">注册</el-button>
    </el-form>
  </template>
  
  <script>
  import request from '../utils/request.js'
  
  export default {
    data() {
      return {
        form: {
          nickname: '',
          username: '',
          password: '',
          confirmPassword: '',
          mailbox: ''
        },
        rules: {
          nickname: [
            { required: true, message: '昵称不符合要求，应为6-12位', trigger: 'blur', type: 'string', min: 6, max: 12 }
          ],
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur', type: 'string' },
            { pattern: /^[a-zA-Z0-9]{6,18}$/, message: '用户名必须为字母和数字的组合，长度在6到18个字符之间' }
          ],
          password: [
            { required: true, message: '密码不符合要求，应为6-12位', trigger: 'blur', type: 'string', min: 6, max: 12 },
            { pattern:  /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d]{6,18}$/, message: '密码必须为字母和数字的组合，长度在6到18个字符之间' }
          ],
          confirmPassword: [
            { required: true, message: '请再次输入密码', trigger: 'blur', type: 'string', min: 6, max: 12 },
            { validator: this.validateConfirmPassword, trigger: 'blur' }
          ],
          mailbox: [
            { required: true, message: '邮箱不符合要求，应为6-12位', trigger: 'blur', type: 'string', min: 6, max: 12 }
          ]
        },
        confirmPasswordIcon: '' // 确认密码框的 status-icon
      };
    },
    computed: {
      isRegisterDisabled() {
        // 检查表单验证是否通过
        return !this.$refs.form || !this.$refs.form.validate() || this.confirmPasswordIcon !== 'el-icon-check';
    },
    },
    methods: {
      register() {
        let This = this;
        request.post('/register', {
          username: this.form.username,
          password: this.form.password,
          nickname: this.form.nickname,
          email: this.form.mailbox
        }, function (data) {
          if (data) {
            alert('注册成功');
            console.log('hhhh');
            This.parentRef.changeState('login');
          } else {
            alert('用户名重复');
          }
        }, function () {
          alert('请求超时');
        });
      },
      validateConfirmPassword(rule, value, callback) {
        if (value === this.form.password) {
          // 确认密码和密码一样，通过验证
          this.confirmPasswordIcon = 'el-icon-check';
          callback();
        } else {
          // 确认密码和密码不一样，不通过验证
          this.confirmPasswordIcon = '';
          callback(new Error('确认密码和密码不一致'));
        }
      }
    }
  };
  </script>
  
  <style>
  .login-form .el-form-item__content {
    margin-left: 5px !important;
  }
  </style>
  