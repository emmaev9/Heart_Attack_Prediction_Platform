// store.js
import Vue from 'vue';
import Vuex from 'vuex';
import { createStore } from 'vuex';
import AdminService from '@/services/admin.service';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    users: [],
    currentUser: null
  },
  mutations: {

    SET_USERS(state, users) {
      state.users = users;
    },

    ADD_USER(state, user) {
      state.users.push(user);
    },

    REMOVE_USER(state, id) {
      state.users = state.users.filter(user => user.id !== id);
    }
  },
  actions: {
    fetchUsers({ commit }) {
      console.log('Fetching users...');
      AdminService.getAllUsers().then(response => {
        console.log('Users fetched successfully:', response.data);
        commit('SET_USERS', response.data);
      }).catch(error => {
        console.error('Error fetching users:', error);
      });
    },
    registerUser({ commit, dispatch }, userData) {
      return AdminService.registerUser(userData).then(response => {
        commit('ADD_USER', response.data);
        dispatch('fetchUsers'); 
        return response; 
      });
    },

    deleteUser({ commit, dispatch }, userId) {
      return AdminService.deleteUser(id).then(response => {
        commit('REMOVE_USER', id);
        dispatch('fetchUsers'); 
        return response; 
      });
    },
  }
  
});
