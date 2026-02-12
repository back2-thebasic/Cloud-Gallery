<template>
  <div id="userRegisterPage">
    <h2 class="title">Cloud Gallery - User Register</h2>
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

      <a-form-item
        name="checkPassword"
        :rules="[
          { required: true, message: 'Please enter checkPassword' },
          { min: 8, message: 'checkPassword must be at least 8 characters long' },
        ]"
      >
        <a-input-password
          v-model:value="formState.checkPassword"
          placeholder="Please enter checkPassword"
        />
      </a-form-item>

      <div class="tips">
        Already Have a UserAccount？
        <RouterLink to="/user/login">Login</RouterLink>
      </div>

      <a-form-item>
        <a-button type="primary" html-type="submit" style="width: 100%">Register</a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script lang="ts" setup>
import { userLoginUsingPost, userRegisterUsingPost } from '@/api/userController'
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
const formState = reactive<API.UserRegisterRequest>({
  userAccount: '',
  userPassword: '',
  checkPassword: '',
})

const loginUserStore = useLoginUserStore()

/**
 * 提交表单
 * @param values
 */
const handleSubmit = async (values: any) => {
  // 校验两次输入的密码是否一致
  if (values.userPassword !== values.checkPassword) {
    message.error("The two passwords you entered do not match");
    return;
  }
  const res = await userRegisterUsingPost(values)
  // 注册成功，跳转到登录页面
  if (res.data.code === 0 && res.data.data) {
    message.success('Registration successful')
    router.push({
      path: '/user/login',
      replace: true,
    })
  } else {
    message.error('Registration Failed' + res.data.message)
  }
}
</script>

<style scoped>
#userRegisterPage {
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
