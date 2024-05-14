<template>
  <div>
    <br>
    <hr style="margin-left:20px; border-top: 1px solid gray;">
    <h6 style="color: gray;"> History of visits </h6>
    <hr style="margin-left:20px; border-top: 1px solid gray;">
    <div class="vl"  :style="{ height: verticalLineHeight }"></div>
    <div class="timeline">
      <div v-for="visit in this.visits" :key="visit.id" @click="selectVisit(visit)" class="timeline-item" :class="{ 'selected': selectedVisit && visit.id === selectedVisit.id }">
        <div class="timeline-dot"></div>
        <div class="timeline-date">{{ formatDate(visit.visitDate) }}</div>
      </div>
    </div>
  </div>

</template>

<script>
import VisitService from '@/services/visit.service';
export default {
  name: 'DocPatientVisits',
  data() {
    return {
      visits: [],
      selectedVisit: null
    };
  },
  props: {
    id: {
      type: String,
      required: true
    },

  },
  methods:{
    getVisits(){
      console.log("patient id "+ this.id)
      VisitService.getPatientVisits(this.id)
        .then(response => {
          console.log(response.data)
          this.visits = response.data;
          this.visits = this.visits.sort((a, b) => new Date(b.visitDate) - new Date(a.visitDate));
          if (this.visits.length > 0) {
            this.selectVisit(this.visits[0]);
          }
        })
        .catch(error => {
          console.log(error);
        });
    },
    selectVisit(visit) {
      if (!visit) {
          console.error('Selected visit is undefined');
          return;
      }
      this.selectedVisit = visit;
      console.log("Selected visit: " + visit.id);
     // this.$router.push({ name: 'VisitD', params: {visitId: , id: this.selectedVisit.patientId }} );
      this.$emit('visit-selected', visit);
      
    },
    formatDate(dateStr) {
      const date = new Date(dateStr);
      return date.toLocaleDateString('en-US', {
        day: '2-digit', 
        month: 'short', 
        year: 'numeric' 
    });
    },
  },
  mounted(){
    this.getVisits();
   // this.selectedVisit = this.visits[0];
  },
  computed:{
    verticalLineHeight(){
      return this.visits.length * 50 + 'px';
    }
  }
}
</script>

<style scoped>
.vl {
  border-left: 3px solid rgb(187, 188, 189);
  height: 10%;
  margin-left: 24px;
  position: absolute;
}
.timeline {
  position: relative;
  padding-left: 5px;
  color:rgb(86, 86, 86);
  font-size: 15.5px;
  font-family:Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
  margin-left: 20px;
}
.timeline-item {
  position: relative;
  padding: 5px 0;
  margin-top: 5px;
  cursor: pointer;
 
}

.timeline-item:hover {
  margin-left: 2px;
  background-color:  #d8e6f0
}

.timeline-item.selected {
  margin-left: 2px;
  background-color: #d8e6f0 ; 
}

.timeline-dot {
  position: absolute;
  left: -6px; 
  width: 12px;
  height: 12px;
  background-color: #fff;
  border: 3px solid #8ba9e0; /* Green border */
  border-radius: 50%;
  margin-top: 8px;
  margin-left: 1px;
  z-index: 1;
}
.timeline-date {
  margin-left: 20px; /* Space between dot and text */
}

.button-link {
  background: #75acca;
  border-radius: 15px;
  box-shadow: #99a0a6 0 10px 20px -10px;
  box-sizing: border-box;
  color: #FFFFFF;
  cursor: pointer;
  font-size: 16px;
  font-weight: 500;
  line-height: 20px;
  opacity: 1;
  outline: 0 solid transparent;
  padding: 8px 18px;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  width: fit-content;
  word-break: break-word;
  border: 0;
  text-decoration: none;
  min-width: 180px
}

.button-link:hover {
  background-color: #7cc3e2; 
}
</style>