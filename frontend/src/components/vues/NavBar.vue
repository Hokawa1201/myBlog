<template>
    <el-row>
        <el-col :span="4">
            <img src="../../assets/logo.png" alt="Sample Image"
                style="width:150px ;border-radius: 50%; margin-top: 5px ">
        </el-col>
        <el-col :span="4">
            <el-menu style=" background-color:rgb(161, 217, 243);"
                :default-active="activeIndex"
                class="el-menu-demo"
                mode="horizontal"
                :ellipsis="false"
                @select="handleSelect"
            >
                <el-menu-item @click="jumphome" index="0">主页</el-menu-item>
            </el-menu>
            </el-col>
            <el-col :span="3.5">
                <el-autocomplete style="margin-top: 13px ;"
                    popper-class="my-autocomplete"
                    v-model="state"
                    :fetch-suggestions="querySearch"
                    placeholder="请输入搜索内容"
                    @select="handleSelect"
                >
                    <template #suffix>
                        <i class="el-icon-edit el-input__icon" @click="handleIconClick"> </i>
                    </template>
                    <template #default="{ item }">
                        <div class="name">{{ item.value }}</div>
                        <span class="addr">{{ item.address }}</span>
                    </template>
                </el-autocomplete>
            </el-col>
            <el-col :span="3.5">
                <el-button style="margin-top: 13px;" type="primary" >
                    <el-icon style="margin-right: 5px"><Search/></el-icon>搜索
                </el-button>
            </el-col>
            <el-col :span="3">
                <el-button  style="margin-top: 13px ;" @click="jumppublish" round type="primary" >
                  <el-icon style="margin-right: 5px;"><CirclePlus /></el-icon>发布
                </el-button>
            </el-col>
            <el-col :span="4">
                <el-menu style="background-color:rgb(161, 217, 243);"
                    :default-active="activeIndex"
                    class="el-menu-demo"
                    mode="horizontal"
                    :ellipsis="false"
                    @select="handleSelect"
                >
                    <el-sub-menu index="1">
                        <template #title>
                          <el-icon style="margin-right: 5px;"><UserFilled /></el-icon>个人中心
                        </template>
                        <el-menu-item @click="jumppersoninformation" index="1-1">个人资料</el-menu-item>
                    </el-sub-menu>
                    <el-sub-menu index="2">
                      <template #title>
                        <el-icon style="margin-right: 5px;"><Setting /></el-icon>设置
                      </template>
                      <el-menu-item @click="jumplogin" index="2-1">退出登录</el-menu-item>
                    </el-sub-menu>
                </el-menu>
            </el-col>
        </el-row>
</template>

<script>

import { ref, onMounted } from 'vue'
import {useRouter} from 'vue-router'
import {store} from '../../utils/store.js'
import {
    CirclePlus,
    Search,
    UserFilled,
    Setting,
  } from '@element-plus/icons-vue'

  export default {
    components: {
      CirclePlus,
      Search,
      UserFilled,
      Setting,
    },
    data() {
      return {
        activeIndex: '1',
        activeIndex2: '1',
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
    },
    setup() {
        const router = useRouter()
        const jumphome =()=>{
          router.push('/myhome')
        };
        const jumppersoninformation =()=>{
          router.push('/personalinformation')
        };
        const jumplogin =()=>{
          router.push('/')
          store.state.user_info = {
                                    id:'',
                                    nickname:'',
                                    username:'',
                                    email:'',
                                    register_date:''
                                  };
        }
        const jumppublish =()=>{
          router.push('/myBlog')
        }

        const restaurants = ref([])
  
        const querySearch = (queryString, cb) => {
          var results = queryString
            ? restaurants.value.filter(createFilter(queryString))
            : restaurants.value
          cb(results)
        }
        const createFilter = (queryString) => {
          return (restaurant) => {
            return (
              restaurant.value
                .toLowerCase()
                .indexOf(queryString.toLowerCase()) === 0
            )
          }
        }
        const loadAll = () => {
          return [
            { value: '赓续华夏篇章，共书人类光芒', },
            { value: '出入自由之门', },
            { value: '亮相', },
          ]
        }
        const handleSelect1 = (item) => {
          console.log(item)
        }
        const handleIconClick = (ev) => {
          console.log(ev)
        }
        onMounted(() => {
          restaurants.value = loadAll()
        })
        return {
          restaurants,
          state: ref(''),
          querySearch,
          createFilter,
          loadAll,
          handleSelect1,
          handleIconClick,
          jumphome,
          jumppersoninformation,
          jumplogin,
          jumppublish,
        }
      },
  }

</script>

<style scoped>
.el-row{
    background-color: rgb(161, 217, 243);
}
.my-autocomplete li {
  line-height: normal;
  padding: 7px;
}
.my-autocomplete li .name {
  text-overflow: ellipsis;
  overflow: hidden;
}
.my-autocomplete li .addr {
  font-size: 12px;
  color: #b4b4b4;
}

.my-autocomplete li .highlighted .addr {
  color: #ddd;
}

.el-dropdown-link {
  cursor: pointer;
  color: #409eff;
  }
.el-icon-arrow-down {
  font-size: 12px;
}  

</style>