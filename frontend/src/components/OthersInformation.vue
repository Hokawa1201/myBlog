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
                      <el-button v-if="store.state.othersInformation.user_id!=store.state.user_info.id" class="editinformation" @click="follow()" round type="primary" plain>
                        <el-icon style="margin-right: 5px;"><CirclePlusFilled /></el-icon>{{isFollowed?"取消关注":"关注"}}
                      </el-button>
                      <el-avatar style="margin-top: 30px;margin-left: 25px;" :size="70" :src="circleUrl"></el-avatar>
                    </el-header>
                    <el-main>
                      <p class="information">昵称：<span>{{user_info.nickname}}</span></p>
                      <p class="information">用户名：<span >{{user_info.username}}</span></p>
                      <p class="information">邮箱：<span>{{user_info.email}}</span></p>
                      <p class="information">注册时间：<span>{{user_info.registerDate}}</span></p>
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
                      <el-tab-pane label="粉丝" name="fourth">
                        <UserList :hideFollowCount="true" :users="fans"/>
                      </el-tab-pane>
                    </el-tabs>
                  </el-main>
                </el-container>
              </el-container>
            </div>
          </el-main>
        </el-container>
        <div v-show="false" v-if="store.state.othersInformation.need_refresh">{{refreshState()}}</div>
      </div>
    </template>
  
<script>

import NarBar from "./vues/NavBar.vue"
import ArticleList from "./vues/ArticleList.vue"
import UserList from "./vues/UserList.vue"
import request from "../utils/request.js"
import {
    CirclePlusFilled,
  } from '@element-plus/icons-vue'

export default {
  data() {
    return {
      user_info:{},
      articles: [],
      users: [],
      fans:[],
      circleUrl:
        'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      activeName: 'second',
      isEditMode:false,
      isFollowed:false
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
        request.post('/getArticlesByUserId',{userId:store.state.othersInformation.user_id},function(data){
          This.articles = data;
        });
      }else if(state == 'second'){
        request.post('/getFollowedUsersByUserId',{userId:store.state.othersInformation.user_id},function(data){
          This.users = data;
        });
      }else if(state == 'third'){
        request.post('/getCollectArticlesByUserId',{userId:store.state.othersInformation.user_id},function(data){
          This.articles = data;
        });
      }else if(state == 'fourth'){
        request.post('/getFansUsersByUserId',{userId:store.state.othersInformation.user_id},function(data){
          This.fans = data;
        });
      }
    },
    refreshState(){
      let This = this;
      request.post('/getUserById',{userId:store.state.othersInformation.user_id},function(data){
        This.user_info = data;
      })
      This.changeArticleState(This.activeName);
      request.post('isFollowed',{userId:store.state.user_info.id,followedUserId:store.state.othersInformation.user_id},function(data){
        This.isFollowed = data;
      })
      store.state.othersInformation.need_refresh = false;
    },
    follow(){
      let This = this;
      request.post('followUser',{userId:store.state.user_info.id,followedUserId:store.state.othersInformation.user_id},function(){
        This.refreshState();
      })
    }
  },
  components: {
    NarBar,ArticleList,UserList,CirclePlusFilled,
  },
  mounted() {
    let This = this;
    This.activeName = 'first';
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

</style>