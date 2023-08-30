<template>
<div>
    <RouterView></RouterView>
</div>
</template>
  
<script>
import {store} from './utils/store.js'
    export default{
        name :'App',
        mounted(){
            let data = sessionStorage.getItem('blog_myData');
            if(data != null)
            {
                try{
                    store.state = JSON.parse(data);
                }catch(error){
                    console.log(error);
                }
                //store.status = data.status;
            }

            window.addEventListener('beforeunload', () => {
                const stateJson = JSON.stringify(store.state);
                sessionStorage.setItem('blog_myData', stateJson);
            });
        }
    }

</script>

<style>
body{
    background-color:rgb(227, 246, 255);
    margin: 0px;
}
</style>