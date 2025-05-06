<template>
  <v-container
    id="staffs"
    fluid
    tag="section"
  >

    <v-dialog v-model="createModal" max-width="600">
      <v-card>
        <v-card-title class="font-weight-bold">직원 등록<v-spacer />
          <v-icon aria-label="Close" @click="closeCreateModal">mdi-close</v-icon>
        </v-card-title>
        <v-card-text class="body-1 text-left">
          <v-form>
            <v-row>
              <v-col class="col">
                <p class="font-weight-bold">이름</p>
              </v-col>
              <v-col class="col">
                <input v-model="createStaffName" placeholder="이름을 입력해주세요."/>
              </v-col>
            </v-row>
            <v-row>
              <v-col class="col">
                <p class="font-weight-bold">개인 핸드폰 번호</p>
              </v-col>
              <v-col class="col">
                <input v-model="createPrivatePhone" placeholder="개인 핸드폰 번호를 입력해주세요."/>
              </v-col>
            </v-row>
            <v-row>
              <v-col class="col">
                <p class="font-weight-bold">개인 주소</p>
              </v-col>
              <v-col class="col">
                <input v-model="createPrivateAddress" placeholder="개인 주소를 입력해주세요."/>
              </v-col>
            </v-row>
            <v-row>
              <v-col class="col">
                <p class="font-weight-bold">핸드폰 번호</p>
              </v-col>
              <v-col class="col">
                <input v-model="createMainPhone" placeholder="핸드폰 번호를 입력해주세요."/>
              </v-col>
            </v-row>
            <v-row>
              <v-col class="col">
                <p class="font-weight-bold">닉네임</p>
              </v-col>
              <v-col class="col">
                <input v-model="createNickname" placeholder="닉네임을 입력해주세요."/>
              </v-col>
            </v-row>
            <v-row class="md-12">
              <v-col class="col col-md-12">
                <span class="font-weight-bold">직급</span>
                <v-select
                  v-model="createPositionId"
                  :items="positions"
                  item-text="name"
                  item-value="id"
                  density="compact"
                ></v-select>
              </v-col>
            </v-row>
            <v-row class="md-12">
              <v-col class="col col-md-12">
                <span class="font-weight-bold">시술</span>
                <span>
                  <v-col
                    class="font-weight-bold"
                    v-for="skill in skills"
                  >
                    <v-checkbox
                      :label="skill.name"
                      :value="skill.id"
                      v-model="createSkillIds"
                      @click="test(skill)"
                    />
                    <input :id="`createPrice_${skill.id}`" :value="`${skill.defaultPrice}원`" :placeholder="`${skill.name}의 가격을 입력해주세요.`"/>
                    <input :id="`createQuarterTaken_${skill.id}`" :value="`${skill.defaultQuarterTaken}분`" :placeholder="`${skill.name}의 가격을 입력해주세요.`"/>
                  </v-col>
                </span>
              </v-col>
            </v-row>
          </v-form>
        </v-card-text>
        <v-card-text class="pb-6 pt-12 text-right">
          <v-btn
              color="success"
              text
              @click="createStaff"
          >
            등록
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
        <p class="ml-2 font-weight-bold">직원 등록</p>
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
              닉네임
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
          <tr v-for="item in staffs" :key="item.id">
            <td>{{item.staffId}}</td>
            <td>{{item.name}}</td>
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

const API_URL = 'http://localhost:3000/api'

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
      positions: [],
      skills: [],
      staffs: [],
      show: true,

      createModal: false,
      createStaffName: '',
      createPrivatePhone: '',
      createPrivateAddress: '',
      createMainPhone: '',
      createNickname: '',
      createPositionId: 0,
      createSkillIds: [],

      editModal: false,
      editBrandId: 0,
      editBrandName: '',
    }
  },

  created() {
    this.fetchData(0)
    this.fetchPositions()
    this.fetchSkills()
  },

  methods: {

    openCreateModal() {
      this.createStaffName = ''
      this.createStaffName = '',
      this.createPrivatePhone = '',
      this.createPrivateAddress = '',
      this.createMainPhone = '',
      this.createNickname = '',
      this.createPositionId = 0,
      this.createModal = true
    },

    async createStaff() {
      if (confirm(`직원을 등록하시겠습니까?`) === true) {

        const url = `${API_URL}/v1/staffs`
        const requestOptions = {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
            name : this.createStaffName,
            privatePhone : this.createPrivatePhone,
            privateAddress : this.createPrivateAddress,
            nickname : this.createNickname,
            mainPhone : this.createMainPhone,
            positionId : this.createPositionId,
            skillMappingRequests: [],
          })
        };
        await fetch(url, requestOptions)
            .then(response => response.json())
            .then(data => {
              if (data.success) {
                this.showMessage('info', '등록됐습니다.')
                this.createStaffName = ''
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
      this.createStaffName = ''
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

        const url = `${API_URL}/v1/staffs${this.editBrandId}`
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
        const url = `${API_URL}/v1/staffs/${item.id}`

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
      const url = `${API_URL}/v1/staffs?size=5000&cursorExclusive=${cursorExclusive}`
      await fetch(url)
          .then(response =>  response.json())
          .then(data => {
            this.staffs = data.data
          })
    },

    async fetchPositions() {
      const url = `${API_URL}/v1/positions?size=5000`
      await fetch(url)
        .then(response =>  response.json())
        .then(data => {
          this.positions = data.data
        })
    },

    async fetchSkills() {
      const url = `${API_URL}/v1/skills`
      await fetch(url)
        .then(response =>  response.json())
        .then(data => {
          this.skills = data.data
        })
    },

    showMessage(color, message) {
      this.snackbarMessage = message
      this.snackbarColor = color
      this.snackbar = true
    },

    test(target) {
      console.log(target)
      console.log(this.createSkillIds)
      console.log(document.getElementById(`skillPrice_${target.id}`))
    }
  }
}
</script>
