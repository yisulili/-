<script setup lang="ts" xmlns="http://www.w3.org/1999/html">
import {ref} from "vue";
import axios from "axios";
import router from "@/router";
import {setTokenCookie} from "@/components/TokenService";

const email = ref("")
const password = ref("")
const password2 = ref("")
const switch_stutus = ref(true)
const v_url = ref("/api/generateImageCode")
const v_code = ref("")

const getCode = ()=> {
  //点击刷新图形验证码
  v_url.value += '?time='+ Date.now();
}
const message_show = ref({
  stutus: false,
  message: ""
})
const start_switch = () => {
  switch_stutus.value = !switch_stutus.value
}
const aaa = () => {
  if (switch_stutus.value) {
    return "panel-active container"
  }else {
    return "container"
  }
  // eslint-disable-next-line no-unreachable
}
const show_message = () => {
  message_show.value.stutus = !message_show.value.stutus
}
const  Login = () => {
  message_show.value.stutus = false
  message_show.value.message = ""
  axios.post(
      'api/login',
      {
        user_email:email.value,
        user_password:password.value,
        code:v_code.value,
      },
      {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }
  ).then((ref_data)=>{
    if(ref_data.data.code == 200){
      message_show.value.message = ref_data.data.msg
      setTokenCookie(ref_data.headers.token,7)
      router.push("/admin")
    }else {
      if (ref_data.data.msg == null){
        message_show.value.message = "登陆失败"
      }
      message_show.value.message = ref_data.data.msg
    }
    message_show.value.stutus = true
  })

}

const  register = () => {
  message_show.value.stutus = false
  message_show.value.message = ""
  axios.post(
      'api/register',
      {
        user_email:email.value,
        user_password:password.value,
        confirmPwd:password2.value
      },
      {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }
  ).then((ref_data)=>{
    if(ref_data.data.code == 200){

      message_show.value.message = ref_data.data.msg
      start_switch()
    }else {
      if (ref_data.data.msg == null){
        message_show.value.message = "登陆失败"
      }
      message_show.value.message = ref_data.data.msg
    }
    message_show.value.stutus = true
  })

}

</script>

<template>
  <mdui-snackbar :open="message_show.stutus" ref="message" action="Undo" class="example-action">{{ message_show.message }}</mdui-snackbar>
  <div class="page"></div>
  <div class="main">
    <div :class="aaa()">
      <div class="container-form container-signup">
        <form action="#" method="POST" class="from" id="from1" style="">
          <h2 class="from-title">注册</h2>
          <mdui-text-field variant="outlined" type="email"
                           :value="email"
                           @input="email = $event.target.value" label="邮箱"></mdui-text-field>
          <mdui-text-field variant="outlined" type="password"
                           :value="password"
                           @input="password = $event.target.value"
                           label="密码"></mdui-text-field>
          <mdui-text-field variant="outlined" type="password"
                           :value="password2"
                           @input="password2 = $event.target.value"
                           label="请再次确认密码"></mdui-text-field>
          <mdui-button @click="register()">注册</mdui-button>
        </form>
      </div>
      <div class="container-form container-signin">
        <form action="#" method="POST" class="from" id="from2">
          <h2 class="from-title">登录</h2>
          <mdui-text-field variant="outlined"
                           :value="email"
                           @input="email = $event.target.value"
                           label="账号/邮箱"></mdui-text-field>
          <mdui-text-field variant="outlined"
                           :value="password"
                           type="password"
                           @input="password = $event.target.value"
                           label="密码"></mdui-text-field>
          <div class="v_box">
            <mdui-text-field variant="outlined" type="text"
                             class="v_input"
                             :value="v_code"
                             @input="v_code = $event.target.value" label="验证码"></mdui-text-field>
            <img class="v_img" :src="v_url" @click="getCode()" >
          </div>
          <mdui-button @click="Login()">登录</mdui-button>
        </form>
      </div>
      <div class="container-overlay">
        <div class="overlay">
          <div class="overlay-panel overlay-left">
            <mdui-button @click="start_switch">没有账号？点击注册</mdui-button>
          </div>
          <div class="overlay-panel overlay-right">
            <mdui-button @click="start_switch">点击登录</mdui-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
*{
  margin: 0;
  padding: 0;
}
.v_box{
  display: flex;
  align-items: center;
  height: 60px;
  width: 100%;
}
.v_input{
  flex: 1;
  width: 70%;
  margin-right: 1rem;
}
.v_img{
  cursor: pointer;
  width: 30%;
}
.page{
  position: absolute;
  height: 100%;
  width: 100%;

  background: #e7e7e7 url("../assets/bg.png") center no-repeat fixed;
  background-size: cover;
  filter: blur(9px);
  display: flex;
}
.main{
  position: absolute;
  height: 100%;
  width: 100%;
  display: flex;
  justify-items: center;
  align-items: center;
}
.container {
  background-color: #e7e7e7;
  border-radius: 0.7rem;
  box-shadow: 0 0.9rem 1.7rem 0 rgba(0, 0, 0, 0.25), 0 0.7rem 0.7rem 0 rgba(0, 0, 0, 0.22);
  height: 520px;
  max-width: 850px;
  overflow: hidden;
  position: relative;
  width: 100%;
  margin: auto;
}
.container-form {
  height: 100%;
  position: absolute;
  top: 0;
  transition: all 0.6s ease-in-out;
}
.container-signup {
  left: 0;
  width: 50%;
  z-index: 2;
}
.container-signin {
  left: 0;
  opacity: 0;
  width: 50%;
  z-index: 1;
}
.from {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 0 3rem;
  height: 100%;
  text-align: center;
  background-color: #e7e7e7;
}
.from *{
  margin: 1vw;
}
.from-title {
  font-weight: 300;
  margin: 0;
  margin-bottom: 1.25rem;
}
.container-overlay{
  height: 100%;
  left: 50%;
  overflow: hidden;
  position: absolute;
  top: 0;
  transition: transform .6s ease-in-out;
  width: 50%;
  z-index: 100;
}
.overlay-panel{
  height: 100%;
  width: 50%;
  position: absolute;
  top: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  transform: translateX(0);
  transition: transform 0.6s ease-in-out;
}
.overlay-left{
  transform: translateX(-20%);
}
.overlay-right{
  right: 0;
  transform: translateX(0);
}
.overlay {
  width: 200%;
  height: 100%;
  position: relative;
  left: -100%;
  background: url("../assets/bg.png") no-repeat center fixed;
  background-size: cover;
  transition: transform .6s ease-in-out;
  transform: translateX(0);
}
.panel-active .overlay-left{
  transform: translateX(0);
}
.panel-active .container-overlay{
  transform: translateX(-100%);
}
.panel-active .overlay{
  transform: translateX(50%);
}
.panel-active .container-signup{
  transform: translateX(100%);
}
.panel-active .container-signin{
  opacity: 1;
  z-index: 5;
  transform: translateX(100%);
}
</style>
