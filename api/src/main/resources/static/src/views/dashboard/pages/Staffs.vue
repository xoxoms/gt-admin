<template>
  <v-container
    id="staffs"
    fluid
    tag="section"
  >

    <v-dialog v-model="createModal" max-width="300">
      <v-card>
        <v-card-title class="font-weight-bold">직원 생성<v-spacer />
          <v-icon aria-label="Close" @click="closeCreateModal">mdi-close</v-icon>
        </v-card-title>
        <v-card-text class="body-1 text-left">
          <v-form>
            <v-row>
              <v-col class="col">
                <p class="font-weight-bold">브랜드명</p>
              </v-col>
              <v-col class="col">
                <input v-model="createBrandName" placeholder="브랜드명을 입력해주세요."/>
              </v-col>
            </v-row>
          </v-form>
        </v-card-text>
        <v-card-text class="pb-6 pt-12 text-right">
          <v-btn
              color="success"
              text
              @click="createBrand"
          >
            생성
          </v-btn>
        </v-card-text>
      </v-card>
    </v-dialog>

    <v-dialog v-model="editModal" max-width="300">
      <v-card>
        <v-card-title class="font-weight-bold">브랜드 상세<v-spacer />
          <v-icon aria-label="Close" @click="closeEditModal">mdi-close</v-icon>
        </v-card-title>
        <v-card-text class="body-1 text-left">
          <v-form>
            <v-row>
              <v-col class="col">
                <p class="font-weight-bold">브랜드명</p>
              </v-col>
              <v-col class="col">
                <input v-model="editBrandName"/>
              </v-col>
            </v-row>
          </v-form>
        </v-card-text>
        <v-card-text class="pb-6 pt-12 text-right">
          <v-btn
              color="success"
              text
              @click="editBrand"
          >
            수정
          </v-btn>
        </v-card-text>
      </v-card>
    </v-dialog>

    <base-material-snackbar
        v-model="snackbar"
        :type="snackbarColor"
        v-bind="{
        ['top']: true,
        ['right']: true
      }"
    >
      <span class="font-weight-bold">{{snackbarMessage}}</span>
    </base-material-snackbar>


    <div>
      <div class="px-6 py-3 ">
        <p class="ml-2 font-weight-bold">직원 생성</p>
        <v-btn color="info" target="_blank" @click="openCreateModal">
          <v-icon>mdi-storefront-plus-outline</v-icon>
        </v-btn>
      </div>
    </div>

    <base-material-card icon="mdi-store" title="직원 목록" class="px-5 py-3">
      <v-simple-table>
        <thead>
          <tr>
            <th class="primary--text font-weight-bold">
              사번
            </th>
            <th class="primary--text font-weight-bold">
              이름
            </th>
            <th class="primary--text font-weight-bold">
              포지션
            </th>
            <th class="primary--text font-weight-bold">
              수정/삭제
            </th>
          </tr>
        </thead>

        <tbody>
          <tr v-for="item in brands" :key="item.id">
            <td>{{item.staffId}}</td>
            <td>{{item.nickname}}</td>
            <td>{{item.position}}</td>
            <td>
              <v-btn
                  color="blue"
                  target="_blank"
                  @click="openEditModal(item)"
              >
                <v-icon>mdi-store-edit-outline</v-icon>
              </v-btn>
              <v-btn
                  color="red"
                  target="_blank"
                  v-bind:disabled="processing"
                  @click="deleteItem(item)"
                  class="ml-1"
              >
                <v-icon>mdi-store-remove-outline</v-icon>
              </v-btn>
            </td>
          </tr>
        </tbody>
      </v-simple-table>
    </base-material-card>
  </v-container>
</template>


<script>
import BaseMaterialTestimony from "../../../components/base/MaterialTestimony.vue";

const API_URL = 'http://localhost:3000/api/v1/staffs'

export default {
  name: 'StaffListView',
  components: {BaseMaterialTestimony},
  props: {
    id: {
      type: Number
    },
    name: {
      type: String
    },
    regTs: {
      type: String
    }
  },

  data() {
    return {
      processing: false,
      snackbar: false,
      snackbarColor: 'success',
      snackbarMessage: '',
      brands: [],
      cursorExclusive: 0,
      show: true,
      list: [1, 2, 3],

      createModal: false,
      createStaffName: '',
      createPrivatePhone: '',
      createPrivateAddress: '',
      createMainPhone: '',
      createNickname: '',
      createPositionId: 0,

      editModal: false,
      editBrandId: 0,
      editBrandName: '',
    }
  },

  created() {
    this.fetchData(0)
  },

  methods: {

    openCreateModal() {
      this.createBrandName = ''
      this.createStaffName = '',
      this.createPrivatePhone = '',
      this.createPrivateAddress = '',
      this.createMainPhone = '',
      this.createNickname = '',
      this.createPositionId = 0,
      this.createModal = true
    },

    async createBrand() {
      if (confirm(`직원을 생성하시겠습니까?`) === true) {

        const url = `${API_URL}`
        const requestOptions = {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
            staffName : this.createStaffName,
            privatePhone : this.createPrivatePhone,
            privateAddress : this.createPrivateAddress,
            mainPhone : this.createMainPhone,
            nickname : this.createNickname,
            positionId : this.createPositionId,
          })
        };
        await fetch(url, requestOptions)
            .then(response => response.json())
            .then(data => {
              if (data.success) {
                this.showMessage('info', '생성됐습니다.')
                this.createBrandName = ''
                var next = 0
                if (this.brands.length === 0) {
                  next = 0
                } else {
                  next = this.brands[0].id
                }

                this.fetchData(next)
                this.closeCreateModal()
              } else {
                this.showMessage('error', data.error.message)
              }
            });
      }
    },

    closeCreateModal() {
      this.createBrandName = ''
      this.createModal = false
    },

    openEditModal(item) {
      this.editModal = true
      this.editBrandId = item.id
      this.editBrandName = item.name
    },

    async editBrand() {
      const brand = this.brands.find(it => it.id === this.editBrandId)
      if (confirm(`브랜드를 수정하시겠습니까?`) === true) {

        const url = `${API_URL}/${this.editBrandId}`
        const requestOptions = {
          method: 'PUT',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ id: this.editBrandId, name: this.editBrandName })
        };
        await fetch(url, requestOptions)
            .then(response => response.json())
            .then(data => {
              if (data.success) {
                this.showMessage('info', '수정됐습니다.')
                brand.name = this.editBrandName
                this.editBrandId = 0
                this.editBrandName = ''
                this.closeEditModal()
              } else {
                this.showMessage('error', data.error.message)
              }
            });
      }
    },

    closeEditModal() {
      this.editModal = false
    },

    async deleteItem(item) {
      if (confirm(`${item.name} 브랜드를 삭제하시겠습니까?`) === true) {
        this.processing = true
        const url = `${API_URL}/${item.id}`

        const requestOptions = {
          method: 'DELETE',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({  })
        };
        fetch(url, requestOptions)
            .then(response => response.json())
            .then(data => {
              if (data.success) {
                const idx = this.brands.findIndex(function(it) { return it.id === item.id })
                if (idx > -1) {
                  this.brands.splice(idx, 1)
                }
                this.showMessage('info', '삭제됐습니다.')
                this.processing = false
              } else {
                this.$router.go(0)
              }
            });
      }
    },

    async fetchData(cursorExclusive) {
      const url = `${API_URL}?size=5000&cursorExclusive=${cursorExclusive}`
      await fetch(url)
          .then(response =>  response.json())
          .then(data => {
            if (this.cursorExclusive === 0) {
              this.brands = data.data
            } else {
              this.brands.unshift(...data.data)
            }
            this.cursorExclusive = data.data.nextCursorExclusive
          })
    },

    showMessage(color, message) {
      this.snackbarMessage = message
      this.snackbarColor = color
      this.snackbar = true
    },
  }
}
</script>
