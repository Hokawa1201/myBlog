<template>
    <ul>
      <div v-for="user in users" :key="user.userId" class="user">
        <User class="icon"></User>
        <el-button @click="gotoAuthor(user.id)" type="text" style="font-size: 20px;">{{user.nickname}}</el-button><br/>
       {{hideFollowCount?'':"粉丝数:" + user.followers_count}}
      </div>
    </ul>
  </template>
  
<script>
import {
    User,
  } from '@element-plus/icons-vue'
import {store} from '../../utils/store.js'
export default {
  components: {
      User,
    },
  data() {
    return {

    };
  },
  props:{
    users: {
      type:Object,
      required:true
    },
    hideFollowCount: {
      type:Boolean,
      required:false,
      default:false
    }
  },
  mounted() {
      
  },
  methods:{
    gotoAuthor(user_id){
      if(user_id == store.state.user_info.id){
        this.$router.push('/personalinformation');
      }else{
        store.state.othersInformation.user_id = user_id;
        store.state.othersInformation.need_refresh = true;
        this.$router.push('/othersinformation');
      }
    }
  }
}  
</script>

<style>
.user {
  border: 1px solid #edebeb;
  padding: 5px;
  margin-bottom: 2px;
  transition: border-color 0.3s;
}

.user:hover {
  border-color: rgb(165, 204, 247);
}

.icon {
  width: 16px;
  height: 16px;
  margin-right: 5px;
}
</style>