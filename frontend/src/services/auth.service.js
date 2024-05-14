import axios from 'axios';

class AuthService {
  login(user) {
    console.log(user)
    return axios
      .post('http://localhost:8080/auth/login', {
        username: user.username,
        password: user.password
      })
      .then(response => {
        console.log(response.data)
        console.log("vh hhhhhhhhhhhhhhhhhhhhhhhhhhh")
        console.log(response.data.token)
        if (response.data.token) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }
        return response.data;
      });
  }

  logout() {
    localStorage.removeItem('user');
  }
}
export default new AuthService();