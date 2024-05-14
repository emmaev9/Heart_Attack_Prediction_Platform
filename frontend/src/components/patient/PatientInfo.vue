<template>
  <div class="patient-profile">
    <img :src="personIcon" class="profile-image" :alt="`${patient.firstName} ${patient.lastName}`" />
    <div class="patient-profile-details">
      <h2 class="patient-name">{{ patient.firstName }} {{ patient.lastName }}</h2>
      <div class="patient-details-all">
      <div class="patient-details">
        <p class="detail-item"><span class="detail-title">Age:</span> {{ calculateAge(patient.birthday) }}</p>
        <p class="detail-item"><span class="detail-title">Sex:</span> {{ patient.gender }}</p>
        <p class="detail-item"><span class="detail-title">CNP:</span> {{ patient.cnp }}</p>
      </div>
      <div class="patient-details">
        <p class="detail-item"><span class="detail-title">Email:</span> {{patient.email}}</p>
        <p class="detail-item"><span class="detail-title">Phone:</span> {{ patient.phone }}</p>
      </div>
      </div>
    </div>
  </div>
</template>

<script>
import personIcon from '@/assets/person-icon.png';
export default {
  name: 'PatientInfo',
  props: {
    patient: {
      type: Object,
      required: true
    },
    personIcon: {
      type: String,
      default: personIcon
    }
  },
  methods: {
    calculateAge(birthday) {
      const birthDate = new Date(birthday);
      const now = new Date();
      let years = now.getFullYear() - birthDate.getFullYear();
      let months = now.getMonth() - birthDate.getMonth();
      let days = now.getDate() - birthDate.getDate();
      if (months < 0 || (months === 0 && days < 0)) {
        years--;
        months += 12;
      }
      if (days < 0) {
        months--;
        days += new Date(now.getFullYear(), now.getMonth(), 0).getDate();
      }
      return `${years} years, ${months} months, ${days} days`;
    }
  }
}

</script>

<style scoped>
.patient-profile {
  display: flex;
  align-items: center;
  background-color: #f6f6f6;
  padding: 1rem;
  border-radius: 0.5rem; 
  margin-top: 1rem;
  margin-bottom: 1rem;
}

.profile-image {
  width: 100px; 
  height: 100px;
  border-radius: 50%; 
  object-fit: cover;
  border: 3px solid #e4e7eb;
  margin-right: 1.5rem; 
}

.patient-profile-details {
  flex-grow: 1;
  color: #6f6f6f;
}
.patient-details-all{
  display: flex;
  gap: 30px;
}

.patient-name {
  font-size: 24px;
  color: #848282; 
  font-weight: bold;
  margin-bottom: 1rem; 
}

.patient-details {
  display: flex;
  font-size: 15px;
  align-items: flex-start;
  flex-direction: column;
}

.detail-item {
  margin-bottom: 0.25rem; 
}

.detail-title {
  font-weight: 600; 
  color: #9d9c9c; 
  margin-right: 0.5rem;
}

.detail-info {
  display: inline-block; 
  color: #a6a8aa; 
}
</style>
