<script setup>
import { store } from '../utils/store.js'
</script>

<template>
    <div>
      <el-container>
        <el-header id="Header1">
          <NarBar></NarBar>
        </el-header>
        <el-main>
          <div>
              <el-container style="margin-top: -20px; margin-left: 70px; margin-right: 70px;">
              <el-header id=header2>
                <div>
                  <el-container>
                    <el-header style="height: 90px;">
                      <el-button class="editinformation" @click="edit()" type="primary" plain v-if="!isEditMode">
                        <el-icon style="margin-right: 5px;"><Edit /></el-icon>编辑信息
                      </el-button>
                      <el-button class="editinformation1" @click="save()" type="primary" plain v-if="isEditMode">
                        <el-icon style="margin-right: 5px;"><Edit /></el-icon>保存
                      </el-button>
                      <el-button class="editinformation2" @click="cancel()" type="primary" plain v-if="isEditMode">
                        <el-icon style="margin-right: 5px;"><Edit /></el-icon>取消
                      </el-button>
                      <el-avatar style="margin-top: 30px;margin-left: 25px;" :size="70" :src="circleUrl"></el-avatar>
                    </el-header>
                    <el-main>
                      <p class="information">昵称：
                        <span v-if="!isEditMode">{{store.state.user_info.nickname}}</span>
                        <el-input v-model="store.state.user_info.nickname" v-if="isEditMode"/>
                      </p>
                      <p class="information">用户名：<span >{{store.state.user_info.username}}</span></p>
                      <p class="information">邮箱：
                        <span v-if="!isEditMode">{{store.state.user_info.email}}</span>
                        <el-input v-model="store.state.user_info.email" v-if="isEditMode"/>
                      </p>
                      <p class="information">注册时间：<span>{{store.state.user_info.registerDate}}</span></p>
                    </el-main>
                  </el-container>
                </div> 
              </el-header>
              <el-container>
                <el-main style="margin-top: 8px; background-color: aliceblue; border-radius: 4px">
                    <el-tabs v-model="activeName" @tab-change="handleChange">
                      <el-tab-pane label="文章" name="first">
                        <ArticleList :articles="articles" style="margin-left: -15px;"/>
                      </el-tab-pane>
                      <el-tab-pane label="关注" name="second">
                        <UserList :hideFollowCount="true" :users="users"/>
                      </el-tab-pane>
                      <el-tab-pane label="收藏" name="third">
                        <ArticleList :articles="articles" style="margin-left: -15px;"/>
                      </el-tab-pane>
                      <el-tab-pane label="赞过" name="fourth">
                        <ArticleList :articles="articles" style="margin-left: -15px;"/>
                      </el-tab-pane>
                    </el-tabs>
                  </el-main>
                </el-container>
              </el-container>
            </div>
        </el-main>
      </el-container>
    </div>
  </template>
  
<script>

import NarBar from "./vues/NavBar.vue"
import ArticleList from "./vues/ArticleList.vue"
import UserList from "./vues/UserList.vue"
import request from "../utils/request.js"
import {
    Edit,
  } from '@element-plus/icons-vue'

export default {
  data() {
    return {
      articles: [],
      users: [],
      circleUrl:
        'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      activeName: 'second',
      isEditMode:false,
      };
    },
  methods: {
    goBack() {
      console.log('go back')
    },
    handleChange(tabName) {
      console.log(tabName)
      this.changeArticleState(tabName);
    },
    changeArticleState(state){
      let This = this;
      if(state=='first'){
        request.post('/getArticlesByUserId',{userId:store.state.user_info.id},function(data){
          This.articles = data;
        });
      }else if(state == 'second'){
        request.post('/getFollowedUsersByUserId',{userId:store.state.user_info.id},function(data){
          This.users = data;
        });
      }else if(state == 'third'){
        request.post('/getCollectArticlesByUserId',{userId:store.state.user_info.id},function(data){
          This.articles = data;
        });
      }else if(state == 'fourth'){
        request.post('/getLikeArticlesByUserId',{userId:store.state.user_info.id},function(data){
          This.articles = data;
        });
      }
    },
    edit(){
      this.isEditMode = true;
    },
    save(){
      let This = this;
      this.isEditMode = false;
      request.post('/updateUserInformation',store.state.user_info,function(){
        This.refreshState();
      });
    },
    cancel(){
      this.isEditMode = false;
      this.refreshState();
    },
    refreshState(){
      request.post('/getUserById',{userId:store.state.user_info.id},function(data){
        store.state.user_info = data;
      })
    }
  },
  components: {
    NarBar,ArticleList,UserList,Edit,
  },
  mounted() {
    let This = this;
    This.activeName = 'first';
    This.changeArticleState(This.activeName);
    This.refreshState();
  }
}

</script>

<style scoped>
#Header1{
  background-color: rgb(161, 217, 243);
}
#container1{
  background-color: aliceblue;
}

.grid-content {
  min-height: 36px;
}

#header2 {
  border-radius: 10px; 
  height: fit-content;
  display: flex; 
  justify-content: center; 
  align-items: center;
}
.information {
  font-size: 17px;
  font-weight: bold;
  margin-top: 10px;
  margin-bottom: 5px;
}

.editinformation {
  position: absolute;
  top: 100px; 
  right: 130px; 
}
.editinformation1 {
  position: absolute;
  top: 110px; 
  right: 160px; 
}
.editinformation2 {
  position: absolute;
  top: 110px; 
  right: 70px; 
}

</style>