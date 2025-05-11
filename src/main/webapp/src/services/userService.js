import axios from 'axios'
import router from '@/router';
import { isEmpty, isEmptyCascade } from '@/utils/utils';

axios.defaults.baseURL = '/api/';

const USER_API_BASE_URL = 'users'
const USER_API_LOGIN_URL = 'login'
const USER_API_REGISTER_URL = 'register'

class UserService {
	getUsers() {
		return axios.get(USER_API_BASE_URL);
	}
	login(user) {
		axios.post(USER_API_LOGIN_URL, user).then(response => {
			if (isEmptyCascade(response, ['data', 'principal', 'user', 'role']) === false ) {
				localStorage.setItem('user', JSON.stringify(response.data.principal.user) );
				localStorage.setItem('role', response.data.principal.user.role );
			}
		}).catch(error => {
			console.log(error)
			alert('Incorrect username or password')
		})
	}
	register(user) {
		axios.post(USER_API_REGISTER_URL, user).then(response => {
			if (isEmpty(response.data) === false ) {
				router.push( "/" ).then()
			}
		})
	}
	logout() {
		localStorage.clear()
	}
}

axios.defaults.withCredentials = true;
axios.defaults.withXSRFToken = true;

export default new UserService()