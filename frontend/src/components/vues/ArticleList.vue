<template>
    <ul>
        <div v-for="article in articles" :key="article.articleId" class="article">
            <el-button  type="text" style="font-size: 25px;" @click="gotoArtilce(article.articleId)">{{ article.title }}</el-button>
            <p class="article-content">{{ truncateContent(article.content, 150) }}</p>
            <div class="article-details">
                <div class="icon-container">
                    <Pointer class="icon"></Pointer>
                    <span>{{ article.likeCount }} 点赞</span>
                    <Star class="icon" style="margin-left: 20px;"></Star>
                    <span>{{ article.collectCount }} 收藏</span>
                    <ChatLineRound class="icon" style="margin-left: 20px;"></ChatLineRound>
                    <span>{{ article.commentCount }} 评论</span>
                </div>
                <div class="icon-container">
                    <Clock class="icon"></Clock>
                    <span>发表时间：{{article.publishTime }}</span>
                </div>
            </div>
        </div>
    </ul>
</template>

<script>

import {store} from '../../utils/store.js'
import {
    Pointer,Star,ChatLineRound,Clock,
  } from '@element-plus/icons-vue'

export default {
    components: {
        Pointer,
        Star,
        ChatLineRound,
        Clock,
    },
  data() {
    return {
    };
  },
  props:{
      articles:{
        type:Object,
        required:true
      }
    },
  mounted() {
    
  },
  methods:{
    gotoArtilce(article_id){
        store.state.articleShow.article_id = article_id;
        this.$router.push('/articleshow');
    },
    truncateContent(content, maxLength) {
      if (content.length <= maxLength) {
        return content;
      } else {
        return content.slice(0, maxLength) + "...";
      }
    },
  }
}
</script>


<style>
.article {
  border: 1px solid #edebeb;
  padding: 10px;
  margin-bottom: 10px;
  transition: border-color 0.3s;
}

.article:hover {
  border-color: rgb(165, 204, 247);
}

.article-content {
  margin-top: 5px;
}

.article-details {
  margin-top: 10px;
  font-size: 14px;
}

.icon-container {
  display: flex;
  align-items: center;
}

.icon {
  width: 16px;
  height: 16px;
  margin-right: 5px;
}
</style>