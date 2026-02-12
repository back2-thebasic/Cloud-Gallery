<template>
  <div id="globalHeader">
    <a-row :wrap="false">
      <a-col flex="120px">
        <RouterLink to="/">
          <div class="title-bar">
            <img class="logo" src="../assets/logo.png" alt="logo" />
            <div class="title">云图库</div>
          </div>
        </RouterLink>
      </a-col>
      <a-col flex="auto">
        <a-menu
          v-model:selectedKeys="current"
          mode="horizontal"
          :items="items"
          @click="doMenuClick"
        />
      </a-col>
      <!-- 用户信息展示栏 -->
      <a-col flex="100px">
        <div class="user-login-status">
          <div v-if="loginUserStore.loginUser.userName">
            <a-dropdown>
              <a-space>
                <!-- 用户头像 -->
                <a-avatar :src="loginUserStore.loginUser.userAvatar" />
                {{ loginUserStore.loginUser.userName ?? 'No Name' }}
              </a-space>

              <template #overlay>
                <a-menu>
                  <a-menu-item @click="doLogout">
                    <LogoutOutlined />
                    Logout
                  </a-menu-item>
                </a-menu>
              </template>
            </a-dropdown>
          </div>

          <div v-else>
            <a-button type="primary" href="/user/login">登录</a-button>
          </div>
        </div>
      </a-col>
    </a-row>
  </div>
</template>

<script lang="ts" setup>
import { computed, h, ref } from 'vue'
import {
  MailOutlined,
  AppstoreOutlined,
  SettingOutlined,
  LogoutOutlined,
  EditOutlined,
} from '@ant-design/icons-vue'
import { message, type MenuProps } from 'ant-design-vue'
import { RouterLink, useRouter } from 'vue-router'
import { useLoginUserStore } from '@/stores/useLoginUserStore'
import { userLogoutUsingPost } from '@/api/userController'

const loginUserStore = useLoginUserStore()

const router = useRouter()
// 当前选中菜单
const current = ref<string[]>([])
// 监听路由变化，更新当前选中菜单
router.afterEach((to, form, next) => {
  current.value = [to.path]
})

// 未经过滤的菜单项
const originItems = [
  {
    key: '/',
    icon: () => h(MailOutlined),
    label: '主页',
    title: '主页',
  },
  {
    key: '/about',
    icon: () => h(AppstoreOutlined),
    label: '关于',
    title: '关于',
  },
  {
    key: '/admin/userManage',
    icon: () => h(EditOutlined),
    label: '用户管理',
    title: '用户管理',
  },
  {
    key: '/others',
    icon: () => h(SettingOutlined),
    label: h('a', { href: 'https://www.codefather.cn', target: '_blank' }, '编程导航'),
    title: '编程导航',
  },
]

// 过滤菜单项
const filterMenus = (menus = [] as MenuProps['items']) => {
  return menus?.filter((menu) => {
    // 管理员才能看到 /admin 开头的菜单
    if (menu?.key.startsWith('/admin')) {
      const loginUser = loginUserStore.loginUser
      if (!loginUser || loginUser.userRole !== 'admin') {
        return false
      }
    }
    return true
  })
}

// 展示在菜单的路由数据
const items = computed(() => filterMenus(originItems))

// 路由跳转事件
const doMenuClick = ({ key }: { key: string }) => {
  router.push({
    path: key,
  })
}

// 用户注销
// 调用后端接口的话，需要使用'async'
const doLogout = async () => {
  const res = await userLogoutUsingPost()
  if (res.data.code === 0) {
    loginUserStore.setLoginUser({
      userName: '',
    })
    message.success('Logged out successfully')
    router.push('/user/login')
  } else {
    message.error('Failed to log out,' + res.data.message)
  }
}
</script>

<style>
#globalHeader .title-bar {
  display: flex;
  align-items: center;
}

#globalHeader .title {
  font-size: 12px;
  color: black;
  margin-left: 10px;
}

#globalHeader .logo {
  height: 20px;
}
</style>
