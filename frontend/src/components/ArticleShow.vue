<script setup>
import {store} from "../utils/store.js"
</script>

<template>
  <div>
    <el-container>
      <el-header id="header1">
        <NarBar></NarBar>
      </el-header>
      <el-container>
        <el-aside width="300px" style="border-radius: 20px;">
          <div>
            <el-container>
              <el-header style="background-color:#d9ecff;">
                <p style="text-align: center;font-size: 25px;font-weight: bold;margin-top: 15px;">评论区<span style="font-size: 17px;font-weight: normal">(评论数:{{article.commentCount}})</span></p>
              </el-header>
              <el-main> 
                <CommentList style="margin-left: -30px;margin-top: 0px;" ref="commentList" :articleId="store.state.articleShow.article_id"></CommentList>
                <el-row>
                  发表评论
                  <el-input
                    v-model="commentToPublish"
                    autosize
                    type="textarea"
                    placeholder="Please input"
                  />
                  <el-button type="primary" style="margin-left: 200px;margin-top: 5px;" @click="comment()">发表</el-button>
                </el-row>
              </el-main>
            </el-container>
          </div>
        </el-aside>
        <el-container>
          <el-main>
            <div class="common-layout">
              <el-container id="container1">
                <el-header style="height: 150px;">
                  <div class="button-container">
                    <el-button type="primary" style="margin-top: -10px;" v-if="isArticleCanEdit() && !isEditMode" @click="edit()">编辑</el-button>
                    <el-button type="primary" style="margin-bottom: 5px;" v-if="isArticleCanEdit() && isEditMode"  @click="save()">保存</el-button>
                    <el-button type="primary" style="margin-bottom: 5px;" v-if="isArticleCanEdit() && isEditMode"  @click="cancel()">取消</el-button> 
                  </div>
                    <el-input
                      v-model="article.title"
                      maxlength="30"
                      placeholder="Please input"
                      show-word-limit
                      v-if="isEditMode"
                    />
                    <el-text v-if="!isEditMode">
                      <div style="text-align: center;">
                        <p class="title">{{article.title}}</p>
                        <el-button @click="gotoAuthor()" type="text">{{author_nickname}}</el-button>
                        <p class="publish-time"><Clock class="icon"></Clock>发布时间: {{article.publishTime}}</p>
                      </div>
                    </el-text>
                  </el-header>
                <el-main>
                  <el-input
                      v-model="article.content"
                      autosize
                      type="textarea"
                      placeholder="Please input"
                      v-if="isEditMode"
                    />
                  <el-text v-if="!isEditMode">
                  {{article.content}}
                  </el-text>
                </el-main>
              </el-container>
            </div>
          </el-main>
          <el-footer>
            <el-row>
              <el-button @click="like()" :color="beliked?'#a0cfff':'white'">
                <Pointer class="icon"></Pointer> {{article.likeCount}} 点赞
              </el-button>
              <el-button @click="collect()" :color="becollected?'#a0cfff':'white'">
                <Star class="icon"></Star> {{article.collectCount}} 收藏
              </el-button>
            </el-row>
          </el-footer>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>


<script>

import request from "../utils/request.js"
import NarBar from "./vues/NavBar.vue"
import CommentList from "./vues/CommentList.vue"
import {
    Pointer,Star,Clock,
  } from '@element-plus/icons-vue'

export default {
  data() {
    return {
      author_nickname:'',
      article:{},
      commentToPublish:'',
      beliked:false,
      becollected:false,
      isEditMode:false
    };
  },
  components: {
    NarBar,CommentList,Pointer,Star,Clock,
  },
  mounted(){
    this.refreshState();
  },
  methods:{
    like(){
      let This = this;
      request.post('likeArticle',{userId:store.state.user_info.id,articleId:store.state.articleShow.article_id},function(){This.refreshState();});
    },
    collect(){
      let This = this;
      request.post('collectArticle',{userId:store.state.user_info.id,articleId:store.state.articleShow.article_id},function(){This.refreshState();});
    },
    refreshState(){
      let This = this;
      request.post('getArticleById',{articleId:store.state.articleShow.article_id},function(data){
        This.article = data;
        request.post('getUserById',{userId:This.article.userId},function(data){
          This.author_nickname = data.nickname;
        })
      });
      request.post('isArticleLiked',{userId:store.state.user_info.id,articleId:store.state.articleShow.article_id},function(data){
        This.beliked = data;
      });
      request.post('isArticleCollected',{userId:store.state.user_info.id,articleId:store.state.articleShow.article_id},function(data){
        This.becollected = data;
      });
    },
    isArticleCanEdit(){
      return this.article.userId == store.state.user_info.id;
    },
    edit(){
      this.isEditMode = true;
    },
    save(){
      let This = this;
      this.isEditMode = false;
      request.post('/updateArticle',this.article,function(){
        This.refreshState();
      });
    },
    cancel(){
      this.isEditMode = false;
      this.refreshState();
    },
    comment(){
      let This = this;
      request.post('/publishComment',{userId:store.state.user_info.id,articleId:This.article.articleId,commentContent:This.commentToPublish},function(){
        This.$refs.commentList.refreshState();
        This.refreshState();
      });
    },
    gotoAuthor(){
      if(this.article.userId == store.state.user_info.id){
        this.$router.push('/personalinformation');
      }else{
        store.state.othersInformation.user_id = this.article.userId;
        this.$router.push('/othersinformation');
      }
    }
  }
}
</script>

<style>
#header1{
  background-color: rgb(161, 217, 243);
}

.button-container {
  display: flex;
  justify-content: flex-end;
  margin-top: 0px;
}

.container {
  text-align: center;
}

.title {
  font-size: 25px;
  font-weight: bold;
  margin-top: 10px;
  margin-bottom: 5px;
}

.publish-time {
  margin-top: 5px;
}

.icon {
  width: 16px;
  height: 16px;
  margin-right: 5px;
}

</style>