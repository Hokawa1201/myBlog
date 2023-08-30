import axios from "axios"

axios.defaults.baseURL = 'http://127.0.0.1:1111';
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8';

function post(path,body,sucessFunc,failedFunc=function(){}){
    axios.post(path, body)
      .then(function (response) {
        if(response.status == 200)
        {
            sucessFunc(response.data);
        }
        else
        {
            failedFunc();
        }
      })
      .catch(function (error) {
        console.log(error)
        failedFunc();
      });
}

function get(path,sucessFunc,failedFunc=function(){}){
    axios.get(path)
    .then(function (response) {
        if(response.status == 200)
        {
            sucessFunc(response.data);
        }
        else
        {
            failedFunc();
        }
    })
    .catch(function () {
        failedFunc();
    });
}

export default {post,get};