<template>
  <ul>
    <div v-for="comment in comments" :key="comment.id" class="comment">
      <el-button @click="gotoUser(comment.user_id)" class="user-button" type="text" style="font-size: 20px;color: rgb(0, 0, 0);font-weight: bold;">{{comment.nickname}}</el-button>
      <div class="comment-container">
        <p class="comment-content">{{comment.comment_content}}</p>
        <p class="comment-like">
          <el-button size="small" @click="likeComment(comment.id)" :color="comment.isCommentLiked?'#a0cfff':'white'">赞 {{comment.comment_like_count}}</el-button>
        </p>
      </div>
       <Clock class="icon"></Clock>{{comment.comment_time}}
    </div>
  </ul>
</template>
  
<script>
import request from "../../utils/request.js"
import {store} from "../../utils/store.js"
import {
    Clock,
  } from '@element-plus/icons-vue'

export default {
  components: {
    Clock,
  },
  data() {
    return {
      comments:[]
    };
  },
  props:{
    articleId: {
      type:Number,
      required:true
    }
  },
  mounted() {
    this.refreshState();
  },
  methods:{
    likeComment(commentId){
      let This = this;
      request.post('likeComment',{userId:store.state.user_info.id,commentId:commentId},function(){This.refreshState();});
    },
    gotoUser(user_id){
      if(user_id == store.state.user_info.id){
        this.$router.push('/personalinformation');
      }else{
        store.state.othersInformation.user_id = user_id;
        store.state.othersInformation.need_refresh = true;
        this.$router.push('/othersinformation');
      }
    },
    refreshState(){
      let This = this;
      request.post('getCommentsByArticleId',{userId:store.state.user_info.id,articleId:This.articleId},function(data){
        This.comments = data;
        This.comments.forEach(function(value){
          value.isCommentLiked = false;
          request.post('isCommentLiked',{userId:store.state.user_info.id,commentId:value.id},function(data){
            value.isCommentLiked = data;
          })
        })
      });
    }
  }
}  
</script>

<style>
.comment {
  border: 1px solid #edebeb;
  padding: 10px;
  margin-bottom: 10px;
  transition: border-color 0.3s;
}

.comment:hover {
  border-color: rgb(165, 204, 247);
}

.comment-content {
  margin-top: 5px;
  margin-bottom: 2px;
}

.user-button {
  text-decoration: none;
  transition: text-decoration 0.3s ease;
}

.user-button:hover {
  text-decoration: underline;
}

.comment-container {
  display: flex;
  flex-direction: column;
}

.comment-like {
  display: flex;
  justify-content: flex-end;
  align-items: flex-end;
  margin-top: 2px;
}

</style>