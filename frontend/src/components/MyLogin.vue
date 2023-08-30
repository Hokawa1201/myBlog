<template>
    <div>
        <el-container>
            <el-header>{{state=='login'?'Blog登录':state=='register'?'Blog注册':'忘记密码'}}</el-header>
            <el-main >
                <LoginForm :parentRef="this" v-if="state=='login'"></LoginForm>
                <RegisterForm :parentRef="this" v-if="state=='register'"></RegisterForm>
            </el-main>
            <el-footer>
              <el-button key="primary" type="primary" :text=true size="small" v-if="state!='login'" @click="changeState('login')">登录   </el-button>  
              <el-button key="primary" type="primary" :text=true size="small" v-if="state!='register'" @click="changeState('register')">注册   </el-button> 
              <el-button key="primary" type="primary" :text=true size="small" v-if="state!='forget'" @click="changeState('forget')">忘记密码</el-button>
            </el-footer>
        </el-container>
          
    </div> 
</template>


<script>
import LoginForm from "./forms/LoginForm.vue"
import RegisterForm from "./forms/RegisterForm.vue"

export default {
    data() {
      return {
        state:'login'
      }
    },
    components: {
      LoginForm,RegisterForm
    },
    methods: {
      changeState(state){
        this.state = state
      }
    }
  }
</script>  

<style scoped>
.login-form .el-form-item__content{
  margin-left: 5px !important;
}
.el-container{
    position: absolute;
    top: 50%;
    left:50%;
    transform: translate(-50%,-50%);
    background-color: white;
    width: 250px;
    box-shadow: 0 2px 4px rgba(0, 0, 0,0.3),
    0 3px 6px rgba(0, 0, 0,0.2);
    text-align: center; 
  }

.el-header{
    font-size: 20px;
    margin-top: 20px;
    margin-bottom: 20px;
    text-align: center; 
    height: 5px !important;
}

</style>