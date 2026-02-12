<template>
  <div id="userLoginPage">
    <h2 class="title">Cloud Gallery - User Login</h2>
    <div class="desc">Enterprise-Level Intelligent Collaborative Cloud Gallery</div>
    <a-form :model="formState" autocomplete="off" @finish="handleSubmit">
      <a-form-item
        name="userAccount"
        :rules="[{ required: true, message: 'Please enter useraccount' }]"
      >
        <a-input v-model:value="formState.userAccount" placeholder="Please enter useraccount" />
      </a-form-item>

      <a-form-item
        name="userPassword"
        :rules="[
          { required: true, message: 'Please enter password' },
          { min: 8, message: 'Password must be at least 8 characters long' },
        ]"
      >
        <a-input-password
          v-model:value="formState.userPassword"
          placeholder="Please enter password"
        />
      </a-form-item>

      <div class="tips">
        Do Not Have a UserAccount？
        <RouterLink to="/user/register">Register</RouterLink>
      </div>

      <a-form-item>
        <a-button type="primary" html-type="submit" style="width: 100%">Login</a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script lang="ts" setup>
import { userLoginUsingPost } from '@/api/userController'
import router from '@/router'
import { useLoginUserStore } from '@/stores/useLoginUserStore'
import { message } from 'ant-design-vue'
import { reactive } from 'vue'
import { p } from 'vue-router/dist/router-CWoNjPRp.mjs'

interface FormState {
  username: string
  password: string
  remember: boolean
}



// 用于接收表单输入的值
const formState = reactive<API.UserLoginRequest>({
  userAccount: '',
  userPassword: '',
})

const loginUserStore = useLoginUserStore()

/**
 * 提交表单
 * @param values
 */
const handleSubmit = async (values: any) => {
  const res = await userLoginUsingPost(values)
  // 登录成功，把登录态保存到全局状态中
  if (res.data.code === 0 && res.data.data) {
    await loginUserStore.fetchLoginUser()
    message.success('Login Success')
    router.push({
      path: '/',
      replace: true,
    })
  } else {
    message.error('Login Fail' + res.data.message)
  }
}
</script>

<style scoped>
#userLoginPage {
  max-width: 360px;
  margin: 0 auto;
}

.title {
  text-align: center;
  margin-bottom: 16px;
}

.desc {
  text-align: center;
  color: #bbb;
  margin-bottom: 16px;
}

.tips {
  text-align: right;
  color: #bbb;
  font-size: 13px;
  margin-bottom: 16px;
}
</style>
