<script setup>
import {Home,Book,BookOpen,Search} from '@icon-park/vue-next';
import {ref} from "vue";

const show_stu = ref(true)
const select_stu = ref(1)
const start_st = () => {
  show_stu.value = !show_stu.value

}
const select_st = (index) => {
  select_stu.value = index
}
const get_show = ()=> {
  if (show_stu.value) {
    return "navgation active"
  }
  return "navgation"
}

const get_select = (index)=>{
  if (select_stu.value === index) {
    return "list active"
  }
  return "list"
}

</script>


<template>
  <div class="mani">
    <div class="bg"></div>
    <div :class="get_show()">
      <div class="menuToggle" @click="start_st()"></div>
      <ul>
        <li :class="get_select(1)" @click="select_st(1)">
          <a href="#" style="--clr:#f44336">
            <span class="icon"><Home theme="outline" size="24" fill="#333"/></span>
            <span class="text">首页</span>
          </a>
        </li>
        <li :class="get_select(2)" @click="select_st(2)">
          <a href="#" style="--clr:#f44336">
            <span class="icon"><Book theme="outline" size="24" fill="#333"/></span>
            <span class="text">借阅图书</span>
          </a>
        </li>
        <li :class="get_select(3)" @click="select_st(3)">
          <a href="#" style="--clr:#f44336">
            <span class="icon"><BookOpen theme="outline" size="24" fill="#333"/></span>
            <span class="text">图书推荐</span>
          </a>
        </li>
        <li :class="get_select(4)" @click="select_st(4)">
          <a href="#" style="--clr:#f44336">
            <span class="icon"><search theme="outline" size="24" fill="#333"/></span>
            <span class="text">查找图书</span>
          </a>
        </li>
      </ul>

    </div>
  </div>
</template>

<style scoped>
.mani * {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
.bg{
  min-height: 100vh;
  background: #2f323f;
}
.navgation {
  position: fixed;
  inset: 40px 40px 40px 20px;
  background: #fff;
  width: 75px;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-content: center;
  transition: 0.5s;

}
.menuToggle {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 60px;
  padding: 0 25px;
  border-bottom: 1px solid rgba(0, 0, 0, 0.25);
  display: flex;
  justify-content: flex-start;
  align-items: center;
  cursor: pointer;
}
.menuToggle::before {
  content: '';
  position: absolute;
  width: 30px;
  height: 2px;
  background: #333;
  transform: translateY(-8px);
  transition: .5s;
}
.menuToggle::after {
  content: '';
  position: absolute;
  width: 30px;
  height: 2px;
  background: #333;
  transform: translateY(8px);
  box-shadow: 0 -8px 0 #333;
  transition: .5s;
}
.navgation.active .menuToggle::before {
  transform: translateY(0) rotate(45deg);
}
.navgation.active .menuToggle::after {
  transform: translateY(0) rotate(-45deg);
  box-shadow: 0 0 0 #333;
}
.navgation.active {
  width: 250px;
}
.navgation ul {
  display: flex;
  flex-direction: column;
  width: 100%;
}
.navgation ul li {
  list-style: none;
  position: relative;
  width: 100%;
  height: 76px;
  border-radius: 12px;
  border: 8px solid transparent;
  transition: .5s;
}
.navgation ul li.active {
  transform: translateX(30px);
  background: #2f323f;
}
.navgation ul li::before {
  content: '';
  position: absolute;
  top: -28px;
  right: -10px;
  width: 20px;
  height: 20px;
  background: transparent;
  border-bottom-right-radius: 20px;
  box-shadow: 6px 5px 0 5px #2f323f;
  transform: scale(0);
  transform-origin: bottom right;
  transition: .5s;
}
.navgation ul li.active::before {
  right: 22px;
  transform: scale(1);
}
.navgation ul li::after{
  content: '';
  position: absolute;
  bottom: -28px;
  right: -10px;
  width: 20px;
  height: 20px;
  background: transparent;
  border-top-right-radius: 20px;
  box-shadow: 6px -3px 0 3px #2f323f;
  transform: scale(0);
  transform-origin: bottom right;
  transition: .5s;
}
.navgation ul li.active::after{
  right: 22px;
  transform: scale(1);
}
.navgation ul li a{
  position: relative;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  width: 100%;
  text-align: center;
  text-decoration: none;
  z-index: 1000;
}
.navgation ul li a .icon{
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  min-width: 60px;
  height: 60px;
  border-radius: 10px;
  font-size: 1.75em;
  line-height: 60px;
  border: 6px solid transparent;
  transition: .5s;
}
.navgation ul li a .icon i {
  font-size: 24px;
}
.navgation ul li.active a .icon{
  color: #fff;
  background: var(--clr);
}
.navgation ul li a .text {
  position: relative;
  padding: 0 15px;
  color: #333;
  display: flex;
  align-items: center;
  height: 60px;
  opacity: 0;
  visibility: hidden;
  transition: .5s;
}
.navgation.active ul li a .text {
  visibility: visible;
  opacity: 1;
}
.navgation ul li.active a .text {
  color: #fff;
}
.navgation ul li a .icon::before {
  content: '';
  position: absolute;
  top: 12px;
  left: 0;
  width: 100%;
  height: 100%;
  background: var(--clr);
  filter: blur(8px);
  opacity: 0;
  transition: .5s;
}
.navgation ul li.active a .icon::before {
  opacity: .5;
}
.navgation ul li a .icon::after {
  content: '';
  position: absolute;
  top: 10px;
  left: -60px;
  width: 15px;
  height: 15px;
  background: var(--clr);
  border: 8px solid #2f323f;
  border-radius: 50%;
  overflow: hidden;
}
</style>
