import axios from 'axios';

axios.defaults.baseURL = '/api/';

// No trailing slashes here
const REGISTER_EMAIL_URL = 'registerEmail';
const UNREGISTER_EMAIL_URL = 'unregisterEmail';

class MailingListService {

	registerEmail(email) {
		axios.post( REGISTER_EMAIL_URL, email ).then();
	}

	unregisterEmail(email) {
		axios.post( UNREGISTER_EMAIL_URL, email ).then();
	}
}

export default new MailingListService();