//import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import 'mdui/mdui.css';
import 'mdui';
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import {getTokenCookie} from "@/components/TokenService";


const app = createApp(App)
app.use(router)
app.use(ElementPlus)
app.mount('#app')
