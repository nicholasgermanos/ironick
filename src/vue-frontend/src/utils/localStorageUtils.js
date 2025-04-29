import { isEmpty } from '@/utils/utils';

export function isAdmin() {
	return localStorage.getItem('role') === 'ADMINISTRATOR'
}

export function isLoggedIn() {
	return isEmpty(localStorage.getItem('user')) !== true
}

export function isLoggedOut() {
	return isLoggedIn() !== true
}