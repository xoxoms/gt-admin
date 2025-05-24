<template>
  <v-container
    id="reservations"
    fluid
    tag="section"
  >
    <div style="width: 1000px; height: 100%; overflow-x: scroll">
      <v-row style="width: 1200px">
        <v-row style="padding: 100px">
          <v-col style="border: 1px solid;"><reservation-hour-header :title="''"></reservation-hour-header></v-col>
          <v-col style="border: 1px solid;"><reservation-hour-header :title="8"></reservation-hour-header></v-col>
          <v-col style="border: 1px solid;"><reservation-hour-header :title="9"></reservation-hour-header></v-col>
          <v-col style="border: 1px solid;"><reservation-hour-header :title="10"></reservation-hour-header></v-col>
          <v-col style="border: 1px solid;"><reservation-hour-header :title="11"></reservation-hour-header></v-col>
          <v-col style="border: 1px solid;"><reservation-hour-header :title="12"></reservation-hour-header></v-col>
          <v-col style="border: 1px solid;"><reservation-hour-header :title="13"></reservation-hour-header></v-col>
          <v-col style="border: 1px solid;"><reservation-hour-header :title="14"></reservation-hour-header></v-col>
          <v-col style="border: 1px solid;"><reservation-hour-header :title="15"></reservation-hour-header></v-col>
          <v-col style="border: 1px solid;"><reservation-hour-header :title="16"></reservation-hour-header></v-col>
          <v-col style="border: 1px solid;"><reservation-hour-header :title="17"></reservation-hour-header></v-col>
          <v-col style="border: 1px solid;"><reservation-hour-header :title="18"></reservation-hour-header></v-col>
          <v-col style="border: 1px solid;"><reservation-hour-header :title="19"></reservation-hour-header></v-col>
        </v-row>
        <v-row style="padding: 100px">
          <reservation-hour :time="0"/>
          <reservation-hour :time="0"/>
          <reservation-hour :time="0"/>
          <reservation-hour :time="0"/>
          <reservation-hour :time="0"/>
          <reservation-hour :time="0"/>
          <reservation-hour :time="0"/>
          <reservation-hour :time="0"/>
          <reservation-hour :time="0"/>
          <reservation-hour :time="0"/>
          <reservation-hour :time="0"/>
          <reservation-hour :time="0"/>
          <reservation-hour :time="0"/>
        </v-row>
      </v-row>
    </div>
  </v-container>
</template>


<script>
import ReservationHourHeader from "@/views/main/components/custom/ReservationHourHeader.vue";
import ReservationHour from "@/views/main/components/custom/ReservationHour.vue";

const API_URL = 'http://localhost:3000/api'

export default {
  name: 'StaffListView',
  components: {ReservationHourHeader, ReservationHour},
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
      reservations: [],

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
            skillMappingRequests:
              this.createSkillIds.map((skillId) => {
                return {
                  "skillId": skillId,
                  "price": document.getElementById(`createPrice_${skillId}`).value,
                  "quarterTaken": document.getElementById(`createQuarterTaken_${skillId}`).value
                }
              }),
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
      const url = `${API_URL}/v1/reservations?size=5000`
      await fetch(url)
          .then(response =>  response.json())
          .then(data => {
            this.reservations = data.data
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
    }
  }
}
</script>
