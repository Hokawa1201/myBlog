<template>
  <div class="common-layout">
    <el-container>
      <el-header id="header1">
        <NarBar></NarBar>
      </el-header>
      <el-header>
        <el-page-header content="文章发布" style="text-align: center; font-size: 50px;"></el-page-header>
      </el-header>
      <el-main>
        <el-input
          type="text"
          placeholder="请输入标题"
          v-model="text"
          maxlength="30"
          show-word-limit
        >
        </el-input>
        <div style="margin: 20px 0;"></div>
        <el-input
          type="textarea"
          :autosize="{ minRows: 2, maxRows: 4}"
          placeholder="请输入文章内容"
          v-model="textarea"
        >
        </el-input>
      </el-main>
      <el-footer>
        <el-button type="primary" round style="float: right;" @click="publisharticle()">
          <el-icon style="margin-right: 5px"><Edit/></el-icon>发布
        </el-button>
      </el-footer>
    </el-container>
  </div>
</template>

<script>

  import NarBar from "./vues/NavBar.vue"
  import request from '@/utils/request'
  import { store } from '../utils/store.js'
  import {
    Edit,
  } from '@element-plus/icons-vue'

  export default {
    components: {
      Edit,
      NarBar,
    },
    data() {
      return {
        text: '',
        textarea: '',
      }
    },
    methods:{
      publisharticle() {
        let This = this;
        request.post("/publish",
        {
          user_id:store.state.user_info.id,
          title:this.text,
          content:this.textarea,
        },function(data){
        if(data){
          alert('发布成功');
          store.state.articleShow.article_id = data;
          This.$router.push('/articleshow');
          store.state.articleShow.article_id = data;
          }
          else{
            alert('发布失败');
          }
        },function(){
          alert('请求超时')
        })        
      }
    },

  }
</script>

<style scoped>

#header1{
    background-color: rgb(161, 217, 243);
}
.el-main{
  margin-left: 200px;
  margin-right: 200px;
}
.el-footer{
  margin-left: 200px;
  margin-right: 200px;
}
</style>
