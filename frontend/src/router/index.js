import { createRouter, createWebHashHistory } from "vue-router";
import Login from "../components/LoginPage.vue";
import PatientInfo from "../components/patient/PatientInfo.vue"
import PatientRegisterVisit from "../components/patient/PatientRegisterVisit.vue"
import PatientDetails from "../components/patient/PatientDetails.vue"
import PatientFamilyHistory from "../components/patient/PatientFamilyHistory.vue"
import VisitDetails from "../components/patient/VisitDetails.vue"
import DocPatientDetails from "../components/doctor/DocPatientDetails.vue"
import DocPatientECGs from "../components/doctor/DocPatientECGs.vue"
import DocPatientTests from "../components/doctor/DocPatientTests.vue"
import DocPatientPrediction from "../components/doctor/DocPatientPrediction.vue"
import DocPatientFamilyHistory from "../components/doctor/DocPatientFamilyHistory.vue"
import DocPatientVisits from "../components/doctor/DocPatientVisits.vue"
import PatientDataAnalysis from "../components/doctor/PatientsDataDashboard.vue"

//lazy-loaded
const AdminHome = () => import("../views/AdminHomeView.vue");
const MedicalAssistatntHome = () => import("../views/MedicalAssistantHomeView.vue");
const DoctorHome = () => import("../views/DoctorHomeView.vue");
const PatientHome = () => import("../views/PatientHomeView.vue");
const DocPatientHome = () => import("../views/DocPatientHomeView.vue");

const routes = [
  {
    path: "/login",
    component: Login,
  },
  {
    path: "/AdminHome",
    name: "AdminHome",
    component: AdminHome,
  },
  {
    path: "/MedicalAssistantHome",
    name: "MedicalAssistantHome",
    component: MedicalAssistatntHome,
  },

  {
    path: "/DoctorHome",
    name: "DoctorHome",
    component: DoctorHome,
    children:[
     { 
        path: "patient-info", 
        component: PatientInfo, 
        name: "PatientInfo", 
        props: true 
      },
    ]
  },
  {
    path: "/DocPatientHome/:id",
    name: "DocPatientHome",
    component: DocPatientHome,
    children:[
      { 
        path: "doc-patient-details", 
        component: DocPatientDetails, 
        name: "DocPatientDetails", 
        props: true
      },
      { 
        path: "ecgs/:visitId?", 
        component: DocPatientECGs, 
        name: "DocPatientECGs", 
        props: true
      },
      { 
        path: "medical-tests/:visitId?", 
        component: DocPatientTests, 
        name: "DocPatientTests", 
        props: true
      },
      {
        path: "prediction/:visitId?", 
        component: DocPatientPrediction, 
        name: "DocPatientPrediction", 
        props: true
      },
      { 
        path: "family-history", 
        component: DocPatientFamilyHistory, 
        name: "DocPatientFamilyHistory", 
        props: true
      },
      {
        path: "visits/:visitId?",
        component: DocPatientVisits,
        name: "DocPatientVisits",
        props: true
      }
    ]
  },
  {
     path:"/PatientsDataDashboard",
      name:"PatientsDataDashboard",
      component:PatientDataAnalysis 
  },
  {
    path: "/patient-home/:id",
    name: "PatientHome",
    component: PatientHome,
    children: [
      { 
        path: "patient-info", 
        component: PatientInfo, 
        name: "PatientInfo", 
        props: true 
      },
      { 
        path: "register-visit", 
        component: PatientRegisterVisit, 
        name: "PatientRegisterVisit", 
        props: true
      },
      { 
        path: "visit-details/:visitId?", 
        component: VisitDetails, 
        name: "VisitDetails", 
        props: true
      },
      { 
        path: "family-history", 
        component: PatientFamilyHistory, 
        name: "PatientFamilyHistory", 
        props: true
      },
      { 
        path: "patient-details", 
        component: PatientDetails, 
        name: "PatientDetails", 
        props: true
      },
     
    ]
  }
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;