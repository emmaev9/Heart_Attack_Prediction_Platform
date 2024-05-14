<template>
  <div class="background-dashboard">
    <div class="dashboard-title">
       Patients Data Dashboard
    </div>
    <iframe
      class="dashboard-iframe-1" 
      width="100%" 
      height="370" 
      frameborder="0" 
      :src="grafanaUrl1">
    </iframe> 
    <iframe
      class="dashboard-iframe-1"
      width="100%"
      height="600"
      frameborder="0"
      :src="grafanaUrl2">
    </iframe>
  </div>
</template>

<script>
export default {
  name: 'PatientsDataDashboard',
  data() {
    return {
      grafanaUrl1: 'http://localhost:3000/d/ddlhr5vjx21a8e/new-dashboard?orgId=1&from=1715470252259&to=1715556652259&theme=light&kiosk',
      grafanaUrl2:'http://localhost:3000/d/ddlierwzogx6od/new-dashboard-2?orgId=1&from=1715518701551&to=1715540301551&theme=light&kiosk'
    }
  }
}
</script>
<style scoped>
.background-dashboard {
  margin-top: 20px;
  background-color: rgb(244, 244, 244);
}
.dashboard-iframe-1 {
  margin-left: 20px;
  margin-bottom: 20px;
}
.dashboard-title {
  font-size: 2.5rem;
  font-weight: bold;
  padding: 1rem;
  text-align: center;
  font-weight: bold;
  margin-top: 30px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  margin-bottom: 30px;
  color: #448ab0
}
</style>