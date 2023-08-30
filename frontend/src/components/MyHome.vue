<template>
    <div class="common-layout">
      <el-container>
        <el-header id="header1">
            <NarBar></NarBar>
        </el-header>
        <el-container>
        <el-aside width="230px" style="border-radius: 20px;">
          <div class="common-layout">
            <el-container>
              <el-header style="background-color:#d9ecff;">
                <p style="text-align: center;font-size: 25px;font-weight: bold;margin-top: 15px;">推荐博主</p>
              </el-header>
              <el-main style="margin-top: -20px;">
                <UserList :users="users" style="margin-left: -20px;"/>
              </el-main>
            </el-container>
          </div>
        </el-aside>
          <el-main>
            <div class="common-layout">
              <el-container>
                <el-header><h1 style="text-align: center;font-size: 30px;margin-top: 0px;">Welcome to the blog ~</h1></el-header>
                <el-main>
                  <ArticleList :articles="articles" style="margin-top: -15px;margin-left: -0px;"/>
                </el-main>
              </el-container>
            </div>
          </el-main>
        </el-container>
      </el-container>
    </div>
  </template>


<script>

import NarBar from "./vues/NavBar.vue"
import ArticleList from "./vues/ArticleList.vue"
import UserList from "./vues/UserList.vue"
import request from "../utils/request.js"

export default {
  data() {
    return {
      articles: [{
        articleId:1,
        userId:0,
        title:"1",
        content:"1",
        publishTime:"2023-08-26",
        likeCount:0,
        collectCount:0,
        commentCount:0}],
      users: [{password:"1",
               followers_count:0,
               nickname:"1",
               register_date:"2023-08-26",
               id:0,
               email:"1",
               username:"1"}]
    };
  },
  components: {
    NarBar,ArticleList,UserList
  },
  mounted() {
    let This = this;
    request.get('/getRecommendArticle',function(data){
      This.articles = data;
    });
    request.get('/getRecommendUsers',function(data){
      This.users = data;
    });
  }
}

</script>

<style scoped>
#header1{
  background-color: rgb(161, 217, 243);
}
</style>