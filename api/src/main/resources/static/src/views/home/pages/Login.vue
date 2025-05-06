<template>
  <v-container
    id="user-profile"
    fluid
    tag="section"
  >
    <v-row justify="center">
      <v-col
        cols="12"
        md="12"
      >
        <base-material-card>
          <template v-slot:heading>
            <div class="display-2 font-weight-bold">
              LOGIN
            </div>
          </template>

          <v-form>
            <v-container class="py-0">
              <v-row>
                <v-col
                  cols="12"
                  md="6"
                >
                  <v-text-field
                    class="purple-input"
                    label="ID"
                    v-model="id"
                  />
                </v-col>

                <v-col
                  cols="12"
                  md="6"
                >
                  <v-text-field
                    class="purple-input"
                    label="비밀번호"
                    v-model="password"
                  />
                </v-col>

                <v-col
                  cols="12"
                  class="text-right"
                >
                  <v-btn
                    color="success"
                    class="mr-0"
                    v-bind:disabled="processing"
                    @click="submit()"
                  >
                    로그인
                  </v-btn>
                </v-col>
              </v-row>
            </v-container>
          </v-form>
        </base-material-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
const API_URL = 'http://localhost:3000/api/v1/login'

export default {
  data() {
    return {
      id: "",
      password: "",
      processing: false,
    }
  },

  methods: {
    async submit() {


      this.processing = true
      const url = `${API_URL}`
      const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ loginId: this.id, password: this.password })
      };
      fetch(url, requestOptions)
        .then(response => response.json())
        .then(data => {
          if (data.success) {
            this.processing = false
            this.$router.addRoute(
              {
                path: '/',
                name: 'home',
                component: () => import('@/views/dashboard/Index'),
                children: [
                  // Dashboard
                  {
                    name: 'test',
                    path: '/test',
                    component: () => import('@/views/dashboard/Dashboard'),
                  },
                  // Brand
                  {
                    name: '브랜드 관리',
                    path: '/brands',
                    component: () => import('@/views/dashboard/pages/Brands'),
                  },
                  // Product
                  {
                    name: '상품 관리',
                    path: '/Products',
                    component: () => import('@/views/dashboard/pages/Products'),
                  },
                  {
                    name: '직원 관리',
                    path: '/Staffs',
                    component: () => import('@/views/dashboard/pages/Staffs'),
                  }
                ],
              }
            )
          } else {
            alert("로그인에 실패했습니다.")
            this.processing = false
          }
        });
    },
  }
}
</script>
