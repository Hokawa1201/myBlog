import { reactive } from 'vue'

export const store = reactive({
  state:{
    user_info:{
        id:'',
        nickname:'',
        username:'',
        email:'',
        register_date:''
    },
    articleShow:{
      article_id:1
    },
    othersInformation:{
      user_id:1,
      need_refresh:false
    }
  }
})
