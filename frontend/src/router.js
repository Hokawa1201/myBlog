import { createRouter,createWebHashHistory } from "vue-router";
import MyHome from "./components/MyHome.vue"
import MyBlog from "./components/MyBlog.vue"
import MyLogin from "./components/MyLogin.vue"
import PersonalInformation from "./components/PersonalInformation.vue"
import ArticleShow from "./components/ArticleShow.vue"
import OthersInformation from "./components/OthersInformation.vue"
import TestPage from "./components/TestPage.vue"
import {store} from "./utils/store.js"

//创建router实例
const router = createRouter({
    history:createWebHashHistory(),
    routes:[
        {
            path:"/myhome",
            component:MyHome
        },
        {
            path:"/myblog",
            component:MyBlog
        },
        {
            path:"/",
            component:MyLogin
        },
        {
            path:"/personalinformation",
            component:PersonalInformation
        },
        {
            path:"/articleshow",
            component:ArticleShow
        },
        {
            path:"/othersinformation",
            component:OthersInformation
        },
        {
            path:"/test",
            component:TestPage
        }
    ]

});

// 全局前置守卫
router.beforeEach((to, from, next) => {
    console.log(from);
    console.log(to);
    if(to.fullPath == '/'){
        next();
    }
    else{
        if(store.state.user_info.id==''){
            next('/');
        }else{
            next();
        }
    }
  });

export default router;
